/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.mp3;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "spatil32_Feedback")
public class Feedback 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId_fk")
    private int customerId;
    
    @Temporal(TemporalType.DATE)
    private Date feedbackDate;
    
    private String description;

    private int rating;

    public Feedback() {
    }

    public Feedback(int feedbackId, Date feedbackDate, String description, int rating) {
        this.feedbackId = feedbackId;
        this.feedbackDate = feedbackDate;
        this.description = description;
        this.rating = rating;
    }

    public Feedback(int feedbackId, int customerId, Date feedbackDate, String description, int rating) {
        this.feedbackId = feedbackId;
        this.customerId = customerId;
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
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public Date getFeedbackDate() {
        return feedbackDate;
    }
    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Feedback{" + "feedbackId=" + feedbackId + ", customerId=" + customerId + ", feedbackDate=" + feedbackDate + ", description=" + description + ", rating=" + rating + '}';
    }
}