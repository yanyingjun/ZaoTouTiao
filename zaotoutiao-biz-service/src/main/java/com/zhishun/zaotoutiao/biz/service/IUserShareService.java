package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.entity.UserShare;

import java.util.Map;

/**
 * @author BugMan
 * @version $Id: IUserShareService, v0.1 2018年03月01日 18:10BugMan Exp $
 */

public interface IUserShareService {

    /**
     * 用户分享
     * @param userId
     * @param type
     * @param source
     * @param infoId
     * @param apprenticeId
     * @return
     */
    UserShare getUserShare(Long userId,String type ,String source,String infoId,String apprenticeId);

    /**
     * 用户分享成功后调用
     * @param userId
     * @param shareId
     * @return
     */
    Map<String,Object> shareSuccessGet(Long userId, Long shareId);
}
