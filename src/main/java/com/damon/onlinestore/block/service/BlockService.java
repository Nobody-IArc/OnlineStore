package com.damon.onlinestore.block.service;

public interface BlockService {

    // 토큰 차단 데이터 생성
    void add(String token);

    // 토큰 차단 데이터 확인
    boolean has(String token);
}
