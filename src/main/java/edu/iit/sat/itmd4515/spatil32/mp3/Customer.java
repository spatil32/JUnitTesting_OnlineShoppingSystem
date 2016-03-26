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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Customer POJO consist of all data fields to be persisted, constructors, getters, setters and toString() method.
 * It also contains JPA mappings and persistence annotations to persist table in database.
 * @author Dell
 */
@Entity
@Table(name = "spatil32_Customer")
@NamedQueries({
    @NamedQuery(name = "Customer.seeAllCustomers", query = "select c from Customer c"),
    @NamedQuery(name = "Customer.findCustomerByName", query = "select c from Customer c where c.firstName = :name")
})
public class Customer 
{
    //primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String firstName;
    private String lastName;
    private int age;
    private char gender;
    private String address;
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    private String phoneNo;
    private String username;
    private String password;
    private char isAdmin;
    
    //foreign key mapping with order
    //One Customer with Many Orders
    @OneToMany(mappedBy = "customer")
    private List<Orders> orders = new ArrayList<>();
    
    //foreign key mapping with feedback
    //one customer one feedback
    @OneToOne(mappedBy = "customer")
    private Feedback feedback;

    //foreign key mapping with wishlist
    //one customer many wishlist
    @OneToMany(mappedBy = "customer")
    private List<Wishlist> wishlist = new ArrayList<>();

    //foreign key mapping with basket
    //one customer one basket
    @OneToOne(mappedBy = "customer")
    private Basket basket;

    /**
     * parameterless constructor
     */
    public Customer() {
    }

    /**
     *
     * @param firstName indicates first name
     * @param lastName indicates last name
     * @param age indicates age
     * @param gender indicates gender
     * @param address indicates address
     * @param email indicates email
     * @param birthDate indicates birth date
     * @param phoneNo indicates phone number
     * @param username indicates username
     * @param password indicates password
     * @param isAdmin indicates if user is admin or customer
     */
    public Customer(String firstName, String lastName, int age, char gender, String address, String email, Date birthDate, String phoneNo, String username, String password, char isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.birthDate = birthDate;
        this.phoneNo = phoneNo;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    /**
     *
     * @param customerId indicates customer id
     * @param firstName indicates first name
     * @param lastName indicates last name
     * @param age indicates age
     * @param gender indicates gender
     * @param address indicates address
     * @param email indicates email
     * @param birthDate indicates birth date
     * @param phoneNo indicates phone 
     * @param username indicates username
     * @param password indicates password
     * @param isAdmin indicates isadmin or customer
     * @param feedback indicates feedback
     */
    public Customer(int customerId, String firstName, String lastName, int age, char gender, String address, String email, Date birthDate, String phoneNo, String username, String password, char isAdmin, Feedback feedback) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.birthDate = birthDate;
        this.phoneNo = phoneNo;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.feedback = feedback;
    }

    /**
     * @param customerId indicates customer id
     * @param firstName indicates first name
     * @param lastName indicates last name
     * @param age indicates age
     * @param gender indicates gender
     * @param address indicates address
     * @param email indicates email
     * @param birthDate indicates birth date
     * @param phoneNo indicates phone 
     * @param username indicates username
     * @param password indicates password
     * @param isAdmin indicates isadmin or customer
     * @param basket indu=icates basket
     */
    public Customer(String firstName, String lastName, int age, char gender, String address, String email, Date birthDate, String phoneNo, String username, String password, char isAdmin, Basket basket) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.birthDate = birthDate;
        this.phoneNo = phoneNo;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.basket = basket;
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

    /**
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName sets firstname
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return lastname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName sets lastname
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @param age sets age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     *
     * @return gender
     */
    public char getGender() {
        return gender;
    }

    /**
     *
     * @param gender sets gender
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address sets address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email sets email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return birth date
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     *
     * @return phone number
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     *
     * @param phoneNo  sets phone
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username return username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password set password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return isadmin or customer
     */
    public char getIsAdmin() {
        return isAdmin;
    }

    /**
     *
     * @param birthDate set birth date
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     *
     * @param isAdmin set isadmin or customer
     */
    public void setIsAdmin(char isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     *
     * @return list of orders
     */
    public List<Orders> getOrders() {
        return orders;
    }

    /**
     *
     * @param orders sets new orders
     */
    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    /**
     *
     * @return feedback
     */
    public Feedback getFeedback() {
        return feedback;
    }

    /**
     *
     * @param feedback sets new feedback
     */
    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
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
     * @param wishlist adds new wishlist
     */
    public void setWishlist(List<Wishlist> wishlist) {
        this.wishlist = wishlist;
    }

    /**
     *
     * @return basket
     */
    public Basket getBasket() {
        return basket;
    }

    /**
     *
     * @param basket sets new basket with products
     */
    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "Customer Id = " + customerId + ", First Name = " + firstName + ", Last Name = " + lastName + ", Age = " + age + ", Gender = " + gender + ", Address = " + address + ", Email = " + email + ", Birth Date = " + birthDate + ", Phone No = " + phoneNo + ", username = " + username + ", password = " + password + ", isAdmin = " + isAdmin + '}';
    }
}