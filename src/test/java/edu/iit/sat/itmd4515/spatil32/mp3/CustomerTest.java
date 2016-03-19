/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.mp3;

import java.util.Date;
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
    
    @Test
    public void testInsertCustomerShoppingDetails()
    {
        System.out.println("Persisting shreyas");
        Customer newCustomer = new Customer("Shreyas", "Patil", 25, 'M', "Pune", "Shreyas.Patil@gmail.com", new GregorianCalendar(1991, 5, 16).getTime(), "12546", "admin", "admin", 'Y');
        Orders order1 = new Orders(newCustomer, 5000, new Date());
        Orders order2 = new Orders(newCustomer, 6000, new Date());
        newCustomer.getOrders().add(order1);
        newCustomer.getOrders().add(order2);
        
        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(order1);
        entityManager.persist(order2);
        entityTransaction.commit();
        Assert.assertNotNull(newCustomer.getCustomerId());
        Assert.assertNotNull(order1.getOrderId());
        Assert.assertNotNull(order2.getOrderId());
        System.out.println("Shreyas persisted");
        System.out.println("A new customer with id " + newCustomer.getCustomerId() + " is persisted to spatil32_Customer table." );
        System.out.println("New orders are persisted to Orders table with CustomerId " + newCustomer.getCustomerId() + " as foreign key constraint.");        
    }
    
    
    /**
     * Inserts new customer 
     */
    @Test
    public void testInsertCustomers()
    {
        System.out.println("IN TESTINSERTCUSTOMER");
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
        Assert.assertNotNull(newCustomer1.getCustomerId());
        Assert.assertNotNull(newCustomer2.getCustomerId());
        Assert.assertNotNull(newCustomer3.getCustomerId());
    }

    @Test
    public void testPersistTableData()
    {
        Customer newCustomer = new Customer("Immanuel", "Stephen", 30, 'M', "Chennai", "Immanuel.S@gmail.com", new GregorianCalendar(1986, 8, 13).getTime(), "12546", "immanuel", "stephen", 'N');
        Orders order1 = new Orders(newCustomer, 2000, new Date());
        Orders order2 = new Orders(newCustomer, 1000, new Date());
        newCustomer.getOrders().add(order1);
        newCustomer.getOrders().add(order2);
        
        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(order1);
        entityManager.persist(order2);
        entityTransaction.commit();
        
        Assert.assertNotNull(newCustomer.getCustomerId());
        System.out.println("Immanuel persisted");
        System.out.println("A new customer with id " + newCustomer.getCustomerId() + " is persisted to spatil32_Customer table." );
        System.out.println("New orders are persisted to Orders table with CustomerId " + newCustomer.getCustomerId() + " as foreign key constraint.");
    }
    
    @Test
    public void testReadAllCustomers()
    {
        System.out.println("IN READ ALL CUSTOMERS");
        System.out.println("***************************************************************");
        System.out.println(" LIST OF ALL THE CUSTOMERS PERSISTED ");
        System.out.println("***************************************************************");
        List<Customer> allCustomers = entityManager.createNamedQuery("Customer.seeAllCustomers", Customer.class).getResultList();
        Assert.assertTrue(allCustomers.size() > 0);
        System.out.println("*** List of all customers persisted ***");
        for (Customer customer : allCustomers)
        {
            System.out.println(customer.toString());
        }
    }
    
/*    
    @Test
    public void testFindCustomerByName()
    {
        System.out.println("IN FIND BY NAME");
        Customer findCustomer = entityManager.createNamedQuery("Customer.findCustomerByName", Customer.class).setParameter("name", "Immanuel").getSingleResult();
        Assert.assertEquals("Immanuel", findCustomer.getFirstName());
        
        System.out.println("_____________________________________________________________________");
        System.out.println("********************************************************************");
        System.out.println("FIND CUSTOMER BY NAME");
        System.out.println("********************************************************************");
        System.out.println("The customer details are as follows : ");
        findCustomer.toString();
        System.out.println("The order details for the customer are : ");
        List<Orders> allOrders = findCustomer.getOrders();
        for (Orders allOrder : allOrders)
        {
            System.out.println(allOrder.toString());
        }
        System.out.println("_______________________________________________________________________");
    }
  */  
    
    @Test
    public void testUpdateExistingCustomer()
    {
        System.out.println("IN UPDATE CUSTOMER");
        Customer updateCustomer = entityManager.createNamedQuery("Customer.findCustomerByName", Customer.class).setParameter("name", "Hrishi").getSingleResult();
        Assert.assertEquals(updateCustomer.getFirstName(), "Hrishi");
        
        System.out.println("__________________________________________________________________");
        System.out.println("CUSTOMER INFORMATION BEFORE UPDATE");
        System.out.println(updateCustomer.toString());
        System.out.println("__________________________________________________________________");
        
        entityTransaction.begin();
        updateCustomer.setFirstName("Spongebob");
        updateCustomer.setLastName("SquarePants");
        updateCustomer.setEmail("spongebob@gmail.com");
        entityTransaction.commit();
        
        System.out.println("__________________________________________________________________");
        System.out.println("CUSTOMER INFORMATION AFTER UPDATE");
        System.out.println(updateCustomer.toString());
        System.out.println("__________________________________________________________________");
        
        Customer updatedCustomer = entityManager.createNamedQuery("Customer.findCustomerByName", Customer.class).setParameter("name", "Spongebob").getSingleResult();
        Assert.assertEquals(updatedCustomer.getFirstName(), "Spongebob");
        Assert.assertEquals(updatedCustomer.getLastName(), "SquarePants");
        Assert.assertEquals(updatedCustomer.getAddress(), "Illinois");
        System.out.println("Customer with first name " + updateCustomer.getFirstName() + " is updated to " + updatedCustomer.getFirstName());
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