package com.sjh.common.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @program:
 * @description:
 * @author: Shen jihao
 * @create: 2021/7/21 15:33
 */
@Data
@ToString
public class User {

    private String id;

    private String username;

    private String password;

    private String token;
}
