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
public class ProductsTest 
{
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public ProductsTest() {
    }

    @BeforeClass
    public static void beforeEachClass()
    {
        System.out.println("Inside @BeforeClass tag for ProductsTest class");
        entityManagerFactory = Persistence.createEntityManagerFactory("spatil32PU");
        System.out.println("entityManagerFactory created..");
    }
    
    @Before
    public void beforeEachTestMethod()
    {
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();                
    }
    
    @Test
    public void testInsertProduct()
    {
        System.out.println("In test insert products.");
        Products newProduct1 = new Products("LED TV", new Date(), 'E', 5000, 10, 150, 120);
        Products newProduct2 = new Products("BOSE Speakers", new Date(), 'E', 800, 20, 50, 20);

        entityTransaction.begin();
        entityManager.persist(newProduct1);
        entityManager.persist(newProduct2);        
        entityTransaction.commit();

       Assert.assertNotNull(newProduct1.getProductId());
       Assert.assertNotNull(newProduct2.getProductId());

       System.out.println("New product is persisted to Products table with product Id " + newProduct1.getProductId());
       System.out.println("New product is persisted to Products table with product Id " + newProduct2.getProductId());
    }

    @Test
    public void testReadAllProducts()
    {
        System.out.println("PERSISTING DATA BEFORE TO READ IN FURTHER STEPS");
        Products newProduct1 = new Products("CANON DSLR", new Date(), 'E', 10000, 25, 50, 10);
        Products newProduct2 = new Products("Amazon Kindle", new Date(), 'E', 800, 10, 100, 30);

        entityTransaction.begin();
        entityManager.persist(newProduct1);
        entityManager.persist(newProduct2);        
        entityTransaction.commit();

       Assert.assertNotNull(newProduct1.getProductId());
       Assert.assertNotNull(newProduct2.getProductId());

       System.out.println("New product is persisted to Products table with product Id " + newProduct1.getProductId());
       System.out.println("New product is persisted to Products table with product Id " + newProduct2.getProductId());

        System.out.println("In test read all products.");
        System.out.println("***************************************************************");
        System.out.println(" LIST OF ALL THE PRODUCTS PERSISTED ");
        System.out.println("***************************************************************");
        List<Products> allProducts = entityManager.createNamedQuery("Products.seeAllProducts", Products.class).getResultList();
        Assert.assertTrue(allProducts.size() > 0);
        System.out.println("*** List of all products persisted ***");
        for (Products allProduct : allProducts) 
        {
            System.out.println(allProduct.toString());
        }
    }
    
    @Test
    public void testUpdateExistingFeedback()
    {
        System.out.println("PERSISTING DATA BEFORE TO UPDATE IN FURTHER STEPS");
        Products newProduct1 = new Products("CANON DSLR", new Date(), 'E', 10000, 25, 50, 10);

        entityTransaction.begin();
        entityManager.persist(newProduct1);
        entityTransaction.commit();

       Assert.assertNotNull(newProduct1.getProductId());

       System.out.println("New product is persisted to Products table with product Id " + newProduct1.getProductId());
        
        System.out.println("IN UPDATE PRODUCT");
        Products updateprProduct = entityManager.createNamedQuery("Products.findProductById", Products.class).setParameter("id", newProduct1.getProductId()).getSingleResult();
        Assert.assertEquals(updateprProduct.getProductId(), newProduct1.getProductId());
        
        System.out.println("__________________________________________________________________");
        System.out.println("PRODUCT BEFORE UPDATE");
        System.out.println(updateprProduct.toString());
        System.out.println("__________________________________________________________________");
        
        entityTransaction.begin();
        updateprProduct.setDiscount(50);
        updateprProduct.setTotalQty(20);
        updateprProduct.setAvailableQty(12);
        updateprProduct.setProductName("SONY DSLR");
        entityTransaction.commit();
        
        System.out.println("__________________________________________________________________");
        System.out.println("PRODUCT AFTER UPDATE");
        System.out.println(updateprProduct.toString());
        System.out.println("__________________________________________________________________");
        
        Products updatedProduct = entityManager.createNamedQuery("Products.findProductById", Products.class).setParameter("id", newProduct1.getProductId()).getSingleResult();
        Assert.assertEquals(50, updatedProduct.getDiscount());
        Assert.assertEquals(20, updatedProduct.getTotalQty());
        Assert.assertEquals(12, updatedProduct.getAvailableQty());
        Assert.assertEquals("SONY DSLR", updatedProduct.getProductName());
        System.out.println("Product with product id " + newProduct1.getProductId() + " is updated.");
    }

    @Test
    public void testDeleteFeedbackById()
    {
        System.out.println("PERSISTING PRODUCT TO DELETE IN FURTHER STEPS");
        Products newProduct1 = new Products("Life of Pi", new Date(), 'B', 300, 25, 20, 5);
    
        entityTransaction.begin();
        entityManager.persist(newProduct1);
        entityTransaction.commit();

       Assert.assertNotNull(newProduct1.getProductId());
    
       System.out.println("New product is persisted to Products table with product Id " + newProduct1.getProductId());
        
        System.out.println("***************************************************************");
        System.out.println("IN DELETE PRODUCT BY ID");
        System.out.println("***************************************************************");
        Products deleteProduct = entityManager.createNamedQuery("Products.findProductById", Products.class).setParameter("id", newProduct1.getProductId()).getSingleResult();
        entityTransaction.begin();
        entityManager.remove(deleteProduct);
        entityTransaction.commit();
        List<Products> deletedProducts = entityManager.createNamedQuery("Products.findProductById", Products.class).setParameter("id", newProduct1.getProductId()).getResultList();
        Assert.assertTrue(deletedProducts.isEmpty());
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
