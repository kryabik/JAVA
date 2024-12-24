package tech.reliab.course.ryabikov.bank.service;

import tech.reliab.course.ryabikov.bank.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee createEmployee(String fullName, LocalDate birthDate, String position, Bank bank, boolean remoteWork,
                            BankOffice bankOffice, boolean canIssueLoans, double salary);

    Optional<Employee> getEmployeeById(int id);

    List<Employee> getAllEmployeesByBank(Bank bank);

    void updateEmployee(int id, String name);

    void deleteEmployee(int id);

    Employee getEmployeeIfExists(int id);
}