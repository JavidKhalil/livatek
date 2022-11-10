package com.example.livatek;

import com.example.livatek.domain.Amount;
import com.example.livatek.domain.Freight;
import com.example.livatek.domain.LivatekType;
import com.example.livatek.domain.Price;
import com.example.livatek.service.FreightService;
import com.example.livatek.service.FreightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class LivatekApplication {

    @Autowired
    private static FreightService freightService;

    public static void main(String[] args) {
        SpringApplication.run(LivatekApplication.class, args);

        List<String> arguments = Arrays.asList(args);

        /**
         * Zero or more parameters of the format <name>=<value>
         * For the test we only assume --vat=<country code>, --input-currency=<currency code>
         * and/or --output-currency=<currency code> can be supplied.
         *
         *
         */

        Amount amount = new Amount(Long.valueOf(arguments.get(0)));
        Price price =   new Price(Double.valueOf(arguments.get(1)));
        LivatekType livatekType = new LivatekType(arguments.get(2));

        if(freightService.applyAble(amount, price, livatekType)){
            Freight freight = freightService.calculateFreight(amount);
            Price totalPrice = freightService.calculatePrice(amount, price, freight);
            System.out.println("Apply freight: " + freight.getTotal().doubleValue());
            System.out.println("Apply total price: " + totalPrice.getPrice().doubleValue());
        }

    }

}
