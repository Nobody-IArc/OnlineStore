package com.damon.onlinestore.member.service;

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
        memberRepository.save(new Member(name, loginId, loginPw));
    }

    // 조회
    @Override
    public Member find(String loginId, String loginPw) {
        Optional<Member> memberOptional = memberRepository.findByLoginIdAndLoginPw(loginId, loginPw);

        return memberOptional.orElse(null);
    }
}
