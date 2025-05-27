package com.damon.onlinestore.block.service;

import com.damon.onlinestore.block.entity.Block;
import com.damon.onlinestore.block.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseBlockService implements BlockService {

    private final BlockRepository blockRepository;

    // 토큰 차단 데이터 생성
    @Override
    public void add(String token){
        blockRepository.save(new Block(token));
    }

    // 토큰 차단 데이터 확인
    @Override
    public boolean has(String token){
        return blockRepository.findByToken(token).isPresent();
    }
}
