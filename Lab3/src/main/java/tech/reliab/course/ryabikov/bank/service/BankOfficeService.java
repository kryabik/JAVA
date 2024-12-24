package tech.reliab.course.ryabikov.bank.service;

import tech.reliab.course.ryabikov.bank.entity.Bank;
import tech.reliab.course.ryabikov.bank.entity.BankOffice;

import java.util.List;

public interface BankOfficeService {
    BankOffice createBankOffice(String name, String address, boolean canPlaceAtm,
                                boolean canIssueLoan, boolean cashWithdrawal, boolean cashDeposit,
                                double rentCost, Bank bank);

    List<BankOffice> getAllBankOffices();

    List<BankOffice> getAllBankOfficesByBank(Bank bank);

    BankOffice getBankOfficeById(long id);
}