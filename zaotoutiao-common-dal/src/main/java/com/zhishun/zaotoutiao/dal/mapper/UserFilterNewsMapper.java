package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.UserFilterNews;

import java.util.Map;

public interface UserFilterNewsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserFilterNews record);

    int insertSelective(UserFilterNews record);

    UserFilterNews selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserFilterNews record);

    int updateByPrimaryKey(UserFilterNews record);

    /**
     * 判断是否添加该条数据
     * @param map
     * @return
     */
    int countUserFilterNewsByParam(Map map);
}