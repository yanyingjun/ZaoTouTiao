/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.UserComments;
import com.zhishun.zaotoutiao.core.model.vo.InfosVo;

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
}
