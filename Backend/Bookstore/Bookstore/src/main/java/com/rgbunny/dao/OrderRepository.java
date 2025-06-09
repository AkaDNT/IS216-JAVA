package com.rgbunny.dao;


import com.rgbunny.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
        @Query("SELECT DISTINCT o FROM Order o " +
                "LEFT JOIN FETCH o.orderItems oi " +
                "LEFT JOIN FETCH o.address " +
                "LEFT JOIN FETCH o.payment")
        List<Order> findAllWithOrderItems();

        @Query("SELECT DISTINCT o FROM Order o " +
                "LEFT JOIN FETCH o.orderItems " +
                "LEFT JOIN FETCH o.address "+
                "LEFT JOIN FETCH o.payment "+
                "WHERE o.orderId = ?1")
        Order findOrderById(Long id);

        @Query("SELECT DISTINCT o FROM Order o " +
                "LEFT JOIN FETCH o.orderItems oi " +
                "LEFT JOIN FETCH o.address " +
                "LEFT JOIN FETCH o.payment "+
                "WHERE o.email = ?1")
        List<Order> findAllUsersOrder(String email);

}
