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
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.common.util.SendMsgUtil;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: SendSmsController, v0.1 2018年02月27日 15:55闫迎军(YanYingJun) Exp $
 */
@RestController
public class SendSmsController extends BaseController{

    @Autowired
    private IUserService userService;
    /**
     * 发送验证码
     * @param telephone
     * @return
     */
    @RequestMapping(value = UserMsgReq.SEND_SMS_CODE, method = RequestMethod.GET)
    public Map<Object,Object> sendSmsCode(final String telephone){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(telephone, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                User user = userService.getUserByMap(telephone);
                if(StringUtils.isEmpty(user)){
                    //判断是否绑定过微信
                    if(StringUtils.isEmpty(user.getWechatId())){
                        dataMap.put("whether_binding_wechat", "false");
                        //生成四位随机数
                        int code = new Random().nextInt(9000) + 1000;
                        //发送验证码
                        SendMsgUtil.sendSms(telephone, String.valueOf(code));
                        dataMap.put("telephone", telephone);
                        dataMap.put("code", code);
                        dataMap.put("result", "success");
                        dataMap.put("msg", "验证码发送成功");
                    }else{
                        dataMap.put("whether_binding_wechat", "true");
                    }
                    dataMap.put("whether_has_user", "false");
                }else{
                    //生成四位随机数
                    int code = new Random().nextInt(9000) + 1000;
                    //发送验证码
                    SendMsgUtil.sendSms(telephone, String.valueOf(code));
                    dataMap.put("telephone", telephone);
                    dataMap.put("code", code);
                    dataMap.put("result", "success");
                    dataMap.put("msg", "验证码发送成功");
                    dataMap.put("whether_has_user", "true");
                }
            }
        });

        return dataMap;
    }
}
