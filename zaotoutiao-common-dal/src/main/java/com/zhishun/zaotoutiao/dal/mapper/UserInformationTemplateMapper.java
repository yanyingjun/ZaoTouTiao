package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.UserInformationTemplate;

import java.util.List;

public interface UserInformationTemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInformationTemplate record);

    int insertSelective(UserInformationTemplate record);

    UserInformationTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInformationTemplate record);

    int updateByPrimaryKey(UserInformationTemplate record);

    /**
     * 获取新用户消息模版
     * @return
     */
    List<UserInformationTemplate> listInformationNew();
}