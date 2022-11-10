package com.example.livatek.domain;

import java.math.BigDecimal;

public class Amount {

    private Long id;
    private String name;
    private BigDecimal amount;

    public Amount() {
    }

    public Amount(Long amount) {
        this.amount = BigDecimal.valueOf(amount);
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
