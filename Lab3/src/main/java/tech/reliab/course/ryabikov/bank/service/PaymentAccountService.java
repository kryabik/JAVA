package tech.reliab.course.ryabikov.bank.service;

import tech.reliab.course.ryabikov.bank.entity.*;

import java.util.List;
import java.util.Optional;

public interface PaymentAccountService {
    PaymentAccount createPaymentAccount(User user, Bank bank);

    Optional<PaymentAccount> getPaymentAccountById(int id);

    List<PaymentAccount> getAllPaymentAccounts();

    List<PaymentAccount> getAllPaymentAccountsByUserId(int userId);

    void updatePaymentAccount(int id, Bank bank);

    void deletePaymentAccount(int id);
}