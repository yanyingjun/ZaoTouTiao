/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.biz.service.impl;

import com.zhishun.zaotoutiao.biz.service.ITaskService;
import com.zhishun.zaotoutiao.core.model.entity.StaticTaskList;
import com.zhishun.zaotoutiao.dal.mapper.StaticTaskListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BugMan
 * @version $Id: TaskServiceImpl, v0.1 2018年02月28日 18:29BugMan Exp $
 */

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private StaticTaskListMapper staticTaskListMapper;

    @Override
    public List<StaticTaskList> getTaskList() {
        return  staticTaskListMapper.getTaskListOrderByType();
    }
}
