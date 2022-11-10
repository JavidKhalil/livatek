package com.example.livatek.domain;

import java.math.BigDecimal;

public class Freight {

    private Long id;
    private String name;
    private BigDecimal total;

    public Freight() {
    }

    public Freight(Integer total) {
        this.total = BigDecimal.valueOf(total);
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
