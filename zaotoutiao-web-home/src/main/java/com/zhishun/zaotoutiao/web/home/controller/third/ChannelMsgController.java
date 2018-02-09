package com.zhishun.zaotoutiao.web.home.controller.third;

import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.service.user.UserBizService;
import com.zhishun.zaotoutiao.web.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.web.home.constant.request.ChannelMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.ChannelMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


/**
 * 渠道配置表 - 控制层
 *
 * @author 王家龙（WangJiaLong）
 * @version $Id: ChannelMsgVO, v0.1 2017年08月17日 20:30 王家龙（WangJiaLong） Exp $
 */
@Controller
public class ChannelMsgController extends BaseController {


    @Autowired
    private UserBizService userBizService;


    /**
     * 分页管理渠道配置表
     *
     * @param modelMap        模型视图
     * @param request         服务请求
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.CHANNEL_ALLOCATION_MANAGE_REQ, method = RequestMethod.GET)
    public String manage(final ModelMap modelMap,
                         HttpServletRequest request) {

        this.excute(modelMap, request, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }
            @Override
            public void handle() throws Exception {

                // 结果值存储
                modelMap.put("channelAllocationQuery", null);
            }

        });

        return ChannelMsgView.CHANNEL_ALLOCATION_MANAGE_VIEW;
    }



    /**
     * 渠道配置单个查询
     * @param modelMap
     * @param request
     * @param channelCode
     * @return
     */
    /*@RequestMapping(value = ChannelMsgReq.CHANNEL_ALLOCATION_SEARCH_REQ, method = RequestMethod.GET)
    @ResponseBody
    public Map<Object, Object> search(final ModelMap modelMap,
                         HttpServletRequest request, final String channelCode) {

        // 定义Map集合对象
        final Map<Object, Object> dataMap = Maps.newHashMap();

        this.excute(modelMap, request, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }
            @Override
            public void handle() throws Exception {

                ChannelMsgVO channelMsgVO = channelMsgBizService.getByChannelCode(channelCode);

                // 结果值存储
                dataMap.put("channelAllocationVO", channelMsgVO);
            }

        });

        return dataMap;
    }*/

    /**
     * 渠道配置表集合查询
     *
     * @param channelMsgQuery 渠道配置表 - Query对象
     * @return JSON
     */
    /*@RequestMapping(value = ChannelMsgReq.CHANNEL_ALLOCATION_QUERY_PAG_REQ, method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> queryList(@ModelAttribute final ChannelMsgQuery channelMsgQuery) {

        // 定义Map集合对象
        final Map<Object, Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }
            @Override
            public void handle() throws Exception {

                // 执行分页查询
                PageBean<ChannelMsgVO> pageBean = channelMsgBizService.queryPageByParam(channelMsgQuery);

                // 结果值存储
                dataMap.put("channelAllocationVOList", pageBean.getResultObj());
                dataMap.put("totalCount", pageBean.getTotalCount());
            }

        });

        return dataMap;
    }*/

    /**
     * 每日额度集合查询
     *
     * @param dailyLimitQuery 每日额度配置表 - Query对象
     * @return JSON
     */
    /*@RequestMapping(value = ChannelMsgReq.DAILY_LIMIT_QUERY_PAG_REQ, method = RequestMethod.POST)
    @ResponseBody*/
    /*public Map<Object, Object> queryList(@ModelAttribute final DailyLimitQuery dailyLimitQuery) {

        // 定义Map集合对象
        final Map<Object, Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }
            @Override
            public void handle() throws Exception {
                *//*if(StringUtil.isNotEmpty(dailyLimitQuery.getMarkYear()) && StringUtil.isNotEmpty(dailyLimitQuery.getMarkMonth())){
                    dailyLimitQuery.setMarkDay(dailyLimitQuery.getMarkYear() + "-" + dailyLimitQuery.getMarkMonth());
                }
                // 执行分页查询
                PageBean<DailyLimitVO> pageBean = dailyLimitBizService.queryPageByParam(dailyLimitQuery);

                // 结果值存储
                dataMap.put("dailyLimitVOList", pageBean.getResultObj());
                dataMap.put("totalCount", pageBean.getTotalCount());*//*
            }
        });

        return dataMap;
    }*/

    /**
     * 渠道配置表添加
     *
     * @param channelMsgVO 渠道配置表 - VO对象
     * @return 添加结果
     */
    /*@RequestMapping(value = ChannelMsgReq.CHANNEL_ALLOCATION_INSERT_REQ, method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> insert(final HttpServletRequest request,@ModelAttribute final ChannelMsgVO channelMsgVO) {

        // 定义Map集合对象
        final Map<Object, Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, ErrorCodeEnum.CHANNEL_ALLOCATION_INSERT_IS_SUCCESS, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                *//*ChannelMsgVO findByName = channelMsgBizService.getByChannelName(channelMsgVO.getChannelName());
                AssertsUtil.isTrue(findByName == null, ErrorCodeEnum.CHANNEL_ALLOCATION_NAME_EXIST);
                ChannelMsgVO findByCode = channelMsgBizService.getByChannelCode(channelMsgVO.getChannelCode());
                AssertsUtil.isTrue(findByCode == null, ErrorCodeEnum.CHANNEL_ALLOCATION_CODE_EXIST);*//*
            }

            @Override
            public void handle() throws Exception {

                *//*// 获取当前登录账号
                String userAccount = CookieTool.getInstance().getAccount(request);
                channelMsgVO.setCreator(userAccount);
                channelMsgVO.setModifier(userAccount);

                //设置合同类型
                channelMsgVO.setContractType(ContractTypesUtils.getContractTypes(channelMsgVO.getChannelCode()));

                int total = channelMsgBizService.insert(channelMsgVO);

                // 结果校验
                AssertsUtil.isNotZero(total, ErrorCodeEnum.CHANNEL_ALLOCATION_INSERT_IS_FAIL);
                AssertsUtil.isFalse(total == -1, ErrorCodeEnum.CHANNEL_ALLOCATION_INSERT_REPEAT);*//*
            }
        });

        return dataMap;
    }*/

    /**
     * 每日额度配置表添加
     *
     * @param dailyLimitVO 渠道配置表 - VO对象
     * @return 添加结果
     */
    /*@RequestMapping(value = ChannelMsgReq.DAILY_LIMIT_INSERT_REQ, method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> insert(final HttpServletRequest request, @ModelAttribute final DailyLimitVO dailyLimitVO) {

        // 定义Map集合对象
        final Map<Object, Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, ErrorCodeEnum.DAILY_LIMIT_INSERT_IS_SUCCESS, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                *//*DailyLimitVO dailyLimit = dailyLimitBizService.getDailyLimitByMarkDay(dailyLimitVO);
                AssertsUtil.isTrue(null == dailyLimit, ErrorCodeEnum.DAILY_LIMIT_INSERT_REPEAT);*//*
            }
            @Override
            public void handle() throws Exception {
                *//*dailyLimitVO.setAvailableAmount(dailyLimitVO.getAvailableAmount().multiply(new BigDecimal(10000)));
                dailyLimitVO.setResidualAmount(dailyLimitVO.getAvailableAmount());
                //获取当前登录账号
                String userAccount = CookieTool.getInstance().getAccount(request);
                dailyLimitVO.setCreator(userAccount);
                dailyLimitVO.setModifier(userAccount);
                // 执行插入
                int total = dailyLimitBizService.insert(dailyLimitVO);

                // 结果校验
                AssertsUtil.isNotZero(total, ErrorCodeEnum.DAILY_LIMIT_INSERT_IS_FAIL);
                AssertsUtil.isFalse(total == -1, ErrorCodeEnum.DAILY_LIMIT_INSERT_REPEAT);*//*
            }
        });

        return dataMap;
    }*/

}