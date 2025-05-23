package com.damon.onlinestore.cart.service;

import com.damon.onlinestore.cart.dto.CartRead;
import com.damon.onlinestore.cart.entity.Cart;

import java.util.List;

public interface CartService {

    // 카트 상품 목록 조회
    List<CartRead> findAll(Integer memberId);

    // 카트 상품 데이터 조회
    CartRead find(Integer memberId, Integer itemId);

    // 카트 상품 전체 삭제
    void removeAll(Integer memberId);

    // 카트 상품 특정 삭제
    void remove(Integer memberId, Integer itemId);

    // 카트 상태 저장
    void save(Cart cart);
}
