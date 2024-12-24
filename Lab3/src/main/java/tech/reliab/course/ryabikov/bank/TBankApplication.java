package tech.reliab.course.ryabikov.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.reliab.course.ryabikov.bank.entity.*;
import tech.reliab.course.ryabikov.bank.service.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

@SpringBootApplication
public class TBankApplication implements CommandLineRunner {
    private static Integer totalBanks = 3;

    @Autowired
    private UserService userService;
    @Autowired
    private BankService bankService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AtmService atmService;
    @Autowired
    private PaymentAccountService paymentAccountService;
    @Autowired
    private CreditAccountService creditAccountService;
    @Autowired
    private BankOfficeService officeService;

    public static void main(String[] args) {
        SpringApplication.run(TBankApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var banks = IntStream.range(0, totalBanks)
                .mapToObj(idx -> {
                    String bankName = String.format("Космический Банк %d", idx + 1);
                    int id = new Random().nextInt(Integer.MAX_VALUE);
                    int rating = new Random().nextInt(101);
                    double totalMoney = new Random().nextInt(1_000_001);
                    double interestRate = 25 - (rating / 10.0);

                    return new Bank(id, bankName, rating, totalMoney, interestRate);
                })
                .toList();

        banks.forEach(this::initializeBankDetails);

        System.out.println("Выберите опцию:");
        System.out.println("1. Просмотреть банк");
        System.out.println("2. Просмотреть информацию о пользователе");

        var scanner = new Scanner(System.in);
        var choice = scanner.nextInt();

        switch (choice) {
            case 1 -> displayBankInfo();
            case 2 -> displayUserInfo();
            default -> System.out.println("Некорректный ввод");
        }
    }

    private void displayBankInfo() {
        var banks = bankService.getAllBanks();

        for (var bank : banks) {
            System.out.println(bank);
        }

        var scanner = new Scanner(System.in);

        System.out.println("Введите ID банка");
        var bankId = scanner.nextInt();

        var selectedBank = bankService.getBankById(bankId);

        System.out.println(selectedBank);

        System.out.println("Банкоматы:");
        System.out.println(atmService.getAllAtmsByBank(selectedBank));

        System.out.println("Офисы:");
        System.out.println(officeService.getAllBankOfficesByBank(selectedBank));

        System.out.println("Сотрудники:");
        System.out.println(employeeService.getAllEmployeesByBank(selectedBank));

        System.out.println("Клиенты:");
        System.out.println(userService.getUsersByBank(selectedBank));
    }

    private void displayUserInfo() {
        var users = userService.getAllUsers();
        for (var user : users) {
            System.out.println(user);
        }

        var scanner = new Scanner(System.in);

        System.out.println("Введите ID пользователя");
        var userId = scanner.nextInt();
        var user = userService.getUserById(userId);

        if (user.isEmpty()) return;

        var creditAccounts = creditAccountService.getCreditAccountByUserId(user.get().getId());
        var paymentAccounts = paymentAccountService.getAllPaymentAccountsByUserId(user.get().getId());

        System.out.println("Кредитные счета:");
        System.out.println(creditAccounts);

        System.out.println("Платежные счета:");
        System.out.println(paymentAccounts);
    }

    private void initializeBankDetails(Bank bank) {
        bankService.registerBank(bank);
        int numEmployees = 4;
        int numOffices = 3;
        int numAtms = 4;

        List<User> clients = Arrays.asList(
                userService.create("Евгений Абрамов", LocalDate.now(), "Менеджер проектов"),
                userService.create("Мария Лисичкина", LocalDate.now(), "HR-менеджер"),
                userService.create("Дмитрий Кузнецов", LocalDate.now(), "Бухгалтер")
        );

        var offices = IntStream.range(0, numOffices).boxed().map((idx) -> officeService.createBankOffice(
                String.format("Филиал № %d", idx + 1),
                "Улица Советов, 12",
                true,
                true,
                true,
                true,
                800,
                bank
        )).toList();

        var employees = IntStream.range(0, numEmployees).boxed().map((idx) -> employeeService.createEmployee(
                String.format("Илья Зарубин %d Викторович", idx + 1),
                LocalDate.now(),
                "Кредитный инспектор",
                bank,
                false,
                offices.get(idx % offices.size()),
                true,
                45000
        )).toList();

        var atms = IntStream.range(0, numAtms).boxed().map((idx) ->
                atmService.createBankAtm(
                        "Банкомат №" + (idx + 1),
                        "Улица Советов, 12",
                        bank,
                        String.valueOf(offices.get(idx % offices.size())),
                        employees.get(idx % employees.size()),
                        true,
                        true,
                        1200
                )).toList();

        for (var client : clients) {
            PaymentAccount payAccount = paymentAccountService.createPaymentAccount(client, bank);

            CreditAccount creditAcc = creditAccountService.createCreditAccount(
                    client,
                    bank,
                    LocalDate.now(),
                    12,
                    500000,
                    10,
                    employees.get(client.hashCode() % employees.size()),
                    payAccount
            );

            paymentAccountService.createPaymentAccount(client, bank);

            creditAccountService.createCreditAccount(
                    client,
                    bank,
                    LocalDate.now(),
                    15,
                    300000,
                    8,
                    employees.get(client.hashCode() % employees.size()),
                    payAccount
            );
        }
    }
}
