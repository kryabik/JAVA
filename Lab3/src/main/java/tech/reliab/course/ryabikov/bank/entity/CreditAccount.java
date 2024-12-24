package tech.reliab.course.ryabikov.bank.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CreditAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private User user;
    private String bankName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int loanTermInMonths;
    private double loanAmount;
    private double monthlyPayment;
    private double interestRate;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private PaymentAccount paymentAccount;
    @ManyToOne
    private Bank bank;

    /**
     * Конструктор класса CreditAccount
     * @param user Пользователь, за которым закреплен этот кредитный счет
     * @param startDate Дата начала кредита
     * @param loanTermInMonths Кол-во месяцев, на которые взят кредит
     * @param interestRate Процентная ставка
     * @param employee Сотрудник, который выдал кредит
     * @param paymentAccount Платежный счет в банке с которого будет осуществляться погашение данного кредита
     */
    public CreditAccount(User user, Bank bank, LocalDate startDate,
                         int loanTermInMonths, double interestRate,
                         Employee employee, PaymentAccount paymentAccount) {
        this.user = user;
        this.bankName = bank.getName();
        this.startDate = startDate;
        this.loanTermInMonths = loanTermInMonths;
        this.interestRate = bank.getInterestRate();
        this.employee = employee;
        this.paymentAccount = paymentAccount;
        this.bank = bank;
    }

    public CreditAccount() { }

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