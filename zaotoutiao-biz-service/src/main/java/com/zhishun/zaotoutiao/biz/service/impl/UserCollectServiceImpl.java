/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.zhishun.zaotoutiao.biz.service.IUserCollectService;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.core.model.entity.UserCollect;
import com.zhishun.zaotoutiao.dal.mapper.UserCollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserCollectServiceImpl, v0.1 2018年02月28日 17:34闫迎军(YanYingJun) Exp $
 */
@Service
public class UserCollectServiceImpl implements IUserCollectService{

    @Autowired
    private UserCollectMapper userCollectMapper;

    @Override
    public int delAllCollect(Long userId) {
        /*return userCollectMapper.delUserCollect(userId);*/
        return 0;
    }

    @Override
    public List<UserCollect> listCollect(Long userId, String infosId) {
        /*Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("infosId", infosId);
        return userCollectMapper.listCollect(map);*/
        return null;
    }

    @Override
    public int delOneCollect(Long userId, String infosId) {
        /*Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("infosId", infosId);
        return userCollectMapper.delOneCollect(map);*/
        return 0;
    }

    @Override
    public int addUserCollect(Long userId, String infosId) {
        UserCollect userCollect = new UserCollect();
        userCollect.setUserId(userId);
        userCollect.setInfosType(infosId);
        userCollect.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        return userCollectMapper.insertSelective(userCollect);
    }

    @Override
    public boolean isCollect(Long userId, String infoId) {
        /*Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("infoId", infoId);
        UserCollect userCollect = userCollectMapper.getUserCollectByParam(map);
        if(StringUtils.isEmpty(userCollect)){
            //还未收藏
            return false;
        }else{
            return true;
        }*/
        return false;
    }
}
