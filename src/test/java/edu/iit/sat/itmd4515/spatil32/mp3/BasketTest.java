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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dell
 */
public class BasketTest {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    /**
     *
     */
    public BasketTest() {
    }

    /**
     *
     */
    @BeforeClass
    public static void beforeEachClass()
    {
        System.out.println("Inside @BeforeClass tag for BasketTest class");
        entityManagerFactory = Persistence.createEntityManagerFactory("spatil32PU");
        System.out.println("entityManagerFactory created..");
    }
    
    /**
     *
     */
    @Before
    public void beforeEachTestMethod()
    {
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();                
    }
    
    /**
     *
     */
    @Test
    public void testInsertBasketDetails()
    {
        System.out.println("Persisting shreyas");
        Customer newCustomer = new Customer("Shreyas", "Patil", 25, 'M', "Pune", "Shreyas.Patil@gmail.com", new GregorianCalendar(1991, 5, 16).getTime(), "12546", "admin", "admin", 'Y');
        Products product1 = new Products("Britannia", new Date(), 'K', 20, 5, 5, 2);
        Products product2 = new Products("Nestle", new Date(), 'K', 30, 10, 7, 5);
        List<Products> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        
        Basket newBasket = new Basket(new Date(), 2, 30, newCustomer);
        newBasket.setProducts(products);
        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(newBasket);
        entityTransaction.commit();
        
    }
    
    /**
     *
     */
    @After
    public void afterEachTestMethod()
    {
        entityManager.close();
    }
    
    /**
     *
     */
    @AfterClass
    public static void afterEachClass()
    {
        entityManagerFactory.close();
    }
    
}
