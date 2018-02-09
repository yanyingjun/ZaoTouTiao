/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.common.dal.mapper;

import com.zhishun.zaotoutiao.core.model.entity.UserWithdrawalState;

/**
 * 提现状态-mapper接口
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public interface UserWithdrawalStateMapper {
    /**
     * 提现状态删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 提现状态插入
     * @param record
     * @return
     */
    int insert(UserWithdrawalState record);

    /**
     * 提现状态插入
     * @param record
     * @return
     */
    int insertSelective(UserWithdrawalState record);

    /**
     * 提现状态查询
     * @param id
     * @return
     */
    UserWithdrawalState selectByPrimaryKey(Long id);

    /**
     * 提现状态修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UserWithdrawalState record);

    /**
     * 提现状态修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserWithdrawalState record);
}