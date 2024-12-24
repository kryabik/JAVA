package tech.reliab.course.ryabikov.bank.entity;

import tech.reliab.course.ryabikov.bank.enums.BankStatus;

public class BankOffice {
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
    private Bank bank;

    /**
     * Конструктор класса BankOffice
     * @param id ID банковского офиса
     * @param name Название офиса
     * @param address Адрес банковского офиса
     * @param bank Банк, которому принадлежит офис
     */
    public BankOffice(int id, String name, String address, Bank bank, int rentCost) {
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

    /**
     * Получение Id.
     * @return Идентификатор
     */
    public int getId() { return this.id; }

    /**
     * Получение адреса офиса.
     * @return Адрес
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Получение имени офиса.
     * @return Имя
     */
    public String getName() {
        return this.name;
    }

    /**
     * Переопределение метода toString() для офиса.
     * @return Информация об обьекте
     */
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