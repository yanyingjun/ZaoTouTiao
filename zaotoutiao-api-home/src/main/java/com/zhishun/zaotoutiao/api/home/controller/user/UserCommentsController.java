/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.user;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.ArticleMsgReq;
import com.zhishun.zaotoutiao.biz.service.ICommentsService;
import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.core.model.entity.UserComments;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.InfosVO;
import com.zhishun.zaotoutiao.core.model.vo.UserReadRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserCommentsController, v0.1 2018年02月28日 18:12闫迎军(YanYingJun) Exp $
 */
@RestController
public class UserCommentsController extends BaseController{

    @Autowired
    private ICommentsService commentsService;


    /**
     * 输入评论
     * @param userComments
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.ADD_COMMENTS_REQ, method = RequestMethod.POST)
    public Map<Object,Object> addComments(final UserComments userComments){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(userComments, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                commentsService.addComments(userComments);
                dataMap.put("result", "success");
                dataMap.put("msg", "评论成功");
            }
        });

        return dataMap;
    }


    /**
     * 我的评论列表
     * @param userId
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.MY_COMMENTS_GET)
    public Map<Object,Object> myCommentsGet(final Long userId, final PageRequest pageRequest){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                List<UserReadRecordVO> list = commentsService.getMyCommentsList(userId, pageRequest);
                dataMap.put("result", "success");
                dataMap.put("msg", "相关信息返回成功");
                dataMap.put("data", list);

            }
        });

        return dataMap;

    }

    /**
     * 删除我的评论
     * @param userId
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.MY_COMMENTS_DEL)
    public Map<Object,Object> myCommentsDel(final Long userId, final Long commentsId, final String infoId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotZero(commentsId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotNull(infoId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                int res = commentsService.delMyComments(userId, infoId);
                commentsService.delUserGiveLike(userId, commentsId);
                if(res == 1) {
                    dataMap.put("result", "success");
                    dataMap.put("msg", "返回信息成功");
                }else{
                    dataMap.put("result", "failure");
                    dataMap.put("msg", "删除失败");
                }
            }
        });

        return dataMap;
    }

    /**
     * 清空我的评论
     * @param userId
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.Empty_MY_COMMENTS)
    public Map<Object,Object> emptyMyComments(final Long userId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                commentsService.delMyComments(userId, null);
                commentsService.delUserGiveLike(userId, null);
                dataMap.put("result", "success");
                dataMap.put("msg", "清空成功");
            }
        });

        return dataMap;
    }
}
