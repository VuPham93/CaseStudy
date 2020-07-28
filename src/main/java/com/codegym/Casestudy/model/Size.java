package com.codegym.Casestudy.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sizeId;

    @NotEmpty
    private String sizeName;

    @ManyToOne
    @JoinColumn("productId")
    private Product product;
}
