package com.codegym.Casestudy.model.user;

import com.codegym.Casestudy.model.validation.ValidEmail;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2,max=16)
    private String name;

    @Column(nullable = false, unique = true)
    @ValidEmail
    @NotEmpty
    @NotNull
    private String mail;

    @Column(nullable = false)
    @NotEmpty
    @NotNull
    private String password;

    private String matchingPassword;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int phoneNumber;

    @ManyToOne
    private Role role;

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String customerName) {
        this.name = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Customer() {
    }

    public Customer(String name, String mail, String password,Role role) {
    }

    public Customer(Long id,String name,String mail,String password, String address, int phoneNumber, Role role) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public Customer(String name,String mail,String password, String address, int phoneNumber, Role role) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}
