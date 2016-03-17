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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "spatil32_Basket")
public class Basket 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int basketId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId_fk")
    private int customerId;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId_fk")
    private int productId;
    @Temporal(TemporalType.DATE)
    private Date shoppingDate;
    private int numberOfItems;
    private int pricePerUnit;

    public Basket() {
    }

    public Basket(int basketId, Date shoppingDate, int numberOfItems, int pricePerUnit) {
        this.basketId = basketId;
        this.shoppingDate = shoppingDate;
        this.numberOfItems = numberOfItems;
        this.pricePerUnit = pricePerUnit;
    }

    public Basket(int basketId, int customerId, int productId, Date shoppingDate, int numberOfItems, int pricePerUnit) {
        this.basketId = basketId;
        this.customerId = customerId;
        this.productId = productId;
        this.shoppingDate = shoppingDate;
        this.numberOfItems = numberOfItems;
        this.pricePerUnit = pricePerUnit;
    }

    
    /**
     * Get the value of basketId
     *
     * @return the value of basketId
     */
    public int getBasketId() {
        return basketId;
    }



    /**
     * Set the value of basketId
     *
     * @param basketId new value of basketId
     */
    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public Date getShoppingDate() {
        return shoppingDate;
    }
    public void setShoppingDate(Date shoppingDate) {
        this.shoppingDate = shoppingDate;
    }
    public int getNumberOfItems() {
        return numberOfItems;
    }
    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
    public int getPricePerUnit() {
        return pricePerUnit;
    }
    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public String toString() {
        return "Basket{" + "basketId=" + basketId + ", customerId=" + customerId + ", productId=" + productId + ", shoppingDate=" + shoppingDate + ", numberOfItems=" + numberOfItems + ", pricePerUnit=" + pricePerUnit + '}';
    }
}