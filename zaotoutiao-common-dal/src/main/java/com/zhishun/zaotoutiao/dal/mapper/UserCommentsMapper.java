package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.UserComments;
import com.zhishun.zaotoutiao.core.model.vo.UserCommentsVO;

import java.util.List;
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

    /**
     * 获取某条新闻的全部评论数
     * @param infoId
     * @return
     */
    int getCommentsNumByInfoId(String infoId);

    /**
     * 获取所有新闻列表
     * @return
     */
    List<UserComments> getCommentsList();

    /**
     * 根据搜索关键词获得相关新闻列表
     * @return
     */
    List<UserComments> getCommentsByKeywordList(String keyword);

    /**
     * 获取最新评论和评论点赞信息
     * @param map
     * @return
     */
    List<UserCommentsVO> getNewComments(Map<String,Object> map);

    /**
     * 获取热门评论和评论点赞信息
     * @param map
     * @return
     */
    List<UserCommentsVO> getHotComments(Map<String,Object> map);
}