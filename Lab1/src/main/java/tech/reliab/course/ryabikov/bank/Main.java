package tech.reliab.course.ryabikov.bank;

import tech.reliab.course.ryabikov.bank.entity.*;
import tech.reliab.course.ryabikov.bank.service.*;
import tech.reliab.course.ryabikov.bank.service.impl.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Создание объекта банка
        Bank myBank = new Bank(101, "Alpha Bank");

        // Создание главного офиса банка
        BankOffice mainBranch = new BankOffice(201, "Central Branch", "456 Elm St", myBank, 15000);

        // Создание сотрудника
        Employee branchManager = new Employee(301, "Jane Roe", LocalDate.of(1988, 3, 12),
                "Branch Manager", myBank, "branch_manager", true, mainBranch, true, 60000);

        // Создание клиента
        User client = new User(401, "Bob Brown", LocalDate.of(1992, 7, 20),
                "Tech Firm");

        // Создание платежного аккаунта
        PaymentAccount userAccount = new PaymentAccount(501, client, myBank.getName(), myBank);

        // Создание банкомата
        BankAtm cashMachine = new BankAtm(601, "ATM-002", "Avenue 78", myBank, "Main Branch",
                branchManager, true, true, 2000);

        // Создание кредитного аккаунта
        CreditAccount borrowingAccount = new CreditAccount(701, client, myBank, LocalDate.of(2015, 1, 10),
                LocalDate.of(2020, 1, 10), 15, 50000, 300,
                3.2, branchManager, userAccount);

        // Сервисы для управления объектами
        BankService bankManagement = new BankServiceImpl();
        BankOfficeService officeManagement = new BankOfficeServiceImpl();
        AtmServiceImpl atmManagement = new AtmServiceImpl();
        EmployeeService staffManagement = new EmployeeServiceImpl();
        UserService clientManagement = new UserServiceImpl();
        PaymentAccountService accountManagement = new PaymentAccountServiceImpl();
        CreditAccountService loanManagement = new CreditAccountServiceImpl();

        // Создание объектов в базе данных
        bankManagement.create(myBank);
        officeManagement.create(mainBranch);
        atmManagement.create(cashMachine);
        staffManagement.create(branchManager);
        clientManagement.create(client);
        accountManagement.create(userAccount);
        loanManagement.create(borrowingAccount);

        // Вывод информации о созданных объектах
        System.out.println(bankManagement.read(101).toString());
        System.out.println(officeManagement.read(201).toString());
        System.out.println(atmManagement.read(601).toString());
        System.out.println(staffManagement.read(301).toString());
        System.out.println(clientManagement.read(401).toString());
        System.out.println(accountManagement.read(501).toString());
        System.out.println(loanManagement.read(701).toString());
    }
}
