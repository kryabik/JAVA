package tech.reliab.course.ryabikov.bank.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import tech.reliab.course.ryabikov.bank.enums.BankStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BankAtm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private int id;
    private String name;
    private String address;
    private BankStatus status;
    @ManyToOne
    private Bank bank;
    @Column(length = 500)
    private String location;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private BankOffice bankOffice;
    private boolean isWithdrawAvaiable;
    private boolean isDepositAvaiable;
    private double balance;
    private double maintenanceCost;

    /**
     * Конструктор класса BankAtm
     * @param name Имя банкомата
     * @param address Адрес банка
     * @param bank Банк, которому принадлежит банкомат
     * @param location Расположение банкомата
     * @param employee Обслуживающий сотрудник
     * @param canWithdraw Работает ли на выдачу денег
     * @param canDeposit Можно ли внести деньги
     * @param maintenanceCost Стоимость обслуживания банкомата
     */
    public BankAtm(String name, String address, Bank bank, String location, Employee employee, boolean canWithdraw, boolean canDeposit, double maintenanceCost) {
        this.name = name;
        this.address = address;
        this.status = BankStatus.WORKING;
        this.bank = bank;
        this.location = location;
        this.employee = employee;
        this.isWithdrawAvaiable = canWithdraw;
        this.isDepositAvaiable = canDeposit;
        this.balance = bank.getTotalMoney();
        this.maintenanceCost = maintenanceCost;
    }

    public BankAtm() { }

    @Override
    public String toString() {
        return "Информация о банкомате " + this.name + ":\n" +
                "ID: " + this.id + "\n" +
                "Адрес: " + this.address + "\n" +
                "Статус: " + this.status + "\n" +
                "Банк: " + this.bank.getName() + "\n" +
                "Расположение: " + this.location + "\n" +
                "Обслуживающий сотрудник: " + this.employee + "\n" +
                "Выдача денег: " + (this.isWithdrawAvaiable ? "да" : "нет") + "\n" +
                "Прием денег: " + (this.isDepositAvaiable ? "да" : "нет") + "\n" +
                "Денег в банкомате: " + this.balance + "\n" +
                "Стоимость обслуживания: " + this.maintenanceCost;
    }
}