/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.dal.mapper;

import com.zhishun.zaotoutiao.core.model.entity.User;

/**
 * 用户表Mapper接口
 * @author 闫迎军(YanYingJun)
 * @version $Id: ZttTest, v0.1 2018年02月10日 15:57闫迎军(YanYingJun) Exp $
 */

public interface UserMapper {

    /**
     * 删除
     * @param userId
     * @return
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * 新增用户
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 新增用户
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 根据用户ID查询
     * @param userId
     * @return
     */
    User selectByPrimaryKey(Long userId);

    /**
     * 更新用户
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 更新用户
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);
}