package tech.reliab.course.ryabikov.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.reliab.course.ryabikov.bank.entity.Bank;
import tech.reliab.course.ryabikov.bank.entity.PaymentAccount;
import tech.reliab.course.ryabikov.bank.entity.User;
import tech.reliab.course.ryabikov.bank.repository.PaymentAccountRepository;
import tech.reliab.course.ryabikov.bank.service.PaymentAccountService;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaymentAccountServiceImpl implements PaymentAccountService {
    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    /**
     * Создание нового платежного счета.
     */
    @Override
    public PaymentAccount createPaymentAccount(User user, Bank bank) {
        PaymentAccount paymentAccount = new PaymentAccount(user, bank);

        return paymentAccountRepository.save(paymentAccount);
    }

    /**
     * Чтение данных платежного счета.
     * @param id Идентификатор платежного счета
     */
    @Override
    public Optional<PaymentAccount> getPaymentAccountById(int id) {
        return paymentAccountRepository.findAll().stream()
                .filter(paymentAccount -> paymentAccount.getId() == id)
                .findFirst();
    }

    /**
     * Обновление данных платежного счета.
     */
    @Override
    public void updatePaymentAccount(int id, Bank bank) {
        PaymentAccount paymentAccount = getPaymentAccountIfExists(id);
        paymentAccount.setBank(bank);
    }

    /**
     * Удаление платежного счета.
     * @param id Идентификатор платежного счета
     */
    @Override
    public void deletePaymentAccount(int id) {
        PaymentAccount paymentAccount = getPaymentAccountIfExists(id);
        paymentAccountRepository.delete(paymentAccount);
    }

    /**
     * Получение списка всех платежных аккаунтов
     * @return Список всех платежных аккаунтов
     */
    public List<PaymentAccount> getAllPaymentAccounts() {
        return paymentAccountRepository.findAll();
    }

    /**
     * Получение всех платежных аккаунтов по идентификатору пользователя
     * @param userId Идентификатор пользователя
     * @return Список платежных аккаунтов пользователя
     */
    @Override
    public List<PaymentAccount> getAllPaymentAccountsByUserId(int userId) {
        return paymentAccountRepository.findAll().stream()
                .filter(account -> account.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    /**
     * Получение платежного аккаунта по идентификатору, если он существует
     * @param id Идентификатор платежного аккаунта
     * @return Платежный аккаунт, если найден, иначе выбрасывается исключение
     */
    private PaymentAccount getPaymentAccountIfExists(int id) {
        return getPaymentAccountById(id).orElseThrow(() -> new NoSuchElementException("PaymentAccount was not found"));
    }
}