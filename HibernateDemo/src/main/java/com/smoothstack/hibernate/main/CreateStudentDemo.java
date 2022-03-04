package com.smoothstack.hibernate.main;

import com.smoothstack.hibernate.model.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 CREATE TABLE `student` (
 `id` int NOT NULL,
 `email` varchar(255) DEFAULT NULL,
 `first_name` varchar(255) DEFAULT NULL,
 `last_name` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 */

public class CreateStudentDemo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            System.out.println("Creating new student object");
            Student tempStudent = new Student("Bob", "Adams", "bob@adams.com");
            session.beginTransaction();
            session.save(tempStudent);
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
