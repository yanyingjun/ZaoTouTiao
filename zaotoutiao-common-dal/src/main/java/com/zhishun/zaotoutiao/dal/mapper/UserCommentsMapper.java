package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.UserComments;

import java.util.Map;

public interface UserCommentsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserComments record);

    int insertSelective(UserComments record);

    UserComments selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserComments record);

    int updateByPrimaryKey(UserComments record);

    /**
     * 根据参数获取点赞详情
     * @param map
     * @return
     */
    UserComments getUserCommnetsInfo(Map<String,Object> map);

    int getCommentsNumByInfoId(String infoId);
}