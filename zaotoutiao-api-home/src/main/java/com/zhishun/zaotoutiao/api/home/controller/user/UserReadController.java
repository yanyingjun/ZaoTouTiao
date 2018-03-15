/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.api.home.controller.user;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.NewsMsgReq;
import com.zhishun.zaotoutiao.api.home.request.UserMsgReq;
import com.zhishun.zaotoutiao.biz.service.IInformationService;
import com.zhishun.zaotoutiao.biz.service.IJpushService;
import com.zhishun.zaotoutiao.biz.service.IUserReadService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.core.model.entity.UserReadRecord;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author BugMan
 * @version $Id: UserReadController, v0.1 2018年03月02日 17:22BugMan Exp $
 */

@RestController
public class UserReadController extends BaseController {

    @Autowired
    private IUserReadService iUserReadService;

    @Autowired
    private IInformationService iInformationService;

    @Autowired
    private IJpushService iJpushService;

    /**
     * 用户已经阅读(24小时热文)，更改阅读状态
     * @param id
     * @param userId
     * @param type
     * @return
     */
    @RequestMapping(value = UserMsgReq.IS_READ, method = RequestMethod.POST)
    public Map<Object,Object> isRead(final String id, final Long userId, final String type){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(id, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotNull(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotNull(type, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                Boolean result = iUserReadService.isUserRead(id, userId, type);
                if(result){
                    dataMap.put("result", "success");
                    dataMap.put("msg", "阅读状态修改成功");
                    dataMap.put("data","true");
                }else{
                    dataMap.put("result", "failure");
                    dataMap.put("msg", "阅读状态修改失败");
                    dataMap.put("data","false");
                }
            }
        });

        return dataMap;
    }

    /**
     * 阅读记录添加
     * @param userId
     * @param infoId
     * @param infoType
     * @return
     */
    @RequestMapping(value = NewsMsgReq.READ_RECORD_ADD, method = RequestMethod.POST)
    public Map<Object,Object> readRecordAdd(final UserReadRecord userReadRecord){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(userReadRecord.getUserId(), ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotNull(userReadRecord.getInfoId(), ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotNull(userReadRecord.getInfoType(), ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotNull(userReadRecord.getChannelId(), ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotNull(userReadRecord.getLabel(), ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotNull(userReadRecord.getTitle(), ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotNull(userReadRecord.getSource(), ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotNull(userReadRecord.getImgUrl(), ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotNull(userReadRecord.getUrl(), ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                int res = iUserReadService.readRecordAdd(userReadRecord);
                if(res == 1) {
                    dataMap.put("result", "success");
                    dataMap.put("msg", "阅读记录添加成功");
                    dataMap.put("data", "true");
                }else{
                    dataMap.put("result", "failure");
                    dataMap.put("msg", "阅读记录添加失败");
                    dataMap.put("data", "false");
                }
            }
        });

        return dataMap;
    }


    /**
     * 获取未读消息公告条数
     * @param userId
     * @return
     */
    @RequestMapping(value = UserMsgReq.UNREAD_MSG_NUM_GET, method = RequestMethod.GET)
    public Map<Object,Object> unreadMsgNumGet( final Long userId){
        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                int msgNum = iInformationService.selectUserInformationByTypeOrUserId(userId,"MSG");
                int noticeNum = iInformationService.selectUserInformationByTypeOrUserId(userId,"NOTICE");
                if(msgNum != 0 || noticeNum != 0){
                    dataMap.put("result", "success");
                    dataMap.put("msg", "您有未读消息");
                    dataMap.put("data","true");
                }else{
                    dataMap.put("result", "failure");
                    dataMap.put("msg", "没有未读消息");
                    dataMap.put("data","false");
                }
                dataMap.put("unReadMsgNum",msgNum);
                dataMap.put("unReadNoticeNum",noticeNum);

            }
        });

        return dataMap;
    }


    /**
     * 获取未读热文条数
     * @param userId
     * @return
     */
    @RequestMapping(value = UserMsgReq.UNREAD_HOT_NUM_GET, method = RequestMethod.GET)
    public Map<Object,Object> unreadHotNumGet( final Long userId){
        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                int hotNum = iJpushService.unreadHotNumGet(userId);
                if(hotNum != 0){
                    dataMap.put("result", "success");
                    dataMap.put("msg", "您有未读消息");
                    dataMap.put("data","true");
                }else{
                    dataMap.put("result", "failure");
                    dataMap.put("msg", "没有未读消息");
                    dataMap.put("data","false");
                }
                dataMap.put("unReadHotNum",hotNum);
            }
        });

        return dataMap;
    }
}
