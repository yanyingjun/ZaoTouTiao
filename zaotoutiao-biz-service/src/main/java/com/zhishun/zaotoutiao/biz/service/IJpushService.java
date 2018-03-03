package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.entity.UserJpush;

import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IJpushService, v0.1 2018年03月01日 12:22闫迎军(YanYingJun) Exp $
 */
public interface IJpushService {

    /**
     * 获取用户推送信息
     * @param userId
     * @param infoId
     * @return
     */
    UserJpush getUserJpush(Long userId, String infoId);

    /**
     * 判断是否是今天
     * @param time
     * @return
     */
    int isToday(String time);

    /**
     * 获取未读热文条数
     * @param userId
     * @return
     */
    int unreadHotNumGet(Long userId);
}
