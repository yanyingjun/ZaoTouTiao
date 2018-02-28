package com.zhishun.zaotoutiao.dal.mapper;

import com.zhishun.zaotoutiao.core.model.entity.UserCollect;

public interface UserCollectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCollect record);

    int insertSelective(UserCollect record);

    UserCollect selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCollect record);

    int updateByPrimaryKey(UserCollect record);
}