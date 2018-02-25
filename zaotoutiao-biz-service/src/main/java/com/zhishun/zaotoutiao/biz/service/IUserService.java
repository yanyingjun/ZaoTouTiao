package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.entity.User;

import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IUserService, v0.1 2018年02月10日 10:10闫迎军(YanYingJun) Exp $
 */
public interface IUserService {

    /**
     * 根据用户ID查询用户信息
     * @param userId  用户ID
     * @return
     */
    User getUserByUserId(Long userId);

    /**
     * 根据参数查询用户信息
     * @param telephone  手机号
     * @return
     */
    User getUserByMap (String telephone);

}
