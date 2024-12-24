package tech.reliab.course.ryabikov.bank;

import tech.reliab.course.ryabikov.bank.entity.*;
import tech.reliab.course.ryabikov.bank.service.*;
import tech.reliab.course.ryabikov.bank.service.impl.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    private static List<String> firstNames = Arrays.asList("Алексей", "Ирина", "Руслан");
    private static List<String> lastNames = Arrays.asList("Смирнов", "Орлова", "Федоров");
    private static Integer bankCount = 3;
    static UserService userService = new UserServiceImpl();
    static BankService bankService = new BankServiceImpl(userService);
    static EmployeeService employeeService = new EmployeeServiceImpl(bankService);
    static AtmService atmService = new AtmServiceImpl(bankService);
    static PaymentAccountService paymentService = new PaymentAccountServiceImpl(userService, bankService);
    static CreditAccountService creditService = new CreditAccountServiceImpl(userService, bankService);
    static BankOfficeService officeService = new BankOfficeServiceImpl(bankService);

    private static List<Bank> createBanks() {
        return IntStream.range(0, bankCount)
                .mapToObj(index -> {
                    String bankName = String.format("Космический Банк %d", index + 1);
                    int id = new Random().nextInt(Integer.MAX_VALUE);
                    int rating = new Random().nextInt(101);
                    double funds = new Random().nextInt(1_000_001);
                    double interestRate = 25 - (rating / 10.0);

                    return new Bank(id, bankName, rating, funds, interestRate);
                })
                .toList();
    }

    public static void main(String[] args) throws IOException {
        var banks = createBanks();
        banks.forEach(Main::initializeBankInfo);

        System.out.println("Выберите действие:");
        System.out.println("1. Просмотреть информацию о банке");
        System.out.println("2. Просмотреть информацию о клиенте");

        var scanner = new Scanner(System.in);
        var choice = scanner.nextInt();

        switch (choice) {
            case 1 -> showBankDetails();
            case 2 -> showCustomerDetails();
            default -> System.out.println("Некорректный ввод");
        }
    }

    private static void showBankDetails() {
        var banks = bankService.getAllBanks();

        for (var bank : banks) {
            System.out.println(bank);
        }

        var scanner = new Scanner(System.in);

        System.out.println("Введите ID банка:");
        var bankId = scanner.nextInt();

        var selectedBank = bankService.getBankById(bankId);
        if (selectedBank.isEmpty())
            return;

        System.out.println(selectedBank.get());

        System.out.println("Банкоматы:");
        System.out.println(atmService.getAllAtmsByBank(selectedBank.get()));

        System.out.println("Офисы:");
        System.out.println(officeService.getAllBankOfficesByBank(selectedBank.get()));

        System.out.println("Сотрудники:");
        System.out.println(employeeService.getAllEmployeesByBank(selectedBank.get()));

        System.out.println("Клиенты:");
        System.out.println(userService.getUsersByBank(selectedBank.get()));
    }

    private static void showCustomerDetails() {
        var users = userService.getAllUsers();
        for (var user : users) {
            System.out.println(user);
        }

        var scanner = new Scanner(System.in);

        System.out.println("Введите ID клиента:");
        var userId = scanner.nextInt();
        var user = userService.getUserById(userId);

        if (user.isEmpty()) return;

        var creditAccounts = creditService.getCreditAccountByUserId(user.get().getId());
        var paymentAccounts = paymentService.getAllPaymentAccountsByUserId(user.get().getId());

        System.out.println("Кредитные счета:");
        System.out.println(creditAccounts);

        System.out.println("Платежные счета:");
        System.out.println(paymentAccounts);
    }

    private static void initializeBankInfo(Bank bank) {
        bankService.registerBank(bank);
        int numEmployees = 4;
        int numOffices = 3;
        int numAtms = 4;

        List<User> customers = Arrays.asList(
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
                600,
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
                        offices.get(idx % offices.size()),
                        employees.get(idx % employees.size()),
                        true,
                        true,
                        800
                )).toList();

        for (var customer : customers) {
            PaymentAccount paymentAccount = paymentService.createPaymentAccount(customer, bank);

            CreditAccount creditAccount = creditService.createCreditAccount(
                    customer,
                    bank,
                    LocalDate.now(),
                    12,
                    500000,
                    11,
                    employees.get(customer.hashCode() % employees.size()),
                    paymentAccount
            );

            paymentService.createPaymentAccount(customer, bank);

            creditService.createCreditAccount(
                    customer,
                    bank,
                    LocalDate.now(),
                    15,
                    150000,
                    10,
                    employees.get(customer.hashCode() % employees.size()),
                    paymentAccount
            );
        }
    }
}
