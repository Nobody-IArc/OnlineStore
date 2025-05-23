package com.damon.onlinestore.item.service;

import com.damon.onlinestore.item.dto.ItemRead;
import com.damon.onlinestore.item.entity.Item;
import com.damon.onlinestore.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseItemService implements ItemService {

    private final ItemRepository itemRepository;

    // 전체 목록 조회
    @Override
    public List<ItemRead> findAll() {
        return itemRepository.findAll().stream().map(Item::toRead).toList();
    }

    // id 리스트로 목록 조회
    @Override
    public List<ItemRead> findAll(List<Integer> ids) {
        return itemRepository.findAllByIdIn(ids).stream().map(Item::toRead).toList();
    }
}
