/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.dal.mapper;

import com.zhishun.zaotoutiao.core.model.entity.UserShare;

import java.util.Map;

public interface UserShareMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_share
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_share
     *
     * @mbggenerated
     */
    int insert(UserShare record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_share
     *
     * @mbggenerated
     */
    int insertSelective(UserShare record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_share
     *
     * @mbggenerated
     */
    UserShare selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_share
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserShare record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_share
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserShare record);

    /**
     * 判断三天内是否有是否唤醒自己
     * @param map
     * @return
     */
    UserShare getWeekupThreeDay(Map<String,Object> map);

    /**
     * 获得最后产生的分享记录
     * @return
     */
    UserShare getLastShare();

    /**
     * 判断是否大于三小时
     * @param map
     * @return
     */
    int getTiming(Map<String,Object> map);


    int getNumOfType(Long userId,String shareType);

    /**
     * 获取分享转发数
     * @param map infoId
     * @return
     */
    Long getNumByInfoId(Map<String,Object> map);
}