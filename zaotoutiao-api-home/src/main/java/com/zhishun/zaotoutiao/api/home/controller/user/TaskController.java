/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.api.home.controller.user;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.TaskMsgReq;
import com.zhishun.zaotoutiao.biz.service.IExchangeRateService;
import com.zhishun.zaotoutiao.biz.service.ITaskService;
import com.zhishun.zaotoutiao.core.model.entity.ExchangeRate;
import com.zhishun.zaotoutiao.core.model.entity.StaticTaskList;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author BugMan
 * @version $Id: TaskController, v0.1 2018年02月28日 18:47BugMan Exp $
 */

@RestController
public class TaskController extends BaseController {

    @Autowired
    private ITaskService iTaskService;

    @Autowired
    private IExchangeRateService exchangeRateService;

    /**
     * 获取任务列表
     * @return
     */
    @RequestMapping(value = TaskMsgReq.GET_TASK_LIST, method = RequestMethod.GET)
    public Map<Object,Object> getTaskList(){
        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                List<StaticTaskList> staticTaskLists = iTaskService.getTaskList();
                dataMap.put("result", "success");
                dataMap.put("msg", "获取任务列表成功");
                dataMap.put("data", staticTaskLists);
            }
        });
        return dataMap;
    }
}
