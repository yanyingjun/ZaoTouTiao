package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.vo.InfosVo;
import com.zhishun.zaotoutiao.core.model.vo.UserCommentsVO;

import java.util.List;

/**
 * @author BugMan
 * @version $Id: ICommentService, v0.1 2018年02月27日 15:00BugMan Exp $
 */

public interface ICommentService {

    /**
     * 获取最新评论和评论点赞信息
     */
    List<InfosVo> getNewCommentVO(String infoId, int userId, int pageNo, int pageSize);

    /**
     * 获取热门评论和评论点赞信息
     */
    List<InfosVo> getHotCommentVO(String infoId, int userId, int pageNo, int pageSize);

    /**
     * 查看该评论自己是否点赞
     * @param userId
     * @param commentsId
     * @return
     */
    Boolean isMyLike (int userId, int commentsId);

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
