package com.example.livatek.service;

import com.example.livatek.domain.Amount;
import com.example.livatek.domain.Freight;
import com.example.livatek.domain.LivatekType;
import com.example.livatek.domain.Price;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class FreightServiceImpl implements FreightService{

    /**
     * Applieble
     *
     * @param amount
     * @param price
     * @param livatekType
     * @return
     */
    @Override
    public Boolean applyAble(Amount amount, Price price, LivatekType livatekType) {
        return livatekType.getName().name().equalsIgnoreCase("BOOK");
    }

    /**
     * calculation of price
     *
     * @param amount
     * @param price
     * @param freight
     * @return
     */
    @Override
    public Price calculatePrice(Amount amount, Price price, Freight freight) {
        /**
         *
         * Total price = amount x price + freight
         *
         */
        return new Price( ( amount.getAmount().multiply(price.getPrice()) ).add( freight.getTotal() ) );
    }

    @Override
    public Freight calculateFreight(Amount amount) {

       Integer amnt = amount.getAmount().intValue();

       Integer sumAmt = amnt/10;
        /**
         * Freight depends on the amount of products.
         * • Up to and including 10 items: 50,-
         * • For each additional 10 items: 25,-
         *
         */
       if(sumAmt <= 1){
           return new Freight(50);
       } else {
           return new Freight((sumAmt * 25) + 50);
       }
    }

}
