package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.UserCollect;

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
     * 根据新闻ID删除
     * @param map
     * @return
     */
    int delOneCollect(Map map);

    /**
     * 根据新闻ID查询
     * @param map
     * @return
     */
    UserCollect getCollectByMap(Map map);

    /**
     * 删除用户所有收藏
     * @param userId
     */
    void delUserCollectByUserId(Long userId);

    /**
     * 根据获得收藏数
     * @param map infoId
     * @return
     */
    Long getNumByInfoId(Map<String,Object> map);
}

