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
    public OrdersTest() {
    }


    @BeforeClass
    public static void beforeEachClass()
    {
        System.out.println("Inside @BeforeClass tag for OrdersTest class");
        entityManagerFactory = Persistence.createEntityManagerFactory("spatil32PU");
        System.out.println("entityManagerFactory created..");
    }
    
    @Before
    public void beforeEachTestMethod()
    {
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();                
        
        Customer newCustomer = new Customer("Uma", "Selvam", 30, 'F', "Chennai", "Uma.Selvam@gmail.com", new GregorianCalendar(1987, 1, 1).getTime(), "14967", "uma", "selvam", 'N');
        Orders order1 = new Orders(newCustomer, 3000, new Date());
        Orders order2 = new Orders(newCustomer, 7000, new Date());
        newCustomer.getOrders().add(order1);
        newCustomer.getOrders().add(order2);        
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
    }
    
    @Test
    public void testInsertOrders()
    {
        System.out.println("In test insert orders.");
        Customer newCustomer = new Customer("Irwin", "Selvam", 22, 'M', "Mumbai", "Irwin.Selvam@gmail.com", new GregorianCalendar(1994, 7, 6).getTime(), "84256", "irwin", "selvam", 'N');
        Orders order1 = new Orders(newCustomer, 10000, new Date());
        Orders order2 = new Orders(newCustomer, 1500, new Date());
        newCustomer.getOrders().add(order1);
        newCustomer.getOrders().add(order2);
        
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
    }
    
    @Test
    public void testReadAllOrders()
    {
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
    
    @Test
    public void testUpdateExistingOrder()
    {
        System.out.println("IN UPDATE ORDER");
        Orders updateOrder = entityManager.createNamedQuery("Orders.findOrdersById", Orders.class).setParameter("id", 1).getSingleResult();
        Assert.assertEquals(updateOrder.getOrderId(), 1);
        
        System.out.println("__________________________________________________________________");
        System.out.println("ORDER INFORMATION BEFORE UPDATE");
        System.out.println(updateOrder.toString());
        System.out.println("__________________________________________________________________");
        
        entityTransaction.begin();
        updateOrder.setTotalBillAmount(10000);
        updateOrder.setDeliveryDate(new GregorianCalendar(2016, 31, 3).getTime());
        entityTransaction.commit();
        
        System.out.println("__________________________________________________________________");
        System.out.println("ORDER INFORMATION AFTER UPDATE");
        System.out.println(updateOrder.toString());
        System.out.println("__________________________________________________________________");
        
        Orders updatedOrder = entityManager.createNamedQuery("Orders.findOrdersById", Orders.class).setParameter("id", 1).getSingleResult();
        Assert.assertEquals(updatedOrder.getTotalBillAmount(), 10000);
        Assert.assertEquals(updatedOrder.getDeliveryDate(), new GregorianCalendar(2016, 31, 3).getTime());
        System.out.println("Order with order id " + updateOrder.getOrderId() + " is updated.");
    }
    
    
    @Test
    public void testDeleteOrdersById()
    {
        System.out.println("IN DELETE ORDERS BY ID");
        System.out.println("***************************************************************");
        Orders deleteOrder = entityManager.createNamedQuery("Orders.findOrdersById", Orders.class).setParameter("id", 2).getSingleResult();
        entityTransaction.begin();
        entityManager.remove(deleteOrder);
        entityTransaction.commit();
        List<Orders> deletedOrder = entityManager.createNamedQuery("Orders.findOrdersById", Orders.class).setParameter("id", 2).getResultList();
        Assert.assertTrue(deletedOrder.isEmpty());
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
