package com.example.livatek.requestbody;

import com.example.livatek.domain.Amount;
import com.example.livatek.domain.LivatekType;
import com.example.livatek.domain.Price;
import lombok.Data;

@Data
public class FreightRequest {

    private Amount amount = new Amount(1L);
    private Price price = new Price(1.0);
    private LivatekType livatekType = new LivatekType("book");

}
