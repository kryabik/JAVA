package tech.reliab.course.ryabikov.bank.entity;

import java.time.LocalDate;

public abstract class Person {
    protected int id;
    protected String fullName;
    protected LocalDate birthDate;

    /**
     * Конструктор класса Person
     * @param id ID человека
     * @param fullName ФИО человека
     * @param birthDate Дата рождения человека
     */
    public Person(int id, String fullName, LocalDate birthDate) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    /**
     * Получение Id.
     * @return Идентификатор
     */
    public int getId() { return this.id; }

    /**
     * Получение имени.
     * @return ФИО
     */
    public String getFullName() { return this.fullName; }
}