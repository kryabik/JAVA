package tech.reliab.course.ryabikov.bank.service;

import tech.reliab.course.ryabikov.bank.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CreditAccountService {
    CreditAccount createCreditAccount(User user, Bank bank, LocalDate startDate, int loanTermMonths,
                                      double loanAmount, double interestRate, Employee employee,
                                      PaymentAccount paymentAccount);

    Optional<CreditAccount> getCreditAccountById(int id);

    List<CreditAccount> getCreditAccountByUserId(int userId);

    void updateCreditAccount(int id, Bank bank);

    void deleteCreditAccount(int accountId, int userId);
}