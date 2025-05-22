package com.rgbunny.controller;

import com.rgbunny.dtos.CartResponse;
import com.rgbunny.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/carts/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartResponse> addProductToCart(@PathVariable Long productId,
                                                         @PathVariable Integer quantity){
        CartResponse cartResponse = cartService.addBookToCard(productId, quantity);
        return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.CREATED);
    }
}
