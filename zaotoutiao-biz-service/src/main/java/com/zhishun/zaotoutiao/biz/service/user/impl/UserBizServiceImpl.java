/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.user.impl;

import com.zhishun.zaotoutiao.common.base.core.BaseCoreService;
import com.zhishun.zaotoutiao.common.dal.mapper.UserMapper;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.service.user.UserBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserServiceImpl, v0.1 2018年02月08日 10:45闫迎军(YanYingJun) Exp $
 */

@Service("userBizServiceImpl")
public class UserBizServiceImpl implements UserBizService {

    private UserMapper userMapper;


    /**
     * Getter method for property <tt>userMapper</tt>.
     *
     * @return property value of userMapper
     */
    public UserMapper getUserMapper() {
        return userMapper;
    }

    /**
     * Setter method for property <tt>userMapper</tt>.
     *
     * @param userMapper value to be assigned to property userMapper
     */
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int countPageByParam(User user) {
        return userMapper.deleteByPrimaryKey(user.getUserId());
    }

    @Override
    public List<User> queryPageByParam(User user) {
        return null;
    }

    @Override
    public List<User> queryByParam(User user) {
        return null;
    }

    @Override
    public int insert(User user) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public int delete(User user) {
        return 0;
    }

    @Override
    public User getById(User user) {
        if(userMapper == null){
            user.setName("张三");
            return user;
        }
        return userMapper.selectByPrimaryKey(user.getUserId());
    }
}
