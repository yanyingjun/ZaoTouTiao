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

    /**
     * 删除我的评论
     * @param map
     * @return
     */
    int delUserComments(Map<String,Object> map);

    /**
     * 获取用户新闻评论
     * @param infoId
     * @return
     */
    UserComments getUserCommentsByInfoId(String infoId);

    /**
     * 根据新闻ID获取新闻评论数
     * @param infoId
     * @return
     */
    int countUserComments(String infoId);

    int getCommentsNumByInfoId(String infoId);
}