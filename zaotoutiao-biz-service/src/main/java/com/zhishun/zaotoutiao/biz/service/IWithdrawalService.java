package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.entity.StaticRedEnvelope;
import com.zhishun.zaotoutiao.core.model.entity.UserWithdrawalState;
import com.zhishun.zaotoutiao.core.model.vo.UserWithdrawalStateVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IWithdrawalService, v0.1 2018年03月01日 14:29闫迎军(YanYingJun) Exp $
 */
public interface IWithdrawalService {


    /**
     * 判断是否有正在申请中的提现
     * @param userId
     * @return
     */
    List<UserWithdrawalState> getHasApplication(Long userId);

    /**
     * 修改提现状态
     * @param userWithdrawalState
     * @return
     */
    int updateWithdrawalState(UserWithdrawalState userWithdrawalState);

    /**
     * 添加零钱提现申请
     * @param userId
     * @param money
     * @return
     */
    Map<String,Object> addWithdrawalState(Long userId, BigDecimal money, int status);

    /**
     * 兑换微信红包
     * @return
     */
    List<StaticRedEnvelope> listRedEnvelope();

    /**
     * 获取用户提现申请列表
     * @param keyWord
     * @param channelId
     * @param createDate
     * @param status
     * @return
     */
    List<UserWithdrawalStateVO> listWithdrawals(String keyWord, String channelId, String createDate, Integer status);
}
