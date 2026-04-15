package com.employee_payroll.app;

import org.hibernate.SessionFactory;

import com.employee_payroll.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hibernate initiation!");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        System.out.println("Session factory opened " + (sessionFactory != null));

        HibernateUtil.shutdown();

        System.out.println("Hibernate shutdown");
    }
    
}
