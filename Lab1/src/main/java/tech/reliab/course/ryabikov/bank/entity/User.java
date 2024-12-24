package tech.reliab.course.ryabikov.bank.entity;

import java.time.LocalDate;

public class User extends Person {
    protected String workplace;
    protected double monthlyIncome;
    protected int bankCount;
    protected int creditAccountCount;
    protected int paymentAccountCount;
    protected int creditRating;

    /**
     * Конструктор класса User
     * @param id ID клиента
     * @param fullName ФИО клиента
     * @param birthDate Дата рождения клиента
     * @param workplace Место работы клиента
     */
    public User(int id, String fullName, LocalDate birthDate, String workplace) {
        super(id, fullName, birthDate);
        this.workplace = workplace;
        this.monthlyIncome = Math.random() * 10000;
        this.bankCount = 0;
        this.creditAccountCount = 0;
        this.paymentAccountCount = 0;
    }

    /**
     * Получение Id.
     * @return Идентификатор
     */
    public int getId() { return this.id; }

    /**
     * Получение ежемесячного дохода.
     * @return ежемесячный доход
     */
    public double getMonthlyIncome() { return this.monthlyIncome; }

    /**
     * Получение ежемесячного дохода.
     * @return ФИО
     */
    public void setCreditRating(int creditRating) {
        this.creditRating = creditRating;
    }

    /**
     * Переопределение метода toString для User
     * @return Данные обьекта
     */
    @Override
    public String toString() {
        return "Id клиента: " + this.id + "\n" +
                "ФИО: " + this.fullName + "\n" +
                "Дата рождения: " + this.birthDate + "\n" +
                "Место работы: " + this.workplace + "\n" +
                "Ежемесячный доход: " + String.format("%.2f", this.monthlyIncome) + "\n" +
                "Кредитный рейтинг для банка: " + this.creditRating + "\n" +
                "Банки, которыми он пользуется: " + this.bankCount + "\n" +
                "Кредитные счета: " + this.creditAccountCount + "\n" +
                "Платежные счета: " + this.paymentAccountCount;
    }
}