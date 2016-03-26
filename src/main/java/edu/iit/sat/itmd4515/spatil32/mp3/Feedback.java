/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.mp3;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Feedback POJO consist of all data fields to be persisted, constructors, getters, setters and toString() method.
 * It also contains JPA mappings and persistence annotations to persist table in database.
 * @author Dell
 */
@Entity
@Table(name = "spatil32_Feedback")
@NamedQueries({
    @NamedQuery(name = "Feedback.seeAllFeedbacks", query = "select f from Feedback f"),
    @NamedQuery(name = "Feedback.findFeedbackById", query = "select f from Feedback f where f.feedbackId = :id")
})

public class Feedback 
{
    //primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackId;

    //mapping with customer
    //one customer one feedback
    @OneToOne
    @JoinColumn(name = "customerId_fk")
    private Customer customer;

    @Temporal(TemporalType.DATE)
    private Date feedbackDate;

    private String description;
    private int rating;

    /**
     * parameterless constructor
     */
    public Feedback() {
    }

    /**
     *
     * @param feedbackId sets feedback id
     * @param feedbackDate sets date
     * @param description sets description
     * @param rating sets rating
     */
    public Feedback(int feedbackId, Date feedbackDate, String description, int rating) {
        this.feedbackId = feedbackId;
        this.feedbackDate = feedbackDate;
        this.description = description;
        this.rating = rating;
    }

    /**
     *
     * @param feedbackId sets feedback id
     * @param customer sets customer for feedback
     * @param feedbackDate sets date
     * @param description sets description
     * @param rating sets rating
    */
    public Feedback(int feedbackId, Customer customer, Date feedbackDate, String description, int rating) {
        this.feedbackId = feedbackId;
        this.customer = customer;
        this.feedbackDate = feedbackDate;
        this.description = description;
        this.rating = rating;
    }

    /**
     *
     * @param customer sets customer for feedback
     * @param feedbackDate sets date
     * @param description sets description
     * @param rating sets rating
     */
    public Feedback(Customer customer, Date feedbackDate, String description, int rating) {
        this.customer = customer;
        this.feedbackDate = feedbackDate;
        this.description = description;
        this.rating = rating;
    }

    /**
     * Get the value of feedbackId
     *
     * @return the value of feedbackId
     */
    public int getFeedbackId() {
        return feedbackId;
    }

    /**
     * Set the value of feedbackId
     *
     * @param feedbackId new value of feedbackId
     */
    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    /**
     *
     * @return feedback date
     */
    public Date getFeedbackDate() {
        return feedbackDate;
    }

    /**
     *
     * @param feedbackDate sets feedback date
     */
    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description sets description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return rating
     */
    public int getRating() {
        return rating;
    }

    /**
     *
     * @param rating sets rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     *
     * @return customer with feedback
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *
     * @param customer set customer for feedback
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Feedback{" + "feedbackId=" + feedbackId + ", customer=" + customer + ", feedbackDate=" + feedbackDate + ", description=" + description + ", rating=" + rating + '}';
    }
}
