package com.example.livatek;

import com.example.livatek.domain.Amount;
import com.example.livatek.domain.Freight;
import com.example.livatek.domain.LivatekType;
import com.example.livatek.domain.Price;
import com.example.livatek.service.FreightService;
import com.example.livatek.utils.file.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class LivatekApplication {

    private static FreightService freightService;
    private static Environment environment;

    @Autowired
    public void setFreightService(FreightService freightService) {
        LivatekApplication.freightService = freightService;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        LivatekApplication.environment = environment;
    }

    public static void main(String[] args) {
        SpringApplication.run(LivatekApplication.class, args);

        List<String> arguments = Arrays.asList(args);
        Amount amount = new Amount(Long.valueOf(arguments.get(0)));
        Price price = new Price(Double.valueOf(arguments.get(1)));
        Freight freight = null;
        LivatekType livatekType = new LivatekType(arguments.get(2));

        if (freightService.applyAble(amount, price, livatekType)) {
            freight = freightService.calculateFreight(amount);
            Price totalPrice = freightService.calculatePrice(amount, price, freight);
            if (arguments.size() > 2) {
                proceedAdditionalParams(args, totalPrice);
                String finalPrice = String.valueOf(price.getPrice().doubleValue());
                System.out.println("final price is : " + finalPrice);
                FileUtils.write("prices.txt", amount, finalPrice, freight);
            }
        }
    }

    private static void proceedAdditionalParams(String[] params, Price price) {
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

    private static void applyAdditionalParam(String additionalParam, Price price) {
        if (additionalParam != null) {
            price.setPrice(price.getPrice().multiply(BigDecimal.valueOf(Double.parseDouble(additionalParam))));
        }
    }

}

