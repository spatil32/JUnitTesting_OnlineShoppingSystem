/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.mp3;

import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dell
 */
public class CustomerTest
{
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public CustomerTest() {
    }
    
    @BeforeClass
    public static void beforeEachClass()
    {
        System.out.println("Inside @BeforeClass tag for CustomerTest class");
        entityManagerFactory = Persistence.createEntityManagerFactory("spatil32PU");
        System.err.println("entityManagerFactory created..");
    }
    
    @Before
    public void beforeEachTestMethod()
    {
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }
    
    /**
     * Inserts new customer 
     */
    @Test
    public void persistCustomer()
    {
        Customer newCustomer = new Customer("Shreyas", "Patil", 25, 'M', "Pune", "Shreyas.Patil@gmail.com", new GregorianCalendar(1991, 5, 16).getTime(), "12546", "admin", "admin", 'Y');
        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityTransaction.commit();
        Assert.assertNotNull(newCustomer.getCustomerId());
        System.err.println("A new customer with customer Id " + newCustomer.getCustomerId() + " is persisted to table spatil32_Customer");
    }

    @Test
    public void insertCustomers()
    {
        Customer newCustomer1 = new Customer("Steve", "Watt", 28, 'M', "Illinois", "Steve.Watt@gmail.com", new GregorianCalendar(1988, 3, 15).getTime(), "65789", "steve", "watt", 'N');
        Customer newCustomer2 = new Customer("Sarah", "Jones", 23, 'F', "Dallas", "Sarah.Jones@gmail.com", new GregorianCalendar(1993, 8, 21).getTime(), "56151", "sarah", "jones", 'N');
        Customer newCustomer3 = new Customer("Hrishi", "Soman", 25, 'M', "Illinois", "Hrishi.Soman@gmail.com", new GregorianCalendar(1991, 10, 11).getTime(), "74256", "hrishi", "soman", 'N');

        entityTransaction.begin();
        entityManager.persist(newCustomer1);
        System.out.println("A new customer is persisted to table spatil32_Customer");
        entityManager.flush();
        entityManager.persist(newCustomer2);
        System.out.println("A new customer is persisted to table spatil32_Customer");
        entityManager.flush();
        entityManager.persist(newCustomer3);
        System.out.println("A new customer is persisted to table spatil32_Customer");
        entityManager.flush();
        entityTransaction.commit();
    }

    @Test
    public void readAllCustomers()
    {
        List<Customer> allCustomers = entityManager.createNamedQuery("Customer.seeAllCustomers", Customer.class).getResultList();
        Assert.assertTrue(allCustomers.size() > 0);
        System.out.println("*** List of all customers persisted ***");
        for (Customer customer : allCustomers)
        {
            System.out.println(customer.toString());
        }
    }
    
    @After
    public void afterEachTestMethod()
    {
        entityManager.close();
    }
    
    @AfterClass
    public static void afterEachClass()
    {
        entityManagerFactory.close();
    }
}