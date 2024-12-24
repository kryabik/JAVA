package tech.reliab.course.ryabikov.bank.service.impl;

import tech.reliab.course.ryabikov.bank.entity.Employee;
import tech.reliab.course.ryabikov.bank.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
    private Employee employee;

    /**
     * Создание нового сотрудника.
     */
    @Override
    public void create(Employee newEmployee) {
        this.employee = newEmployee;
    }

    /**
     * Чтение данных сотрудника.
     * @param id Идентификатор сотрудника
     */
    @Override
    public Employee read(int id) {
        if (this.employee != null && this.employee.getId() == id) {
            return employee;
        } else {
            return null;
        }
    }

    /**
     * Обновление данных сотрудника.
     */
    @Override
    public void update(Employee employee) {
        if (this.employee != null && this.employee.getId() == employee.getId()) {
            this.employee = employee;
        }
    }

    /**
     * Удаление сотрудника.
     * @param id Идентификатор сотрудника
     */
    @Override
    public void delete(int id) {
        if (this.employee != null && this.employee.getId() == id) {
            this.employee = null;
        }
    }
}