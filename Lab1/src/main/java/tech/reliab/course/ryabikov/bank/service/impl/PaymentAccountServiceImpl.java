package tech.reliab.course.ryabikov.bank.service.impl;

import tech.reliab.course.ryabikov.bank.entity.PaymentAccount;
import tech.reliab.course.ryabikov.bank.service.PaymentAccountService;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    private PaymentAccount paymentAccount;

    /**
     * Создание нового платежного счета.
     */
    @Override
    public void create(PaymentAccount newPaymentAccount) {
        this.paymentAccount = newPaymentAccount;
    }

    /**
     * Чтение данных платежного счета.
     * @param id Идентификатор платежного счета
     */
    @Override
    public PaymentAccount read(int id) {
        if (this.paymentAccount != null && this.paymentAccount.getId() == id) {
            return paymentAccount;
        } else {
            return null;
        }
    }

    /**
     * Обновление данных платежного счета.
     */
    @Override
    public void update(PaymentAccount paymentAccount) {
        if (this.paymentAccount != null && this.paymentAccount.getId() == paymentAccount.getId()) {
            this.paymentAccount = paymentAccount;
        }
    }

    /**
     * Удаление платежного счета.
     * @param id Идентификатор платежного счета
     */
    @Override
    public void delete(int id) {
        if (this.paymentAccount != null && this.paymentAccount.getId() == id) {
            this.paymentAccount = null;
        }
    }
}