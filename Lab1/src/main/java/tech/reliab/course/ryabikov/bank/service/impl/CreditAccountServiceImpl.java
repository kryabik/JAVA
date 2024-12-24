package tech.reliab.course.ryabikov.bank.service.impl;

import tech.reliab.course.ryabikov.bank.entity.CreditAccount;
import tech.reliab.course.ryabikov.bank.service.CreditAccountService;

public class CreditAccountServiceImpl implements CreditAccountService {
    private CreditAccount creditAccount;

    /**
     * Создание нового кредитный счета.
     */
    @Override
    public void create(CreditAccount newCreditAccount) {
        this.creditAccount = newCreditAccount;
    }

    /**
     * Чтение данных кредитный счета.
     * @param id Идентификатор кредитный счета
     */
    @Override
    public CreditAccount read(int id) {
        if (this.creditAccount != null && this.creditAccount.getId() == id) {
            return creditAccount;
        } else {
            return null;
        }
    }

    /**
     * Обновление данных кредитный счета.
     */
    @Override
    public void update(CreditAccount newCreditAccount) {
        if (this.creditAccount != null && this.creditAccount.getId() == newCreditAccount.getId()) {
            this.creditAccount = newCreditAccount;
        }
    }

    /**
     * Удаление кредитный счета.
     * @param id Идентификатор кредитный счета
     */
    @Override
    public void delete(int id) {
        if (this.creditAccount != null && this.creditAccount.getId() == id) {
            this.creditAccount = null;
        }
    }
}