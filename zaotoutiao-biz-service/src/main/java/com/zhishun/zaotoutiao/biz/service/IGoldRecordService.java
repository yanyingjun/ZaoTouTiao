/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.UserGoldRecord;
import com.zhishun.zaotoutiao.core.model.vo.ApprenticeRepVO;
import com.zhishun.zaotoutiao.core.model.vo.UserGoldRecordVO;

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

    /**
     * 统计用户所获得的金币和零钱
     * @param type
     * @param startDate
     * @param endDate
     * @return
     */
    Map countUserGoldAndMoney(String type, String startDate, String endDate);

    /**
     * 按日期分组统计金币数量
     * @param channelId 渠道
     * @param source 金币来源
     * @param type 时间段
     * @param isNewAndOld 新老用户
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<UserGoldRecordVO> listGoldCount(int channelId, String source, String type, String isNewAndOld, String startDate, String endDate);

    /**
     * 按金币来源统计金币数量
     * @param map
     * @return
     */
    List<UserGoldRecordVO> listGoldCountBySource(String isNewAndOld, String type, String startDate, String endDate);

    /**
     * 获取活动时间内有效徒弟数量
     * @param map
     * @return
     */
    int getEffectiveApprenticeNum(Long userId, String startDate, String endDate);

    /**
     * 获取活动时间内累计获得的奖励
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    int getActivityGoldSum(Long userId, String startDate, String endDate);

    /**
     * 获取未唤醒徒弟数量
     * @param map
     * @return
     */
    List<ApprenticeRepVO> listAwakenApprentice(Long userId, PageRequest pageRequest);

    /**
     * 获取有效徒弟列表
     * @param map
     * @return
     */
    List<ApprenticeRepVO> listEffectiveApprentice(Long userId, PageRequest pageRequest);

    /**
     * 获取我的徒弟列表
     * @param map
     * @return
     */
    List<ApprenticeRepVO> listMyApprentice(Long userId, PageRequest pageRequest);
}
