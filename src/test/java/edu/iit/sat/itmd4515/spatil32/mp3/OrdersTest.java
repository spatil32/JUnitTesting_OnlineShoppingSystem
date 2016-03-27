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
public class OrdersTest 
{
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private int orderId = 2;

    /**
     * Parameterless constructor
     */
    public OrdersTest() {
    }

    /**
     * before class, a new persistence unit with name spatil32PU will be created.
     */
    @BeforeClass
    public static void beforeEachClass()
    {
        System.out.println("Inside @BeforeClass tag for OrdersTest class");
        entityManagerFactory = Persistence.createEntityManagerFactory("spatil32PU");
        System.out.println("entityManagerFactory created..");
    }
    
    /**
     * before starting test, entityManager and entityTransaction are instantiated.
     */
    @Before
    public void beforeEachTestMethod()
    {
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();                
    }
    
    /**
     * Persists new orders for new customer.
     */
    @Test
    public void testInsertOrders()
    {
        System.out.println("In test insert orders.");
        Customer newCustomer = new Customer("Irwin", "Selvam", 22, 'M', "Mumbai", "Irwin.Selvam@gmail.com", new GregorianCalendar(1994, 7, 6).getTime(), "84256", "irwin", "selvam", 'N');
        Orders order1 = new Orders(newCustomer, 10000, new Date());
        Orders order2 = new Orders(newCustomer, 1500, new Date());
        newCustomer.addOrders(order1);
        newCustomer.addOrders(order2);
        
        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(order1);
        entityManager.persist(order2);
        entityTransaction.commit();

        //asserts that new orders are persisted with positive order id.
        Assert.assertNotNull(order1.getOrderId());
        Assert.assertNotNull(order2.getOrderId());

        for (Orders order : newCustomer.getOrders())
        {
            System.out.println("New order is persisted to Orders table with Order Id " + order.getOrderId());        
        }        
    }
    
    /**
     * persists and reads all orders for newly persisted customer.
     */
    @Test
    public void testReadAllOrders()
    {
        System.out.println("PERSISTING TE ORDERS BEFORE TO VIEW THEM IN FURTHER STEPS.");
        Customer newCustomer = new Customer("Irwin", "Selvam", 22, 'M', "Mumbai", "Irwin.Selvam@gmail.com", new GregorianCalendar(1994, 7, 6).getTime(), "84256", "irwin", "selvam", 'N');
        Orders order1 = new Orders(newCustomer, 10000, new Date());
        Orders order2 = new Orders(newCustomer, 1500, new Date());
        Orders order3 = new Orders(newCustomer, 4000, new Date());
        Orders order4 = new Orders(newCustomer, 8000, new Date());
        
        newCustomer.addOrders(order1);
        newCustomer.addOrders(order2);
        newCustomer.addOrders(order3);
        newCustomer.addOrders(order4);
        
        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(order1);
        entityManager.persist(order2);
        entityManager.persist(order3);
        entityManager.persist(order4);
        entityTransaction.commit();

        //Asserts new orders are persisted.
        Assert.assertNotNull(order1.getOrderId());
        Assert.assertNotNull(order2.getOrderId());
        Assert.assertNotNull(order3.getOrderId());
        Assert.assertNotNull(order4.getOrderId());

        for (Orders order : newCustomer.getOrders())
        {
            System.out.println("New order is persisted to Orders table with Order Id " + order.getOrderId());        
        }        
        
        System.out.println("In test read all orders.");
        System.out.println("IN READ ALL ORDERS");
        System.out.println("***************************************************************");
        System.out.println(" LIST OF ALL THE ORDERS PERSISTED ");
        System.out.println("***************************************************************");
        List<Orders> allOrders = entityManager.createNamedQuery("Orders.seeAllOrders", Orders.class).getResultList();
        Assert.assertTrue(allOrders.size() > 0);
        System.out.println("*** List of all orders persisted ***");
        for (Orders order : allOrders)
        {
            System.out.println(order.toString());
        }
    }
    
    /**
     * updates newly persisted orders for new customer.
     */
    @Test
    public void testUpdateExistingOrder()
    {
        System.out.println("PERSISTING ORDERS BEFORE TO UPDATE THEM IN FIRTHER STEPS.");
        Customer newCustomer = new Customer("Jennifer", "Yale", 26, 'F', "Illinois", "Jennifer.Yale@gmail.com", new GregorianCalendar(1990, 12, 12).getTime(), "36243", "jennifer", "yale", 'N');
        Orders order1 = new Orders(newCustomer, 10000, new Date());
        Orders order2 = new Orders(newCustomer, 1500, new Date());
        Orders order3 = new Orders(newCustomer, 4000, new Date());
        
        newCustomer.addOrders(order1);
        newCustomer.addOrders(order2);
        newCustomer.addOrders(order3);
        
        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(order1);
        entityManager.persist(order2);
        entityManager.persist(order3);
        entityTransaction.commit();

        Assert.assertNotNull(order1.getOrderId());
        Assert.assertNotNull(order2.getOrderId());
        Assert.assertNotNull(order3.getOrderId());

        for (Orders order : newCustomer.getOrders())
        {
            System.out.println("New order is persisted to Orders table with Order Id " + order.getOrderId());        
        }        

        System.out.println("IN UPDATE ORDER");
        Orders updateOrder1 = entityManager.createNamedQuery("Orders.findOrdersById", Orders.class).setParameter("id", order1.getOrderId()).getSingleResult();
        Assert.assertEquals(updateOrder1.getOrderId(), order1.getOrderId());
        
        System.out.println("__________________________________________________________________");
        System.out.println("ORDER1 INFORMATION BEFORE UPDATE");
        System.out.println(updateOrder1.toString());
        System.out.println("__________________________________________________________________");
        
        entityTransaction.begin();
        updateOrder1.setTotalBillAmount(10000);
        updateOrder1.setDeliveryDate(new GregorianCalendar(2016, 31, 3).getTime());
        entityTransaction.commit();
        
        System.out.println("__________________________________________________________________");
        System.out.println("ORDER INFORMATION AFTER UPDATE");
        System.out.println(updateOrder1.toString());
        System.out.println("__________________________________________________________________");
        
        Orders updatedOrder1 = entityManager.createNamedQuery("Orders.findOrdersById", Orders.class).setParameter("id", order1.getOrderId()).getSingleResult();
        //asserts updated orders are equal to new values.
        Assert.assertEquals(updatedOrder1.getTotalBillAmount(), 10000);
        Assert.assertEquals(updatedOrder1.getDeliveryDate(), new GregorianCalendar(2016, 31, 3).getTime());
        System.out.println("Order with order id " + updateOrder1.getOrderId() + " is updated.");


        Orders updateOrder2 = entityManager.createNamedQuery("Orders.findOrdersById", Orders.class).setParameter("id", order2.getOrderId()).getSingleResult();
        Assert.assertEquals(updateOrder2.getOrderId(), order2.getOrderId());

        System.out.println("__________________________________________________________________");
        System.out.println("ORDER2 INFORMATION BEFORE UPDATE");
        System.out.println(updateOrder2.toString());
        System.out.println("__________________________________________________________________");
        
        entityTransaction.begin();
        updateOrder2.setTotalBillAmount(20000);
        updateOrder2.setDeliveryDate(new GregorianCalendar(2016, 30, 4).getTime());
        entityTransaction.commit();
        
        System.out.println("__________________________________________________________________");
        System.out.println("ORDER2 INFORMATION AFTER UPDATE");
        System.out.println(updateOrder2.toString());
        System.out.println("__________________________________________________________________");
        
        Orders updatedOrder2 = entityManager.createNamedQuery("Orders.findOrdersById", Orders.class).setParameter("id", order2.getOrderId()).getSingleResult();
        //asserts updated orders equal to new values.
        Assert.assertEquals(updatedOrder2.getTotalBillAmount(), 20000);
        Assert.assertEquals(updatedOrder2.getDeliveryDate(), new GregorianCalendar(2016, 30, 4).getTime());
        System.out.println("Order with order id " + updateOrder2.getOrderId() + " is updated.");
    }
    
    /**
     * deletes newly persisted order for new customer.
     */
    @Test
    public void testDeleteOrdersById()
    {
        System.out.println("PERSISTING CUSTOMER AND ORDERS TO DELETE IN FURTHER STEPS");
        Customer newCustomer = new Customer("Uma", "Selvam", 30, 'F', "Chennai", "Uma.Selvam@gmail.com", new GregorianCalendar(1987, 1, 1).getTime(), "14967", "uma", "selvam", 'N');
        Orders order1 = new Orders(newCustomer, 3000, new Date());
        Orders order2 = new Orders(newCustomer, 7000, new Date());
        newCustomer.addOrders(order1);
        newCustomer.addOrders(order2);        
        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(order1);
        entityManager.persist(order2);
        entityTransaction.commit();

        Assert.assertNotNull(order1.getOrderId());
        Assert.assertNotNull(order2.getOrderId());

        for (Orders order : newCustomer.getOrders())
        {
            System.out.println("New order is persisted to Orders table with Order Id " + order.getOrderId());        
        }
        
        System.out.println("IN DELETE ORDERS BY ID");
        System.out.println("***************************************************************");
        Orders deleteOrder1 = entityManager.createNamedQuery("Orders.findOrdersById", Orders.class).setParameter("id", order1.getOrderId()).getSingleResult();
        entityTransaction.begin();
        entityManager.remove(deleteOrder1);
        entityTransaction.commit();
        List<Orders> deletedOrder1 = entityManager.createNamedQuery("Orders.findOrdersById", Orders.class).setParameter("id", order1.getOrderId()).getResultList();
        //asserts orders are deleted.
        Assert.assertTrue(deletedOrder1.isEmpty());
        Orders deleteOrder2 = entityManager.createNamedQuery("Orders.findOrdersById", Orders.class).setParameter("id", order2.getOrderId()).getSingleResult();
        entityTransaction.begin();
        entityManager.remove(deleteOrder2);
        entityTransaction.commit();
        List<Orders> deletedOrder2 = entityManager.createNamedQuery("Orders.findOrdersById", Orders.class).setParameter("id", order2.getOrderId()).getResultList();
        //asserts orders are deleted.
        Assert.assertTrue(deletedOrder2.isEmpty());
        System.out.println("All orders of customer with customer id " + newCustomer.getCustomerId() + " are deleted successfully.");
    }
    
    /**
     *  After each test, closes entity manager
     */
    @After
    public void afterEachTestMethod()
    {
        entityManager.close();
    }
    
    /**
     * after class, closes entityManagerFactory
     */
    @AfterClass
    public static void afterEachClass()
    {
        entityManagerFactory.close();
    }
}