package com.employee_payroll.service;

import java.math.BigDecimal;
import java.util.List;

import com.employee_payroll.dao.EmployeeDao;
import com.employee_payroll.model.Employee;

public class EmployeeService {
    private final EmployeeDao employeeDao = new EmployeeDao();

    private static final String FORBIDDEN_POSITION = "CEO";
    public void addEmployee(String name, String position, BigDecimal salary) {
        if (FORBIDDEN_POSITION.equalsIgnoreCase(position)) {
            throw new IllegalArgumentException("Cannot add employee with position " + FORBIDDEN_POSITION);
        }
        Employee employee = new Employee(name, position, salary);
        employeeDao.addEmployee(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }


    private static final BigDecimal MIN_ALLOWED_SALARY = new BigDecimal("2000.00");
    public void updateSalary(Integer id, BigDecimal newSalary) {
        if (newSalary.compareTo(MIN_ALLOWED_SALARY) < 0) {
            throw new IllegalArgumentException("Salary cannot be lower than " + MIN_ALLOWED_SALARY);
        }
        employeeDao.updateSalary(id, newSalary);
    }

    public void deleteEmployee(Integer id) {
        employeeDao.deleteEmployee(id);
    }
}
