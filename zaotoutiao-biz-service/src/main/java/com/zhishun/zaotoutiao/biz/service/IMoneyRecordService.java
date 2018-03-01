package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.vo.AllRankingVO;

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
}
