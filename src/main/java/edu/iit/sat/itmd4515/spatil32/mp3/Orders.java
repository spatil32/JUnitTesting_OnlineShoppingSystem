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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dell
*/
@Entity
@Table(name = "spatil32_Orders")
public class Orders 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @ManyToOne
    @JoinColumn(name = "customerId_fk")
    private Customer customer;
    private int totalBillAmount;
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    public Orders() {
    }
    
    public Orders(int totalBillAmount, Date deliveryDate) {
        this.totalBillAmount = totalBillAmount;
        this.deliveryDate = deliveryDate;
    }

    public Orders(Customer customer, int totalBillAmount, Date deliveryDate) {
        this.customer = customer;
        this.totalBillAmount = totalBillAmount;
        this.deliveryDate = deliveryDate;
    }

    public Orders(int orderId, Customer customer, int totalBillAmount, Date deliveryDate) {
        this.orderId = orderId;
        this.customer = customer;
        this.totalBillAmount = totalBillAmount;
        this.deliveryDate = deliveryDate;
    }


    /**
     * Get the value of orderId
     *
     * @return the value of orderId
     */
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTotalBillAmount() {
        return totalBillAmount;
    }
    public void setTotalBillAmount(int totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }
    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderId=" + orderId + ", totalBillAmount=" + totalBillAmount + ", deliveryDate=" + deliveryDate + '}';
    }
}