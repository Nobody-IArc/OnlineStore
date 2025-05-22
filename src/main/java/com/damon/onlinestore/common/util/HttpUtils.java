package com.damon.onlinestore.common.util;

import jakarta.servlet.http.HttpServletRequest;

public class HttpUtils {
    // Session - Set
    public static void setSession(HttpServletRequest req, String key, Object value) {
        req.getSession().setAttribute(key, value);
    }

    // Session - Get
    public static Object getSessionValue(HttpServletRequest req, String key) {
        return req.getSession().getAttribute(key);
    }

    // Session - Remove
    public static void removeSession(HttpServletRequest req, String key) {
        req.getSession().removeAttribute(key);
    }
}
