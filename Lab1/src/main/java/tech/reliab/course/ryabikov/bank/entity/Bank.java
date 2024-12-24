package tech.reliab.course.ryabikov.bank.entity;

public class Bank {
    private int id;
    private String name;
    public int officesCount;
    public int atmsCount;
    private int employeesCount;
    private int clientsCount;
    private int rating;
    private double totalMoney;
    private double interestRate;

    /**
     * Конструктор обьекта Банк
     * @param id Уникальный идентификатор банка
     * @param name Название банка
     */
    public Bank(int id, String name) {
        this.id = id;
        this.name = name;
        this.officesCount = 0;
        this.atmsCount = 0;
        this.employeesCount = 0;
        this.clientsCount = 0;

        this.rating = (int) (Math.random() * 101);
        this.totalMoney = Math.random() * 1000000;
        this.interestRate = Math.max(0, 20 - (rating / 5.0));
    }

    /**
     * Получение Id.
     * @return Идентификатор
     */
    public int getId() { return this.id; }

    /**
     * Получение названия банка.
     * @return Название банка.
     */
    public String getName() { return name; }

    /**
     * Получение общего количества денег.
     * @return Общее количество денег.
     */
    public double getTotalMoney() { return totalMoney; }

    /**
     * Получение общего количества денег.
     * @return Общее количество денег.
     */
    public double getInterestRate() { return interestRate; }

    /**
     * Получение общего количества денег.
     * @return Общее количество денег.
     */
    public double getAtmCount() { return atmsCount; }

    /**
     * Переопределение метода toString() для банка.
     * @return Информация об обьекте
     */
    @Override
    public String toString() {
        return "Информация о банке " + this.name + ":\n" +
                "ID: " + this.id + "\n" +
                "Количество офисов: " + this.officesCount + "\n" +
                "Количество банкоматов: " + this.atmsCount + "\n" +
                "Количество сотрудников: " + this.employeesCount + "\n" +
                "Количество клиентов: " + this.clientsCount + "\n" +
                "Рейтинг: " + this.rating + "\n" +
                "Всего денег: " + this.totalMoney + "\n" +
                "Процентная ставка: " + this.interestRate;
    }
}