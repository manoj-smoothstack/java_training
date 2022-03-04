package com.smoothstack.hibernate.model;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.*;


public class EntityManagerTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManagerFactory mysqlEntityManagerFactory;


    @BeforeEach
    public void setUp() {
        // like discussed with regards to SessionFactory, an EntityManagerFactory is set up once for an application
        //    IMPORTANT: notice how the name here matches the name we gave the persistence-unit in persistence.xml!
        mysqlEntityManagerFactory = Persistence.createEntityManagerFactory( "org.smoothstack.hibernate.jpamysql" );
        entityManagerFactory = Persistence.createEntityManagerFactory( "org.smoothstack.hibernate.jpa" );
    }

    @AfterEach
    protected void tearDown() throws Exception {
        entityManagerFactory.close();
        mysqlEntityManagerFactory.close();
    }

    @Test
    public void testBasicUsage() {
        // create a couple of events...
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist( new Event( "Our very first event!", new Date() ) );
        entityManager.persist( new Event( "A follow up event", new Date() ) );
        entityManager.getTransaction().commit();
        entityManager.close();

        // now lets pull events from the database and list them
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Event> result = entityManager.createQuery( "from Event", Event.class ).getResultList();
        for ( Event event : result ) {
            System.out.println( "Event (" + event.getDate() + ") : " + event.getTitle() );
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testStudent() {
        // create a couple of events...
        EntityManager entityManager = mysqlEntityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Student tempStudent = new Student("Bob", "Parker", "bob@parker.com");
        entityManager.persist(tempStudent);
        entityManager.getTransaction().commit();
        entityManager.close();

        // now lets pull events from the database and list them
        entityManager = mysqlEntityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Student> result = entityManager.createQuery( "from Student", Student.class).getResultList();
        for ( Student student : result ) {
            System.out.println(student);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}