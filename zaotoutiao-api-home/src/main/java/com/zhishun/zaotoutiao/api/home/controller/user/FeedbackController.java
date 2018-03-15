/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.user;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.UserMsgReq;
import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.core.model.entity.UserFeedbackFaq;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: FeedbackController, v0.1 2018年03月01日 16:57闫迎军(YanYingJun) Exp $
 */

@RestController
public class FeedbackController extends BaseController{


    @Autowired
    private IUserService userService;

    /**
     * 建议与反馈常见问题列表
     * @return
     */
    @RequestMapping(value = UserMsgReq.FEEDBACK_FAQ_GET)
    public Map<Object,Object> feedbackFaqGet(){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
            }

            @Override
            public void handle() throws Exception {

                List<UserFeedbackFaq> list = userService.listFeedbackFaq();
                dataMap.put("result", "success");
                dataMap.put("msg", "相关信息返回成功");
                dataMap.put("data", list);
            }
        });

        return dataMap;
    }

    /**
     * 建议与反馈用户提交表
     * @return
     */
    @RequestMapping(value = UserMsgReq.FEEDBACK_PUBLISH_ADD)
    public Map<Object,Object> feedbackPublishAdd(final Long userId, final String question, final String imgPath){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
            }

            @Override
            public void handle() throws Exception {
                userService.addUserFeedbackPublish(userId, question, imgPath);
                dataMap.put("result", "success");
                dataMap.put("msg", "相关信息返回成功");
                dataMap.put("data", true);
            }
        });

        return dataMap;
    }

}
