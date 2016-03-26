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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Orders POJO consist of all data fields to be persisted, constructors, getters, setters and toString() method.
 * It also contains JPA mappings and persistence annotations to persist table in database.
 * @author Dell
 */
@Entity
@Table(name = "spatil32_Orders")
@NamedQueries({
    @NamedQuery(name = "Orders.seeAllOrders", query = "select o from Orders o"),
    @NamedQuery(name = "Orders.findOrdersById", query = "select o from Orders o where o.orderId = :id")
})

public class Orders 
{
    //primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    
    //mapping with customer
    //one customer many orders
    @ManyToOne
    @JoinColumn(name = "customerId_fk")
    private Customer customer;
    private int totalBillAmount;
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    /**
     * parameterless constructor
     */
    public Orders() {
    }

    /**
     *
     * @param totalBillAmount sets total bill
     * @param deliveryDate sets delivery date
     */
    public Orders(int totalBillAmount, Date deliveryDate) {
        this.totalBillAmount = totalBillAmount;
        this.deliveryDate = deliveryDate;
    }

    /**
     *
     * @param customer sets customer for orders
     * @param totalBillAmount sets total bill
     * @param deliveryDate sets delivery date
     */
    public Orders(Customer customer, int totalBillAmount, Date deliveryDate) {
        this.customer = customer;
        this.totalBillAmount = totalBillAmount;
        this.deliveryDate = deliveryDate;
    }

    /**
     *
     * @param orderId sets order id
     * @param customer sets customer for orders
     * @param totalBillAmount sets total bill
     * @param deliveryDate sets delivery date
     */
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

    /**
     *
     * @param orderId sets order id
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     *
     * @return total bill amount
     */
    public int getTotalBillAmount() {
        return totalBillAmount;
    }

    /**
     *
     * @param totalBillAmount sets new bill
     */
    public void setTotalBillAmount(int totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }

    /**
     *
     * @return delivery date
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     *
     * @param deliveryDate sets delivery date
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     *
     * @return customer for order
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *
     * @param customer sets customer to order
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderId=" + orderId + ", totalBillAmount=" + totalBillAmount + ", deliveryDate=" + deliveryDate + '}';
    }
}
