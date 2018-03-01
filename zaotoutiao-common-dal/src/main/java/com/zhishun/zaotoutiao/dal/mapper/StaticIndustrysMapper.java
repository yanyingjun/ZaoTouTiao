/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.dal.mapper;

import com.zhishun.zaotoutiao.core.model.entity.StaticIndustrys;
import com.zhishun.zaotoutiao.core.model.vo.StaticIndustrysVO;

import java.util.List;

public interface StaticIndustrysMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_industrys
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_industrys
     *
     * @mbggenerated
     */
    int insert(StaticIndustrys record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_industrys
     *
     * @mbggenerated
     */
    int insertSelective(StaticIndustrys record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_industrys
     *
     * @mbggenerated
     */
    StaticIndustrys selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_industrys
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(StaticIndustrys record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_industrys
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(StaticIndustrys record);

    /**
     * 获取职业列表
     * @return
     */
    List<StaticIndustrysVO> listStaticIndustrys();
}