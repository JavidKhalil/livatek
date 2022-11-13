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

        /**
         * TO DO
         *
         * Add exception handling algebra
         * Add persistence for prices
         *       use docker-compose
         * Add RestController for API generation
         */

        List<String> arguments = Arrays.asList(args);

        freightService.calculateTotal(
                new Amount(Long.valueOf(arguments.get(0))),
                new Price(Double.valueOf(arguments.get(1))),
                new LivatekType(arguments.get(2)), arguments, args);

    }

}

