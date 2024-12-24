package tech.reliab.course.ryabikov.bank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
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
    public Bank(int id, String name, int rating, double totalMoney, double interestRate) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.totalMoney = totalMoney;
        this.interestRate = interestRate;

        this.rating = (int) (Math.random() * 101);
        this.totalMoney = Math.random() * 1000000;
        this.interestRate = Math.max(0, 20 - (rating / 5.0));
    }

    public Bank() { }

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