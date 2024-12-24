package tech.reliab.course.ryabikov.bank.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PaymentAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private User user;
    private String bankName;
    private double balance;
    @ManyToOne
    private Bank bank;

    /**
     * Конструктор класса PaymentAccount
     * @param user Пользователь, за которым закреплен этот платежный счет
     * @param bank Банк, в котором открыт этот счет
     */
    public PaymentAccount(User user, Bank bank) {
        this.user = user;
        this.balance = 0;
        this.bank = bank;
    }

    public PaymentAccount() { }

    /**
     * Переопределение метода toString() для аккаунта.
     * @return Информация об обьекте
     */
    @Override
    public String toString() {
        return "Id платёжного счета: " + this.id + "\n" +
                "Пользователь, за которым закреплен этот платежный счет: " + this.user.getFullName() + "\n" +
                "Название банка, в котором открыт этот счет: " + this.bank.getName() + "\n" +
                "Сумма, которая лежит в данный момент на счету: " + this.balance;
    }
}