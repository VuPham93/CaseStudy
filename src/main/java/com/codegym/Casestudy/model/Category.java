package com.codegym.Casestudy.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table
public class Category {
    @Id
    private Long categoryId;
    private String categoryName;
}
