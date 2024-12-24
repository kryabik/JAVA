package tech.reliab.course.ryabikov.bank.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends Person {
    protected String workplace;
    protected double monthlyIncome;
    protected int bankCount;
    protected int creditRating;
    @ManyToOne
    private Bank bank;

    /**
     * Конструктор класса User
     * @param id ID клиента
     * @param fullName ФИО клиента
     * @param birthDate Дата рождения клиента
     * @param workplace Место работы клиента
     */
    public User(int id, String fullName, LocalDate birthDate, String workplace) {
        super(fullName, birthDate);
        this.workplace = workplace;
        this.monthlyIncome = Math.random() * 10000;
    }

    public User() {
        super();
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
                "Банки, которыми он пользуется: " + this.bankCount + "\n";
    }
}