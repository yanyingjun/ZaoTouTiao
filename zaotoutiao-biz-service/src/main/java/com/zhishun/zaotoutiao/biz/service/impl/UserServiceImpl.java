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
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.common.util.Md5Util;
import com.zhishun.zaotoutiao.common.util.RandomUtil;
import com.zhishun.zaotoutiao.core.model.entity.*;
import com.zhishun.zaotoutiao.core.model.vo.StaticIndustrysVO;
import com.zhishun.zaotoutiao.core.model.vo.UserGoldRecordVO;
import com.zhishun.zaotoutiao.core.model.vo.UserMoneyRecordVO;
import com.zhishun.zaotoutiao.core.model.vo.UserVO;
import com.zhishun.zaotoutiao.dal.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private UserReadRecordMapper userReadRecordMapper;

    @Autowired
    private StaticGoldConfigMapper staticGoldConfigMapper;

    @Autowired
    private UserShareMapper userShareMapper;

    @Autowired
    private StaticIndustrysMapper staticIndustrysMapper;

    @Autowired
    private StaticJobsMapper staticJobsMapper;

    @Autowired
    private UserInformationMapper userInformationMapper;

    @Autowired
    private UserLocationMapper userLocationMapper;

    @Autowired
    private UserFeedbackFaqMapper userFeedbackFaqMapper;

    @Autowired
    private UserFeedbackPublishMapper userFeedbackPublishMapper;

    @Autowired
    private UserChannelsMapper userChannelsMapper;

    @Autowired
    private StaticFakeDataMapper staticFakeDataMapper;

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
    public Map getUserByTelephone(String telephone){
        //查询用户信息
        Map<String,Object> map = Maps.newHashMap();
        map.put("telephone", telephone);
        return userMapper.getUserByTelephone(map);
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
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void updateUserInfo(Long userId, int gold) {
        User user = userMapper.selectByPrimaryKey(userId);
        BigDecimal newGold = new BigDecimal(gold).add(new BigDecimal(user.getGold()));
        user.setGold(newGold.intValue());
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

    @Override
    public User isSurpassingActivtiy(int readActivityDays, Long userId) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("readActivityDays", readActivityDays);
        map.put("userId", userId);
        return userMapper.isSurpassingActivtiy(map);
    }

    @Override
    public UserGoldRecord isGetNewbieGoldToday(Long userId) {
        return userGoldRecordMapper.isGetNewbieGoldToday(userId);
    }

    @Override
    public List<UserReadRecord> isReadThreeToday(Long userId) {
        return userReadRecordMapper.isReadThreeToday(userId);
    }

    @Override
    public int addUserGoldRecord(int source, Long userId, int gold, Long apprenticeId) {
        UserGoldRecord userGoldRecord = new UserGoldRecord();
        userGoldRecord = new UserGoldRecord();
        userGoldRecord.setSource(source);
        userGoldRecord.setUserId(userId);
        userGoldRecord.setGold(gold);
        userGoldRecord.setType((byte)1);
        userGoldRecord.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        userGoldRecord.setApprenticeId(apprenticeId);
        return userGoldRecordMapper.insertSelective(userGoldRecord);
    }

    @Override
    public int getReadGoldToday(Long userId, int source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 00:00:00");
        String zeroDay = sdf.format(new Date());
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("source", source);
        map.put("zeroDay", zeroDay);
        return userGoldRecordMapper.getReadGoldToday(map);
    }

    @Override
    public StaticGoldConfig getReadGoldConfig(String name) {
        return staticGoldConfigMapper.getReadGoldConfig(name);
    }

    @Override
    public List<UserReadRecord> isReadFive(Long userId) {
        return userReadRecordMapper.isReadFive(userId);
    }

    @Override
    public List<User> isParentFirstRecruit(Long userId) {
        return userMapper.isParentFirstRecruit(userId);
    }

    @Override
    public int addUserMoneyRecord(int source, Long userId, BigDecimal money, Long apprenticeId) {
        UserMoneyRecord userMoneyRecord = new UserMoneyRecordVO();
        userMoneyRecord.setSource(source);
        userMoneyRecord.setUserId(userId);
        userMoneyRecord.setApprenticeId(apprenticeId);
        userMoneyRecord.setType(1);
        userMoneyRecord.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        return userMoneyRecordMapper.insertSelective(userMoneyRecord);
    }

    @Override
    public int updateUserMoneyRecord(Long userId, BigDecimal money) {
        User user = userMapper.selectByPrimaryKey(userId);
        BigDecimal oldMoney = user.getMoney();
        //获取新零钱数
        BigDecimal newMoney = oldMoney.add(money).setScale(2, BigDecimal.ROUND_HALF_UP);
        user.setMoney(newMoney);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public UserGoldRecord isGiveParentRecruitGold(Long userId, Long parentId) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("apprenticeId", parentId);
        return userGoldRecordMapper.isGiveParentRecruitGold(map);
    }

    @Override
    public UserGoldRecord getWeekupThreeDayGetGold(Long userId, int source) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("source", source);
        return userGoldRecordMapper.getWeekupThreeDayGetGold(map);
    }

    @Override
    public UserShare getWeekupThreeDay(Long userId, String type) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("type", type);
        return userShareMapper.getWeekupThreeDay(map);
    }

    @Override
    public UserGoldRecord getUserGoldRecordInfo(Long userId) {
        return userGoldRecordMapper.getUserGoldRecordInfo(userId);
    }

    @Override
    public List<StaticIndustrysVO> listStaticIndustrys() {
        List<StaticIndustrysVO> listVO = staticIndustrysMapper.listStaticIndustrys();
        for(StaticIndustrysVO vo : listVO){
            List<StaticJobs> listJobs = staticJobsMapper.listStaticJobs(vo.getId());
            vo.setListJobs(listJobs);
        }
        return listVO;
    }

    @Override
    public int delUserReadRecord(Long userId) {
        return userReadRecordMapper.delUserReadRecord(userId);
    }

    @Override
    public int getApprenticeSum(Long parentId) {
        return userMapper.getApprenticeSum(parentId);
    }

    @Override
    public String delUserInformation(int userId, String type) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId",userId);
        map.put("type",type);
        int result = userInformationMapper.deleteByType(map);
        if(result == 0){
            return "通知列表已经为空！";
        }else if(result > 0){
            return "删除成功！";
        }else{
            return "删除失败";
        }
    }

    @Override
    public int addUserLocation(Long userId, float lat, float lng) {
        UserLocation userLocation = new UserLocation();
        userLocation.setUserId(userId);
        userLocation.setLat(lat);
        userLocation.setLng(lng);
        userLocation.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        return userLocationMapper.insertSelective(userLocation);
    }

    @Override
    public List<UserFeedbackFaq> listFeedbackFaq() {
        return userFeedbackFaqMapper.listFeedbackFaq();
    }

    @Override
    public int addUserFeedbackPublish(Long userId, String question, String imgPath) {
        UserFeedbackPublish userFeedbackPublish = new UserFeedbackPublish();
        userFeedbackPublish.setUserId(userId);
        userFeedbackPublish.setQuestion(question);
        userFeedbackPublish.setImgPath(imgPath);
        userFeedbackPublish.setCreateDate(new Date());
        userFeedbackPublish.setIsFinish(0);
        return userFeedbackPublishMapper.insertSelective(userFeedbackPublish);
    }

    @Override
    public Long addUser(String wechatId, String wechatHead, String wechatName) {
        //我的邀请码为11为随机数转16进制
        String myInvitation = String.valueOf(RandomUtil.getSecurityCode(11,RandomUtil.SecurityCodeLevel.Hard,false));
        //设置默认头像
        String headpath = wechatHead;
        User user = new User();
        user.setName(wechatName);
        user.setNickName(wechatName);
        user.setMyInvitation(myInvitation);
        user.setHeadPath(headpath);
        user.setWechatId(wechatId);
        user.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        userMapper.insertSelective(user);

        //添加用户关注频道
        UserChannels userChannels = new UserChannels();
        userChannels.setUserId(user.getUserId());
        userChannelsMapper.insertSelective(userChannels);
        return user.getUserId();
    }

    @Override
    public void updateWechatUser(String telephone, String wechatName, String wechatId, String wechatHead) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("telephone", telephone);
        User user = userMapper.getUserByMap(map);
        //判断是否是之前绑定的状态
        if(!user.getWechatId().equals(wechatId)){
            user.setNickName(wechatName);
            user.setHeadPath(wechatHead);
            user.setWechatId(wechatId);
            userMapper.updateByPrimaryKeySelective(user);
        }
    }


    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public Boolean getNewUserMoney(Long userId) {
        int result = userMoneyRecordMapper.getNewUserMoney(userId);
        if(result > 0){
            return false;
        }else{
            //获得原零钱
            BigDecimal oldMoney = userMapper.selectByPrimaryKey(userId).getMoney();
            //获得新零钱数
            BigDecimal newMoney = new BigDecimal(oldMoney.toString()).add(new BigDecimal("5"));
            //四舍五入到两位小数存储
            newMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
            //存入相应新用户
            User user = new User();
            user.setUserId(userId);
            user.setMoney(newMoney);
            int countByUpdate = userMapper.updateByPrimaryKeySelective(user);
            if(countByUpdate > 0){
                //假如新增成功，添加零钱记录
                UserMoneyRecord userMoneyRecord = new UserMoneyRecord();
                userMoneyRecord.setMoney(new BigDecimal("5"));
                userMoneyRecord.setUserId(userId);
                userMoneyRecord.setType(1);
                userMoneyRecord.setSource(0);
                userMoneyRecord.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                //插入数据库
                int res = userMoneyRecordMapper.insertSelective(userMoneyRecord);
                if( 0 == res){
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public List<StaticFakeData> getFakeData(String type) {
        List<StaticFakeData> staticFakeDataList = staticFakeDataMapper.selectDataByType(type);
        return staticFakeDataList;
    }

    @Override
    public Long addUserInfo(String telephone, String password, Integer platformId, String channelId, String address, String idImei) {
        password = Md5Util.md5Encode(password);
        String nickName = "手机用户_" + telephone.substring(7, 11);
        //我的邀请码为我的手机号转16进制
        String myInvitation = Long.toHexString(Long.valueOf(telephone));
        String name = 'A' + telephone.substring(3,11);
        //设置默认头像
        String headpath = "http://daoyi-content.oss-cn-hangzhou.aliyuncs.com/default_headpath.png";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setNickName(nickName);
        user.setMyInvitation(myInvitation);
        user.setHeadPath(headpath);
        user.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        user.setPlatformId(platformId);
        user.setChannelId(channelId);
        user.setAddress(address);
        user.setIdImei(idImei);
        user.setLastVisitDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));

        userMapper.insertSelective(user);

        //添加用户关注频道
        UserChannels userChannels = new UserChannels();
        userChannels.setUserId(user.getUserId());
        userChannelsMapper.insertSelective(userChannels);

        return user.getUserId();
    }

    @Override
    public List<UserVO> listCanBePresentedUser(String keyWord, String channelId, String createDate, BigDecimal minMoney, BigDecimal maxMoney) {
        Map map = Maps.newHashMap();
        if(!StringUtils.isEmpty(keyWord)){
            map.put("keyWord", keyWord);
        }
        if(!StringUtils.isEmpty(channelId) && Integer.valueOf(channelId) != 0){
            map.put("channelId", channelId);
        }
        if(!StringUtils.isEmpty(createDate)){
            map.put("createDate", createDate);
        }
        if(!StringUtils.isEmpty(minMoney)){
            map.put("minMoney", minMoney);
        }
        if(!StringUtils.isEmpty(maxMoney)){
            map.put("maxMoney", maxMoney);
        }
        return userMapper.listCanBePresentedUser(map);
    }
}
