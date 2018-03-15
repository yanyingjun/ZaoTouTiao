/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IGoldRecordService;
import com.zhishun.zaotoutiao.biz.service.IUserShareService;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.entity.UserShare;
import com.zhishun.zaotoutiao.dal.mapper.UserGoldRecordMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserShareMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author BugMan
 * @version $Id: UserShareServiceImpl, v0.1 2018年03月01日 18:08BugMan Exp $
 */

@Service
public class UserShareServiceImpl implements IUserShareService{

    @Autowired
    private UserShareMapper userShareMapper;

    @Autowired
    private UserGoldRecordMapper userGoldRecordMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IGoldRecordService goldRecordService;

    @Override
    public UserShare getUserShare(Long userId, String type, String source, String infoId, String apprenticeId) {
        Map map = Maps.newHashMap();
        if(null == infoId || infoId.isEmpty()){
            infoId="0";
        }
        if(null == apprenticeId || apprenticeId.isEmpty()){
            apprenticeId="0";
        }
        //创建插入的新对象
        UserShare userShare = new UserShare();
        userShare.setUserId(userId);
        userShare.setType(type);
        userShare.setSource(source);
        userShare.setInfoId(infoId);
        userShare.setApprenticeId(apprenticeId);
        int result = userShareMapper.insertSelective(userShare);
        if(result > 0){
            //返回更新结果
            userShare = userShareMapper.getLastShare();
        }
        String date = userShare.getCreateDate();
        Date date1 = DateUtil.toDate(date,DateUtil.DEFAULT_DATETIME_FORMAT);
        userShare.setCreateDate(DateUtil.toString(date1, DateUtil.DEFAULT_DATETIME_FORMAT));
        return userShare;
    }

    /**
     * 金币 source : 10分享收徒,  11分享文章， 12，唤醒徒弟(师傅奖励) ，13，被唤醒(徒弟奖励)，14，晒收入
     * 分享 type:分享类型  收徒RECRUIT，文章ARTICLE， 唤醒AWAKEN，晒收入INCOME
     * @param userId
     * @param shareId
     * @return
     */
    @Override
    public Map<String, Object> shareSuccessGet(Long userId, Long shareId) {
        Map<String, Object> map = Maps.newHashMap();
        UserShare userShare = userShareMapper.selectByPrimaryKey(shareId);
        String shareType = userShare.getType();
        int gold = 0;
        int result = 0;
            //收徒分享
        if("RECRUIT".equals(shareType)){
            gold = 50;
            result = goldRecordService.getShareRecruitGold(userId,shareId,10L,gold);
            map.put("getGold",result);
        }else if("ARTICLE".equals(shareType)){
            //文章
            gold = 30;
            result = goldRecordService.getShareArticleGold(userId,shareId,11L,gold);
            map.put("getGold",result);
        }else if("AWAKEN".equals(shareType)){
            //唤醒
            //唤醒徒弟  1000  徒弟 300
            map.put("getGold",0);
        }else if("INCOME".equals(shareType)){
            //晒收入
            //晒收入  首次分享 30
            gold = 30;
            result = goldRecordService.getShareIncomeGold(userId,shareId,14L,gold);
            map.put("getGold",result);
        }
        map.put("type",shareType);
        return map;
    }
}
