/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IGoldRecordService;
import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.entity.UserGoldRecord;
import com.zhishun.zaotoutiao.core.model.vo.UserGoldRecordVO;
import com.zhishun.zaotoutiao.dal.mapper.UserGoldRecordMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserShareMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: GoldRecordServiceImpl, v0.1 2018年02月28日 19:29闫迎军(YanYingJun) Exp $
 */

@Service
public class GoldRecordServiceImpl implements IGoldRecordService{

    @Autowired
    private UserGoldRecordMapper userGoldRecordMapper;

    @Autowired
    private UserShareMapper userShareMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService iUserService;

    @Override
    public int getOpenGoldToday(Long userId, int source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 00:00:00");
        String zeroDay = sdf.format(new Date());
        Map map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("source", source);
        map.put("zeroDay", zeroDay);
        return userGoldRecordMapper.getOpenGoldToday(map);
    }

    @Override
    public Map leadTime(Long userId) {
        return userGoldRecordMapper.leadTime(userId);
    }

    @Override
    public Map leadTimeTwo(Long userId) {
        return userGoldRecordMapper.leadTimeTwo(userId);
    }

    @Override
    public List<UserGoldRecord> listUserGoldRecord(Long userId) {
        return userGoldRecordMapper.listUserGoldRecord(userId);
    }

    @Override
    @Transactional
    public int getShareRecruitGold(Long userId, Long shareId, Long source, int gold) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId",userId);
        map.put("shareId",shareId);
        map.put("source",source);
        map.put("gold",gold);

        //返回当天新增金币记录
        int count = userGoldRecordMapper.getShareRecruitGold(map);
        //第二或第三次
        if(count < 3 && count > 0){
            //返回是否大于3小时
            int resNum = userShareMapper.getTiming(map);
            if(1 == resNum || "1".equals(resNum)){
                //更新用户金币
                User user = new User();
                user.setUserId(userId);
                user.setGold(gold);
                int res =userMapper.updateByPrimaryKeySelective(user);
                if(0 < res){
                    //新增金币记录
                    int sources = source.intValue();
                    int resNew = updateUserGold(userId,shareId,sources,gold);
                    if(0 < resNew){
                        return gold;
                    }
                }
            }
        }else if(0 == count){
            //第一次
            //更新用户金币
            User user = new User();
            user.setUserId(userId);
            user.setGold(gold);
            int res =userMapper.updateByPrimaryKeySelective(user);
            if(0 < res){
                //新增金币记录
                int sources = source.intValue();
                int resNew = updateUserGold(userId,shareId,sources,gold);
                if(0 < resNew){
                    return gold;
                }
            }
        }
        return 0;
    }

    @Override
    @Transactional
    public int getShareArticleGold(Long userId, Long shareId, Long source, int gold) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId",userId);
        map.put("shareId",shareId);
        map.put("source",source);
        map.put("gold",gold);

        //返回当天新增金币记录
        int count = userGoldRecordMapper.getShareRecruitGold(map);
        //加入小于1，没有
        if(1 > count){
            //判断当天是否超过三次通过该途径分享文章
            String shareType = userShareMapper.selectByPrimaryKey(shareId).getType();
            int countOfShare = userShareMapper.getNumOfType(userId,shareType);
            if(3 < countOfShare){
                //更新用户金币
                User user = new User();
                user.setUserId(userId);
                user.setGold(gold);
                int res =userMapper.updateByPrimaryKeySelective(user);
                if(0 < res){
                    //新增金币记录
                    int sources = source.intValue();
                    int resNew = updateUserGold(userId,shareId,sources,gold);
                    if(0 < resNew){
                        return gold;
                    }
                }
            }
        }
        return 0;
    }

    @Override
    @Transactional
    public int getShareIncomeGold(Long userId, Long shareId, Long source, int gold) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId",userId);
        map.put("shareId",shareId);
        map.put("source",source);
        map.put("gold",gold);

        //返回当天新增金币记录
        int count = userGoldRecordMapper.getShareRecruitGold(map);
        if(1 > count){
            //更新用户金币
            User user = new User();
            user.setUserId(userId);
            user.setGold(gold);
            int res =userMapper.updateByPrimaryKeySelective(user);
            if(0 < res){
                //新增金币记录
                int sources = source.intValue();
                int resNew = updateUserGold(userId,shareId,sources,gold);
                if(0 < resNew){
                    return gold;
                }
            }

        }
        return 0;
    }

    @Override
    public Map countUserGoldAndMoney(String type, String startDate, String endDate) {
        Map map = Maps.newHashMap();
        if(!StringUtils.isEmpty(type)){
            map.put("type", type);
        }
        if(!StringUtils.isEmpty(startDate)){
            map.put("startDate", startDate);
        }
        if(!StringUtils.isEmpty(endDate)){
            map.put("endDate", endDate);
        }
        return userMapper.countUserGoldAndMoney(map);
    }

    @Override
    public List<UserGoldRecordVO> listGoldCount(int channelId, String source, String type, String isNewAndOld, String startDate, String endDate) {
        Map map = Maps.newHashMap();
        if(!StringUtils.isEmpty(channelId) && channelId != 0){
            map.put("channelId", channelId);
        }
        if(!StringUtils.isEmpty(source)){
            map.put("source", source);
        }
        if(!StringUtils.isEmpty(type)){
            map.put("type", type);
        }
        if(!StringUtils.isEmpty(isNewAndOld) && !isNewAndOld.equals("wholeUser")){
            map.put("isNewOrOld", isNewAndOld);
        }
        if(!StringUtils.isEmpty(startDate)){
            map.put("startDate", startDate);
        }
        if(!StringUtils.isEmpty(endDate)){
            map.put("endDate", endDate);
        }
        return userGoldRecordMapper.listGoldCount(map);
    }

    @Override
    public List<UserGoldRecordVO> listGoldCountBySource(String isNewAndOld, String type, String startDate, String endDate) {
        Map map = Maps.newHashMap();
        if(!StringUtils.isEmpty(type)){
            map.put("type", type);
        }
        if(!StringUtils.isEmpty(isNewAndOld) && !isNewAndOld.equals("wholeUser")){
            map.put("isNewOrOld", isNewAndOld);
        }
        if(!StringUtils.isEmpty(startDate)){
            map.put("startDate", startDate);
        }
        if(!StringUtils.isEmpty(endDate)){
            map.put("endDate", endDate);
        }
        return userGoldRecordMapper.listGoldCountBySource(map);
    }

    /**
     * 新增用户金币并新增金币记录
     * @param userId
     * @param shareId
     * @param source
     * @param gold
     * @return
     */
    private int updateUserGold(Long userId, Long shareId, int source, int gold){
        UserGoldRecord userGoldRecord =new UserGoldRecord();
        userGoldRecord.setSource(source);
        userGoldRecord.setUserId(userId);
        userGoldRecord.setGold(gold);
        userGoldRecord.setShareId(shareId);
        userGoldRecord.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        int res = userGoldRecordMapper.insertSelective(userGoldRecord);
        return res;
    }


}
