package com.zhishun.zaotoutiao.biz.service;

/**
 * @author BugMan
 * @version $Id: IUserReadService, v0.1 2018年03月02日 17:27BugMan Exp $
 */

public interface IUserReadService {

    /**
     * 用户已经阅读(24小时热文)，更改阅读状态并返回是否成功
     * @param id
     * @param userId
     * @param type
     * @return
     */
    boolean isUserRead(String id, Long userId, String type);

    /**
     * 新闻阅读记录添加
     * @param userId
     * @param infoId
     * @param infoType
     */
    void readRecordAdd(Long userId,String infoId ,String infoType);
}
