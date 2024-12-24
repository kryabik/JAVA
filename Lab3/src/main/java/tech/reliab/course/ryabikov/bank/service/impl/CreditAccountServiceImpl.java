package tech.reliab.course.ryabikov.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.reliab.course.ryabikov.bank.entity.Bank;
import tech.reliab.course.ryabikov.bank.entity.*;
import tech.reliab.course.ryabikov.bank.repository.CreditAccountRepository;
import tech.reliab.course.ryabikov.bank.service.CreditAccountService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditAccountServiceImpl implements CreditAccountService {
    @Autowired
    private CreditAccountRepository creditAccountRepository;

    /**
     * Создание нового кредитный счета.
     */
    @Override
    public CreditAccount createCreditAccount(User user, Bank bank, LocalDate startDate, int loanTermMonths,
                                              double loanAmount, double interestRate, Employee employee,
                                              PaymentAccount paymentAccount) {
        CreditAccount creditAccount = new CreditAccount(user, bank, startDate, loanTermMonths,
                interestRate, employee, paymentAccount);
        creditAccount.setEndDate(calculateEndDate(startDate, loanTermMonths));
        creditAccount.setLoanAmount(calculateLoanAmount(loanAmount, bank));
        creditAccount.setMonthlyPayment(calculateMonthlyPayment(interestRate, loanAmount, loanTermMonths));
        creditAccount.setInterestRate(calculateInterestRate(interestRate, bank));

        return creditAccountRepository.save(creditAccount);
    }

    /**
     * Чтение данных кредитный счета.
     * @param id Идентификатор кредитный счета
     */
    @Override
    public CreditAccount getCreditAccountById(long id) {
        return creditAccountRepository.findById(id).orElse(null);
    }

    /**
     * Обновление данных кредитный счета.
     */
    @Override
    public void updateCreditAccount(int id, Bank bank) {
        CreditAccount creditAccount = getCreditAccountIfExists(id);
        creditAccount.setBank(bank);
    }

    /**
     * Вычисление даты окончания кредита
     * @param startDate Дата начала кредита
     * @param loanTermMonths Срок кредита в месяцах
     * @return Дата окончания кредита
     */
    private LocalDate calculateEndDate(LocalDate startDate, int loanTermMonths) {
        return startDate.plusMonths(loanTermMonths);
    }

    /**
     * Вычисление ежемесячного платежа по кредиту
     * @param interestRate Процентная ставка
     * @param loanAmount Сумма кредита
     * @param loanTermMonths Срок кредита в месяцах
     * @return Ежемесячный платеж
     */
    private double calculateMonthlyPayment(double interestRate, double loanAmount, int loanTermMonths) {
        double monthlyRate = interestRate / 12 / 100;
        return loanAmount * (monthlyRate / (1 - Math.pow(1 + monthlyRate, -loanTermMonths)));
    }

    /**
     * Вычисление суммы кредита с учетом доступных средств банка
     * @param loanAmount Запрашиваемая сумма кредита
     * @param bank Банк, предоставляющий кредит
     * @return Окончательная сумма кредита, скорректированная по средствам банка
     */
    private double calculateLoanAmount(double loanAmount, Bank bank) {
        if (loanAmount > bank.getTotalMoney()) {
            loanAmount = bank.getTotalMoney();
        }
        return loanAmount;
    }

    /**
     * Вычисление процентной ставки с учетом ограничений банка
     * @param interestRate Заданная процентная ставка
     * @param bank Банк, предоставляющий кредит
     * @return Процентная ставка, скорректированная по ставке банка
     */
    private double calculateInterestRate(double interestRate, Bank bank) {
        if (interestRate > bank.getInterestRate()) {
            System.out.println(
                    "Заданная процентная ставка превышает процентную ставку банка. Ставка будет скорректирована.");
            interestRate = bank.getInterestRate();
        }
        return interestRate;
    }

    /**
     * Получение кредитных аккаунтов по идентификатору пользователя
     * @param userId Идентификатор пользователя
     * @return Список кредитных аккаунтов пользователя
     */
    @Override
    public List<CreditAccount> getCreditAccountByUserId(int userId) {
        return creditAccountRepository.findAll().stream()
                .filter(account -> account.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    /**
     * Получение списка всех кредитных аккаунтов
     * @return Список всех кредитных аккаунтов
     */
    public List<CreditAccount> getAllCreditAccounts() {
        return creditAccountRepository.findAll();
    }

    /**
     * Получение кредитного аккаунта по идентификатору, если он существует
     * @param id Идентификатор кредитного аккаунта
     * @return Кредитный аккаунт, если найден, иначе выбрасывается исключение
     */
    private CreditAccount getCreditAccountIfExists(int id) {
        return getCreditAccountById(id);
    }
}