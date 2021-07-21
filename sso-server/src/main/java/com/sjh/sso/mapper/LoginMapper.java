package com.sjh.sso.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjh.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @program:
 * @description:
 * @author: Shen jihao
 * @create: 2021/7/21 15:38
 */
@Mapper
@Repository
public interface LoginMapper extends BaseMapper<User> {

}
