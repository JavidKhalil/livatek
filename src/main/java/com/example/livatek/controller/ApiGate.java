package com.example.livatek.controller;

import com.example.livatek.domain.Amount;
import com.example.livatek.domain.LivatekType;
import com.example.livatek.domain.Price;
import com.example.livatek.requestbody.FreightRequest;
import com.example.livatek.service.FreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class ApiGate {

    private FreightService freightService;

    public ApiGate(FreightService freightService) {
        this.freightService = freightService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Price> calculateWithEmpty(){

        FreightRequest freightRequest = new FreightRequest();

        return ResponseEntity.ok(
                freightService.calculateTotal(
                        freightRequest.getAmount(), freightRequest.getPrice(), freightRequest.getLivatekType(), null, null));

    }

    @RequestMapping(value = "/freight", method = RequestMethod.GET)
    public ResponseEntity<Price> calculateWithBody(@RequestBody FreightRequest freightRequest){

        return ResponseEntity.ok(
                freightService.calculateTotal(
                        freightRequest.getAmount(), freightRequest.getPrice(), freightRequest.getLivatekType(), null, null));

    }
}
