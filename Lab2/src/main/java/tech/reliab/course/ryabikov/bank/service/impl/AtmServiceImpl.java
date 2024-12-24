package tech.reliab.course.ryabikov.bank.service.impl;

import tech.reliab.course.ryabikov.bank.entity.Bank;
import tech.reliab.course.ryabikov.bank.entity.BankAtm;
import tech.reliab.course.ryabikov.bank.entity.BankOffice;
import tech.reliab.course.ryabikov.bank.entity.Employee;
import tech.reliab.course.ryabikov.bank.enums.BankStatus;
import tech.reliab.course.ryabikov.bank.service.AtmService;
import tech.reliab.course.ryabikov.bank.service.BankService;

import java.util.*;
import java.util.stream.Collectors;

public class AtmServiceImpl implements AtmService {
    private static int bankAtmsCount = 0;
    private List<BankAtm> bankAtms = new ArrayList<>();
    private BankService bankService;

    public AtmServiceImpl(BankService bankSrv) {
        this.bankService = bankService;
    }

    /**
     * Создание нового банкомата.
     */
    public BankAtm createBankAtm(String name, String address, Bank bank, String location, Employee employee,
                                 boolean cashWithdrawal, boolean cashDeposit, double maintenanceCost) {
        BankAtm bankAtm = new BankAtm(name, address, bank, location, employee,
                cashWithdrawal, cashDeposit, maintenanceCost);
        bankAtm.setId(bankAtmsCount++);
        bankAtm.setStatus(generateStatus());
        bankAtm.setBalance(generateAtmMoney(bank));
        bankService.addAtm(bank.getId());
        bankAtms.add(bankAtm);

        return bankAtm;
    }

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
    public BankAtm createBankAtm(String name, String address, Bank bank, BankOffice location, Employee employee, boolean cashWithdrawal, boolean cashDeposit, double maintenanceCost) {
        return null;
    }

    /**
     * Получение банкомата по идентификатору
     * @param id Идентификатор банкомата
     * @return Optional с банкоматом, если найден, или пустой Optional, если не найден
     */
    @Override
    public Optional<BankAtm> getAtmById(int id) {
        return Optional.empty();
    }

    /**
     * Получение списка всех банкоматов
     * @return Список всех банкоматов (в данный момент возвращает пустой список)
     */
    @Override
    public List<BankAtm> getAllAtms() {
        return List.of();
    }

    /**
     * Получение всех банкоматов по указанному банку
     * @param bank Банк для фильтрации банкоматов
     * @return Список банкоматов, связанных с указанным банком (в данный момент возвращает пустой список)
     */
    @Override
    public List<BankAtm> getAllAtmsByBank(Bank bank) {
        return List.of();
    }

    /**
     * Чтение данных банкомата.
     * @param id Идентификатор банкомата
     */
    public Optional<BankAtm> getBankAtmById(int id) {
        return bankAtms.stream()
                .filter(bankAtm -> bankAtm.getId() == id)
                .findFirst();
    }

    /**
     * Обновление данных банкомата.
     */
    public void updateBankAtm(int id, String name) {
        BankAtm bankAtm = getBankAtmIfExists(id);
        bankAtm.setName(name);
    }

    /**
     * Удаление банкомата.
     * @param id Идентификатор банкомата
     */
    public void deleteBankAtm(int id) {
        BankAtm bankAtm = getBankAtmIfExists(id);
        bankAtms.remove(bankAtm);
        Bank bank = bankAtm.getBank();
        bankService.removeAtm(bank.getId());
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
        return new ArrayList<>(bankAtms);
    }

    /**
     * Получение всех банкоматов по указанному банку
     * @param bank Банк для фильтрации банкоматов
     * @return Список банкоматов, связанных с указанным банком
     */
    public List<BankAtm> getAllBankAtmsByBank(Bank bank) {
        return bankAtms.stream()
                .filter(bankAtm -> bankAtm.getBank().getId() == bank.getId())
                .collect(Collectors.toList());
    }

    /**
     * Получение банкомата по идентификатору, если он существует
     * @param id Идентификатор банкомата
     * @return Банкомат, если найден, иначе выбрасывается исключение
     * @throws NoSuchElementException если банкомат не найден
     */
    private BankAtm getBankAtmIfExists(int id) {
        return getBankAtmById(id).orElseThrow(() -> new NoSuchElementException("BankAtm was not found"));
    }
}