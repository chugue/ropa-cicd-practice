package com.example.finalproject.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    // 사용자 장바구니 찾기
    @Query("select c from Cart c join fetch c.items i join fetch i.photos p where c.user.id = :userId")
    List<Cart> findAllByUserId(@Param("userId") Integer userId);
}