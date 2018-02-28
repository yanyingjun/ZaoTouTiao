package com.zhishun.zaotoutiao.dal.mapper;

import com.zhishun.zaotoutiao.core.model.entity.StaticTaskList;

import java.util.List;

public interface StaticTaskListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaticTaskList record);

    int insertSelective(StaticTaskList record);

    StaticTaskList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaticTaskList record);

    int updateByPrimaryKey(StaticTaskList record);

    List<StaticTaskList> getTaskListOrderByType();

}