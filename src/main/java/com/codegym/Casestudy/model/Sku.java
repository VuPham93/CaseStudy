package com.codegym.Casestudy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
@Table
public class Sku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skuId;

    @NotEmpty
    private String skuValue;

    @Column(nullable = false)
    private int quantity;

    @ManyToMany
    @JoinTable(name = "product_option",
               joinColumns = @JoinColumn(name = "skuId"),
               inverseJoinColumns = @JoinColumn(name = "optionId"))
    private Collection<Option> options;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JsonIgnoreProperties(value = "sku")
    private Product product;

    public Sku(Long skuId, @NotEmpty String skuValue, int quantity, Collection<Option> options, Product product) {
        this.skuId = skuId;
        this.skuValue = skuValue;
        this.quantity = quantity;
        this.options = options;
    }

    public Sku() {
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuValue() {
        return skuValue;
    }

    public void setSkuValue(String sku) {
        this.skuValue = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Collection<Option> getOptions() {
        return options;
    }

    public void setOptions(Collection<Option> options) {
        this.options = options;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getOption(Long optionGroupId) {
        for (Option option : options) {
            if (option.getOptionGroup().getOptionGroupId().equals(optionGroupId)) {
                return option.getOptionId();
            }
        }
        return null;
    }
}
