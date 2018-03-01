/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.user;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.ArticleMsgReq;
import com.zhishun.zaotoutiao.api.home.request.UserMsgReq;
import com.zhishun.zaotoutiao.biz.service.ICommentsService;
import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.core.model.entity.UserCollect;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.InfosVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
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
                Page<InfosVo> page = commentsService.getMyCommentsList(userId, pageRequest);
                dataMap.put("result", "success");
                dataMap.put("msg", "相关信息返回成功");
                dataMap.put("data", page.getRows());

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
    public Map<Object,Object> myCommentsDel(final Long userId, final Long commentsId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                commentsService.delMyComments(userId, commentsId);
                commentsService.delUserGiveLike(userId, commentsId);
                dataMap.put("result", "success");
                dataMap.put("msg", "返回信息成功");
            }
        });

        return dataMap;
    }
}
