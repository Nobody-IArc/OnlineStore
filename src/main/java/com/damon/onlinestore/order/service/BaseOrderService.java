package com.damon.onlinestore.order.service;

import com.damon.onlinestore.cart.service.CartService;
import com.damon.onlinestore.item.dto.ItemRead;
import com.damon.onlinestore.item.service.ItemService;
import com.damon.onlinestore.order.dto.OrderRead;
import com.damon.onlinestore.order.dto.OrderRequest;
import com.damon.onlinestore.order.entity.Order;
import com.damon.onlinestore.order.entity.OrderItem;
import com.damon.onlinestore.order.repository.OrderItemRepository;
import com.damon.onlinestore.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final ItemService itemService;
    private final CartService cartService;

    // 주문 목록 조회
    @Override
    public List<OrderRead> findAll(Integer memberId) {
        // DTO 변환 후 반환
        return orderRepository.findAllByMemberIdOrderByIdDesc(memberId).stream().map(Order::toRead).toList();
    }

    // 주문 상세 조회
    @Override
    public OrderRead find(Integer id, Integer memberId) {
        Optional<Order> orderOptional = orderRepository.findByIdAndMemberId(id, memberId);

        if(orderOptional.isPresent()) {

            // DTO로 변환
            OrderRead order = orderOptional.get().toRead();

            // 주문 상품 목록 조회
            List<OrderItem> orderItems = orderItemService.findAll(order.getId());

            // 주문 상품 목록 id 추출
            List<Integer> orderItemIds = orderItems.stream().map(OrderItem::getItemId).toList();

            // 주문 상품 리스트의 id를 통한 상품 목록 조회
            List<ItemRead> items = itemService.findAll(orderItemIds);

            // 응답 값에 상품 리스트 데이터 설정
            order.setItems(items);

            return order;
        }

        return null;
    }

    // 주문 내용 저장
    @Override
    @Transactional
    public void order(OrderRequest orderReq, Integer memberId) {

        // 주문 상품의 결제 금액 계산
        List<ItemRead> items = itemService.findAll(orderReq.getItemIds());
        long amount = 0L;

        for (ItemRead item: items) {
            amount += item.getPrice() - item.getPrice().longValue() * item.getDiscountPer() / 100;
        }

        // 주문 요청에 결제 금액 입력
        orderReq.setAmount(amount);

        // 주문 저장
        Order order = orderRepository.save(orderReq.toEntity(memberId));

        // 주문 상푸 데이터 생성
        List<OrderItem> newOrderItems = new ArrayList<>();

        // 상품 id 만큼 주문 상품 추가
        orderReq.getItemIds().forEach((itemId) -> {
            OrderItem newOrderItem = new OrderItem(order.getId(), itemId);
            newOrderItems.add(newOrderItem);
        });

        // 주문 상품 데이터 저장
        orderItemService.saveAll(newOrderItems);

        // 특정 회원의 장바구니 데이터 삭제
        cartService.removeAll(order.getMemberId());
    }
}
