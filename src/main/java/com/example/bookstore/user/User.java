package com.example.bookstore.user;

import jakarta.persistence.*;

@Entity
@Table
public class User {

    @Id
    @SequenceGenerator(name = "number-generator", sequenceName = "number-generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "number-generator")
    private Long id;

    private String fullName;
    private String email;
    private String password;
    private String billingAddress;
    private String paymentInfo;

    public User() {
    }

    public User(
            String fullName,
            String email,
            String password,
            String billingAddress,
            String paymentInfo
    ) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.billingAddress = billingAddress;
        this.paymentInfo = paymentInfo;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
