package tech.reliab.course.ryabikov.bank.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    protected String fullName;
    protected LocalDate birthDate;

    /**
     * Конструктор класса Person
     * @param fullName ФИО человека
     * @param birthDate Дата рождения человека
     */
    public Person(String fullName, LocalDate birthDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public Person() { }
}