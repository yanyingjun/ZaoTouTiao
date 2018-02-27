package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.entity.UserGoldRecord;
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
     * 更新用户信息
     * @param user
     */
    void updateUserInfo(User user);

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
     * @param userGoldRecord
     * @return
     */
    int addUserGoldRecord(UserGoldRecord userGoldRecord);

    /**
     * 三天未活跃用户列表,每页50
     * @param userId
     * @param pageRequest
     * @return
     */
    Page<UserVO> getWakeUpApprenticePage(Long userId, PageRequest pageRequest);

}
