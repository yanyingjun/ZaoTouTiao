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
     * 删除用户全部收藏
     * @param userId
     * @return
     */
    int delUserCollect(Long userId);

    /**
     * 查询收藏列表
     * @param map
     * @return
     */
    List<UserCollect> listCollect(Map<String,Object> map);

    /**
     * 删除单条收藏
     * @param map
     * @return
     */
    int delOneCollect(Map<String,Object> map);

    /**
     * 获取用户新闻收藏列表
     * @param map
     * @return
     */
    UserCollect getUserCollectByParam(Map<String,Object> map);
}