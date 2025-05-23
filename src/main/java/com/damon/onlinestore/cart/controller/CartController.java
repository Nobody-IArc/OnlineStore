package com.damon.onlinestore.cart.controller;

import com.damon.onlinestore.account.helper.AccountHelper;
import com.damon.onlinestore.cart.dto.CartRead;
import com.damon.onlinestore.cart.dto.CartRequest;
import com.damon.onlinestore.cart.service.CartService;
import com.damon.onlinestore.item.dto.ItemRead;
import com.damon.onlinestore.item.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ItemService itemService;
    private final AccountHelper accountHelper;

    @GetMapping("/api/cart/items")
    public ResponseEntity<?> readAll(HttpServletRequest req) {

        // 로그인 아이디
        Integer memberId = accountHelper.getMemberId(req);

        // 카트 목록 조회
        List<CartRead> carts = cartService.findAll(memberId);

        // 카트 내 상품 id로 상품 조회
        List<Integer> itemIds = carts.stream().map(CartRead::getItemId).toList();
        List<ItemRead> items = itemService.findAll(itemIds);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/api/carts")
    public ResponseEntity<?> push(HttpServletRequest req, @RequestBody CartRequest cartReq) {

        // 로그인 아이디
        Integer memberId = accountHelper.getMemberId(req);

        // 카트 상품 조회
        CartRead cart = cartService.find(memberId, cartReq.getItemId());

        // 카트가 비어있는 경우
        if(cart == null) {
            cartService.save(cartReq.toEntity(memberId));
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/cart/items/{itemId}")
    public ResponseEntity<?> remove(HttpServletRequest req, @PathVariable("itemId") Integer itemId) {

        // 로그인 아이디
        Integer memberId = accountHelper.getMemberId(req);

        cartService.remove(memberId, itemId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
