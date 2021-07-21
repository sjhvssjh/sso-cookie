package com.sjh.sso.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program:
 * @description:
 * @author: Shen jihao
 * @create: 2021/7/21 16:27
 */
@Controller
public class IndexController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response){

        String token = request.getParameter("token");
        if (StringUtils.isNotBlank(token)){
            //校验token是否有效
            if (stringRedisTemplate.hasKey(token)){
                //获取用户信息
                String username = stringRedisTemplate.opsForValue().get(token);
                request.getSession().setAttribute("user", username);
                return "index";
            }
        }
        //不含token
        return "redirect:http://127.0.0.1:8001/login?redirect_url=http://127.0.0.1:8002/index";
    }
}
