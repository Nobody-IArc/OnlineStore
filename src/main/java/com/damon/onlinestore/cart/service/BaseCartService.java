package com.damon.onlinestore.cart.service;

import com.damon.onlinestore.cart.dto.CartRead;
import com.damon.onlinestore.cart.entity.Cart;
import com.damon.onlinestore.cart.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseCartService implements CartService {

    private final CartRepository cartRepository;

    // 카트 상품 목록 조회
    @Override
    public List<CartRead> findAll(Integer memberId) {

        // List -> DTO 변환 후 리턴
        return cartRepository.findAllByMemberId(memberId).stream().map(Cart::toRead).toList();
    }

    // 카트 상품 데이터 조회
    @Override
    public CartRead find(Integer memberId, Integer itemId) {
        Optional<Cart> cartOptional = cartRepository.findByMemberIdAndItemId(memberId, itemId);

        return cartOptional.map(Cart::toRead).orElse(null);
    }

    // 카트 상품 전체 삭제
    @Override
    @Transactional
    public void removeAll(Integer memberId) {
        cartRepository.deleteByMemberId(memberId);
    }

    // 카트 상품 특정 삭제
    @Override
    @Transactional
    public void remove(Integer memberId, Integer itemId) {
        cartRepository.deleteByMemberIdAndItemId(memberId, itemId);
    }

    // 카트 상태 저장
    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

}
