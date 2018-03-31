/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.dal.mapper;

import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.UserGoldRecord;
import com.zhishun.zaotoutiao.core.model.vo.ApprenticeRepVO;
import com.zhishun.zaotoutiao.core.model.vo.UserGoldRecordVO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserGoldRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_gold_record
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_gold_record
     *
     * @mbggenerated
     */
    int insert(UserGoldRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_gold_record
     *
     * @mbggenerated
     */
    int insertSelective(UserGoldRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_gold_record
     *
     * @mbggenerated
     */
    UserGoldRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_gold_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserGoldRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_gold_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserGoldRecord record);

    /**
     * 根据用户id查询金币来源去向
     * @param userId
     * @return
     */
    List<UserGoldRecordVO> getUserGoldRecord(Long userId);

    /**
     * 根据用户id查询用户昨天收入金币数
     * @param userId
     * @return
     */
    BigDecimal getGoldYesterday(Long userId);

    /**
     * 根据用户id查询用户当天收入金币数
     * @param userId
     * @return
     */
    BigDecimal getGoldAll(Long userId);

    /**
     * 查询该评论是否已经获得过奖励
     * @param map
     * @return
     */
    UserGoldRecord getCommentsHasGold(Map<String,Object> map);

    /**
     * 查询今天评论获得的金币数
     * @param userId
     * @return
     */
    int getTodayCommentsGold(Long userId);

    /**
     * 判断今天是否已经获取新手阅读奖励
     * @param userId
     * @return
     */
    UserGoldRecord isGetNewbieGoldToday(Long userId);

    /**
     * 获取用户当天获得的金币总数
     * @param map
     * @return
     */
    int getReadGoldToday(Map<String,Object> map);

    /**
     * 判断师傅已经收到收徒奖励
     * @param map
     * @return
     */
    UserGoldRecord isGiveParentRecruitGold(Map<String,Object> map);

    /**
     * 判断三天内是否添加过唤醒金币
     * @param map
     * @return
     */
    UserGoldRecord getWeekupThreeDayGetGold(Map<String,Object> map);

    /**
     * 根据用户id查询金币来源去向
     * @param userId
     * @return
     */
    UserGoldRecord getUserGoldRecordInfo(Long userId);

    /**
     * 获取用户当天获得的金币总数
     * @param map
     * @return
     */
    String getOpenGoldToday(Map<String,Object> map);

    /**
     * 计算时间差
     * @param userId
     * @return
     */
    Map leadTime(Long userId);

    /**
     * 计算下次开宝箱时间
     * @param userId
     * @return
     */
    Map leadTimeTwo(Long userId);

    /**
     * 根据用户获取金币
     * @param userId
     * @return
     */
    List<UserGoldRecord> listUserGoldRecord(Long userId);

    /**
     * 判断今天加过几次某个来源（source）的金币
     * @return
     */
    int getShareRecruitGold(Map<String,Object> map);

    /**
     * 根据来源类型获得最新金币变更记录
     * @param map
     * @return
     */
    Date getNewDateBySource(Map<String,Object> map);

    /**
     * 按日期分组统计金币数量
     * @param map
     * @return
     */
    List<UserGoldRecordVO> listGoldCount(Map map);

    /**
     * 按金币来源统计金币数量
     * @param map
     * @return
     */
    List<UserGoldRecordVO> listGoldCountBySource(Map map);

    /**
     * 获取活动时间内有效徒弟数量
     * @param map
     * @return
     */
    int getEffectiveApprenticeNum(Map map);

    /**
     * 获取活动时间内累计获得的奖励
     * @param map
     * @return
     */
    int getActivityGoldSum(Map map);

    /**
     * 获取我的徒弟状态分类列表
     * @return
     */
    List<UserGoldRecordVO> listApprenticeNum(Map map);

    /**
     * 获取未唤醒徒弟数量
     * @param map
     * @return
     */
    List<ApprenticeRepVO> listAwakenApprentice(Map map);

    /**
     * 获取有效徒弟列表
     * @param map
     * @return
     */
    List<ApprenticeRepVO> listEffectiveApprentice(Map map);

    /**
     * 获取我的徒弟列表
     * @param map
     * @return
     */
    List<ApprenticeRepVO> listMyApprentice(Map map);

}