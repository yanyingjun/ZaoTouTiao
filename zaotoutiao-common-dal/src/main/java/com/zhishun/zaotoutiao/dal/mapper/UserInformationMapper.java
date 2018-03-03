/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.dal.mapper;

import com.zhishun.zaotoutiao.core.model.entity.UserInformation;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface UserInformationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_information
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_information
     *
     * @mbggenerated
     */
    int insert(UserInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_information
     *
     * @mbggenerated
     */
    int insertSelective(UserInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_information
     *
     * @mbggenerated
     */
    UserInformation selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_information
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_information
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserInformation record);


    int deleteByType(Map<String,Object> map);

    /**
     * 获取用户消息记录
     * @param map
     * @return
     */
    List<UserInformation> listInformationPage(Map map);

    /**
     * 获取新用户消息模版
     * @return
     */
    List<UserInformation> listInformationNew();


    /**
     * 修改用户消息表为已读
     * @param map
     * @return
     */
    int updateInformationById(Map<String,Object> map);

    /**
     * 根据类别获取用户消息记录个数
     * @param map
     * @return
     */
    int getInformationNumByType(Map map);
}