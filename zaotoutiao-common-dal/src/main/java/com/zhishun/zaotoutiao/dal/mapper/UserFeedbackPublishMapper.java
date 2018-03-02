package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.UserFeedbackPublish;

public interface UserFeedbackPublishMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserFeedbackPublish record);

    int insertSelective(UserFeedbackPublish record);

    UserFeedbackPublish selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserFeedbackPublish record);

    int updateByPrimaryKey(UserFeedbackPublish record);
}