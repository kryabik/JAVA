package tech.reliab.course.ryabikov.bank.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import tech.reliab.course.ryabikov.bank.enums.BankStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BankOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private int id;
    private String name;
    private String address;
    private BankStatus status;
    private boolean canPlaceAtm;
    private int atmCount;
    private boolean canLoan;
    private boolean canWithdraw;
    private boolean canDeposit;
    private double balance;
    private double rentCost;
    @ManyToOne
    private Bank bank;

    /**
     * Конструктор класса BankOffice
     * @param name Название офиса
     * @param address Адрес банковского офиса
     * @param bank Банк, которому принадлежит офис
     */
    public BankOffice(String name, String address, Bank bank, double rentCost) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.bank = bank;
        this.status = BankStatus.WORKING;
        this.canPlaceAtm = true;
        this.atmCount = 0;
        this.canLoan = true;
        this.canWithdraw = true;
        this.canDeposit = true;
        this.balance = bank.getTotalMoney();
        this.rentCost = rentCost;
    }

    public BankOffice() { }

    public int getBankId() { return this.bank.getId(); }

    @Override
    public String toString() {
        return "Id банковского офиса: " + id + "\n" +
                "Название офиса: " + name + "\n" +
                "Адрес банковского офиса: " + address + "\n" +
                "Статус: " + status.name() + "\n" +
                "Можно разместить банкомат: " + (this.canPlaceAtm ? "да" : "нет") + "\n" +
                "Кол-во банкоматов в офисе: " + atmCount + "\n" +
                "Можно оформить кредит в данном офисе: " + canLoan + "\n" +
                "Работает на выдачу денег: " + canWithdraw + "\n" +
                "Можно внести деньги: " + canDeposit + "\n" +
                "Кол-во денег в банковском офисе: " + balance + "\n" +
                "Стоимость аренды банковского офиса: " + rentCost;
    }
}