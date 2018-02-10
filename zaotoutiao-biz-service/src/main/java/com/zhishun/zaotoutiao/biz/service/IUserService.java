package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.entity.User;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IUserService, v0.1 2018年02月10日 10:10闫迎军(YanYingJun) Exp $
 */
public interface IUserService {

    /**
     *
     */
    int deleteByPrimaryKey(Long userId);

    /**
     *
     */
    int insert(User record);

    /**
     *
     */
    int insertSelective(User record);

    /**
     *
     */
    User selectByPrimaryKey(Long userId);

    /**
     *
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *
     */
    int updateByPrimaryKey(User record);

}
