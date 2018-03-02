package com.zhishun.zaotoutiao.biz.service;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IFilterNewsService, v0.1 2018年03月01日 13:33闫迎军(YanYingJun) Exp $
 */
public interface IFilterNewsService {

    /**
     * 判断是否添加该条数据
     * @param userId
     * @param infoId
     * @param type
     * @return
     */
    int countUserFilterNewsByParam(Long userId, String infoId, String type);

    /**
     * 添加文章过滤记录
     * @param userId
     * @param infoId
     * @param type
     * @param content
     * @return
     */
    int addUserFilterNews(Long userId, String infoId, String type, String content);
}
