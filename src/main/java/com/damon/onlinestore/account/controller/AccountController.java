package com.damon.onlinestore.account.controller;

import com.damon.onlinestore.account.dto.AccountJoinRequest;
import com.damon.onlinestore.account.dto.AccountLoginRequest;
import com.damon.onlinestore.account.helper.AccountHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class AccountController {

    private final AccountHelper accountHelper;

    // 회원가입
    @PostMapping("/api/account/join")
    public ResponseEntity<?> join(@RequestBody AccountJoinRequest joinReq) {

        // 입력값 확인
        if (!StringUtils.hasLength(joinReq.getName()) ||
        !StringUtils.hasLength(joinReq.getLoginId()) || !StringUtils.hasLength(joinReq.getLoginPw())) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        accountHelper.join(joinReq);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/api/account/login")
    public ResponseEntity<?> login(HttpServletRequest req, HttpServletResponse res, @RequestBody AccountLoginRequest loginReq) {

        // 입력값 확인
        if (!StringUtils.hasLength(loginReq.getLoginId()) ||
        !StringUtils.hasLength(loginReq.getLoginPw())) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String output = accountHelper.login(loginReq, req, res);

        if(output == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    // 로그인 상태 확인
    @GetMapping("/api/account/check")
    public ResponseEntity<?> check(HttpServletRequest req) {

        return new ResponseEntity<>(accountHelper.isLoggedIn(req), HttpStatus.OK);
    }

    // 로그아웃
    @PostMapping("/api/account/logout")
    public ResponseEntity<?> logout(HttpServletRequest req, HttpServletResponse res) {

        accountHelper.logout(req, res);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
