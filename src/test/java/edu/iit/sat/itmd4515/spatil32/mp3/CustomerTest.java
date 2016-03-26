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
 * CustomerTest class contains functionality to persist Customer entity in database table with help of javax.persistence API's.
 * @author Dell
 */
public class CustomerTest 
{
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    /**
     * parameterless constructor
     */
    public CustomerTest() {
    }

    /**
     * before class, a new persistence unit with name spatil32PU will be created.
     */
    @BeforeClass
    public static void beforeEachClass() {
        System.out.println("Inside @BeforeClass tag for CustomerTest class");
        entityManagerFactory = Persistence.createEntityManagerFactory("spatil32PU");
        System.out.println("entityManagerFactory created..");
    }

    /**
     * before starting test, entityManager and entityTransaction are instantiated.
     */
    @Before
    public void beforeEachTestMethod() {
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    /**
     * Test to insert new customer with new orders, wishlists, feedback, basket with new products.
     * This function will persist all 6 entities for one customer. So indicates functionality of whole Online shopping system 
     */
    @Test
    public void testInsertCustomerShoppingDetails() 
    {
        System.out.println("Persisting new customer");
        Customer newCustomer = new Customer("Shreyas", "Patil", 25, 'M', "Pune", "Shreyas.Patil@gmail.com", new GregorianCalendar(1991, 5, 16).getTime(), "12546", "admin", "admin", 'Y');
        Orders order1 = new Orders(newCustomer, 5000, new Date());
        Orders order2 = new Orders(newCustomer, 6000, new Date());
        newCustomer.getOrders().add(order1);
        newCustomer.getOrders().add(order2);
        Feedback feedback = new Feedback(newCustomer, new Date(), "Good Clothes", 7);
        newCustomer.setFeedback(feedback);
        Products product1 = new Products("Britannia", new Date(), 'K', 20, 5, 5, 2);
        Products product2 = new Products("Nestle", new Date(), 'K', 30, 10, 7, 5);
        Wishlist wishlist1 = new Wishlist(newCustomer, product1, new Date());
        Wishlist wishlist2 = new Wishlist(newCustomer, product2, new Date());
        newCustomer.getWishlist().add(wishlist1);
        newCustomer.getWishlist().add(wishlist2);

        Products product3 = new Products("Beginning J2EE", new Date(), 'B', 300, 10, 50, 20);
        Products product4 = new Products("Chicago Tourism", new Date(), 'B', 200, 10, 20, 15);

        Basket newBasket = new Basket(new Date(), 5, 50, newCustomer);
        newBasket.getProducts().add(product3);
        newBasket.getProducts().add(product4);
        newCustomer.setBasket(newBasket);

        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(order1);
        entityManager.persist(order2);
        entityManager.persist(feedback);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(product3);
        entityManager.persist(product4);
        entityManager.persist(wishlist1);
        entityManager.persist(wishlist2);
        entityManager.persist(newBasket);
        entityTransaction.commit();
        
        //asserts new ustomer persisted with positive id
        Assert.assertNotNull(newCustomer.getCustomerId());
        //asserts new orders persisted with positive id's
        Assert.assertNotNull(order1.getOrderId());
        Assert.assertNotNull(order2.getOrderId());
        //asserts new feedback persisted with positive id
        Assert.assertNotNull(feedback.getFeedbackId());
        //asserts new produts persisted with positive id's
        Assert.assertNotNull(product1.getProductId());
        Assert.assertNotNull(product2.getProductId());
        //asserts new basket persisted with positive id
        Assert.assertNotNull(newBasket.getBasketId());
        //asserts new wishlist persisted with positive id's
        Assert.assertNotNull(wishlist1.getWishlistId());
        Assert.assertNotNull(wishlist2.getWishlistId());

        System.out.println("A new customer with id " + newCustomer.getCustomerId() + " is persisted to spatil32_Customer table.");
        System.out.println("New orders are persisted to Orders table with CustomerId " + newCustomer.getCustomerId() + " as foreign key constraint.");
        System.out.println("A new feedback is persisted with customer id " + feedback.getCustomer().getCustomerId() + " as foreign key constraint.");
        System.out.println("A new product with is " + product1.getProductId() + " is persisted in product table.");
        System.out.println("A new product with is " + product2.getProductId() + " is persisted in product table.");
        System.out.println("A new basket is persisted to Basket table with customer id  " + newBasket.getCustomer().getCustomerId() + " as foreign key constraint.");
        System.out.println("A new wishlist is persisted with customer id " + newCustomer.getCustomerId() + " as foreign key constraint.");
    }

    /**
     *  Inserts new customers to Customer table.
     */
    @Test
    public void testInsertCustomers() {
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

    /**
     * Test to insert new extra customer with new orders, wishlists, feedback, basket with new products.
     * This function will persist all 6 entities for one customer. So indicates functionality of whole Online shopping system 
     */
    @Test
    public void testPersistTableData() {
        Customer newCustomer = new Customer("Immanuel", "Stephen", 30, 'M', "Chennai", "Immanuel.S@gmail.com", new GregorianCalendar(1986, 8, 13).getTime(), "12546", "immanuel", "stephen", 'N');
        Orders order1 = new Orders(newCustomer, 2000, new Date());
        Orders order2 = new Orders(newCustomer, 1000, new Date());
        newCustomer.getOrders().add(order1);
        newCustomer.getOrders().add(order2);
        Feedback feedback = new Feedback(newCustomer, new Date(), "Good Products", 8);
        newCustomer.setFeedback(feedback);
        Products product1 = new Products("Hair Dryer", new Date(), 'E', 1200, 10, 50, 35);
        Products product2 = new Products("Juicer", new Date(), 'E', 800, 10, 10, 5);
        Wishlist wishlist1 = new Wishlist(newCustomer, product1, new Date());
        Wishlist wishlist2 = new Wishlist(newCustomer, product1, new Date());
        newCustomer.getWishlist().add(wishlist1);
        newCustomer.getWishlist().add(wishlist2);
        Products product3 = new Products("Beginning J2EE", new Date(), 'B', 300, 10, 50, 20);
        Products product4 = new Products("Chicago Tourism", new Date(), 'B', 200, 10, 20, 15);

        Basket newBasket = new Basket(new Date(), 5, 50, newCustomer);
        newBasket.getProducts().add(product3);
        newBasket.getProducts().add(product4);
        newCustomer.setBasket(newBasket);

        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(order1);
        entityManager.persist(order2);
        entityManager.persist(feedback);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(wishlist1);
        entityManager.persist(wishlist2);
        entityManager.persist(product3);
        entityManager.persist(product4);
        entityManager.persist(newBasket);
        entityTransaction.commit();

        Assert.assertNotNull(newCustomer.getCustomerId());
        System.out.println("Immanuel persisted");
        System.out.println("A new customer with id " + newCustomer.getCustomerId() + " is persisted to spatil32_Customer table.");
        System.out.println("New orders are persisted to Orders table with CustomerId " + newCustomer.getCustomerId() + " as foreign key constraint.");
        System.out.println("A new feedback is persisted with customer id " + feedback.getCustomer().getCustomerId() + " as foreign key constraint.");
        System.out.println("A new wishlist is persisted to table with customer id " + newCustomer.getCustomerId() + " as foreign key constraint.");
        System.out.println("A new basket is persisted to Basket table with customer id  " + newBasket.getCustomer().getCustomerId() + " as foreign key constraint.");
    }

    /**
     * Reads list of all customers persisted.
     */
    @Test
    public void testReadAllCustomers() {
        System.out.println("IN READ ALL CUSTOMERS");
        System.out.println("***************************************************************");
        System.out.println(" LIST OF ALL THE CUSTOMERS PERSISTED ");
        System.out.println("***************************************************************");
        List<Customer> allCustomers = entityManager.createNamedQuery("Customer.seeAllCustomers", Customer.class).getResultList();
        //asserts new customers persisted by ensuring size of list as positive.
        Assert.assertTrue(allCustomers.size() > 0);
        System.out.println("*** List of all customers persisted ***");
        for (Customer customer : allCustomers) {
            System.out.println(customer.toString());
        }
    }

    /**
     * Persists new customer and updates its data
     */
    @Test
    public void testUpdateExistingCustomer() {
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
        //asserts the updated data matches with new values.
        Assert.assertEquals(updatedCustomer.getFirstName(), "Spongebob");
        Assert.assertEquals(updatedCustomer.getLastName(), "SquarePants");
        Assert.assertEquals(updatedCustomer.getAddress(), "Illinois");
        System.out.println("Customer with first name " + updateCustomer.getFirstName() + " is updated to " + updatedCustomer.getFirstName());
    }

    /**
     * Deletes the customer by finding it with name.
     * Deletes corrousponding orders, wishlists, feedback and basket for the same customer
     */
    @Test
    public void testDeleteCustomer() {
        System.out.println("IN DELETE CUSTOMER");
        Customer deleteCustomer = entityManager.createNamedQuery("Customer.findCustomerByName", Customer.class).setParameter("name", "Immanuel").getSingleResult();
        List<Orders> customerOrders = deleteCustomer.getOrders();
        Feedback deleteFeedback = deleteCustomer.getFeedback();
        for (Orders customerOrder : customerOrders) {
            entityTransaction.begin();
            entityManager.remove(customerOrder);
            entityTransaction.commit();
        }
        List<Wishlist> deleteWishList = deleteCustomer.getWishlist();
        for (Wishlist wishlist : deleteWishList) {
            entityTransaction.begin();
            entityManager.remove(wishlist);
            entityTransaction.commit();
        }
        Basket deleteBasket = deleteCustomer.getBasket();
        List<Products> basketProducts = deleteBasket.getProducts();
        for (Products basketProduct : basketProducts) {
            entityTransaction.begin();
            entityManager.remove(basketProduct);
            entityTransaction.commit();
        }

        entityTransaction.begin();
        entityManager.remove(deleteFeedback);
        entityManager.remove(deleteBasket);
        entityManager.remove(deleteCustomer);
        entityTransaction.commit();

        List<Customer> namedCustomers = entityManager.createNamedQuery("Customer.findCustomerByName", Customer.class).setParameter("name", "Immanuel").getResultList();
        if (namedCustomers.isEmpty()) {
            System.out.println("Customer with name " + deleteCustomer.getFirstName() + " deleted successfully");
            Assert.assertTrue("Customer with name " + deleteCustomer.getFirstName() + " deleted successfully", true);
        }
    }

    /**
     *  After each test, closes entity manager
     */
    @After
    public void afterEachTestMethod() {
        entityManager.close();
    }

    /**
     * after class, closes entityManagerFactory
     */
    @AfterClass
    public static void afterEachClass() {
        entityManagerFactory.close();
    }
}