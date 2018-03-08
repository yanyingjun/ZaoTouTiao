/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.user;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.UserVO;
import com.zhishun.zaotoutiao.dal.mapper.UserMapper;
import com.zhishun.zaotoutiao.web.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.web.home.constant.request.ZttWebMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.ZttWebMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserController, v0.1 2018年02月24日 11:31闫迎军(YanYingJun) Exp $
 */
@Controller
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;


    @RequestMapping(value = ZttWebMsgReq.ZTT_CAN_BE_PRESENTED_USER_REQ)
    public ModelAndView canBePresentedUser(){
        ModelAndView mv = new ModelAndView(ZttWebMsgView.ZTT_CAN_BE_PRESENTED_USER_VIEW);
        return mv;
    }

    /**
     * 获取可提现用户列表
     * @param keyWord
     * @param channelId
     * @param createDate
     * @param money
     * @param pageRequest
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_CAN_BE_PRESENTED_USER_LIST_REQ)
    @ResponseBody
    public Map<Object,Object> listCanBePresentedUser(final String keyWord, final String channelId, final String createDate, final BigDecimal money, final PageRequest pageRequest){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                Page<UserVO> page = userService.listCanBePresentedUser(keyWord, channelId, createDate, money, pageRequest);
                dataMap.put("total", page.getTotal());
                dataMap.put("rows", page.getRows());
            }
        });

        return dataMap;
    }
}
