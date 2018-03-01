/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service;

import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IStaticFakeDataService, v0.1 2018年02月28日 21:19闫迎军(YanYingJun) Exp $
 */
public interface IStaticFakeDataService {

    /**
     * 获取周排行
     * @return
     */
    List<Map> weekRankings();
}
