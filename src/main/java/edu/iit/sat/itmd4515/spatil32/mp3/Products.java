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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "spatil32_Products")
public class Products 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String productName;
    @Temporal(TemporalType.DATE)
    private Date mfgDate;
    private char category;
    private int price;
    private int discount;
    private int totalQty;
    private int availableQty;

    public Products() {
    }

    public Products(int productId, String productName, Date mfgDate, char category, int price, int discount, int totalQty, int availableQty) {
        this.productId = productId;
        this.productName = productName;
        this.mfgDate = mfgDate;
        this.category = category;
        this.price = price;
        this.discount = discount;
        this.totalQty = totalQty;
        this.availableQty = availableQty;
    }


    /**
     * Get the value of productId
     *
     * @return the value of productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Set the value of productId
     *
     * @param productId new value of productId
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Date getMfgDate() {
        return mfgDate;
    }
    public void setMfgDate(Date mfgDate) {
        this.mfgDate = mfgDate;
    }
    public char getCategory() {
        return category;
    }
    public void setCategory(char category) {
        this.category = category;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public int getTotalQty() {
        return totalQty;
    }
    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }
    public int getAvailableQty() {
        return availableQty;
    }
    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    @Override
    public String toString() {
        return "Products{" + "productId=" + productId + ", productName=" + productName + ", mfgDate=" + mfgDate + ", category=" + category + ", price=" + price + ", discount=" + discount + ", totalQty=" + totalQty + ", availableQty=" + availableQty + '}';
    }
}