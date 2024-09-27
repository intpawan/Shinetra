package com.contact.contact.dto;

import java.util.List;

public class CartDto {
    private Long id;
    private List<ProductDto> products;
    private double totalPrice;

    // Constructors
    public CartDto() {
    }

    public CartDto(Long id, List<ProductDto> products, double totalPrice) {
        this.id = id;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}