package com.sjh.common.help;

import com.sjh.common.constant.SSOConstant;
import com.sjh.common.util.CookieUtil;
import javax.servlet.http.HttpServletResponse;

/**
 * @program:
 * @description:
 * @author: Shen jihao
 * @create: 2021/7/21 16:00
 */
public class SsoWebLoginHelper {

    public static void login(HttpServletResponse response, String sessionId) {
        //设置cookie
        CookieUtil.set(response, SSOConstant.SSO_SESSIONID, sessionId, true);
    }
}
