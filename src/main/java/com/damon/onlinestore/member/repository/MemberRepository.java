package com.damon.onlinestore.member.repository;

import com.damon.onlinestore.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByLoginIdAndLoginPw(String loginId, String loginPw);
    Optional<Member> findByLoginId(String loginId);
}
