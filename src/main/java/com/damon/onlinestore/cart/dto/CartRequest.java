package com.damon.onlinestore.cart.dto;

import com.damon.onlinestore.cart.entity.Cart;
import lombok.Getter;

@Getter
public class CartRequest {

    private Integer itemId;

    // 엔티티로 변환
    public Cart toEntity(Integer memberId) {
        return new Cart(memberId, itemId);
    }
}
