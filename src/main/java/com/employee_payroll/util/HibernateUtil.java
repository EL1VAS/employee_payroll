package com.employee_payroll.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.employee_payroll.model.Employee;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry registry;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            registry = new StandardServiceRegistryBuilder().build();

            try {
                sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(Employee.class)
                    .buildMetadata()
                    .buildSessionFactory();
            } catch (Exception e) {
                StandardServiceRegistryBuilder.destroy(registry);
                throw new RuntimeException("Failed to build session factory", e);
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }  
}
