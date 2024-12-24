package tech.reliab.course.ryabikov.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.reliab.course.ryabikov.bank.entity.Bank;
import tech.reliab.course.ryabikov.bank.entity.BankAtm;
import tech.reliab.course.ryabikov.bank.entity.Employee;
import tech.reliab.course.ryabikov.bank.enums.BankStatus;
import tech.reliab.course.ryabikov.bank.repository.BankAtmRepository;
import tech.reliab.course.ryabikov.bank.service.AtmService;
import java.util.*;

@Service
public class AtmServiceImpl implements AtmService {
    @Autowired
    private BankAtmRepository bankAtmRepository;

    /**
     * Создание нового банкомата
     * @param name Название банкомата
     * @param address Адрес банкомата
     * @param bank Банк, которому принадлежит банкомат
     * @param location Офис банка, в котором находится банкомат
     * @param employee Сотрудник, ответственный за банкомат
     * @param cashWithdrawal Возможность снятия наличных
     * @param cashDeposit Возможность внесения наличных
     * @param maintenanceCost Стоимость обслуживания банкомата
     * @return Созданный банкомат (в данный момент возвращает null)
     */
    @Override
    public BankAtm createBankAtm(String name, String address, Bank bank, String location, Employee employee, boolean cashWithdrawal, boolean cashDeposit, double maintenanceCost) {
        BankAtm bankAtm = new BankAtm(name, address, bank, location, employee,
                cashWithdrawal, cashDeposit, maintenanceCost);
        bankAtm.setStatus(generateStatus());
        bankAtm.setBalance(generateAtmMoney(bank));

        return bankAtmRepository.save(bankAtm);
    }

    /**
     * Получение банкомата по идентификатору
     * @param id Идентификатор банкомата
     * @return Optional с банкоматом, если найден, или пустой Optional, если не найден
     */
    @Override
    public Optional<BankAtm> getAtmById(int id) {
        return Optional.ofNullable(bankAtmRepository.findById((long) id).orElse(null));
    }

    /**
     * Получение списка всех банкоматов
     * @return Список всех банкоматов (в данный момент возвращает пустой список)
     */
    @Override
    public List<BankAtm> getAllAtms() {
        return bankAtmRepository.findAll();
    }

    /**
     * Получение всех банкоматов по указанному банку
     * @param bank Банк для фильтрации банкоматов
     * @return Список банкоматов, связанных с указанным банком (в данный момент возвращает пустой список)
     */
    @Override
    public List<BankAtm> getAllAtmsByBank(Bank bank) {
        return bankAtmRepository.findAll();
    }

    /**
     * Чтение данных банкомата.
     * @param id Идентификатор банкомата
     */
    public BankAtm getBankAtmById(long id) {
        return bankAtmRepository.findById(id).orElse(null);
    }

    /**
     * Удаление банкомата.
     * @param id Идентификатор банкомата
     */
    public void deleteBankAtm(long id) {
        bankAtmRepository.deleteById(id);
    }

    /**
     * Генерация случайного статуса для банкомата
     * @return Случайный статус банкомата
     */
    private BankStatus generateStatus() {
        return BankStatus.getRandomStatus();
    }

    /**
     * Генерация случайной суммы наличных для банкомата
     * @param bank Банк, которому принадлежит банкомат
     * @return Случайная сумма наличных, не превышающая общую сумму средств банка
     */
    private double generateAtmMoney(Bank bank) {
        return new Random().nextDouble(bank.getTotalMoney());
    }

    /**
     * Получение списка всех банкоматов банка
     * @return Список всех банкоматов
     */
    public List<BankAtm> getAllBankAtms() {
        return bankAtmRepository.findAll();
    }

    /**
     * Получение банкомата по идентификатору, если он существует
     * @param id Идентификатор банкомата
     * @return Банкомат, если найден, иначе выбрасывается исключение
     * @throws NoSuchElementException если банкомат не найден
     */
    private BankAtm getBankAtmIfExists(int id) {
        return getBankAtmById(id);
    }
}