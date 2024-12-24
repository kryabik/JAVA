package tech.reliab.course.ryabikov.bank.service;

import tech.reliab.course.ryabikov.bank.entity.Bank;

public interface BankService extends CrudOperations<Bank> {
    void addOffice();

    void addAtm();

    void removeAtm();
}