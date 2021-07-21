package com.sjh.sso.controller;

import com.sjh.common.constant.SSOConstant;
import com.sjh.common.entity.User;
import com.sjh.common.help.SsoWebLoginHelper;
import com.sjh.common.util.CookieUtil;
import com.sjh.sso.service.LoginService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @program:
 * @description:
 * @author: Shen jihao
 * @create: 2021/7/21 14:38
 */
@Controller
public class LoginController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request){
        //登录，判断是否已有cookie
        String cookieSessionId = CookieUtil.getValue(request, SSOConstant.SSO_SESSIONID);
        if (StringUtils.isNotBlank(cookieSessionId)){
            if (stringRedisTemplate.hasKey(cookieSessionId)){
                //已登录
                String redirectUrl = request.getParameter(SSOConstant.REDIRECT_URL);
                return "redirect:" + redirectUrl + "?token=" + cookieSessionId;
            }
        }
        //未登录
        model.addAttribute(SSOConstant.REDIRECT_URL, request.getParameter(SSOConstant.REDIRECT_URL));
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
                          String username, String password){
        //判断用户名密码是否正确
        User user = loginService.login(username, password);
        if (user == null){
            redirectAttributes.addAttribute(SSOConstant.REDIRECT_URL, request.getParameter(SSOConstant.REDIRECT_URL));
            return "redirect:/login";
        }
        //登录成功
        //设置sessionid
        String sessionId = UUID.randomUUID().toString().replace("-", "");

        //存放到redis里
        stringRedisTemplate.opsForValue().set(sessionId, user.getUsername());

        //登录痕迹
        SsoWebLoginHelper.login(response, sessionId);

        //返回
        String redirectUrl = request.getParameter(SSOConstant.REDIRECT_URL);
        if (redirectUrl!=null && redirectUrl.trim().length()>0) {
            String redirectUrlFinal = redirectUrl + "?token=" + sessionId;
            return "redirect:" + redirectUrlFinal;
        } else {
            return "redirect:/login";
        }
    }
}
