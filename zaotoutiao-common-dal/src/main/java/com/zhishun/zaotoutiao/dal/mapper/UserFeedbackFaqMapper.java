package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.UserFeedbackFaq;

import java.util.List;

public interface UserFeedbackFaqMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserFeedbackFaq record);

    int insertSelective(UserFeedbackFaq record);

    UserFeedbackFaq selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserFeedbackFaq record);

    int updateByPrimaryKey(UserFeedbackFaq record);

    /**
     * 获取用户建议与反馈常见问题
     * @return
     */
    List<UserFeedbackFaq> listFeedbackFaq();
}