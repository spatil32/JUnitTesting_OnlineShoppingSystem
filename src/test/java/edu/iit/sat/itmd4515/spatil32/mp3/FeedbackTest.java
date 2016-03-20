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
public class FeedbackTest 
{
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public FeedbackTest() {
    }

    @BeforeClass
    public static void beforeEachClass()
    {
        System.out.println("Inside @BeforeClass tag for FeedbackTest class");
        entityManagerFactory = Persistence.createEntityManagerFactory("spatil32PU");
        System.out.println("entityManagerFactory created..");
    }
    
    @Before
    public void beforeEachTestMethod()
    {
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();                
/*        
        Customer newCustomer = new Customer("Paul", "Jacob", 20, 'M', "Oregaon", "Paul.Jacob@gmail.com", new GregorianCalendar(1996, 3, 3).getTime(), "84529", "paul", "jacob", 'N');
        Feedback newFeedback = new Feedback(newCustomer, new Date(), "Nice discounts", 9);
        newCustomer.setFeedback(newFeedback);
        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(newFeedback);
        entityTransaction.commit();

        Assert.assertNotNull(newFeedback.getFeedbackId());
       System.out.println("New feedback is persisted to Feedback table with customer Id " + newCustomer.getCustomerId() + " as foreign key constraint.");
*/
    }
    
    @Test
    public void testInsertFeedback()
    {
        System.out.println("In test insert feedback.");
        Customer newCustomer1 = new Customer("Irwin", "Selvam", 22, 'M', "Mumbai", "Irwin.Selvam@gmail.com", new GregorianCalendar(1994, 7, 6).getTime(), "84256", "irwin", "selvam", 'N');
        Feedback newFeedback1 = new Feedback(newCustomer1, new Date(), "Good brands", 6);
        newCustomer1.setFeedback(newFeedback1);

        Customer newCustomer2 = new Customer("Jennifer", "Yale", 26, 'F', "Illinois", "Jennifer.Yale@gmail.com", new GregorianCalendar(1990, 12, 12).getTime(), "36243", "jennifer", "yale", 'N');
        Feedback newFeedback2 = new Feedback(newCustomer2, new Date(), "Nice discounts", 9);
        newCustomer2.setFeedback(newFeedback2);
        
        entityTransaction.begin();
        entityManager.persist(newCustomer1);
        entityManager.persist(newFeedback1);
        entityManager.persist(newCustomer2);
        entityManager.persist(newFeedback2);
        entityTransaction.commit();

       Assert.assertNotNull(newFeedback1.getFeedbackId());
       System.out.println("New feedback is persisted to Feedback table with customer Id " + newCustomer1.getCustomerId() + " as foreign key constraint.");
       Assert.assertNotNull(newFeedback2.getFeedbackId());
       System.out.println("New feedback is persisted to Feedback table with customer Id " + newCustomer2.getCustomerId() + " as foreign key constraint.");
    }

    @Test
    public void testReadAllFeedbacks()
    {
        System.out.println("In test read all feedbacks.");
        System.out.println("***************************************************************");
        System.out.println(" LIST OF ALL THE FEEDBACKS PERSISTED ");
        System.out.println("***************************************************************");
        List<Feedback> allFeedbacks = entityManager.createNamedQuery("Feedback.seeAllFeedbacks", Feedback.class).getResultList();
        Assert.assertTrue(allFeedbacks.size() > 0);
        System.out.println("*** List of all feedbacks persisted ***");
        for (Feedback allFeedback : allFeedbacks) 
        {
            System.out.println(allFeedback.toString());            
        }
    }
    
    @Test
    public void testUpdateExistingFeedback()
    {
        System.out.println("PERSISTING A CUSTOMER AND FEEDBACK BEFORE TO UPDATE IN FURTHER STEPS.");
        Customer newCustomer = new Customer("Joy", "Dsouza", 23, 'M', "Delhi", "Joy.Dsouza@gmail.com", new GregorianCalendar(1993, 2, 2).getTime(), "11489", "joy", "dsouza", 'N');
        Feedback newFeedback = new Feedback(newCustomer, new Date(), "Include sport section", 5);
        newCustomer.setFeedback(newFeedback);
        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(newFeedback);
        entityTransaction.commit();

        Assert.assertNotNull(newFeedback.getFeedbackId());
        System.out.println("New feedback is persisted to Feedback table with customer Id " + newCustomer.getCustomerId() + " as foreign key constraint.");
        
        System.out.println("IN UPDATE FEEDBACK");
        Feedback updateFeedback = entityManager.createNamedQuery("Feedback.findFeedbackById", Feedback.class).setParameter("id", newFeedback.getFeedbackId()).getSingleResult();
        Assert.assertEquals(updateFeedback.getFeedbackId(), newFeedback.getFeedbackId());
        
        System.out.println("__________________________________________________________________");
        System.out.println("FEEDBACK BEFORE UPDATE");
        System.out.println(updateFeedback.toString());
        System.out.println("__________________________________________________________________");
        
        entityTransaction.begin();
        updateFeedback.setDescription("Good sports section");
        updateFeedback.setRating(6);
        updateFeedback.setFeedbackDate(new GregorianCalendar(2016, 31, 3).getTime());
        entityTransaction.commit();
        
        System.out.println("__________________________________________________________________");
        System.out.println("FEEDBACK AFTER UPDATE");
        System.out.println(updateFeedback.toString());
        System.out.println("__________________________________________________________________");
        
        Feedback updatedFeedback = entityManager.createNamedQuery("Feedback.findFeedbackById", Feedback.class).setParameter("id", newFeedback.getFeedbackId()).getSingleResult();
        Assert.assertEquals("Good sports section", updatedFeedback.getDescription());
        Assert.assertEquals(6, updatedFeedback.getRating());
        Assert.assertEquals(updatedFeedback.getFeedbackDate(), new GregorianCalendar(2016, 31, 3).getTime());
        System.out.println("Feedback with feedback id " + updateFeedback.getFeedbackId() + " is updated.");
    }

    @Test
    public void testDeleteFeedbackById()
    {
        System.out.println("PERSISTING A CUSTOMER AND FEEDBACK BEFORE TO DELETE IN FURTHER STEPS.");
        Customer newCustomer = new Customer("Paul", "Jacob", 20, 'M', "Oregaon", "Paul.Jacob@gmail.com", new GregorianCalendar(1996, 3, 3).getTime(), "84529", "paul", "jacob", 'N');
        Feedback newFeedback = new Feedback(newCustomer, new Date(), "Nice discounts", 9);
        newCustomer.setFeedback(newFeedback);
        entityTransaction.begin();
        entityManager.persist(newCustomer);
        entityManager.persist(newFeedback);
        entityTransaction.commit();

        Assert.assertNotNull(newFeedback.getFeedbackId());
        System.out.println("New feedback is persisted to Feedback table with customer Id " + newCustomer.getCustomerId() + " as foreign key constraint.");
        
        System.out.println("***************************************************************");
        System.out.println("IN DELETE FEEDBACK BY ID");
        System.out.println("***************************************************************");
        Feedback deleteFeedback = entityManager.createNamedQuery("Feedback.findFeedbackById", Feedback.class).setParameter("id", newFeedback.getFeedbackId()).getSingleResult();
        entityTransaction.begin();
        entityManager.remove(deleteFeedback);
        entityTransaction.commit();
        List<Feedback> deletedFeedback = entityManager.createNamedQuery("Feedback.findFeedbackById", Feedback.class).setParameter("id", newFeedback.getFeedbackId()).getResultList();
        Assert.assertTrue(deletedFeedback.isEmpty());
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