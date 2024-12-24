package tech.reliab.course.ryabikov.bank.service.impl;

import tech.reliab.course.ryabikov.bank.entity.Bank;
import tech.reliab.course.ryabikov.bank.service.BankService;

public class BankServiceImpl implements BankService {
    private Bank bank;

    /**
     * Создание нового банка.
     */
    @Override
    public void create(Bank newBank) {
        this.bank = newBank;
    }

    /**
     * Чтение данных банка.
     * @param id Идентификатор банка
     */
    @Override
    public Bank read(int id) {
        if (this.bank != null && this.bank.getId() == id) {
            return bank;
        } else {
            return null;
        }
    }

    /**
     * Обновление данных банка.
     */
    @Override
    public void update(Bank newBank) {
        if (this.bank != null && this.bank.getId() == newBank.getId()) {
            this.bank = newBank;
        }
    }

    /**
     * Удаление банка.
     * @param id Идентификатор банка
     */
    @Override
    public void delete(int id) {
        if (this.bank != null && this.bank.getId() == id) {
            this.bank = null;
        }
    }

    /**
     * Добавление офиса.
     */
    public void addOffice() {
        this.bank.officesCount++;
    }

    /**
     * Добавление банкомата.
     */
    public void addAtm() {
        this.bank.atmsCount++;
    }

    /**
     * Удаление банкомата.
     */
    public void removeAtm() {
        this.bank.atmsCount--;
    }
}