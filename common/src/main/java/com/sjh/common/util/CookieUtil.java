package com.sjh.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program:
 * @description:
 * @author: Shen jihao
 * @create: 2021/7/21 16:05
 */
public class CookieUtil {

    // 默认缓存时间,单位/秒, 2H
    private static final int COOKIE_MAX_AGE = Integer.MAX_VALUE;
    // 保存路径,根路径
    private static final String COOKIE_PATH = "/";

    public static void set(HttpServletResponse response, String key, String value, boolean ifRemember) {
        int age = ifRemember?COOKIE_MAX_AGE:-1;
        set(response, key, value, null, COOKIE_PATH, age, true);
    }

    private static void set(HttpServletResponse response, String key, String value, String domain, String path, int maxAge, Boolean flag) {
        Cookie cookie = new Cookie(key, value);
        if (domain != null) {
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(flag);
        response.addCookie(cookie);
    }

    public static String getValue(HttpServletRequest request, String key) {
        Cookie cookie = get(request, key);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    private static Cookie get(HttpServletRequest request, String key) {
        Cookie[] arr_cookie = request.getCookies();
        if (arr_cookie != null && arr_cookie.length > 0) {
            for (Cookie cookie : arr_cookie) {
                if (cookie.getName().equals(key)) {
                    return cookie;
                }
            }
        }
        return null;
    }


}
