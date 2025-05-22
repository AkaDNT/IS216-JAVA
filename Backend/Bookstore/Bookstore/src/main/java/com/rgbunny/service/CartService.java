package com.rgbunny.service;

import com.rgbunny.dtos.CartResponse;

public interface CartService {
    CartResponse addBookToCard(Long Id, Integer quantity);
}
