/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.mp3;

import java.util.ArrayList;
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
 * BasketTest class contains functionality to persist Basket entity in database table with help of javax.persistence API's.
 * @author Dell
 */
public class BasketTest 
{
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    /**
     * parameterless constructor
     */
    public BasketTest() {
    }

    /**
     * before class, a new persistence unit with name spatil32PU will be created.
     */
    @BeforeClass
    public static void beforeEachClass() {
        System.out.println("Inside @BeforeClass tag for BasketTest class");
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
     * Test method to insert basket details.
     * At first, new customer will be persisted. Then new products will be persisted and added to new basket.
     */
    @Test
    public void testInsertBasketDetails() 
    {
        System.out.println("Persisting New customer");
        Customer newCustomer = new Customer("Shreyas", "Patil", 25, 'M', "Pune", "Shreyas.Patil@gmail.com", new GregorianCalendar(1991, 5, 16).getTime(), "12546", "admin", "admin", 'Y');
        Products product1 = new Products("Britannia", new Date(), 'K', 20, 5, 5, 2);
        Products product2 = new Products("Nestle", new Date(), 'K', 30, 10, 7, 5);
        List<Products> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        Basket newBasket = new Basket(new Date(), 2, 30, newCustomer);
        for (Products product : products) {
            newBasket.addProducts(product);
        }
    
        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(newBasket);
        entityManager.flush();
        entityTransaction.commit();
        
        //Asserts that new basket is inserted with positive basket id
        Assert.assertNotNull(newBasket.getBasketId());
    }

    /**
     * Reads all data in basket.
     * At first, new customer and products will be persisted. Then new baskets will be assigned with products.
     */
    @Test
    public void testReadAllBasketItems() 
    {
        System.out.println("**************************************************************************");
        System.out.println("Persisting basket of the new customer before to read in further steps");
        Customer newCustomer = new Customer("Shreyas", "Patil", 25, 'M', "Pune", "Shreyas.Patil@gmail.com", new GregorianCalendar(1991, 5, 16).getTime(), "12546", "admin", "admin", 'Y');
        Products product1 = new Products("iPhone 6S", new Date(), 'E', 1200, 20, 20, 3);
        Products product2 = new Products("iPhone 6", new Date(), 'E', 1000, 10, 7, 2);
        List<Products> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        Basket newBasket = new Basket(new Date(), 2, 2, newCustomer);
        for (Products product : products) {
            newBasket.addProducts(product);
        }

        Customer newCustomer1 = new Customer("Immanuel", "Stephen", 30, 'M', "Chennai", "Immanuel.S@gmail.com", new GregorianCalendar(1986, 8, 13).getTime(), "12546", "immanuel", "stephen", 'N');
        Products product3 = new Products("Beginning J2EE", new Date(), 'B', 300, 10, 50, 20);
        Products product4 = new Products("Chicago Tourism", new Date(), 'B', 200, 10, 20, 15);
        List<Products> products1 = new ArrayList<>();
        products1.add(product3);
        products1.add(product4);
        Basket newBasket1 = new Basket(new Date(), 2, 2, newCustomer1);
        for (Products product : products1) {
            newBasket.addProducts(product);
        }

        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(newBasket);
        entityManager.persist(newCustomer1);
        entityManager.persist(product3);
        entityManager.persist(product4);
        entityManager.persist(newBasket1);
        entityManager.flush();
        entityTransaction.commit();
        
        System.out.println("*********************************************************************");
        System.out.println("IN READ ALL BASKETS OF CUSTOMERS");
        System.out.println("***************************************************************");
        System.out.println(" LIST OF ALL THE BASKETS PERSISTED AND PRODUCTS IN IT ");
        System.out.println("***************************************************************");
        List<Basket> allBaskets = entityManager.createNamedQuery("Basket.seeAllCustomersBaskets", Basket.class).getResultList();
        //Asserts that baskets are persisted in database.
        Assert.assertTrue(allBaskets.size() > 0);
        
        System.out.println("*** List of all baskets persisted ***");
        for (Basket allBasket : allBaskets) 
        {
            System.out.println(allBasket.toString());
            System.out.println("List of all products in basket..");
            List<Products> productsInBasket = allBasket.getProducts();
            for (Products product : productsInBasket) 
            {
                System.out.println(product.toString());
            }
        }
    }

    /**
     * Updates existing basket.
     * At first, new customer with basket and products will be persisted and then will be updated.
     */
    @Test
    public void testUpdateExistingBasket() 
    {
        System.out.println("*************************************************************************");
        System.out.println("Persisting basket of the new customer before updating in further steps");
        Customer newCustomer = new Customer("Shreyas", "Patil", 25, 'M', "Pune", "Shreyas.Patil@gmail.com", new GregorianCalendar(1991, 5, 16).getTime(), "12546", "admin", "admin", 'Y');
        Products product1 = new Products("iPhone 6S", new Date(), 'E', 1200, 20, 20, 3);
        Products product2 = new Products("iPhone 6", new Date(), 'E', 1000, 10, 7, 2);
        List<Products> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        Basket newBasket = new Basket(new Date(), 2, 2, newCustomer);
        for (Products product : products) {
            newBasket.addProducts(product);
        }

        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(newBasket);
        entityManager.flush();
        entityTransaction.commit();
        System.out.println("***********************************************************************");

        System.out.println("IN UPDATE BASKET");
        Basket updateBasket = entityManager.createNamedQuery("Basket.findBasketByCustomerId", Basket.class).setParameter("id", newCustomer.getCustomerId()).getSingleResult();

        //asserts that received basket belongs to the new persisted customer
        Assert.assertEquals(updateBasket.getBasketId(), newBasket.getBasketId());
        
        System.out.println("__________________________________________________________________");
        System.out.println("BASKET INFORMATION BEFORE UPDATE");
        System.out.println(updateBasket.toString());
        System.out.println("__________________________________________________________________");
        entityTransaction.begin();
        updateBasket.setNumberOfItems(4);
        updateBasket.setPricePerUnit(1000);
        entityTransaction.commit();

        System.out.println("__________________________________________________________________");
        System.out.println("BASKET INFORMATION AFTER UPDATE");
        System.out.println(updateBasket.toString());
        System.out.println("__________________________________________________________________");

        Basket updatedBasket = entityManager.createNamedQuery("Basket.findBasketByCustomerId", Basket.class).setParameter("id", newCustomer.getCustomerId()).getSingleResult();
        //Asserts than updated values are correct to newly set values
        Assert.assertEquals(4, updatedBasket.getNumberOfItems());
        Assert.assertEquals(1000, updatedBasket.getPricePerUnit());
        System.out.println("Basket with basket Id " + updateBasket.getBasketId() + " is updated.");
    }

    /**
     * Deletes the basket of the customer.
     * At first new customer is persisted with products and basket and the same is deleted later.
     */
    @Test
    public void testDeleteBasket() 
    {
        System.out.println("************************************************************************");
        System.out.println("Persisting basket of the new customer before deleting in further steps");
        Customer newCustomer = new Customer("Shreyas", "Patil", 25, 'M', "Pune", "Shreyas.Patil@gmail.com", new GregorianCalendar(1991, 5, 16).getTime(), "12546", "admin", "admin", 'Y');
        Products product1 = new Products("iPhone 6S", new Date(), 'E', 1200, 20, 20, 3);
        Products product2 = new Products("iPhone 6", new Date(), 'E', 1000, 10, 7, 2);
        List<Products> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        Basket newBasket = new Basket(new Date(), 2, 2, newCustomer);
        for (Products product : products) {
            newBasket.addProducts(product);
        }

        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(newBasket);
        entityManager.flush();
        entityTransaction.commit();

        System.out.println("*************************************************************************");
        System.out.println("IN DELETE BASKET");
        Basket deleteBasket = entityManager.createNamedQuery("Basket.findBasketByCustomerId", Basket.class).setParameter("id", newCustomer.getCustomerId()).getSingleResult();

        Assert.assertEquals(deleteBasket.getBasketId(), newBasket.getBasketId());
        List<Products> basketProducts = deleteBasket.getProducts();
        for (Products basketProduct : basketProducts) 
        {
            entityTransaction.begin();
            entityManager.remove(basketProduct);
            entityTransaction.commit();
        }
        System.out.println("All products from basket with basket id " + deleteBasket.getBasketId() + " are deleted.");
        entityTransaction.begin();
        entityManager.remove(deleteBasket);
        entityTransaction.commit();

        List<Basket> deletedBasket = entityManager.createNamedQuery("Basket.findBasketByCustomerId", Basket.class).setParameter("id", newCustomer.getCustomerId()).getResultList();
        if (deletedBasket.isEmpty()) {
            System.out.println("Basket for customer with customer id " + deleteBasket.getCustomer().getCustomerId() + " is deleted.");
            //asserts that the basket is deleted by comparing list size to zero.
            Assert.assertTrue("Basket for customer with customer id " + deleteBasket.getCustomer().getCustomerId() + " is deleted.", true);
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