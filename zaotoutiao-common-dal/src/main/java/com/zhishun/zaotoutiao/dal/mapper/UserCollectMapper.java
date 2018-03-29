package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.UserCollect;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.List;
import java.util.Map;

public interface UserCollectMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserCollect record);

    int insertSelective(UserCollect record);

    UserCollect selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCollect record);

    int updateByPrimaryKey(UserCollect record);

    /**
     * 获取收藏列表
     * @param map
     * @return
     */
    List<UserCollect> getCollectList(Map<String, Object> map);

    /**
     * 根据获得收藏数
     * @param map infoId
     * @return
     */
    Long getNumByInfoId(Map<String,Object> map);
}