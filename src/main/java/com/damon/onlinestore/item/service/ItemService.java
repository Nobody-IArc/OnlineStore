package com.damon.onlinestore.item.service;

import com.damon.onlinestore.item.dto.ItemRead;
import java.util.List;

public interface ItemService {

    // 전체 목록 조회
    List<ItemRead> findAll();

    // id 리스트로 목록 조회
    List<ItemRead> findAll(List<Integer> ids);
}
