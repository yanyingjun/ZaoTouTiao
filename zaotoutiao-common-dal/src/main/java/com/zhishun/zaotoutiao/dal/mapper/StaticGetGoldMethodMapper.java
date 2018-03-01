/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.dal.mapper;

import com.zhishun.zaotoutiao.core.model.entity.StaticGetGoldMethod;

import java.util.List;

public interface StaticGetGoldMethodMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_get_gold_method
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_get_gold_method
     *
     * @mbggenerated
     */
    int insert(StaticGetGoldMethod record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_get_gold_method
     *
     * @mbggenerated
     */
    int insertSelective(StaticGetGoldMethod record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_get_gold_method
     *
     * @mbggenerated
     */
    StaticGetGoldMethod selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_get_gold_method
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(StaticGetGoldMethod record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_get_gold_method
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(StaticGetGoldMethod record);

    /**
     * 怎样获取金币奖励
     * @return
     */
    List<StaticGetGoldMethod> listGoldMethod();
}