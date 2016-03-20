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


    public Customer() {
    }

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
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public char getIsAdmin() {
        return isAdmin;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public void setIsAdmin(char isAdmin) {
        this.isAdmin = isAdmin;
    }    

    public List<Orders> getOrders() {
        return orders;
    }
    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
    public Feedback getFeedback() {
        return feedback;
    }
    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Customer Id = " + customerId + ", First Name = " + firstName + ", Last Name = " + lastName + ", Age = " + age + ", Gender = " + gender + ", Address = " + address + ", Email = " + email + ", Birth Date = " + birthDate + ", Phone No = " + phoneNo + ", username = " + username + ", password = " + password + ", isAdmin = " + isAdmin + '}';
    }

    public List<Wishlist> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<Wishlist> wishlist) {
        this.wishlist = wishlist;
    }
}