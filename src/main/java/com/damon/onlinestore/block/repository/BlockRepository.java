package com.damon.onlinestore.block.repository;

import com.damon.onlinestore.block.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlockRepository extends JpaRepository<Block, Integer> {

    // Token 차단 데이터 조회
    Optional<Block> findByToken(String token);
}
