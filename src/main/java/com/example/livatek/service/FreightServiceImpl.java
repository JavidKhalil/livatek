package com.example.livatek.service;

import com.example.livatek.domain.Amount;
import com.example.livatek.domain.Freight;
import com.example.livatek.domain.LivatekType;
import com.example.livatek.domain.Price;
import com.example.livatek.utils.file.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FreightServiceImpl implements FreightService{

    @Autowired
    private Environment environment;

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

    @Override
    public Price calculateTotal(Amount amount, Price price, LivatekType livatekType, List<String> arguments, String[] args) {
        if(args == null || arguments == null){
            args = "23 199.95 book vat=DKK input-currency=EUR output-currency=EUR".split(" ");
            arguments = Arrays.asList(args);
        }

        Price totalPrice = null;
        Freight freight = null;
        if (applyAble(amount, price, livatekType)) {
            freight = calculateFreight(amount);
            totalPrice = calculatePrice(amount, price, freight);
            if (arguments.size() > 2) {
                proceedAdditionalParams(args, totalPrice);
                String finalPrice = String.valueOf(price.getPrice().doubleValue());
                System.out.println("final price is : " + finalPrice);
                FileUtils.write("prices.txt", amount, finalPrice, freight);
            }
        }

        return totalPrice;
    }

    void proceedAdditionalParams(String[] params, Price price) {
        List<String> additionalParams = Arrays.asList(params).stream().filter(p -> p.contains("=")).collect(Collectors.toList());
        for (String param : additionalParams) {
            String[] vals = param.split("=");
            if (param.contains("vat")) {
                String vat = environment.getProperty("vat." + vals[1]);
                applyAdditionalParam(vat, price);
            } else if (param.contains("input-currency")) {
                String curr = environment.getProperty("curr." + vals[1]);
                applyAdditionalParam(curr, price);
            }
        }
    }

    void applyAdditionalParam(String additionalParam, Price price) {
        if (additionalParam != null) {
            price.setPrice(price.getPrice().multiply(BigDecimal.valueOf(Double.parseDouble(additionalParam))));
        }
    }

}
