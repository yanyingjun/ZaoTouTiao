package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.*;
import com.zhishun.zaotoutiao.core.model.vo.StaticIndustrysVO;
import com.zhishun.zaotoutiao.core.model.vo.UserGoldRecordVO;
import com.zhishun.zaotoutiao.core.model.vo.UserMoneyRecordVO;
import com.zhishun.zaotoutiao.core.model.vo.UserVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IUserService, v0.1 2018年02月10日 10:10闫迎军(YanYingJun) Exp $
 */
public interface IUserService {

    /**
     * 根据用户ID查询用户信息
     * @param userId  用户ID
     * @return
     */
    User getUserByUserId(Long userId);

    /**
     * 根据参数查询用户信息
     * @param telephone  手机号
     * @return
     */
    User getUserByMap (String telephone);


    /**
     * 用户登录
     * @param telephone
     * @param password
     * @return
     */
    Boolean isUserLogin(String telephone, String password);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 更新用户金币
     * @param userId
     */
    void updateUserInfo(Long userId, int gold);

    /**
     * 根据参数查询用户信息
     * @param map
     * @return
     */
    User getUserByParam(Map<String,Object> map);

    /**
     * 根据ID查询金币来源去向(最近三天)
     * @param userId
     * @return
     */
    List<UserGoldRecordVO> getUserGoldRecord(Long userId);

    /**
     * 根据用户id查询零钱来源去向(最近三天)
     * @param userId
     * @return
     */
    List<UserMoneyRecordVO> getUserMoneyRecord(Long userId);

    /**
     * 根据用户id查询用户昨天收入金币数
     * @param userId
     * @return
     */
    BigDecimal getGoldYesterday(Long userId);

    /**
     * 根据用户id查询用户昨天收入零钱数
     * @param userId
     * @return
     */
    BigDecimal getMoneyYesterday(Long userId);

    /**
     * 根据用户id查询用户当天收入金币数
     * @param userId
     * @return
     */
    BigDecimal getGoldAll(Long userId);

    /**
     * 根据用户id查询用户当天收入零钱数
     * @param userId
     * @return
     */
    BigDecimal getMoneyAll(Long userId);

    /**
     * 获取徒弟列表信息
     * @param userId
     * @param pageRequest
     * @return
     */
    Page<User> getApprenticePage(Long userId, PageRequest pageRequest);

    /**
     * 根据邀请码查询师傅id
     * @param myInvitation
     * @return
     */
    User getUserParent(String myInvitation);

    /**
     * 判断是否已经绑定过师徒关系
     * @param userId
     * @param parentId
     * @return
     */
    User getParentApprentice(Long userId, Long parentId);

    /**
     * 添加金币新增记录
     * @param source
     * @param userId
     * @param gold
     * @param apprenticeId
     * @return
     */
    int addUserGoldRecord(int source, Long userId, int gold, Long apprenticeId);

    /**
     * 三天未活跃用户列表,每页50
     * @param userId
     * @param pageRequest
     * @return
     */
    Page<UserVO> getWakeUpApprenticePage(Long userId, PageRequest pageRequest);

    /**
     * 判断是否超过活动时间
     * @param readActivityDays
     * @param userId
     * @return
     */
    User isSurpassingActivtiy(int readActivityDays, Long userId);

    /**
     * 判断今天是否已经获取新手阅读奖励
     * @param userId
     * @return
     */
    UserGoldRecord isGetNewbieGoldToday(Long userId);

    /**
     * 判断当天阅读是否大于三篇
     * @param userId
     * @return
     */
    List<UserReadRecord> isReadThreeToday(Long userId);

    /**
     * 获取用户当天获得的金币总数
     * @param userId
     * @param source
     * @return
     */
    int getReadGoldToday(Long userId, int source);

    /**
     * 根据名称获取金币配置
     * @param name
     * @return
     */
    StaticGoldConfig getReadGoldConfig(String name);

    /**
     * 判断昨天是否阅读
     * @param userId
     * @return
     */
    List<UserReadRecord> isReadFive(Long userId);

    /**
     * 判断师傅是否是首次收徒
     * @param userId
     * @return
     */
    List<User> isParentFirstRecruit(Long userId);

    /**
     * 给师傅添加收徒奖励零钱和奖励记录零钱（首次）
     * @param source
     * @param userId
     * @param money
     * @param apprenticeId
     * @return
     */
    int addUserMoneyRecord(int source, Long userId, BigDecimal money, Long apprenticeId);

    /**
     * 为用户添加零钱
     * @param userId
     * @param money
     * @return
     */
    int updateUserMoneyRecord(Long userId, BigDecimal money);

    /**
     * 判断是否已经收到收徒奖励
     * @param userId
     * @param parentId
     * @return
     */
    UserGoldRecord isGiveParentRecruitGold(Long userId, Long parentId);

    /**
     * 判断三天内是否添加过唤醒金币
     * @param userId
     * @param source
     * @return
     */
    UserGoldRecord getWeekupThreeDayGetGold(Long userId, int source);

    /**
     * 判断三天内是否有师傅唤醒自己
     * @param userId
     * @param type
     * @return
     */
    UserShare getWeekupThreeDay(Long userId, String type);

    /**
     * 根据用户id查询金币来源去向
     * @param userId
     * @return
     */
    UserGoldRecord getUserGoldRecordInfo(Long userId);

    /**
     * 获取职业列表
     * @return
     */
    List<StaticIndustrysVO> listStaticIndustrys();

    /**
     * 请求用户历史记录
     * @param userId
     * @return
     */
    int delUserReadRecord(Long userId);

    /**
     * 获取徒弟数
     * @param parentId
     * @return
     */
    int getApprenticeSum(Long parentId);

    /**
     * 根据type类型删除用户相关通知信息
     * @param userId
     * @param type
     * @return
     */
    String delUserInformation(int userId, String type);

    /**
     * 新增用户经纬度
     * @param userId
     * @param lat
     * @param lng
     * @return
     */
    int addUserLocation(Long userId, float lat, float lng);


    /**
     * 获取用户建议与反馈常见问题
     * @return
     */
    List<UserFeedbackFaq> listFeedbackFaq();

    /**
     * 用户建议与反馈提交
     * @param userId
     * @param question
     * @param imgPath
     * @return
     */
    int addUserFeedbackPublish(Long userId, String question, String imgPath);

    /**
     * 微信用户注册
     * @param telephone
     * @param password
     * @param wechatId
     * @param wechatHead
     * @param wechatName
     * @return
     */
    Long addUser(String telephone, String password, String wechatId, String wechatHead, String wechatName);

    /**
     * 用户信息修改（绑定微信）
     * @param telephone
     * @param wechatName
     * @param wechatId
     * @param wechatHead
     * @return
     */
    void updateWechatUser(String telephone, String wechatName, String wechatId, String wechatHead);

    /**
     * 判断是否获取过新人礼包
     * @param userId
     * @return
     */
    Boolean getNewUserMoney(Long userId);

    /**
     * 根据类型返回相应的数据信息
     * @param type
     * @return
     */
    List<StaticFakeData> getFakeData(String type);

    /**
     * 新增用户
     * @param telephone
     * @param password
     * @return
     */
    Long addUserInfo(String telephone, String password, Integer platformId, Integer channelId, String address);

    /**
     * 可提现用户列表
     * @param keyWord
     * @param channelId
     * @param createDate
     * @param money
     * @param pageRequest
     * @return
     */
    Page<UserVO> listCanBePresentedUser(String keyWord, String channelId, String createDate, BigDecimal minMoney, BigDecimal maxMoney, PageRequest pageRequest);

}
