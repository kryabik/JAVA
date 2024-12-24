package tech.reliab.course.ryabikov.bank.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee extends Person {
    private String job;
    private String position;
    @ManyToOne
    private Bank bank;
    private boolean worksInOffice;
    @ManyToOne
    private BankOffice bankOffice;
    private boolean canIssueLoans;
    private double salary;

    public Employee(String name, LocalDate birthDate, String job, Bank bank,
                     boolean worksInOffice, BankOffice bankOffice,
                    boolean canIssueLoans, double salary) {
        super(name, birthDate);
        this.job = job;
        this.bank = bank;
        this.worksInOffice = worksInOffice;
        this.bankOffice = bankOffice;
        this.canIssueLoans = canIssueLoans;
        this.salary = salary;
    }

    public Employee() { }

    /**
     * Переопределение метода toString() для сотрудника.
     * @return Информация об обьекте
     */
    @Override
    public String toString() {
        return "Id клиента: " + this.id + "\n" +
                "ФИО: " + this.fullName + "\n" +
                "Дата рождения: " + this.birthDate + "\n" +
                "Должность: " + this.position + "\n" +
                "В каком банке работает: " + this.bank.getName() + "\n" +
                "Работает в банковском офисе: " + (this.worksInOffice ? "да" : "нет") + "\n" +
                "Банковский офис, в котором работает: " + (bankOffice != null ? bankOffice.getName() : "Работает удаленно") + "\n" +
                "Может выдавать кредиты: " + (this.canIssueLoans ? "да" : "нет") + "\n" +
                "Размер зарплаты: " + this.salary;
    }
}