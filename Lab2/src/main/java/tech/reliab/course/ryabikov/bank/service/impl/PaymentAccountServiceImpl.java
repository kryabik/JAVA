package tech.reliab.course.ryabikov.bank.service.impl;

import tech.reliab.course.ryabikov.bank.entity.Bank;
import tech.reliab.course.ryabikov.bank.entity.PaymentAccount;
import tech.reliab.course.ryabikov.bank.entity.User;
import tech.reliab.course.ryabikov.bank.service.BankService;
import tech.reliab.course.ryabikov.bank.service.PaymentAccountService;
import tech.reliab.course.ryabikov.bank.service.UserService;
import java.util.*;
import java.util.stream.Collectors;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    private PaymentAccount paymentAccount;
    private static int paymentAccountCount = 0;
    private final UserService userService;
    private final BankService bankService;
    private final List<PaymentAccount> paymentAccounts = new ArrayList<>();

    public PaymentAccountServiceImpl(UserService userService, BankService bankService) {
        this.userService = userService;
        this.bankService = bankService;
    }

    /**
     * Создание нового платежного счета.
     */
    @Override
    public PaymentAccount createPaymentAccount(User user, Bank bank) {
        PaymentAccount paymentAccount = new PaymentAccount(user, bank);
        paymentAccount.setId(paymentAccountCount++);
        paymentAccounts.add(paymentAccount);
        userService.addPaymentAccount(paymentAccount, user);
        userService.addBank(bank, user);
        bankService.addClient(bank.getId());

        return paymentAccount;
    }

    /**
     * Чтение данных платежного счета.
     * @param id Идентификатор платежного счета
     */
    @Override
    public Optional<PaymentAccount> getPaymentAccountById(int id) {
        return paymentAccounts.stream()
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
        paymentAccounts.remove(paymentAccount);
        userService.deletePaymentAccount(paymentAccount, paymentAccount.getUser());
    }

    /**
     * Получение списка всех платежных аккаунтов
     * @return Список всех платежных аккаунтов
     */
    public List<PaymentAccount> getAllPaymentAccounts() {
        return new ArrayList<>(paymentAccounts);
    }

    /**
     * Получение всех платежных аккаунтов по идентификатору пользователя
     * @param userId Идентификатор пользователя
     * @return Список платежных аккаунтов пользователя
     */
    @Override
    public List<PaymentAccount> getAllPaymentAccountsByUserId(int userId) {
        return paymentAccounts.stream()
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