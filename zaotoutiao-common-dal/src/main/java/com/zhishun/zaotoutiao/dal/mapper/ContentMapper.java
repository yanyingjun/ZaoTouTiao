package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.Content;

import java.util.Map;

public interface ContentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);

    /**
     * 根据条件获取新闻内容
     * @param map
     * @return
     */
    Content getContent(Map<String,Object> map);

    Content selectByInfoId(String infoId);
}