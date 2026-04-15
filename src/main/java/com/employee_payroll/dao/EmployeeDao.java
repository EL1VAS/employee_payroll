package com.employee_payroll.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;


import com.employee_payroll.model.Employee;
import com.employee_payroll.util.HibernateUtil;

// Implement DAO methods:

// addEmployee()
// updateSalary()
// deleteEmployee()
// getAllEmployees()


public class EmployeeDao {
    public void addEmployee(Employee employee) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
        } catch(Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to add employee", e);
        }
    }

    public List<Employee> getAllEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Employee", Employee.class).list();
        }
    }

    public void updateSalary(Integer id, BigDecimal salary) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Employee employee = session.get(Employee.class, id);
            if (employee == null) {
                throw new IllegalArgumentException("Employee not found with id: " + id);
            }

            employee.setSalary(salary);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update salary", e);
        }
    }

    public void deleteEmployee(Integer id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Employee employee = session.get(Employee.class, id);
            if (employee == null) {
                throw new IllegalArgumentException("Employee not found with id: " + id);
            }

            session.remove(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to delete employee", e);
        }
    }

    
    
}
