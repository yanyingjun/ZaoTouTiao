/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.api.home.controller.news;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.NewsMsgReq;
import com.zhishun.zaotoutiao.biz.service.INewsService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.InfosVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author BugMan
 * @version $Id: NewsController, v0.1 2018年02月26日 13:19BugMan Exp $
 */
@RestController
public class NewsController extends BaseController{

    @Autowired
    private INewsService iNewsService;

    /**
     * 获取新闻列表
     * @param pageNo
     * @param pageSize
     * @param channelId
     * @return
     */
    @RequestMapping(value = NewsMsgReq.NEWS_GET_REQ , method = RequestMethod.GET)
    public Map<Object,Object> getNews(final int pageNo, final int pageSize, final int channelId){
        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(pageNo, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(pageSize, ErrorCodeEnum.PARAMETER_ANOMALY);

            }

            @Override
            public void handle() throws Exception {
                List<InfosVo> list = iNewsService.getInfosByType("article",channelId ,pageNo,pageSize);
                dataMap.put("result", "success");
                dataMap.put("msg", "获取新闻列表成功");
                dataMap.put("data", list);
            }
        });
        return dataMap;
    }
}
