package com.codegym.Casestudy.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String customerName;

    @Column
    private String account;

    @Column
    private String password;

    @Column
    private String mail;

    @Column
    private String address;

    @Column
    private int phoneNumber;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
