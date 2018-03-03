/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IInformationService;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.UserInformation;
import com.zhishun.zaotoutiao.dal.mapper.UserInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: InformationServiceImpl, v0.1 2018年03月01日 14:02闫迎军(YanYingJun) Exp $
 */
@Service
public class InformationServiceImpl implements IInformationService{

    @Autowired
    private UserInformationMapper userInformationMapper;



    @Override
    public List<UserInformation> listInformationPage(Long userId, String type, PageRequest pageRequest) {
        Map map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("type", type);
        if(!StringUtils.isEmpty(pageRequest)){
            map.put("offset", pageRequest.getOffset());
            map.put("limit", pageRequest.getPageSize());
        }
        return userInformationMapper.listInformationPage(map);
    }

    @Override
    public List<UserInformation> listInformationNew() {
        return userInformationMapper.listInformationNew();
    }

    @Override
    public int addUserInformation(UserInformation information) {
        return userInformationMapper.insertSelective(information);
    }

    @Override
    public int selectUserInformationByTypeOrUserId(Long userId, String type) {
        Map map = Maps.newHashMap();
        map.put("userId",userId);
        map.put("type",type);
        return userInformationMapper.getInformationNumByType(map);
    }
}
