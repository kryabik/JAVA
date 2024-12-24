package tech.reliab.course.ryabikov.bank.service.impl;

import tech.reliab.course.ryabikov.bank.entity.Bank;
import tech.reliab.course.ryabikov.bank.entity.BankOffice;
import tech.reliab.course.ryabikov.bank.enums.BankStatus;
import tech.reliab.course.ryabikov.bank.service.BankOfficeService;
import tech.reliab.course.ryabikov.bank.service.BankService;

import java.util.*;
import java.util.stream.Collectors;

public class BankOfficeServiceImpl implements BankOfficeService {
    private static int bankOfficesCount = 0;
    private List<BankOffice> bankOffices = new ArrayList<>();
    private final BankService bankService;

    public BankOfficeServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    /**
     * Создание нового офиса.
     */
    @Override
    public BankOffice createBankOffice(String name, String address, boolean canPlaceAtm,
                                       boolean canIssueLoan, boolean cashWithdrawal, boolean cashDeposit,
                                       double rentCost, Bank bank) {
        BankOffice bankOffice = new BankOffice(name, address, bank, rentCost);
        bankOffice.setId(bankOfficesCount++);
        bankOffice.setStatus(generateStatus());
        bankOffice.setBalance(generateOfficeMoney(bank));
        bankOffices.add(bankOffice);
        bankService.addOffice(bank.getId());

        return bankOffice;
    }

    /**
     * Чтение данных офиса.
     * @param id Идентификатор офиса
     */
    @Override
    public Optional<BankOffice> getBankOfficeById(int id) {
        return bankOffices.stream()
                .filter(bankOffice -> bankOffice.getId() == id)
                .findFirst();
    }

    /**
     * Обновление данных офиса.
     */
    @Override
    public void updateBankOffice(int id, String name) {
        BankOffice bankOffice = getBankOfficeIfExists(id);
        bankOffice.setName(name);
    }

    /**
     * Удаление офиса.
     * @param bankId Идентификатор банка
     */
    @Override
    public void deleteBankAtm(int officeId, int bankId) {
        BankOffice bankOffice = getBankOfficeIfExists(officeId);
        bankOffices.remove(bankOffice);
        bankService.removeOffice(bankId);
    }

    /**
     * Генерация случайного статуса банка
     * @return Случайный статус банка
     */
    private BankStatus generateStatus() {
        return BankStatus.getRandomStatus();
    }

    /**
     * Получение списка всех офисов банка
     * @return Список всех офисов банка
     */
    public List<BankOffice> getAllBankOffices() {
        return new ArrayList<>(bankOffices);
    }

    /**
     * Получение всех офисов по указанному банку
     * @param bank Банк для фильтрации офисов
     * @return Список офисов, связанных с указанным банком
     */
    @Override
    public List<BankOffice> getAllBankOfficesByBank(Bank bank) {
        return bankOffices.stream()
                .filter(bankOffice -> bankOffice.getBankId() == bank.getId())
                .collect(Collectors.toList());
    }

    /**
     * Получение офиса банка по идентификатору, если он существует
     * @param id Идентификатор офиса банка
     * @return Офис банка, если найден, иначе выбрасывается исключение
     * @throws NoSuchElementException если офис не найден
     */
    private BankOffice getBankOfficeIfExists(int id) {
        return getBankOfficeById(id).orElseThrow(() -> new NoSuchElementException("BankOffice was not found"));
    }

    /**
     * Генерация случайной суммы средств для офиса банка
     * @param bank Банк, которому принадлежит офис
     * @return Случайная сумма средств, не превышающая общую сумму средств банка
     */
    private double generateOfficeMoney(Bank bank) {
        return new Random().nextDouble(bank.getTotalMoney());
    }
}