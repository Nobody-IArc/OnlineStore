package com.damon.onlinestore.account.helper;

import com.damon.onlinestore.account.dto.AccountJoinRequest;
import com.damon.onlinestore.account.dto.AccountLoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AccountHelper {

    // 회원가입
    void join(AccountJoinRequest joinReq);

    // 로그인
    String login(AccountLoginRequest loginReq, HttpServletRequest req, HttpServletResponse res);

    // Id 조회
    Integer getMemberId(HttpServletRequest req);

    // 로그인 상태 확인
    boolean isLoggedIn(HttpServletRequest req);

    // 로그아웃
    void logout(HttpServletRequest req, HttpServletResponse res);
}
