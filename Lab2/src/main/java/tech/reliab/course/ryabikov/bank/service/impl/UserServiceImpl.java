package tech.reliab.course.ryabikov.bank.service.impl;

import tech.reliab.course.ryabikov.bank.entity.Bank;
import tech.reliab.course.ryabikov.bank.entity.CreditAccount;
import tech.reliab.course.ryabikov.bank.entity.PaymentAccount;
import tech.reliab.course.ryabikov.bank.entity.User;
import tech.reliab.course.ryabikov.bank.service.UserService;

import java.time.LocalDate;
import java.util.*;

public class UserServiceImpl implements UserService {
    private static int usersCount = 0;
    private final List<User> users = new ArrayList<>();

    /**
     * Создание нового пользователя.
     * @return Новый пользователь
     */
    @Override
    public User create(String fullName, LocalDate birthDate, String job) {
        User user = new User(0, fullName, birthDate, job);
        user.setCreditRating(calculateCreditRating(user));
        user.setId(usersCount++);
        user.setMonthlyIncome(new Random().nextInt(1001));
        users.add(user);

        return user;
    }

    /**
     * Чтение данных пользователя.
     * @param id Идентификатор пользователя
     */
    @Override
    public Optional<User> getUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    /**
     * Рассчитывает кредитный рейтинг пользователя
     * @return Кредитный рейтинг
     */
    public int calculateCreditRating(User user) {
        if (user.getMonthlyIncome() < 1000) {
            return 100;
        } else if (user.getMonthlyIncome() < 2000) {
            return 200;
        } else if (user.getMonthlyIncome() < 3000) {
            return 300;
        } else if (user.getMonthlyIncome() < 4000) {
            return 400;
        } else if (user.getMonthlyIncome() < 5000) {
            return 500;
        } else if (user.getMonthlyIncome() < 6000) {
            return 600;
        } else if (user.getMonthlyIncome() < 7000) {
            return 700;
        } else if (user.getMonthlyIncome() < 8000) {
            return 800;
        } else if (user.getMonthlyIncome() < 9000) {
            return 900;
        } else {
            return 1000;
        }
    }

    /**
     * Получение списка всех пользователей
     * @return Пользователи
     */
    @Override
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Получение пользователя
     * @param id АЙДИ пользователя
     * @return Пользователь, если найден, иначе выбрасывается исключение
     */
    @Override
    public User getUserIfExists(int id) {
        return getUserById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    /**
     * Добавление кредитного аккаунта пользователю
     * @param creditAccount Кредитный аккаунт для добавления
     * @param user Пользователь, которому добавляется аккаунт
     */
    @Override
    public void addCreditAccount(CreditAccount creditAccount, User user) {
        List<CreditAccount> creditAccounts = user.getCreditAccounts();
        creditAccounts.add(creditAccount);
        user.setCreditAccounts(creditAccounts);
    }

    /**
     * Добавление платежного аккаунта пользователю
     * @param paymentAccount Платежный аккаунт для добавления
     * @param user Пользователь, которому добавляется аккаунт
     */
    @Override
    public void addPaymentAccount(PaymentAccount paymentAccount, User user) {
        List<PaymentAccount> paymentAccounts = user.getPaymentAccounts();
        paymentAccounts.add(paymentAccount);
        user.setPaymentAccounts(paymentAccounts);
    }

    /**
     * Добавление банка пользователю
     * @param bank Банк для добавления
     * @param user Пользователь, которому добавляется банк
     */
    @Override
    public void addBank(Bank bank, User user) {
        List<Bank> banks = user.getBanks();
        banks.add(bank);
        user.setBanks(banks);
    }

    /**
     * Удаление кредитного аккаунта у пользователя
     * @param creditAccount Кредитный аккаунт для удаления
     * @param user Пользователь, у которого удаляется аккаунт
     */
    @Override
    public void deleteCreditAccount(CreditAccount creditAccount, User user) {
        List<CreditAccount> creditAccounts = user.getCreditAccounts();
        creditAccounts.remove(creditAccount);
        user.setCreditAccounts(creditAccounts);
    }

    /**
     * Удаление платежного аккаунта у пользователя
     * @param paymentAccount Платежный аккаунт для удаления
     * @param user Пользователь, у которого удаляется аккаунт
     */
    @Override
    public void deletePaymentAccount(PaymentAccount paymentAccount, User user) {
        List<PaymentAccount> paymentAccounts = user.getPaymentAccounts();
        paymentAccounts.remove(paymentAccount);
        user.setPaymentAccounts(paymentAccounts);
    }

    /**
     * Удаление банка у всех пользователей
     * @param bank Банк для удаления
     */
    @Override
    public void deleteBank(Bank bank) {
        for (User user : users) {
            List<Bank> banks = user.getBanks();
            banks.remove(bank);
            user.setBanks(banks);
        }
    }

    /**
     * Получение списка пользователей, связанных с банком
     * @param bank Банк для поиска пользователей
     * @return Список пользователей, связанных с банком
     */
    @Override
    public List<User> getUsersByBank(Bank bank) {
        return List.of();
    }
}