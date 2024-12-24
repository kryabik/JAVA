package tech.reliab.course.ryabikov.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.reliab.course.ryabikov.bank.entity.Bank;
import tech.reliab.course.ryabikov.bank.entity.BankOffice;
import tech.reliab.course.ryabikov.bank.enums.BankStatus;
import tech.reliab.course.ryabikov.bank.repository.BankOfficeRepository;
import tech.reliab.course.ryabikov.bank.service.BankOfficeService;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BankOfficeServiceImpl implements BankOfficeService {
    @Autowired
    private BankOfficeRepository bankOfficeRepository;

    /**
     * Создание нового офиса.
     */
    @Override
    public BankOffice createBankOffice(String name, String address, boolean canPlaceAtm,
                                       boolean canIssueLoan, boolean cashWithdrawal, boolean cashDeposit,
                                       double rentCost, Bank bank) {
        BankOffice bankOffice = new BankOffice(name, address, bank, rentCost);
        bankOffice.setStatus(generateStatus());
        bankOffice.setBalance(generateOfficeMoney(bank));

        return bankOfficeRepository.save(bankOffice);
    }

    /**
     * Чтение данных офиса.
     * @param id Идентификатор офиса
     */
    @Override
    public BankOffice getBankOfficeById(long id) {
        return bankOfficeRepository.findById(id).orElse(null);
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
        return bankOfficeRepository.findAll();
    }

    @Override
    public List<BankOffice> getAllBankOfficesByBank(Bank bank) {
        return bankOfficeRepository.findAll().stream()
                .filter(bankOffice -> bankOffice.getBankId() == bank.getId())
                .collect(Collectors.toList());
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