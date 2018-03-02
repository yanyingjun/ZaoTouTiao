/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.entity.UserGoldRecord;

import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IGoldRecordService, v0.1 2018年02月28日 19:28闫迎军(YanYingJun) Exp $
 */
public interface IGoldRecordService {

    /**
     * 获取用户当天获取的金币总数
     * @param userId
     * @param source
     * @return
     */
    int getOpenGoldToday(Long userId, int source);

    /**
     * 计算时间差
     * @param userId
     * @return
     */
    Map leadTime(Long userId);

    /**
     * 计算下次开宝箱时间
     * @param userId
     * @return
     */
    Map leadTimeTwo(Long userId);

    /**
     * 用户金币记录
     * @param userId
     * @return
     */
    List<UserGoldRecord> listUserGoldRecord(Long userId);

    /**
     * 返回徒弟分享获得的金币数量
     * @param userId
     * @param shareId
     * @param source
     * @param gold
     * @return
     */
    int getShareRecruitGold(Long userId, Long shareId, Long source, int gold);

    /**
     * 返回文章分享获得的金币数
     * @param userId
     * @param shareId
     * @param source
     * @param gold
     * @return
     */
    int getShareArticleGold(Long userId, Long shareId, Long source, int gold);

    /**
     * 获取晒收入分享(当天)
     * @param userId
     * @param shareId
     * @param source
     * @param gold
     * @return
     */
    int getShareIncomeGold(Long userId, Long shareId, Long source, int gold);
}
