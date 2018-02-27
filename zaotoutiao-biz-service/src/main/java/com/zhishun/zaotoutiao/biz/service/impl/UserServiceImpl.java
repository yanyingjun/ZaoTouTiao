/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageBuilder;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.entity.UserGoldRecord;
import com.zhishun.zaotoutiao.core.model.vo.UserGoldRecordVO;
import com.zhishun.zaotoutiao.core.model.vo.UserMoneyRecordVO;
import com.zhishun.zaotoutiao.core.model.vo.UserVO;
import com.zhishun.zaotoutiao.dal.mapper.UserGoldRecordMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserMoneyRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserServiceImpl, v0.1 2018年02月10日 10:12闫迎军(YanYingJun) Exp $
 */

@Service
public class UserServiceImpl implements IUserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserGoldRecordMapper userGoldRecordMapper;

    @Autowired
    private UserMoneyRecordMapper userMoneyRecordMapper;

    @Override
    public User getUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User getUserByMap(String telephone) {
        //查询用户信息
        Map<String,Object> map = Maps.newHashMap();
        map.put("telephone", telephone);
        return userMapper.getUserByMap(map);
    }

    @Override
    public Boolean isUserLogin(String telephone, String password) {
        //查询用户信息
        Map<String,Object> map = Maps.newHashMap();
        map.put("telephone", telephone);
        map.put("password", password);
        User user = userMapper.getUserByMap(map);
        if(StringUtils.isEmpty(user)){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void updateUserInfo(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User getUserByParam(Map<String,Object> map) {
        return userMapper.getUserByMap(map);
    }

    @Override
    public List<UserGoldRecordVO> getUserGoldRecord(Long userId) {
        return userGoldRecordMapper.getUserGoldRecord(userId);
    }

    @Override
    public List<UserMoneyRecordVO> getUserMoneyRecord(Long userId) {
        return userMoneyRecordMapper.getUserMoneyRecord(userId);
    }

    @Override
    public BigDecimal getGoldYesterday(Long userId) {
        return userGoldRecordMapper.getGoldYesterday(userId);
    }

    @Override
    public BigDecimal getMoneyYesterday(Long userId) {
        return userMoneyRecordMapper.getMoneyYesterday(userId);
    }

    @Override
    public BigDecimal getGoldAll(Long userId) {
        return userGoldRecordMapper.getGoldAll(userId);
    }

    @Override
    public BigDecimal getMoneyAll(Long userId) {
        return userMoneyRecordMapper.getMoneyAll(userId);
    }

    @Override
    public Page<User> getApprenticePage(Long userId, PageRequest pageRequest) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        int total = userMapper.countApprentice(map);
        if (pageRequest != null) {
            map.put("limit", pageRequest.getPageSize());
            map.put("offset", pageRequest.getOffset());
        }
        List<User> list = userMapper.getApprentice(map);
        return PageBuilder.buildPage(pageRequest, list, total);
    }

    @Override
    public User getUserParent(String myInvitation) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("myInvitation", myInvitation);
        return userMapper.getUserByMap(map);
    }

    @Override
    public User getParentApprentice(Long userId, Long parentId) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("parentId", parentId);
        return userMapper.getUserByMap(map);
    }

    @Override
    public int addUserGoldRecord(UserGoldRecord userGoldRecord) {
        return userGoldRecordMapper.insertSelective(userGoldRecord);
    }

    @Override
    public Page<UserVO> getWakeUpApprenticePage(Long userId, PageRequest pageRequest) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        int count = userMapper.countWakeUpApprentice(map);
        if(!StringUtils.isEmpty(pageRequest)){
            map.put("offset", pageRequest.getOffset());
            map.put("limit", pageRequest.getPageSize());
        }
        List<UserVO> list = userMapper.getWakeUpApprenticePage(map);
        return PageBuilder.buildPage(pageRequest, list, count);
    }
}
