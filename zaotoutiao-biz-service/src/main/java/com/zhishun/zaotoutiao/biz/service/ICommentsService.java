/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.UserComments;
import com.zhishun.zaotoutiao.core.model.vo.InfosVo;
import com.zhishun.zaotoutiao.core.model.vo.UserCommentsVO;

import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ICommentsService, v0.1 2018年02月28日 18:10闫迎军(YanYingJun) Exp $
 */
public interface ICommentsService {

    /**
     * 获取我的评论列表
     * @param userId
     * @param pageRequest
     * @return
     */
    Page<InfosVo> getMyCommentsList(Long userId, PageRequest pageRequest);

    /**
     * 删除我的评论
     * @param userId
     * @return
     */
    int delMyComments(Long userId, String infoId);

    /**
     * 删除评论相关的点赞记录
     * @param userId
     * @param commentsId
     * @return
     */
    int delUserGiveLike(Long userId, Long commentsId);

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
     * 添加评论
     */
    int addComments(UserComments userComments);

    /**
     * 获取最新评论和评论点赞信息
     */
    List<UserCommentsVO> getNewCommentVO(String infoId, int userId, int pageNo, int pageSize);

    /**
     * 获取热门评论和评论点赞信息
     */
    List<UserCommentsVO> getHotCommentVO(String infoId, int userId, int pageNo, int pageSize);

    /**
     * 查看该评论自己是否点赞
     * @param userId
     * @param commentsId
     * @return
     */
    Boolean isMyLike (int userId, Long commentsId);

    /**
     * 获得新闻的评论数
     * @param infoId
     * @return
     */
    int getCommentsNumByInfoId(String infoId);

    /**
     * 获取新闻列表（可以根据关键词搜索）
     * @param keyword
     * @return
     */
    List<UserCommentsVO> getUserListOrByKey(String keyword);

    /**
     * 根据评论删除用户留言
     * @param id
     * @return
     */
    int delComments(Long id);
}
