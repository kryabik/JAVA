package tech.reliab.course.ryabikov.bank.service.impl;

import tech.reliab.course.ryabikov.bank.entity.BankOffice;
import tech.reliab.course.ryabikov.bank.service.BankOfficeService;

public class BankOfficeServiceImpl implements BankOfficeService {
    private BankOffice office;

    /**
     * Создание нового офиса.
     */
    @Override
    public void create(BankOffice newOffice) {
        this.office = newOffice;
    }

    /**
     * Чтение данных офиса.
     * @param id Идентификатор офиса
     */
    @Override
    public BankOffice read(int id) {
        if (this.office != null && this.office.getId() == id) {
            return office;
        } else {
            return null;
        }
    }

    /**
     * Обновление данных офиса.
     */
    @Override
    public void update(BankOffice newOffice) {
        if (this.office != null && this.office.getId() == newOffice.getId()) {
            this.office = newOffice;
        }
    }

    /**
     * Удаление офиса.
     * @param id Идентификатор офиса
     */
    @Override
    public void delete(int id) {
        if (this.office != null && this.office.getId() == id) {
            this.office = null;
        }
    }
}