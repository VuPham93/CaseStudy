package com.codegym.Casestudy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(unique = true, nullable = false)
    private String productName;

    @NotEmpty
    private String detail;

    @Column(nullable = false)
    private double price;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = {CascadeType.ALL})
    @JsonIgnoreProperties(value = "product")
    private List<Sku> skuList;

    public void removeSku() {
        skuList = new ArrayList<>();
    }

    public void addSku(Sku tempSku) {
        if (skuList == null) {
            skuList = new ArrayList<>();
        }
        skuList.add(tempSku);
        tempSku.setProduct(this);
    }

    public List<Long> getSkuIdList() {
        List<Long> skuIdList = new ArrayList<>();
        for (Sku sku : skuList) {
            skuIdList.add(sku.getSkuId());
        }
        return skuIdList;
    }

    @ManyToOne
    private Category category;

    public Product(Long productId, String productName, @NotEmpty String detail, @NotEmpty double price, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.detail = detail;
        this.price = price;
        this.category = category;
    }

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }
}

