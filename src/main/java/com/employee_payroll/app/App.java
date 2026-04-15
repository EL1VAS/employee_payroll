package com.employee_payroll.app;

import java.math.BigDecimal;
import java.util.List;

import com.employee_payroll.model.Employee;
import com.employee_payroll.service.EmployeeService;
import com.employee_payroll.util.HibernateUtil;

public class App {
    public static void main( String[] args ) {
        EmployeeService employeeService = new EmployeeService();

        try {
            employeeService.addEmployee("Maria Sanchez","Engineer", new BigDecimal(6785.35));
            employeeService.addEmployee("Robin Vanhalen","Developer", new BigDecimal(6596.83));
            employeeService.addEmployee("Anna Borowski","Architect", new BigDecimal(9546.12));
            employeeService.addEmployee("Bob Ziegler","HR Manager", new BigDecimal(6924.46));
            employeeService.addEmployee("Poppy DeWeiver","Head of accounting", new BigDecimal(9647.57));

            System.out.println("List of employees after adding: ");
            printEmployees(employeeService.getAllEmployees());

            employeeService.updateSalary(1, new BigDecimal(7006.29));
            System.out.println("List of employess after salary update: ");
            printEmployees(employeeService.getAllEmployees());

            employeeService.deleteEmployee(2);
            System.out.println("List of employees after deletion of one: ");
            printEmployees(employeeService.getAllEmployees());
        } catch (IllegalArgumentException e) {
            System.out.println("Bussiness rule exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error " + e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
        
    }

    private static void printEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("There were no empployees found");
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
