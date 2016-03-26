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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

    @Temporal(TemporalType.DATE)
    private Date shoppingDate;
    private int numberOfItems;
    private int pricePerUnit;

    @OneToOne
    @JoinColumn(name = "customerId_fk")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "spatil32_basket_products",
            joinColumns = @JoinColumn(name = "BASKET_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    private List<Products> products = new ArrayList<>();

    /**
     *
     */
    public Basket() {
    }

    /**
     *
     * @param basketId
     * @param shoppingDate
     * @param numberOfItems
     * @param pricePerUnit
     */
    public Basket(int basketId, Date shoppingDate, int numberOfItems, int pricePerUnit) {
        this.basketId = basketId;
        this.shoppingDate = shoppingDate;
        this.numberOfItems = numberOfItems;
        this.pricePerUnit = pricePerUnit;
    }

    /**
     *
     * @param shoppingDate
     * @param numberOfItems
     * @param pricePerUnit
     * @param customer
     */
    public Basket(Date shoppingDate, int numberOfItems, int pricePerUnit, Customer customer) {
        this.shoppingDate = shoppingDate;
        this.numberOfItems = numberOfItems;
        this.pricePerUnit = pricePerUnit;
        this.customer = customer;
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

    /**
     *
     * @return
     */
    public Date getShoppingDate() {
        return shoppingDate;
    }

    /**
     *
     * @param shoppingDate
     */
    public void setShoppingDate(Date shoppingDate) {
        this.shoppingDate = shoppingDate;
    }

    /**
     *
     * @return
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }

    /**
     *
     * @param numberOfItems
     */
    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    /**
     *
     * @return
     */
    public int getPricePerUnit() {
        return pricePerUnit;
    }

    /**
     *
     * @param pricePerUnit
     */
    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    /**
     *
     * @return
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
/*
    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
*/

    /**
     *
     * @return
     */
    public List<Products> getProducts() {
        return products;
    }

    /**
     *
     * @param products
     */
    public void setProducts(List<Products> products) {
        this.products = products;
    }
}