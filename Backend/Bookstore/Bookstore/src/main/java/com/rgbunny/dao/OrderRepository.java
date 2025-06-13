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
                "WHERE o.orderId = ?1"
                )
        Order findOrderById(Long id);

        @Query("""
    SELECT DISTINCT o                         
    FROM   Order o
           JOIN FETCH o.orderItems           
           JOIN FETCH o.address a
           JOIN FETCH o.payment
    WHERE  a.user.id = ?1
    ORDER  BY o.orderDate DESC
        """)
        List<Order> findAllUsersOrder(Long id);

}
