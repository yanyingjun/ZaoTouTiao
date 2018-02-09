/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.user.impl;

import com.zhishun.zaotoutiao.dal.mapper.UserMapper;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.service.user.UserBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserBizServiceImpl, v0.1 2018年02月09日 13:21闫迎军(YanYingJun) Exp $
 */

@Service
public class UserBizServiceImpl implements UserBizService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public int countPageByParam(User user) {
        return 0;
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
        return userMapper.selectByPrimaryKey((long)19);
        //return null;
    }
}
