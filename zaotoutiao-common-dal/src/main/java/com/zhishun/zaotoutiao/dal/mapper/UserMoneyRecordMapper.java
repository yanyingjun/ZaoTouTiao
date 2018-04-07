/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.dal.mapper;

import com.zhishun.zaotoutiao.core.model.entity.UserMoneyRecord;
import com.zhishun.zaotoutiao.core.model.vo.AllRankingVO;
import com.zhishun.zaotoutiao.core.model.vo.UserGoldRecordVO;
import com.zhishun.zaotoutiao.core.model.vo.UserMoneyRecordVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UserMoneyRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_money_record
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_money_record
     *
     * @mbggenerated
     */
    int insert(UserMoneyRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_money_record
     *
     * @mbggenerated
     */
    int insertSelective(UserMoneyRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_money_record
     *
     * @mbggenerated
     */
    UserMoneyRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_money_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserMoneyRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_money_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserMoneyRecord record);

    /**
     * 根据id查询零钱来源去向（最近三天）
     * @param userId
     * @return
     */
    List<UserMoneyRecordVO> getUserMoneyRecord(Long userId);

    /**
     * 根据用户id查询用户昨天收入零钱数
     * @param userId
     * @return
     */
    BigDecimal getMoneyYesterday(Long userId);

    /**
     * 根据用户id查询用户当天收入零钱数
     * @param userId
     * @return
     */
    BigDecimal getMoneyAll(Long userId);

    /**
     * 总排行
     * @return
     */
    List<AllRankingVO> allRankings();

    /**
     * 周排行
     * @return
     */
    List<AllRankingVO> weekRankings();

    /**
     * 获得某个用户是否获得新手红包（1或0）
     * @param userId
     * @return
     */
    int getNewUserMoney(Long userId);

    /**
     * 按日期分组统计零钱数量
     * @param map
     * @return
     */
    List<UserMoneyRecordVO> listMoneyCount(Map map);

    /**
     * 按金币来源统计零钱数量
     * @param map
     * @return
     */
    List<UserMoneyRecordVO> listMoneyCountBySource(Map map);

    /**
     * 根据userId获得用户的总零钱
     * @param userId
     * @return
     */
    BigDecimal getMoneyByUserId(Long userId);
}