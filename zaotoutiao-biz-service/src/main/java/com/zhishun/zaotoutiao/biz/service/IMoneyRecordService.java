package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.vo.AllRankingVO;
import com.zhishun.zaotoutiao.core.model.vo.UserGoldRecordVO;
import com.zhishun.zaotoutiao.core.model.vo.UserMoneyRecordVO;

import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IMoneyRecordService, v0.1 2018年02月28日 21:24闫迎军(YanYingJun) Exp $
 */
public interface IMoneyRecordService {

    /**
     * 总排行
     * @return
     */
    List<AllRankingVO> allRankings();

    /**
     * 周排行
     * @return
     */
    List<AllRankingVO> weekRankings();

    /**
     * 按日期分组统计零钱数量
     * @param channelId 渠道
     * @param source 金币来源
     * @param type 时间段
     * @param isNewAndOld 新老用户
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<UserMoneyRecordVO> listMoneyCount(int channelId, String source, String type, String isNewAndOld, String startDate, String endDate);

    /**
     * 按金币来源统计零钱数量
     * @param isNewAndOld
     * @param type
     * @param startDate
     * @param endDate
     * @return
     */
    List<UserMoneyRecordVO> listMoneyCountBySource(String isNewAndOld, String type, String startDate, String endDate);
}
