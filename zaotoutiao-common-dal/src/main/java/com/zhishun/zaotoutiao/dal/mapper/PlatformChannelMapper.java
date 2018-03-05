package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.PlatformChannel;

import java.util.List;

public interface PlatformChannelMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PlatformChannel record);

    int insertSelective(PlatformChannel record);

    PlatformChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlatformChannel record);

    int updateByPrimaryKey(PlatformChannel record);

    /**
     * 获取平台渠道记录
     * @param platformId
     * @return
     */
    List<PlatformChannel> listPlatformChannelByPlatformId(int platformId);

}