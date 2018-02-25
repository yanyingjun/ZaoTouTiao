/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.common.client.redis.RedisCommonClient;
import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.common.util.LoggerUtils;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.dal.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserServiceImpl, v0.1 2018年02月10日 10:12闫迎军(YanYingJun) Exp $
 */

@Service
public class UserServiceImpl implements IUserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User getUserByMap(String telephone) {
        //查询用户信息
        Map<String,Object> map = Maps.newHashMap();
        map.put("telephone", telephone);
        return userMapper.getUserByMap(map);
    }

    @Override
    public Boolean isUserLogin(String telephone, String password) {
        //查询用户信息
        Map<String,Object> map = Maps.newHashMap();
        map.put("telephone", telephone);
        map.put("password", password);
        User user = userMapper.getUserByMap(map);
        if(StringUtils.isEmpty(user)){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void updateUserInfo(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}
