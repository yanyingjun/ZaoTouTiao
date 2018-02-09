package com.zhishun.zaotoutiao.biz.service.user.impl;

/**
 * 贷款申请任务类
 *
 * @author 夏庆峰
 * @version $Id: LoanApplyTask, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2017年11月23日 16:49 夏庆峰 Exp $
 */
/*public class LoanApplyTask implements Runnable {

    *//**
     * 申请贷款统一服务
     *//*
    private LoanCommonBizService loanCommonBizService;

    *//**
     * 申请贷款统一消息VO
     *//*
    private LoanApplyVO loanApplyVO;

    *//**
     * 申请贷款统一返回服务
     *//*
    private LoanApplyResponseService loanApplyResponseService;

    public LoanApplyTask(LoanApplyVO loanApplyVO, LoanCommonBizService loanCommonBizService,
                         LoanApplyResponseService loanApplyResponseService) {
        this.loanCommonBizService = loanCommonBizService;
        this.loanApplyVO = loanApplyVO;
        this.loanApplyResponseService = loanApplyResponseService;
    }

    @Override
    public void run() {
        //执行真正申请放款操作
        LoanApplyOutDTO loanApplyOutDTO = loanCommonBizService.loanApply(loanApplyVO);
        //返回消息统一处理
        loanApplyResponseService.loanApplyResponse(loanApplyOutDTO);
    }
}*/
