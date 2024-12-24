package tech.reliab.course.ryabikov.bank.service.impl;

import tech.reliab.course.ryabikov.bank.entity.Bank;
import tech.reliab.course.ryabikov.bank.service.BankService;
import tech.reliab.course.ryabikov.bank.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class BankServiceImpl implements BankService {
    private final UserService userService;
    private List<Bank> banks = new ArrayList<>();

    public BankServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void registerBank(Bank bank) {
        if (getBankById(bank.getId()).isPresent())
            return;

        banks.add(bank);
    }

    /**
     * Чтение данных банка.
     * @param id Идентификатор банка
     */
    @Override
    public Optional<Bank> getBankById(int id) {
        return banks.stream()
                .filter(bank -> bank.getId() == id)
                .findFirst();
    }

    /**
     * Обновление данных банка.
     */
    @Override
    public void updateBank(int id, String name) {
        Bank bank = getBankIfExists(id);
        bank.setName(name);
    }

    /**
     * Удаление банка.
     * @param id Идентификатор банка
     */
    @Override

    public void deleteBank(int id) {
        Bank bank = getBankIfExists(id);
        banks.remove(bank);
        userService.deleteBank(bank);
    }

    /**
     * Добавление офиса.
     */
    public int addOffice(int id) {
        var bank = getBankIfExists(id);
        bank.setOfficesCount(bank.getOfficesCount() + 1);

        return bank.getOfficesCount();
    }

    /**
     * Добавление банкомата.
     */
    public int addAtm(int id) {
        var bank = getBankIfExists(id);
        bank.setAtmsCount(bank.getAtmsCount() + 1);

        return bank.getAtmsCount();
    }

    /**
     * Удаление банкомата.
     */
    public int removeAtm(int id) {
        var bank = getBankIfExists(id);
        bank.setAtmsCount(bank.getAtmsCount() - 1);

        return bank.getAtmsCount();
    }

    /**
     * Получение списка всех банков
     * @return Список всех банков
     */
    public List<Bank> getAllBanks() {
        return new ArrayList<>(banks);
    }

    /**
     * Получение банка по идентификатору, если он существует
     * @param id Идентификатор банка
     * @return Банк, если найден, иначе выбрасывается исключение
     * @throws NoSuchElementException если банк не найден
     */
    public Bank getBankIfExists(int id) throws NoSuchElementException {
        return getBankById(id).orElseThrow(() -> new NoSuchElementException("Bank was not found"));
    }

    /**
     * Увеличение числа сотрудников банка
     * @param id Идентификатор банка
     * @return Обновленное количество сотрудников банка
     */
    public int addEmployee(int id) {
        var bank = getBankIfExists(id);
        bank.setEmployeesCount(bank.getEmployeesCount() + 1);
        return bank.getEmployeesCount();
    }

    /**
     * Увеличение числа клиентов банка
     * @param id Идентификатор банка
     * @return Обновленное количество клиентов банка
     */
    public int addClient(int id) {
        var bank = getBankIfExists(id);
        bank.setClientsCount(bank.getClientsCount() + 1);
        return bank.getClientsCount();
    }

    /**
     * Уменьшение числа офисов банка
     * @param id Идентификатор банка
     * @return Обновленное количество офисов банка
     */
    public int removeOffice(int id) {
        var bank = getBankIfExists(id);
        bank.setOfficesCount(bank.getOfficesCount() - 1);
        return bank.getOfficesCount();
    }

    /**
     * Уменьшение числа сотрудников банка
     * @param id Идентификатор банка
     * @return Обновленное количество сотрудников банка
     */
    public int removeEmployee(int id) {
        var bank = getBankIfExists(id);
        bank.setEmployeesCount(bank.getEmployeesCount() - 1);
        return bank.getEmployeesCount();
    }

    /**
     * Уменьшение числа клиентов банка
     * @param id Идентификатор банка
     * @return Обновленное количество клиентов банка
     */
    public int removeClient(int id) {
        var bank = getBankIfExists(id);
        bank.setClientsCount(bank.getClientsCount() - 1);
        return bank.getClientsCount();
    }
}