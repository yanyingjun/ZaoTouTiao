package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.UserBehavior;

import java.util.List;
import java.util.Map;

public interface UserBehaviorMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserBehavior record);

    int insertSelective(UserBehavior record);

    UserBehavior selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBehavior record);

    int updateByPrimaryKey(UserBehavior record);

    /**
     * 查询用户统计数据
     * @param map
     * @return
     */
    List<UserBehavior> listBehaviorByType(Map<String,Object> map);

    /**
     * 统计打开APP人数
     * @param map
     * @return
     */
    List<UserBehavior> listOpenAppCount(Map<String,Object> map);
}