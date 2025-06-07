package com.rgbunny.service;

import com.rgbunny.dtos.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(String emailId, Long addressId, String paymentMethod, String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage);
    OrderResponse findOrderById(Long id);
    List<OrderResponse> findAllOrder();
}
