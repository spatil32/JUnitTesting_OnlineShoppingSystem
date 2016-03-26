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
 * WishlistTest class contains functionality to persist Wishlist entity in database table with help of javax.persistence API's.
 * @author Dell
 */
public class WishlistTest 
{
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    /**
     * parameterless constructor
     */
    public WishlistTest() {
    }

    /**
     * before class, a new persistence unit with name spatil32PU will be created.
     */
    @BeforeClass
    public static void beforeEachClass()
    {
        System.out.println("Inside @BeforeClass tag for WishlistTest class");
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
     * Persists new wishlist with newly created products for newly created customer.
     */
    @Test
    public void testInsertWishlist()
    {
        Customer newCustomer1 = new Customer("Shreyas", "Patil", 25, 'M', "Pune", "Shreyas.Patil@gmail.com", new GregorianCalendar(1991, 5, 16).getTime(), "12546", "admin", "admin", 'Y');
        Customer newCustomer2 = new Customer("Sarah", "Jones", 23, 'F', "Dallas", "Sarah.Jones@gmail.com", new GregorianCalendar(1993, 8, 21).getTime(), "56151", "sarah", "jones", 'N');

        Products product1 = new Products("Britannia", new Date(), 'K', 20, 5, 5, 2);
        Products product2 = new Products("Nestle", new Date(), 'K', 30, 10, 7, 5);
        Products product3 = new Products("Hair Dryer", new Date(), 'E', 1500, 30, 10, 3);
        Products product4 = new Products("Microwave", new Date(), 'E', 4000, 10, 7, 2);

        Wishlist wishlist1 = new Wishlist(newCustomer1, product1, new Date());
        Wishlist wishlist2 = new Wishlist(newCustomer1, product2, new Date());
        Wishlist wishlist3 = new Wishlist(newCustomer2, product3, new Date());
        Wishlist wishlist4 = new Wishlist(newCustomer2, product4, new Date());

        newCustomer1.getWishlist().add(wishlist1);
        newCustomer1.getWishlist().add(wishlist2);
        newCustomer2.getWishlist().add(wishlist3);
        newCustomer2.getWishlist().add(wishlist4);

        entityTransaction.begin();
        entityManager.persist(newCustomer1);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(wishlist1);
        entityManager.persist(wishlist2);

        entityManager.persist(newCustomer2);
        entityManager.persist(product3);
        entityManager.persist(product4);
        entityManager.persist(wishlist3);
        entityManager.persist(wishlist4);

        entityTransaction.commit();
        Assert.assertNotNull(wishlist1.getWishlistId());
        Assert.assertNotNull(wishlist2.getWishlistId());
        System.out.println("A new wishlist is persisted with customer id " + newCustomer1.getCustomerId() + " as foreign key constraint.");
        Assert.assertNotNull(wishlist3.getWishlistId());
        Assert.assertNotNull(wishlist4.getWishlistId());
        System.out.println("A new wishlist is persisted with customer id " + newCustomer2.getCustomerId() + " as foreign key constraint.");
    }

    /**
     *  Reads newly persisted wishlist details for newly persisted customers.
     */
    @Test
    public void testReadAllWishlist()
    {
        System.out.println("PERSISTING DATA BEFORE TO READ IT IN FURTHER STEPS...");
        Customer newCustomer1 = new Customer("Shreyas", "Patil", 25, 'M', "Pune", "Shreyas.Patil@gmail.com", new GregorianCalendar(1991, 5, 16).getTime(), "12546", "admin", "admin", 'Y');
        Customer newCustomer2 = new Customer("Sarah", "Jones", 23, 'F', "Dallas", "Sarah.Jones@gmail.com", new GregorianCalendar(1993, 8, 21).getTime(), "56151", "sarah", "jones", 'N');

        Products product1 = new Products("Britannia", new Date(), 'K', 20, 5, 5, 2);
        Products product2 = new Products("Nestle", new Date(), 'K', 30, 10, 7, 5);
        Products product3 = new Products("Hair Dryer", new Date(), 'E', 1500, 30, 10, 3);
        Products product4 = new Products("Microwave", new Date(), 'E', 4000, 10, 7, 2);
        
        Wishlist wishlist1 = new Wishlist(newCustomer1, product1, new Date());
        Wishlist wishlist2 = new Wishlist(newCustomer1, product2, new Date());
        Wishlist wishlist3 = new Wishlist(newCustomer2, product3, new Date());
        Wishlist wishlist4 = new Wishlist(newCustomer2, product4, new Date());

        newCustomer1.getWishlist().add(wishlist1);
        newCustomer1.getWishlist().add(wishlist2);
        newCustomer2.getWishlist().add(wishlist3);
        newCustomer2.getWishlist().add(wishlist4);

        entityTransaction.begin();
        entityManager.persist(newCustomer1);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(wishlist1);
        entityManager.persist(wishlist2);

        entityManager.persist(newCustomer2);
        entityManager.persist(product3);
        entityManager.persist(product4);
        entityManager.persist(wishlist3);
        entityManager.persist(wishlist4);

        entityTransaction.commit();
        Assert.assertNotNull(wishlist1.getWishlistId());
        Assert.assertNotNull(wishlist2.getWishlistId());
        System.out.println("A new wishlist is persisted with customer id " + newCustomer1.getCustomerId() + " as foreign key constraint.");
        Assert.assertNotNull(wishlist3.getWishlistId());
        Assert.assertNotNull(wishlist4.getWishlistId());
        System.out.println("A new wishlist is persisted with customer id " + newCustomer2.getCustomerId() + " as foreign key constraint.");

        
        System.out.println("IN READ ALL WISHLISTS");
        System.out.println("***************************************************************");
        System.out.println(" LIST OF ALL THE WISHLISTS ");
        System.out.println("***************************************************************");
        List<Wishlist> allWishlists = entityManager.createNamedQuery("Wishlist.seeAllWishlists", Wishlist.class).getResultList();
        Assert.assertTrue(allWishlists.size() > 0);
        System.out.println("*** List of all wishlists persisted ***");
        for (Wishlist allWishlist : allWishlists) 
        {
            System.out.println(allWishlist.toString());
        }
    }

    /**
     * Updates newly persisted wishlist.
     */
    @Test
    public void testUpdateExistingWishlist()
    {
        System.out.println("PERSISTING WISHLIST TO UPDATE IN FURTHER STEPS");
        Customer newCustomer = new Customer("Sarah", "Jones", 23, 'F', "Dallas", "Sarah.Jones@gmail.com", new GregorianCalendar(1993, 8, 21).getTime(), "56151", "sarah", "jones", 'N');
        Products product1 = new Products("Britannia", new Date(), 'K', 20, 5, 5, 2);
        Products product2 = new Products("Nestle", new Date(), 'K', 30, 10, 7, 5);
        Wishlist wishlist1 = new Wishlist(newCustomer, product1, new Date());
        Wishlist wishlist2 = new Wishlist(newCustomer, product2, new Date());
        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(wishlist1);
        entityManager.persist(wishlist2);
        entityTransaction.commit();
        Assert.assertNotNull(wishlist1.getWishlistId());
        Assert.assertNotNull(wishlist2.getWishlistId());
        System.out.println("A new wishlist is persisted with customer id " + newCustomer.getCustomerId() + " as foreign key constraint.");


        System.out.println("IN UPDATE WISHLIST");
        List<Wishlist> updateWishlist = entityManager.createNamedQuery("Wishlist.seeWishlistsByCustomerId", Wishlist.class).setParameter("id", newCustomer.getCustomerId()).getResultList();
        Assert.assertEquals(updateWishlist.get(0).getCustomer().getCustomerId(), newCustomer.getCustomerId());

        System.out.println("__________________________________________________________________");
        System.out.println("WISHLIST BEFORE UPDATE");
        for (Wishlist wishlist : updateWishlist) 
        {
            System.out.println(wishlist.toString());
        }

        System.out.println("__________________________________________________________________");
        
        for (Wishlist wishlist : updateWishlist) 
        {
            entityTransaction.begin();
            wishlist.setCreationDate(new GregorianCalendar(2016, 4, 10).getTime());
            entityTransaction.commit();
        }
        System.out.println("__________________________________________________________________");
        System.out.println("WISHLIST AFTER UPDATE");
        for (Wishlist wishlist : updateWishlist) 
        {
            System.out.println(wishlist.toString());
        }
        System.out.println("__________________________________________________________________");
        
        for (Wishlist wishlist : updateWishlist)
        {
            Assert.assertEquals(wishlist.getCreationDate(), new GregorianCalendar(2016, 4, 10).getTime());
        }
        System.out.println("Wishlist of customer with customer id " + newCustomer.getCustomerId() + " is updated successfully.");
    }

    /**
     * deletes wishlist for a customer by finding him by ID
     */
    @Test
    public void testDeleteWishlistByCustomerId()
    {
        System.out.println("IN DELETE WISHLIST");
        System.out.println("PERSISTING WISHLIST TO DELETE IN FURTHER STEPS");
        Customer newCustomer = new Customer("Sarah", "Jones", 23, 'F', "Dallas", "Sarah.Jones@gmail.com", new GregorianCalendar(1993, 8, 21).getTime(), "56151", "sarah", "jones", 'N');
        Products product1 = new Products("Britannia", new Date(), 'K', 20, 5, 5, 2);
        Products product2 = new Products("Nestle", new Date(), 'K', 30, 10, 7, 5);
        Wishlist wishlist1 = new Wishlist(newCustomer, product1, new Date());
        Wishlist wishlist2 = new Wishlist(newCustomer, product2, new Date());
        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(wishlist1);
        entityManager.persist(wishlist2);
        entityTransaction.commit();
        Assert.assertNotNull(wishlist1.getWishlistId());
        Assert.assertNotNull(wishlist2.getWishlistId());
        System.out.println("A new wishlist is persisted with customer id " + newCustomer.getCustomerId() + " as foreign key constraint.");
        
        List<Wishlist> deleteWishlist = entityManager.createNamedQuery("Wishlist.seeWishlistsByCustomerId", Wishlist.class).setParameter("id", newCustomer.getCustomerId()).getResultList();
        Assert.assertEquals(deleteWishlist.get(0).getCustomer().getCustomerId(), newCustomer.getCustomerId());
        
        for (Wishlist wishlist : deleteWishlist) 
        {
            entityTransaction.begin();
            entityManager.remove(wishlist);
            entityTransaction.commit();
        }
        
        List<Wishlist> deletedWishlist = entityManager.createNamedQuery("Wishlist.seeWishlistsByCustomerId", Wishlist.class).setParameter("id", newCustomer.getCustomerId()).getResultList();
        Assert.assertTrue(deletedWishlist.isEmpty());
        System.out.println("Wishlist of customer with customer id "+ newCustomer.getCustomerId()+" is deleted successfully.");
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