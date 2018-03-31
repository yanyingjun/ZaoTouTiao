/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IUserCollectService;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.core.model.entity.UserCollect;
import com.zhishun.zaotoutiao.core.model.entity.UserReadRecord;
import com.zhishun.zaotoutiao.core.model.vo.UserReadRecordVO;
import com.zhishun.zaotoutiao.dal.mapper.UserCollectMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserReadRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserCollectServiceImpl, v0.1 2018年02月28日 17:34闫迎军(YanYingJun) Exp $
 */
@Service
public class UserCollectServiceImpl implements IUserCollectService{

    @Autowired
    private UserCollectMapper userCollectMapper;

    @Autowired
    private UserReadRecordMapper userReadRecordMapper;

    @Override
    public void delAllCollect(Long userId) {
        userCollectMapper.delUserCollectByUserId(userId);
    }

    @Override
    public UserCollect getCollectByMap(Long userId, String infoId) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("infoId", infoId);
        return userCollectMapper.getCollectByMap(map);
    }

    @Override
    public int delOneCollect(Long userId, String infoId) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("infoId", infoId);
        return userCollectMapper.delOneCollect(map);
    }

    @Override
    public int addUserCollect(Long userId, String infoId) {
        UserReadRecord userReadRecord = userReadRecordMapper.getUserReadRecordByInfoId(infoId);
        UserCollect userCollect = new UserCollect();
        userCollect.setUserId(userId);
        userCollect.setInfoId(infoId);
        userCollect.setAuthor(null);
        userCollect.setChannel(userReadRecord.getChannelId());
        userCollect.setFilter(userReadRecord.getLabel());
        userCollect.setInfosType(userReadRecord.getInfoType());
        userCollect.setPicUrl(userReadRecord.getImgUrl());
        userCollect.setPublishDate(null);
        userCollect.setRawUrl(null);
        userCollect.setSource(userReadRecord.getSource());
        userCollect.setStyle(null);
        userCollect.setUrl(userReadRecord.getUrl());
        userCollect.setTitle(userReadRecord.getTitle());
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
