/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.user;

import com.google.common.collect.Maps;
import com.sun.javafx.collections.MappingChange;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.UserMsgReq;
import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.common.util.Md5Util;
import com.zhishun.zaotoutiao.core.model.UserVO;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserRegisterController, v0.1 2018年02月23日 11:44闫迎军(YanYingJun) Exp $
 */
@RestController
public class UserController extends BaseController{


    @Autowired
    private IUserService userService;

    /**
     * 用户注册
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = UserMsgReq.USER_REGISTER_REQ, method = RequestMethod.POST)
    public Map<Object, Object> search(final ModelMap modelMap,
                                      HttpServletRequest request, final User user) {

        // 定义Map集合对象
        final Map<Object, Object> dataMap = Maps.newHashMap();

        this.excute(modelMap, request, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(user, ErrorCodeEnum.PARAMETER_ANOMALY);
            }
            @Override
            public void handle() throws Exception {
                //业务逻辑

            }

        });

        return dataMap;
    }

    /**
     * 查询电话是否已存在
     * @param modelMap
     * @param request
     * @param telephone
     * @return
     */
    @RequestMapping(value = UserMsgReq.USER_EXIST_ACCOUNT_REQ, method = RequestMethod.POST)
    public Map<Object,Object> existAccount(final ModelMap modelMap,
                                           HttpServletRequest request, final String telephone){

        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(modelMap, request, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(telephone, ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                User user = userService.getUserByMap(telephone);
                if(StringUtils.isEmpty(user)){
                    dataMap.put("result","success");
                    dataMap.put("msg","用户不存在，可以创建");
                }else{
                    dataMap.put("result","failure");
                    dataMap.put("msg","用户已存在，请直接登录");
                    dataMap.put("data",user);
                }
            }
        });
        return dataMap;
    }


    /**
     * 用户登录
     * @param modelMap
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = UserMsgReq.USER_LOGIN_REQ, method = RequestMethod.POST)
    public Map<Object,Object> login(final ModelMap modelMap,
                                    HttpServletRequest request, final UserVO user){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(modelMap, request, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(user.getTelephone(), ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotBlank(user.getPassword(), ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                //查询用户信息
                String password = Md5Util.md5Encode(user.getPassword());
                User userData = userService.getUserByMap(user.getTelephone());
                if(StringUtils.isEmpty(user)){
                    dataMap.put("result", "failure");
                    dataMap.put("msg", "用户不存在，请先注册");
                }else{
                    Boolean userLogin = userService.isUserLogin(user.getTelephone(), password);
                    if(userLogin == false){
                        dataMap.put("result", "failure");
                        dataMap.put("msg", "密码错误");
                    }else{
                        //更改用户登录状态为在线
                        dataMap.put("result", "success");
                        dataMap.put("msg", "用户存在，直接登录");
                        if(userData.getIsOnline() == 0){
                            dataMap.put("isFirstLogin", "true");
                        }else{
                            dataMap.put("isFirstLogin", "false");
                        }
                        userData.setIsOnline(1);
                        userService.updateUserInfo(userData);
                        dataMap.put("data", userData);
                    }
                }
            }
        });
        return dataMap;
    }

    /**
     * 忘记密码
     * @param modelMap
     * @param request
     * @param userVO
     * @return
     */
    @RequestMapping(value = UserMsgReq.USER_FORGET_PASSWORD_REQ, method = RequestMethod.POST)
    public Map<Object,Object> forgetPassword(final ModelMap modelMap, HttpServletRequest request, final UserVO userVO){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(modelMap, request, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(userVO.getTelephone(), ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotBlank(userVO.getPassword(), ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                String password = Md5Util.md5Encode(userVO.getTelephone());
                User user = userService.getUserByMap(userVO.getTelephone());
                if(StringUtils.isEmpty(user)){
                    dataMap.put("result", "failure");
                    dataMap.put("msg", "用户不存在，请先注册");
                }else{
                    //更新用户密码
                    user.setPassword(password);
                    userService.updateUserInfo(user);
                    dataMap.put("result", "success");
                    dataMap.put("msg", "修改密码成功");
                    dataMap.put("data", user);
                }
            }
        });

        return dataMap;
    }

    /**
     * 退出登录
     * @param modelMap
     * @param request
     * @param telephone
     * @return
     */
    @RequestMapping(value = UserMsgReq.USER_LOGOUT_REQ, method = RequestMethod.POST)
    public Map<Object,Object> logout(final ModelMap modelMap, HttpServletRequest request, final String telephone){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(modelMap, request, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(telephone,ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                User user = userService.getUserByMap(telephone);
                //更改用户状态为离线
                user.setIsOnline(0);
                userService.updateUserInfo(user);
                dataMap.put("result", "success");
                dataMap.put("msg", "成功退出");
                dataMap.put("data", user);
            }
        });

        return dataMap;
    }

}
