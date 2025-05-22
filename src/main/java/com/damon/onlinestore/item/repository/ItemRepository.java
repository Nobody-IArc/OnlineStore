package com.damon.onlinestore.item.repository;

import com.damon.onlinestore.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    // In 접미어 - 매개변수의 값이 배열이나 리스트인 경우 해당 값을 모두 포함함
    List<Item> findAllByIdIn(List<Integer> ids);
}
