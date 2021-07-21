package com.sjh.sso.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjh.sso.mapper.LoginMapper;
import com.sjh.common.entity.User;
import com.sjh.sso.service.LoginService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program:
 * @description:
 * @author: Shen jihao
 * @create: 2021/7/21 15:37
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, User> implements LoginService {

    private static List<User> mockUserList = new ArrayList<>();
    static {

        User user = new User();
        user.setId("1");
        user.setUsername("admin");
        user.setPassword("123");
        mockUserList.add(user);
    }

    @Override
    public User login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return null;
        }
        return mockUserList.get(0);
    }
}
