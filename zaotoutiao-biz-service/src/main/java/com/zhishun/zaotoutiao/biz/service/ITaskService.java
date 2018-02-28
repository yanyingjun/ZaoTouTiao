/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.entity.StaticTaskList;

import java.util.List;

/**
 * @author BugMan
 * @version $Id: ITaskService, v0.1 2018年02月28日 18:29BugMan Exp $
 */

public interface ITaskService {

    /**
     * 获取任务列表(排序：type asc)
     * @return
     */
    List<StaticTaskList> getTaskList();
}
