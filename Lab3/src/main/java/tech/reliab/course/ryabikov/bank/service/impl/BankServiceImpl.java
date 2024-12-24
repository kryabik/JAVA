package tech.reliab.course.ryabikov.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.reliab.course.ryabikov.bank.entity.Bank;
import tech.reliab.course.ryabikov.bank.repository.BankRepository;
import tech.reliab.course.ryabikov.bank.service.BankService;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankRepository bankRepository;

    @Override
    public void registerBank(Bank bank) {
        bankRepository.save(bank);
    }

    /**
     * Чтение данных банка.
     *
     * @param id Идентификатор банка
     */
    @Override
    public Bank getBankById(long id) {
        return bankRepository.findById(id).orElse(null);
    }

    /**
     * Удаление банка.
     * @param id Идентификатор банка
     */
    @Override
    public void deleteBank(long id) {
        bankRepository.deleteById(id);
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
        return bankRepository.findAll();
    }

    /**
     * Получение банка по идентификатору, если он существует
     * @param id Идентификатор банка
     * @return Банк, если найден, иначе выбрасывается исключение
     * @throws NoSuchElementException если банк не найден
     */
    public Bank getBankIfExists(int id) throws NoSuchElementException {
        return getBankById(id);
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