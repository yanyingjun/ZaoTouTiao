/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.dal.mapper;

import com.zhishun.zaotoutiao.core.model.entity.StaticHtml;

public interface StaticHtmlMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_html
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long staticId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_html
     *
     * @mbggenerated
     */
    int insert(StaticHtml record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_html
     *
     * @mbggenerated
     */
    int insertSelective(StaticHtml record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_html
     *
     * @mbggenerated
     */
    StaticHtml selectByPrimaryKey(Long staticId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_html
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(StaticHtml record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_html
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(StaticHtml record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table static_html
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(StaticHtml record);

    /**
     * 获取新闻详情
     * @param name
     * @return
     */
    StaticHtml getStaticHtml(String name);
}