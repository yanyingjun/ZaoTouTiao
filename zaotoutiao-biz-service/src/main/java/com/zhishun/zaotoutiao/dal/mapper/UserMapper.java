/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.dal.mapper;

import com.zhishun.zaotoutiao.core.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface UserMapper {
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