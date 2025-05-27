package com.damon.onlinestore.common.interceptor;

import com.damon.onlinestore.account.helper.AccountHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiInterceptor implements HandlerInterceptor {

    private final AccountHelper accountHelper;

    public ApiInterceptor(AccountHelper accountHelper) {
        this.accountHelper = accountHelper;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {

        if (accountHelper.getMemberId(req) == null) {
            res.setStatus(401);
            return false;
        }
        return true;
    }
}
