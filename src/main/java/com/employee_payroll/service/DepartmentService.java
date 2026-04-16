package com.employee_payroll.service;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.employee_payroll.model.Department;
import com.employee_payroll.model.Employee;
import com.employee_payroll.util.HibernateUtil;

public class DepartmentService {
    public void createDepartmentWithEmployees() {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Department it = new Department("IT department");
            Employee elena = new Employee("Elena Vasileia", "Developer", new BigDecimal("6874.15"));
            Employee colyn = new Employee("Colyn Black", "Tester", new BigDecimal(5872.34));

            it.addEmployee(elena);
            it.addEmployee(colyn);

            session.persist(it);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to create department with employees", e);
        }
    }
    
    public void removeEmployeeFromDepartment(Integer departmentId, Integer employeeId) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Department dept = session.get(Department.class, departmentId);
            if (dept == null) {
                throw new IllegalArgumentException("Department not found: " + departmentId);
            }

            if (dept.getEmployees().isEmpty()) {
                System.out.println("No employees in department.");
            } else {
                Employee toRemove = dept.getEmployees()
                .stream()
                .filter(emp -> emp.getId()
                .equals(employeeId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Employee with id " + employeeId + " not found in department with id " + departmentId));
                System.out.println("Removing employee: " + toRemove.getName());

                // orphanRemoval=true means this will DELETE the row
                dept.removeEmployee(toRemove);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Failed to remove employee from department", e);
        }
    }
}
