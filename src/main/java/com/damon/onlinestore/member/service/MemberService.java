package com.damon.onlinestore.member.service;

import com.damon.onlinestore.member.entity.Member;

public interface MemberService {

    // 저장
    void save(String name, String loginId, String loginPw);

    // 조회
    Member find(String loginId, String loginPw);
}
