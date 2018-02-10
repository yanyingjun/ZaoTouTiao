/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.core.model.entity.User;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserServiceImpl, v0.1 2018年02月10日 10:12闫迎军(YanYingJun) Exp $
 */

@Service
public class UserServiceImpl implements IUserService{



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
        return null;
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
