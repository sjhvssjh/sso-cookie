package com.sjh.sso.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sjh.common.entity.User;

/**
 * @program:
 * @description:
 * @author: Shen jihao
 * @create: 2021/7/21 15:37
 */
public interface LoginService extends IService<User> {

    User login(String username, String password);
}
