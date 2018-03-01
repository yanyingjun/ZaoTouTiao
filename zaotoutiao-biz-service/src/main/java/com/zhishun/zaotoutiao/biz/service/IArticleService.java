package com.zhishun.zaotoutiao.biz.service;


import com.zhishun.zaotoutiao.core.model.entity.*;
import com.zhishun.zaotoutiao.core.model.vo.StaticFaqVO;

import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IArticleService, v0.1 2018年02月26日 18:34闫迎军(YanYingJun) Exp $
 */
public interface IArticleService {

    /**
     * 添加评论
     */
    int addComments(UserComments userComments);

    /**
     * 根据ID获取评论
     * @param id
     * @return
     */
    UserComments getUserCommonets(Long id);

    /**
     * 判断是否点过赞
     * @param userId
     * @Param commentsId
     * @return
     */
    UserGiveLike getUserGiveLike(Long userId, Long commentsId);

    /**
     * 更新是否点赞
     * @param userId
     * @param commentsId
     * @return
     */
    int upateUserGiveLike(UserGiveLike userGiveLike);

    /**
     * 获取评论点赞数
     * @param commentsId
     * @return
     */
    int countUserGiveLike(Long commentsId);

    /**
     * 修改点赞人数
     * @param commentsId
     * @param likes
     * @return
     */
    int updateUserComments(UserComments userComments);

    /**
     * 获取点赞详情
     * @param id
     * @param userId
     * @return
     */
    UserComments getUserCommonetsInfo(Long id, Long userId);

    /**
     * 新增点赞
     * @param userId
     * @param commentsId
     * @return
     */
    int addUserGiveLike(Long userId, Long commentsId);

    /**
     * 是否获得奖励
     * @param commentsId
     * @param likes
     */
    void isCommentsLikeGold(Long commentsId, int likes);

    /**
     * 获取新闻内容
     * @param infoId
     * @return
     */
    Content getContent(Long infoId);

    /**
     * 获取文章类常见问题
     * @return
     */
    List<StaticFaqVO> listFaq();

    /**
     * 怎样获取金币奖励
     * @return
     */
    List<StaticGetGoldMethod> listGoldMethod();
}
