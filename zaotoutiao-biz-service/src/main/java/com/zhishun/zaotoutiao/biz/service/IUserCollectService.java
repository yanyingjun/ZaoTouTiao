/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.entity.UserCollect;

import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IUserCollectService, v0.1 2018年02月28日 17:33闫迎军(YanYingJun) Exp $
 */
public interface IUserCollectService {


    /**
     * 删除全部收藏
     * @param userId
     * @return
     */
    int delAllCollect(Long userId);

    /**
     * 查询收藏列表
     * @param userId
     * @param infosId
     * @return
     */
    List<UserCollect> listCollect(Long userId, String infosId);

    /**
     * 删除收藏（单条）
     * @param userId
     * @param infosId
     * @return
     */
    int delOneCollect(Long userId, String infosId);

    /**
     * 添加收藏
     * @param userId
     * @param infosId
     * @return
     */
    int addUserCollect(Long userId, String infosId);

    /**
     * 是否收藏
     * @param userId
     * @param infoId
     * @return
     */
    boolean isCollect(Long userId, String infoId);

}
