package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.PlatformChannel;

public interface PlatformChannelMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PlatformChannel record);

    int insertSelective(PlatformChannel record);

    PlatformChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlatformChannel record);

    int updateByPrimaryKey(PlatformChannel record);
}