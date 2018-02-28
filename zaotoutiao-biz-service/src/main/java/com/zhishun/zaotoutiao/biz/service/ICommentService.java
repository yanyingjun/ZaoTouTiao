package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.vo.InfosVo;

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
}
