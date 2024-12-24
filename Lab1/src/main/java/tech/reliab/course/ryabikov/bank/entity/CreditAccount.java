package tech.reliab.course.ryabikov.bank.entity;

import java.time.LocalDate;

public class CreditAccount {
    private int id;
    private User user;
    private String bankName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int loanTermInMonths;
    private double loanAmount;
    private double monthlyPayment;
    private double interestRate;
    private Employee employee;
    private PaymentAccount paymentAccount;
    private Bank bank;

    /**
     * Конструктор класса CreditAccount
     * @param id ID аккаунта
     * @param user Пользователь, за которым закреплен этот кредитный счет
     * @param startDate Дата начала кредита
     * @param endDate Дата окончания кредита
     * @param loanTermInMonths Кол-во месяцев, на которые взят кредит
     * @param loanAmount Сумма кредита
     * @param monthlyPayment Ежемесячный платеж
     * @param interestRate Процентная ставка
     * @param employee Сотрудник, который выдал кредит
     * @param paymentAccount Платежный счет в банке с которого будет осуществляться погашение данного кредита
     */
    public CreditAccount(int id, User user, Bank bank, LocalDate startDate, LocalDate endDate,
                         int loanTermInMonths, double loanAmount, double monthlyPayment, double interestRate,
                         Employee employee, PaymentAccount paymentAccount) {
        this.id = id;
        this.user = user;
        this.bankName = bank.getName();
        this.startDate = startDate;
        this.endDate = endDate;
        this.loanTermInMonths = loanTermInMonths;
        this.loanAmount = loanAmount;
        this.monthlyPayment = monthlyPayment;
        this.interestRate = bank.getInterestRate();
        this.employee = employee;
        this.paymentAccount = paymentAccount;
        this.bank = bank;
    }

    /**
     * Получение Id.
     * @return Идентификатор
     */
    public int getId() { return this.id; }

    /**
     * Переопределение метода toString() для аккаунта.
     * @return Информация об обьекте
     */
    @Override
    public String toString() {
        return "Информация о кредитном счете: " + this.id + ":\n" +
                "ID: " + this.id + "\n" +
                "Пользователь, за которым закреплен этот кредитный счет: " + this.user.getFullName() + "\n" +
                "Название банка, где взят кредит: " + this.bank.getName() + "\n" +
                "Дата начала кредита: " + this.startDate.toString() + "\n" +
                "Дата окончания кредита: " + this.endDate.toString() + "\n" +
                "Кол-во месяцев, на которые взят кредит: " + this.loanTermInMonths + "\n" +
                "Сумма кредита: " + this.loanAmount + "\n" +
                "Ежемесячный платеж: " + this.monthlyPayment + "\n" +
                "Процентная ставка: " + this.bank.getInterestRate() + "\n" +
                "Платежный счет в банке, с которого будет осуществляться погашение данного кредита: " + this.paymentAccount.getId();
    }
}