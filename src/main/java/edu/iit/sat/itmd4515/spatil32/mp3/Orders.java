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
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId_fk")
    private int customerId;
    
    private int totalBillAmount;

    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    public Orders() {
    }

    public Orders(int orderId, int customerId, int totalBillAmount, Date deliveryDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalBillAmount = totalBillAmount;
        this.deliveryDate = deliveryDate;
    }

    public Orders(int orderId, int totalBillAmount, Date deliveryDate) {
        this.orderId = orderId;
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

    /**
     * Get the value of customerId
     *
     * @return the value of customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Set the value of customerId
     *
     * @param customerId new value of customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    @Override
    public String toString() {
        return "Orders{" + "orderId=" + orderId + ", customerId=" + customerId + ", totalBillAmount=" + totalBillAmount + ", deliveryDate=" + deliveryDate + '}';
    }
}
