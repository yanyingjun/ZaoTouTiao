package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.UserGiveLike;

import java.util.Map;

public interface UserGiveLikeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserGiveLike record);

    int insertSelective(UserGiveLike record);

    UserGiveLike selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserGiveLike record);

    int updateByPrimaryKey(UserGiveLike record);

    /**
     * 根据参数查询点赞记录
     * @param map
     * @return
     */
    UserGiveLike getUserGiveLike(Map<String,Object> map);

    /**
     * 获取评论点赞数
     * @param commentsId
     * @return
     */
    int countUserGiveLike(Long commentsId);
}