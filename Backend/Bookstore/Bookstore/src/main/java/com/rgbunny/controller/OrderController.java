package com.rgbunny.controller;

import com.rgbunny.dtos.OrderRequest;
import com.rgbunny.dtos.OrderResponse;
import com.rgbunny.service.OrderService;
import com.rgbunny.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3001")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    AuthUtil authUtil;

    @PostMapping("/order/users/payments/{paymentMethod}")
    public ResponseEntity<OrderResponse> orderBooks(@PathVariable String paymentMethod, @RequestBody OrderRequest orderRequest){
        String emailId = authUtil.loggedInEmail();
        OrderResponse order = orderService.placeOrder(
                emailId,
                orderRequest.getAddressId(),
                paymentMethod,
                orderRequest.getPgName(),
                orderRequest.getPgPaymentId(),
                orderRequest.getPgStatus(),
                orderRequest.getPgResponseMessage()
        );
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    @GetMapping("/order")
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        List<OrderResponse> orderResponses = orderService.findAllOrder();
        return new ResponseEntity<>(orderResponses, HttpStatus.OK);
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id){
        OrderResponse orderResponse = orderService.findOrderById(id);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

}
