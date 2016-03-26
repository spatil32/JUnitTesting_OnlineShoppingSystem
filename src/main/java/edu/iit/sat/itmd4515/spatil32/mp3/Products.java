/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.mp3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Products POJO consist of all data fields to be persisted, constructors, getters, setters and toString() method.
 * It also contains JPA mappings and persistence annotations to persist table in database.
 * @author Dell
 */
@Entity
@Table(name = "spatil32_Products")
@NamedQueries({
    @NamedQuery(name = "Products.seeAllProducts", query = "select p from Products p"),
    @NamedQuery(name = "Products.findProductById", query = "select p from Products p where p.productId = :id")
})

public class Products 
{
    //primary key
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

    //foreign key mapping
    //one product many wishlists
    @OneToMany(mappedBy = "product")
    private List<Wishlist> wishlist = new ArrayList<>();

    //foreign key mapping
    //many products many baskets
    @ManyToMany(mappedBy = "products")
    private List<Basket> basket;

    /**
     * parameterless constructor
     */
    public Products() {
    }

    /**
     *
     * @param productId sets product id
     * @param productName sets product name
     * @param mfgDate sets manufacturing date
     * @param category sets category of product
     * @param price sets price
     * @param discount sets discount
     * @param totalQty sets total quantity
     * @param availableQty sets available quantity
     */
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
     *
     * @param productName sets product name
     * @param mfgDate sets manufacturing date
     * @param category sets category of product
     * @param price sets price
     * @param discount sets discount
     * @param totalQty sets total quantity
     * @param availableQty sets available quantity
     */
    public Products(String productName, Date mfgDate, char category, int price, int discount, int totalQty, int availableQty) {
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

    /**
     *
     * @return product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     *
     * @param productName sets product name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     *
     * @return manufactring date
     */
    public Date getMfgDate() {
        return mfgDate;
    }

    /**
     *
     * @param mfgDate sets manufacturing date
     */
    public void setMfgDate(Date mfgDate) {
        this.mfgDate = mfgDate;
    }

    /**
     *
     * @return category
     */
    public char getCategory() {
        return category;
    }

    /**
     *
     * @param category sets category
     */
    public void setCategory(char category) {
        this.category = category;
    }

    /**
     *
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @param price sets new price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     *
     * @return discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     *
     * @param discount sets discount
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     *
     * @return total quantity
     */
    public int getTotalQty() {
        return totalQty;
    }

    /**
     *
     * @param totalQty sets total quantity
     */
    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    /**
     *
     * @return available number of products
     */
    public int getAvailableQty() {
        return availableQty;
    }

    /**
     *
     * @param availableQty sets new count of available products
     */ 
    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    /**
     *
     * @return wishlists
     */
    public List<Wishlist> getWishlist() {
        return wishlist;
    }

    /**
     *
     * @param wishlist set new wishlist
     */
    public void setWishlist(List<Wishlist> wishlist) {
        this.wishlist = wishlist;
    }

    /**
     *
     * @return get list of baskets
     */
    public List<Basket> getBasket() {
        return basket;
    }

    /**
     *
     * @param basket sets new basket
     */
    public void setBasket(List<Basket> basket) {
        this.basket = basket;
    }
    
    @Override
    public String toString() {
        return "Products{" + "productId=" + productId + ", productName=" + productName + ", mfgDate=" + mfgDate + ", category=" + category + ", price=" + price + ", discount=" + discount + ", totalQty=" + totalQty + ", availableQty=" + availableQty + '}';
    }    
}