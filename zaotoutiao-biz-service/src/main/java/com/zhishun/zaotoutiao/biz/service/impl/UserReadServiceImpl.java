/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IUserReadService;
import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.common.util.LoggerUtils;
import com.zhishun.zaotoutiao.core.model.entity.*;
import com.zhishun.zaotoutiao.dal.mapper.ExchangeRateMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserInformationMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserJpushMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserReadRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
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
    private IUserService userService;

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
    public int readRecordAdd(UserReadRecord userReadRecord) {
            return userReadRecordMapper.insertSelective(userReadRecord);

        //判断用户是否浏览过该新闻
        //if(!isRead(userId,infoId)){
        //    //新手任务
        //    //判断是否超过活动日期
        //    //判断是否超过活动天数newbie_read_time < 0为该活动永不过期
        //    int newbieReadTime = getNewbieReadTime();
        //    Map<String,Object> map = Maps.newHashMap();
        //    List<UserReadRecord> userReadRecordList = userReadRecordMapper.isContinuousReadYesterday(userId);
        //    map.put("readActivityDays",newbieReadTime);
        //    map.put("userId",userId);
        //    Map<String,Object> maps = Maps.newHashMap();
        //    maps.put("userId",userId);
        //    maps.put("infoId",infoId);
        //    maps.put("infoType",infoType);
        //    maps.put("channelId",channelId);
        //    maps.put("label",label);
        //    maps.put("title",title);
        //    maps.put("source",source);
        //    int readContinuousDay = 0;
        //    //查询连续阅读天数
        //    if(userReadRecordList.size() > 0){
        //        readContinuousDay = userReadRecordList.get(0).getReadContinuousDay();
        //    }
        //    if(userMapper.isSurpassingActivity(map) > 0 ||  newbieReadTime < 0){
        //        //查询昨天是否已经阅读
        //        if(userReadRecordList.size() <= 0){
        //            if(readContinuousDay >= 3 ){
        //                //如果超过三天，则从三天继续
        //                maps.put("readContinuousDay",readContinuousDay+1);
        //                userReadRecordMapper.addLookRecord(maps);
        //            }else{
        //                //没有连续阅读,重头开始
        //                maps.put("readContinuousDay",1);
        //                userReadRecordMapper.addLookRecord(maps);
        //            }
        //        }else{
        //            //判断今天是否已阅读
        //            if(userReadRecordMapper.isContinuousReadToday(userId) > 0){
        //                maps.put("readContinuousDay",readContinuousDay);
        //                userReadRecordMapper.addLookRecord(maps);
        //            }else{
        //                //天数 +1
        //                maps.put("readContinuousDay",readContinuousDay+1);
        //                userReadRecordMapper.addLookRecord(maps);
        //            }
        //        }
        //    }else{
        //        //活动过期，按照阅读天数继续添加
        //        //添加到浏览记录
        //        //添加到历史记录列表
        //        //查询连续阅读天数
        //        maps.put("readContinuousDay",readContinuousDay);
        //        userReadRecordMapper.addLookRecord(maps);
        //    }
        //    return true;
        //}else{
        //    return false;
        //}
    }

    @Override
    public UserReadRecord getUserReadRecord(Map map) {
        return userReadRecordMapper.getUserReadRecord(map);
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

    @Override
    public int CountReadRecord(Long userId){
        return userReadRecordMapper.isContinuousReadToday(userId);
    }

    @Override
    public UserReadRecord maxReadRecord(Long userId) {
        return userReadRecordMapper.maxReadRecord(userId);
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public void readAddGold(Long userId, int gold, User user, ExchangeRate exchangeRate, UserReadRecord userReadRecord){
        //添加自己阅读金币和金币记录
        //新闻阅读奖励类型

        int source = 1;
        userService.addUserGoldRecord(source, userId, gold, null);

        //更新用户金币
        userService.updateUserInfo(userId, gold);
        //添加阅读记录
        userReadRecord.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        userReadRecordMapper.insertSelective(userReadRecord);

        //判断是否有师傅
        Long parentId = user.getParentId();
        if(!StringUtils.isEmpty(parentId) && parentId != 0){
            //判断是否已经阅读超过五篇文章，确定是否有师徒关系
            //List<UserReadRecord> listURR = userService.isReadFive(userId);

            //为父类添加金币

            //添加金币和金币记录
            //这里把父类id当作用户id,用户id当作徒弟id,添加的金币作为阅读进贡
            //添加徒弟阅读进贡金币记录
            //徒弟新闻阅读进贡奖励类型
            userService.addUserGoldRecord(2,parentId, gold, userId);
            //更新用户金币
            userService.updateUserInfo(parentId, gold);

            //--------收徒奖励 --------
            //获取配置,判断师傅是否是首次收徒
            List<User> user4 = userService.isParentFirstRecruit(parentId);
            if(StringUtils.isEmpty(user4)){
                //给师傅添加收徒奖励和奖励记录（首次）
                Integer parentGoldNum = exchangeRate.getNewbieRecruitGold();
                BigDecimal parentMoneyNum = exchangeRate.getNewbieRecruitMoney();
                //添加金币和金币记录
                userService.addUserGoldRecord(9,parentId, parentGoldNum, userId);
                //更新用户金币
                userService.updateUserInfo(parentId, parentGoldNum);
                //添加零钱和零钱记录
                userService.addUserMoneyRecord(3, parentId, parentMoneyNum, userId);
                userService.updateUserMoneyRecord(parentId, parentMoneyNum);
            }else{
                //判断师傅已经收到自己的收徒奖励
                UserGoldRecord userGoldRecord1 = userService.isGiveParentRecruitGold(userId, parentId);
                if(StringUtils.isEmpty(userGoldRecord1)){
                    //给师傅添加收徒奖励和奖励记录（普通）
                    BigDecimal goldNum = new BigDecimal(gold).multiply(new BigDecimal(2));
                    userService.addUserGoldRecord(6,parentId, goldNum.intValue(), userId);
                    userService.updateUserInfo(parentId, goldNum.intValue());
                }
            }
            //判断三天内是否添加过唤醒金币
            UserGoldRecord userGoldRecord = userService.getWeekupThreeDayGetGold(userId, 13);
            if(StringUtils.isEmpty(userGoldRecord)){
                //判断三天内是否有师傅唤醒自己
                // 被唤醒类型
                String type = "AWAKEN";
                UserShare userShare = userService.getWeekupThreeDay(userId, type);
                if(!StringUtils.isEmpty(userShare)){
                    //添加金币和金币记录
                    //给师傅添加唤醒徒弟奖励金币
                    userService.addUserGoldRecord(12,parentId, exchangeRate.getAwakenParentGold(), userId);
                    userService.updateUserInfo(parentId, exchangeRate.getAwakenParentGold().intValue());
                    //给师徒弟加被唤醒奖励金币
                    userService.addUserGoldRecord(13,userId, exchangeRate.getAwakenUserGold(), userId);
                    userService.updateUserInfo(userId, exchangeRate.getAwakenUserGold().intValue());
                }
            }
        }

    }
}
