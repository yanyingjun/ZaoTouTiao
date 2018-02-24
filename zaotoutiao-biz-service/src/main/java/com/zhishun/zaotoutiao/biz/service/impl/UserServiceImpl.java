/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhishun.zaotoutiao.common.client.redis.RedisCommonClient;
import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.dal.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserServiceImpl, v0.1 2018年02月10日 10:12闫迎军(YanYingJun) Exp $
 */

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisCommonClient redisCommonClient;

    @Override
    public int deleteByPrimaryKey(Long userId) {
        return 0;
    }

    @Override
    public int insert(User record) {
        return 0;
    }

    @Override
    public int insertSelective(User record) {
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Long userId) {

        User user = userMapper.selectByPrimaryKey(userId);
        redisCommonClient.set(userId.toString(), user);
        return user;
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return 0;
    }
}
