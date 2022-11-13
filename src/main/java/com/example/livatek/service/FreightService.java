package com.example.livatek.service;

import com.example.livatek.domain.Amount;
import com.example.livatek.domain.Freight;
import com.example.livatek.domain.LivatekType;
import com.example.livatek.domain.Price;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FreightService {

    Boolean applyAble(Amount amount, Price price, LivatekType livatekType);

    Price calculatePrice(Amount amount, Price price, Freight freight);

    Freight calculateFreight(Amount amount);

    Price calculateTotal(Amount amount, Price price, LivatekType livatekType, List<String> arguments, String[] args);

}
