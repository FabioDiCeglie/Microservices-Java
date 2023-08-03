package com.programming.orderservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programming.orderservice.dto.OrderRequest;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @PostMapping
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        return "Order Placed Successfully";
    }
}