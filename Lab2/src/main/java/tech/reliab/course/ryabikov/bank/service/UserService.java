package tech.reliab.course.ryabikov.bank.service;

import tech.reliab.course.ryabikov.bank.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {
    int calculateCreditRating(User user);

    User create(String fullName, LocalDate birthDate, String job);

    Optional<User> getUserById(int id);

    List<User> getAllUsers();

    User getUserIfExists(int id);

    void addCreditAccount(CreditAccount creditAccount, User user);

    void addPaymentAccount(PaymentAccount paymentAccount, User user);

    void addBank(Bank bank, User user);

    void deleteCreditAccount(CreditAccount creditAccount, User user);

    void deletePaymentAccount(PaymentAccount paymentAccount, User user);

    void deleteBank(Bank bank);

    List<User> getUsersByBank(Bank bank);
}