package com.damon.onlinestore.member.service;

import com.damon.onlinestore.common.util.HashUtils;
import com.damon.onlinestore.member.entity.Member;
import com.damon.onlinestore.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseMemberService implements MemberService {

    private final MemberRepository memberRepository;

    // 저장
    @Override
    public void save(String name, String loginId, String loginPw) {
        String loginPwSalt = HashUtils.generateSalt(16);

        String loginPwSalted = HashUtils.generateHash(loginPw, loginPwSalt);

        memberRepository.save(new Member(name, loginId, loginPwSalted, loginPwSalt));
    }

    // 조회
    @Override
    public Member find(String loginId, String loginPw) {
        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            String loginPwSalt = memberOptional.get().getLoginPwSalt();

            String loginPwSalted = HashUtils.generateHash(loginPw, loginPwSalt);

            if (member.getLoginPw().equals(loginPwSalted)) {
                return member;
            }
        }

        return null;
    }
}
