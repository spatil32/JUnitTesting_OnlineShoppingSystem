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
 *
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
    @OneToMany(mappedBy = "customer")
    private List<Orders> orders = new ArrayList<>();
    @OneToOne(mappedBy = "customer")
    private Feedback feedback;

    @OneToMany(mappedBy = "customer")
    private List<Wishlist> wishlist = new ArrayList<>();

    @OneToOne(mappedBy = "customer")
    private Basket basket;

    /**
     *
     */
    public Customer() {
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param age
     * @param gender
     * @param address
     * @param email
     * @param birthDate
     * @param phoneNo
     * @param username
     * @param password
     * @param isAdmin
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
     * @param customerId
     * @param firstName
     * @param lastName
     * @param age
     * @param gender
     * @param address
     * @param email
     * @param birthDate
     * @param phoneNo
     * @param username
     * @param password
     * @param isAdmin
     * @param feedback
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
     *
     * @param firstName
     * @param lastName
     * @param age
     * @param gender
     * @param address
     * @param email
     * @param birthDate
     * @param phoneNo
     * @param username
     * @param password
     * @param isAdmin
     * @param basket
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
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     *
     * @return
     */
    public char getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     *
     * @return
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     *
     * @param phoneNo
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public char getIsAdmin() {
        return isAdmin;
    }

    /**
     *
     * @param birthDate
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     *
     * @param isAdmin
     */
    public void setIsAdmin(char isAdmin) {
        this.isAdmin = isAdmin;
    }    

    /**
     *
     * @return
     */
    public List<Orders> getOrders() {
        return orders;
    }

    /**
     *
     * @param orders
     */
    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    /**
     *
     * @return
     */
    public Feedback getFeedback() {
        return feedback;
    }

    /**
     *
     * @param feedback
     */
    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Customer Id = " + customerId + ", First Name = " + firstName + ", Last Name = " + lastName + ", Age = " + age + ", Gender = " + gender + ", Address = " + address + ", Email = " + email + ", Birth Date = " + birthDate + ", Phone No = " + phoneNo + ", username = " + username + ", password = " + password + ", isAdmin = " + isAdmin + '}';
    }

    /**
     *
     * @return
     */
    public List<Wishlist> getWishlist() {
        return wishlist;
    }

    /**
     *
     * @param wishlist
     */
    public void setWishlist(List<Wishlist> wishlist) {
        this.wishlist = wishlist;
    }

    /**
     *
     * @return
     */
    public Basket getBasket() {
        return basket;
    }

    /**
     *
     * @param basket
     */
    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}