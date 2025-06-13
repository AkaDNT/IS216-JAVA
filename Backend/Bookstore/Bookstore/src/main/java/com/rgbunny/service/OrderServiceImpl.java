package com.rgbunny.service;

import com.rgbunny.dao.*;
import com.rgbunny.dtos.*;
import com.rgbunny.entity.*;
import com.rgbunny.exceptions.APIException;
import com.rgbunny.utils.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    CartRepository cartRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CartService cartService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthUtil authUtil;

    @Transactional
    @Override
    public OrderResponse placeOrder(String emailId, Long addressId, String paymentMethod, String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage) {
        Cart cart = cartRepository.findCartByEmail(emailId);
        if (cart == null) {
            throw new ResourceNotFoundException("Not found cart");
        }

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found address"));

        Order order = new Order();
        order.setEmail(emailId);
        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(cart.getTotalPrice());
        order.setOrderStatus("ACCEPTED");
        order.setAddress(address);

        Payment payment = new Payment(paymentMethod, pgPaymentId, pgStatus, pgResponseMessage, pgName);
        payment.setOrder(order);
        payment = paymentRepository.save(payment);
        order.setPayment(payment);

        Order savedOrder = orderRepository.save(order);

        List<CartItem> cartItems = cart.getCartItems();
        if (cartItems.isEmpty()) {
            throw new APIException("Cart is empty");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setDiscount(cartItem.getDiscount());
            orderItem.setOrderedBookPrice(cartItem.getBookPrice());
            orderItem.setOrder(savedOrder);
            orderItems.add(orderItem);
        }

        orderItems = orderItemRepository.saveAll(orderItems);

        cart.getCartItems().forEach(item -> {
            int quantity = item.getQuantity();
            Book book = item.getBook();

            // Reduce stock quantity
            book.setQuantity(book.getQuantity() - quantity);

            // Save product back to the database
            bookRepository.save(book);

            // Remove items from cart
            cartService.deleteBookInCart(item.getBook().getId());
        });

        OrderResponse orderResponse = modelMapper.map(savedOrder, OrderResponse.class);
        orderItems.forEach(item -> orderResponse.getOrderItems().add(modelMapper.map(item, OrderItemResponse.class)));

        orderResponse.setAddressId(addressId);

        return orderResponse;
    }

    @Override
    public OrderResponse findOrderById(Long id) {
        User user = authUtil.loggedInUser();
        if(!CheckAdminOrEmployee(user)) throw new RuntimeException("Access denied");
        Order order = orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order not found with id "+ id));
        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
        orderResponse.setOrderItems(order.getOrderItems().stream().map(oi-> modelMapper.map(oi, OrderItemResponse.class)
        ).toList());
        orderResponse.setAddressId(order.getAddress().getId());
        orderResponse.setPayment(modelMapper.map(order.getPayment(), PaymentResponse.class));
        return orderResponse;
    }

    @Override
    public List<OrderResponse> findAllOrder() {
        User user = authUtil.loggedInUser();
        if(!CheckAdminOrEmployee(user)) throw new RuntimeException("Access denied");
        List<Order> orders = orderRepository.findAllWithOrderItems();
        return orders.stream().map(order -> {
            OrderResponse response = modelMapper.map(order, OrderResponse.class);
            response.setAddressId(order.getAddress().getId());

            response.setOrderItems(
                    order.getOrderItems().stream()
                            .map(item -> modelMapper.map(item, OrderItemResponse.class))
                            .toList()
            );

            return response;
        }).toList();
    }

    @Override
    public List<OrderResponse> getAllMyOrder(Long id) {
        List<Order> orders = orderRepository.findAllUsersOrder(id);
        return orders.stream().map(order -> {
            OrderResponse response = modelMapper.map(order, OrderResponse.class);
            response.setAddressId(order.getAddress().getId());

            response.setOrderItems(
                    order.getOrderItems().stream()
                            .map(item -> modelMapper.map(item, OrderItemResponse.class))
                            .toList()
            );

            return response;
        }).toList();
    }

    @Override
    public OrderResponse updateOrderStatus(Long orderId, UpdateOrderRequest updateOrderRequest) {
        Order order = orderRepository.findOrderById(orderId);
        if(order == null) throw new ResourceNotFoundException("Order not found with id: "+orderId);
        String updatedStatus = updateOrderRequest.getOrderStatus().toUpperCase();
        if(!updatedStatus.equals("SHIPPING") && !updatedStatus.equals("COMPLETED")) throw new RuntimeException("Invalid order status");
        order.setOrderStatus(updatedStatus);
        orderRepository.save(order);
        return findOrderById(orderId);
    }

    private Boolean CheckAdminOrEmployee(User user){
        String appRoles = user.getRoles().stream()
                .map(role -> role.getRoleName().toString())
                .collect(Collectors.joining(","));
        return appRoles.contains("ROLE_ADMIN") || appRoles.contains("ROLE_EMPLOYEE");
    }
}
