package com.rgbunny.dao;

import com.rgbunny.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = ?1 AND ci.book.id = ?2")
    CartItem findCartItemByBookIdAndCartId(Long cartId, Long bookId);

    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.cart.cartId = ?1 AND ci.book.id = ?2")
    void deleteCartItemByCartIdAndBookId(Long cartId, Long bookId);

    @Modifying
    @Query("DELETE FROM CartItem ci Where ci.book.id = ?1")
    void deleteCartItemByBookId(Long bookId);
}
