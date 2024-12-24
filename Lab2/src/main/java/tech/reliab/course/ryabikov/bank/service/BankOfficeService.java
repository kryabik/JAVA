package tech.reliab.course.ryabikov.bank.service;

import tech.reliab.course.ryabikov.bank.entity.Bank;
import tech.reliab.course.ryabikov.bank.entity.BankOffice;

import java.util.List;
import java.util.Optional;

public interface BankOfficeService {
    BankOffice createBankOffice(String name, String address, boolean canPlaceAtm,
                                boolean canIssueLoan, boolean cashWithdrawal, boolean cashDeposit,
                                double rentCost, Bank bank);

    Optional<BankOffice> getBankOfficeById(int id);

    List<BankOffice> getAllBankOffices();

    List<BankOffice> getAllBankOfficesByBank(Bank bank);

    void updateBankOffice(int id, String name);

    void deleteBankAtm(int officeId, int bankId);
}