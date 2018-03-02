package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.UserOperationInfo;

public interface UserOperationInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserOperationInfo record);

    int insertSelective(UserOperationInfo record);

    UserOperationInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserOperationInfo record);

    int updateByPrimaryKey(UserOperationInfo record);
}