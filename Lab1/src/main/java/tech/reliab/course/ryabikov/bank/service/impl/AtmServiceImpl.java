package tech.reliab.course.ryabikov.bank.service.impl;

import tech.reliab.course.ryabikov.bank.entity.BankAtm;
import tech.reliab.course.ryabikov.bank.service.AtmService;

public class AtmServiceImpl implements AtmService {
    private BankAtm bankAtm;

    /**
     * Создание нового банкомата.
     */
    @Override
    public void create(BankAtm newUser) {
        this.bankAtm = newUser;
    }

    /**
     * Чтение данных банкомата.
     * @param id Идентификатор банкомата
     */
    @Override
    public BankAtm read(int id) {
        if (this.bankAtm != null && this.bankAtm.getId() == id) {
            return bankAtm;
        } else {
            return null;
        }
    }

    /**
     * Обновление данных банкомата.
     */
    @Override
    public void update(BankAtm newUser) {
        if (this.bankAtm != null && this.bankAtm.getId() == newUser.getId()) {
            this.bankAtm = newUser;
        }
    }

    /**
     * Удаление банкомата.
     * @param id Идентификатор банкомата
     */
    @Override
    public void delete(int id) {
        if (this.bankAtm != null && this.bankAtm.getId() == id) {
            this.bankAtm = null;
        }
    }
}