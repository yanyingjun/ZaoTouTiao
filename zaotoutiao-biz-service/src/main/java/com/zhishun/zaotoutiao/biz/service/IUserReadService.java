package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.entity.ExchangeRate;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.entity.UserReadRecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zhishun.zaotoutiao.core.model.vo.InfoRankVO;
import com.zhishun.zaotoutiao.core.model.vo.NavigationVO;

/**
 * @author BugMan
 * @version $Id: IUserReadService, v0.1 2018年03月02日 17:27BugMan Exp $
 */

public interface IUserReadService {

    /**
     * 用户已经阅读(24小时热文)，更改阅读状态并返回是否成功
     * @param id
     * @param userId
     * @param type
     * @return
     */
    boolean isUserRead(String id, Long userId, String type);

    /**
     * 新闻阅读记录添加
     * @param userReadRecord
     */
    int readRecordAdd(UserReadRecord userReadRecord);

    /**
     * 获取阅读记录
     * @param map
     * @return
     */
    UserReadRecord getUserReadRecord(Map map);

    /**
     * 获取用户当天总得阅读记录数
     * @param userId
     * @return
     */
    int CountReadRecord(Long userId);

    /**
     * 获取最大的一条记录
     * @param userId
     * @return
     */
    UserReadRecord maxReadRecord(Long userId);

    /**
     * 阅读添加金币
     * @param userId
     * @param gold
     * @param user
     * @param exchangeRate
     */
    void readAddGold(Long userId, int gold, User user, ExchangeRate exchangeRate, UserReadRecord userReadRecord);

    /**
     * 获得导航排行数据
     * @param dateNum
     * @param date
     * @param appType
     * @return
     */
    List<NavigationVO> getNavList(Integer dateNum, String date, Integer appType);

    /**
     * 获得一级标签排行数据
     * @param dateNum
     * @param date
     * @param appType
     * @param parentId
     * @return
     */
    List<NavigationVO> getFirstTabList(Integer dateNum, String date, Integer appType, Long parentId);

    /**
     * 获得二级标签排行数据
     * @param dateNum
     * @param date
     * @param appType
     * @param parentId
     * @return
     */
    List<NavigationVO> getSecondTabList(Integer dateNum, String date, Integer appType, Long parentId);

    /**
     * 获得info排行前30
     * @param navId
     * @param theClass  判断是导航还是几级标签
     * @return
     */
    List<InfoRankVO> getInfoRankVOList(Long navId,int theClass,Integer dateNum, String date);
}
