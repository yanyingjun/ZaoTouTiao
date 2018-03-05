package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.UserPlatform;

import java.util.List;

public interface UserPlatformMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPlatform record);

    int insertSelective(UserPlatform record);

    UserPlatform selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPlatform record);

    int updateByPrimaryKey(UserPlatform record);

    /**
     * 获取用户平台列表
     * @return
     */
    List<UserPlatform> listUserPlatform();
}