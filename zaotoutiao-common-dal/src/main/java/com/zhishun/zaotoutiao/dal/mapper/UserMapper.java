/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.dal.mapper;

import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.vo.UserVO;

import java.util.List;
import java.util.Map;

/**
 * 用户表Mapper接口
 * @author 闫迎军(YanYingJun)
 * @version $Id: ZttTest, v0.1 2018年02月10日 15:57闫迎军(YanYingJun) Exp $
 */

public interface UserMapper {

    /**
     * 删除
     * @param userId
     * @return
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * 新增用户
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 新增用户
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 根据用户ID查询
     * @param userId
     * @return
     */
    User selectByPrimaryKey(Long userId);

    /**
     * 更新用户
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 更新用户
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * 根据参数获取用户信息
     * @param map
     * @return
     */
    User getUserByMap(Map<String,Object> map);

    /**
     * 获取徒弟列表信息
     * @param map
     * @return
     */
    List<User> getApprentice(Map<String,Object> map);

    /**
     * 获取徒弟总记录数
     * @param map
     * @return
     */
    int countApprentice(Map<String,Object> map);

    /**
     * 三天未活跃用户列表，每页50
     * @param map
     * @return
     */
    List<UserVO> getWakeUpApprenticePage(Map<String,Object> map);

    /**
     * 三天未活跃用户总记录数
     * @param map
     * @return
     */
    int countWakeUpApprentice(Map<String,Object> map);

    /**
     * 判断是否超过活动时间
     * @param map
     * @return
     */
    User isSurpassingActivtiy(Map<String,Object> map);

    /**
     * 判断是否是首次收徒
     * @param userId
     * @return
     */
    User isParentFirstRecruit(Long userId);

    /**
     * 获取徒弟数
     * @param userId
     * @return
     */
    int getApprenticeSum(Long parentId);

}