package com.example.livatek.domain;

import java.math.BigDecimal;

public class Price {

    private Long id;
    private String name;
    private BigDecimal price;

    public Price(){}

    public Price(Double price){
        this.price = BigDecimal.valueOf(price);
    }

    public Price(BigDecimal price){
        this.price = price;
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

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
