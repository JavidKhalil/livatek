package com.example.livatek.domain;

import com.example.livatek.domain.enums.LivatekTypeEnum;

public class LivatekType {

    private Long id;
    private LivatekTypeEnum name;

    public LivatekType() {
        this.name = LivatekTypeEnum.book;
    }

    public LivatekType(String name) {
        try {
           this.name = LivatekTypeEnum.valueOf(name);
        } catch (Exception e) {
            // TO DO Add logger
            System.out.println("Incorrect type name provided setting default TEST");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LivatekTypeEnum getName() {
        return name;
    }

    public void setName(LivatekTypeEnum name) {
        this.name = name;
    }
}
