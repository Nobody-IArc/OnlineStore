package com.damon.onlinestore.order.repository;

import com.damon.onlinestore.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    // 주문 목록 조회
    List<Order> findAllByMemberIdOrderByIdDesc(Integer memberId);

    // 주문 정보 조회
    Optional<Order> findByIdAndMemberId(Integer id, Integer memberId);
}
