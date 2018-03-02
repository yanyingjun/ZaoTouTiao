package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.UserInformation;

import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IInformationService, v0.1 2018年03月01日 14:02闫迎军(YanYingJun) Exp $
 */
public interface IInformationService {

    /**
     * 获取用户消息记录
     * @param userId
     * @param pageRequest
     * @return
     */
    List<UserInformation> listInformationPage(Long userId, String type, PageRequest pageRequest);

    /**
     * 获取新用户消息模版
     * @return
     */
    List<UserInformation> listInformationNew();

    /**
     * 添加消息和公告
     * @param userInformation
     * @return
     */
    int addUserInformation(UserInformation userInformation);
}
