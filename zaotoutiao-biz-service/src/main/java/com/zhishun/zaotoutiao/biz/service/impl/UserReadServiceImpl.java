/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zhishun.zaotoutiao.biz.service.IUserReadService;
import com.zhishun.zaotoutiao.core.model.entity.UserReadRecord;
import com.zhishun.zaotoutiao.dal.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author BugMan
 * @version $Id: UserReadServiceImpl, v0.1 2018年03月02日 17:28BugMan Exp $
 */

@Service
public class UserReadServiceImpl implements IUserReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserReadServiceImpl.class);

    @Autowired
    private UserJpushMapper userJpushMapper;

    @Autowired
    private UserInformationMapper userInformationMapper;

    @Autowired
    private UserReadRecordMapper userReadRecordMapper;

    @Autowired
    private ExchangeRateMapper exchangeRateMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean isUserRead(String id, Long userId, String type) {
        if ("article".equals(type) || "video".equals(type)){
            Map<String,Object> map = Maps.newHashMap();
            map.put("infoId",id);
            map.put("userId",userId);
            int result = userJpushMapper.updateUserReadByInfoId(map);
            if(0 < result){
                return true;
            }
            return false;
        }else if("MSG".equals(type) || "NOTICE".equals(type)){
            Map<String,Object> map = Maps.newHashMap();
            map.put("informationId",Integer.parseInt(id));
            map.put("userId",userId);
            int res = userInformationMapper.updateInformationById(map);
            int result = userJpushMapper.updateUserReadByInformationId(map);
            if(0 < result && 0 < res){
                //加入更新都成功返回true
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public void readRecordAdd(Long userId, String infoId, String infoType) {
        //判断用户是否浏览过该新闻
        if(!isRead(userId,infoId)){
            //新手任务
            //判断是否超过活动日期
            //判断是否超过活动天数newbie_read_time < 0为该活动永不过期
            int newbieReadTime = getNewbieReadTime();
            Map<String,Object> map = Maps.newHashMap();
            List<UserReadRecord> userReadRecordList = userReadRecordMapper.isContinuousReadYesterday(userId);
            map.put("readActivityDays",newbieReadTime);
            map.put("userId",userId);
            Map<String,Object> maps = Maps.newHashMap();
            maps.put("userId",userId);
            maps.put("infoId",infoId);
            maps.put("infoType",infoType);
            int readContinuousDay = 0;
            //查询连续阅读天数
            if(userReadRecordList.size() > 0){
                readContinuousDay = userReadRecordList.get(0).getReadContinuousDay();
            }
            if(userMapper.isSurpassingActivity(map) > 0 ||  newbieReadTime < 0){
                //查询昨天是否已经阅读
                if(userReadRecordList.size() <= 0){
                    if(readContinuousDay >= 3 ){
                        //如果超过三天，则从三天继续
                        maps.put("readContinuousDay",readContinuousDay);
                        userReadRecordMapper.addLookRecord(maps);
                    }else{
                        //没有连续阅读,重头开始
                        maps.put("readContinuousDay",1);
                        userReadRecordMapper.addLookRecord(maps);
                    }
                }else{
                    //判断今天是否已阅读
                    if(userReadRecordMapper.isContinuousReadToday(userId) > 0){
                        maps.put("readContinuousDay",readContinuousDay);
                        userReadRecordMapper.addLookRecord(maps);
                    }else{
                        //天数 +1
                        maps.put("readContinuousDay",readContinuousDay+1);
                        userReadRecordMapper.addLookRecord(maps);
                    }
                }
            }else{
                //活动过期，按照阅读天数继续添加
                //添加到浏览记录
                //添加到历史记录列表
                //查询连续阅读天数
                maps.put("readContinuousDay",readContinuousDay);
                userReadRecordMapper.addLookRecord(maps);
            }

        }
    }

    /**
     * 判断用户是否浏览过该新闻
     * @param userId
     * @param infoId
     * @return
     */
    private Boolean isRead(Long userId, String infoId){
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId",userId);
        map.put("infoId",infoId);
        int res=  userReadRecordMapper.isRead(map);
        if(1 > res){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 获得活动天数
     * @return
     */
    private int getNewbieReadTime(){
        return exchangeRateMapper.getNewbieReadTime();
    }
}
