package com.dev.order_service.controller;

import com.dev.order_service.dto.OrderRequest;
import com.dev.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author DELL
 **/
@RequiredArgsConstructor
@RestController
@ResponseStatus(HttpStatus.CREATED)
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order placed successfuly";
    }
}
