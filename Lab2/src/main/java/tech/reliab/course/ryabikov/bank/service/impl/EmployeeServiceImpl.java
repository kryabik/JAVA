package tech.reliab.course.ryabikov.bank.service.impl;

import tech.reliab.course.ryabikov.bank.entity.Bank;
import tech.reliab.course.ryabikov.bank.entity.BankOffice;
import tech.reliab.course.ryabikov.bank.entity.Employee;
import tech.reliab.course.ryabikov.bank.service.BankService;
import tech.reliab.course.ryabikov.bank.service.EmployeeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private static int employeesCount = 0;

    private final BankService bankService;

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    /**
     * Создание нового сотрудника.
     */
    @Override
    public Employee createEmployee(String fullName,
                                   LocalDate birthDate,
                                   String position,
                                   Bank bank,
                                   boolean remoteWork,
                                   BankOffice bankOffice,
                                   boolean canIssueLoans,
                                   double salary) {

        Employee employee = new Employee(fullName,
                birthDate,
                position,
                bank,
                remoteWork,
                bankOffice,
                canIssueLoans,
                salary);

        employee.setId(employeesCount++);
        employees.add(employee);
        bankService.addEmployee(bank.getId());
        return employee;
    }

    /**
     * Чтение данных сотрудника.
     * @param id Идентификатор сотрудника
     */
    @Override
    public Employee getEmployeeIfExists(int id) {
        return getEmployeeById(id).orElseThrow(() -> new NoSuchElementException("Employee was not found"));
    }

    /**
     * Обновление данных сотрудника.
     */
    @Override
    public void updateEmployee(int id, String name) {
        Employee employee = getEmployeeIfExists(id);
        employee.setFullName(name);
    }

    /**
     * Удаление сотрудника.
     * @param id Идентификатор сотрудника
     */
    @Override
    public void deleteEmployee(int id) {
        employees.remove(getEmployeeIfExists(id));
    }

    /**
     * Получение сотрудника по идентификатору
     * @param id Идентификатор сотрудника
     * @return Optional с сотрудником, если найден, или пустой Optional, если нет
     */
    public Optional<Employee> getEmployeeById(int id) {
        return employees.stream().filter(employee -> employee.getId() == id).findFirst();
    }

    /**
     * Получение списка всех сотрудников
     * @return Список всех сотрудников
     */
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    /**
     * Получение списка всех сотрудников, связанных с банком
     * @param bank Банк для фильтрации сотрудников
     * @return Список сотрудников, связанных с указанным банком
     */
    @Override
    public List<Employee> getAllEmployeesByBank(Bank bank) {
        return employees.stream()
                .filter(employee -> employee.getBank().getId() == bank.getId())
                .collect(Collectors.toList());
    }
}