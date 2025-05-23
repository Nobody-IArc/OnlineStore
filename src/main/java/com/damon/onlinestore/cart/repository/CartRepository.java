package com.damon.onlinestore.cart.repository;

import com.damon.onlinestore.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    // 특정 회원 카트 목록 조회
    List<Cart> findAllByMemberId(Integer memberId);

    // 특정 회원 및 특정 상품 카트 목록 조회
    Optional<Cart> findByMemberIdAndItemId(Integer memberId, Integer itemId);

    // 특정 회원 카트 삭제
    void deleteByMemberId(Integer memberId);

    // 특정 회원 및 특정 상품 카트 삭제
    void deleteByMemberIdAndItemId(Integer memberId, Integer itemId);
}
