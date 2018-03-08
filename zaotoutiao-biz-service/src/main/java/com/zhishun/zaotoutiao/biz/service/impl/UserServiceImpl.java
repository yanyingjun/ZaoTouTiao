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
import com.zhishun.zaotoutiao.core.model.entity.*;
import com.zhishun.zaotoutiao.core.model.vo.StaticIndustrysVO;
import com.zhishun.zaotoutiao.core.model.entity.StaticFakeData;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.entity.UserGoldRecord;
import com.zhishun.zaotoutiao.core.model.entity.UserMoneyRecord;
import com.zhishun.zaotoutiao.core.model.vo.UserGoldRecordVO;
import com.zhishun.zaotoutiao.core.model.vo.UserMoneyRecordVO;
import com.zhishun.zaotoutiao.core.model.vo.UserVO;
import com.zhishun.zaotoutiao.dal.mapper.*;
import com.zhishun.zaotoutiao.dal.mapper.UserGoldRecordMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserInformationMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserMoneyRecordMapper;
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
        user.setGold(newGold.longValue());
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
    public int isRead(Long userId, Long infoId) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("infoId", infoId);
        UserReadRecord userReadRecord = userReadRecordMapper.getUserReadRecord(map);
        map.put("isRequestGold", 1);
        UserReadRecord userReadRecord1 = userReadRecordMapper.getUserReadRecord(map);
        if(StringUtils.isEmpty(userReadRecord1)){
            //判断是否添加到阅读列表
            if(StringUtils.isEmpty(userReadRecord)){
                return 0;
            }else{
                userReadRecord.setIsRequestGold(1);
                userReadRecordMapper.updateReadRecord(userReadRecord);
                return 2;
            }
        }else {
            return 1;
        }
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
    public int addUserGoldRecord(int source, Long userId, Long gold, Long apprenticeId) {
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
    public User isParentFirstRecruit(Long userId) {
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
    public int addUser(String telephone, String password, String wechatId, String wechatHead, String wechatName) {
        password = Md5Util.md5Encode(password);
        //我的邀请码为我的手机号转16进制
        String myInvitation = Long.toHexString(Long.valueOf(telephone));
        String name = 'A' + telephone.substring(3,11);
        //设置默认头像
        String headpath = wechatHead;
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setNickName(wechatName);
        user.setMyInvitation(myInvitation);
        user.setHeadPath(headpath);
        user.setWechatId(wechatId);
        user.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        int id = userMapper.insertSelective(user);

        //添加用户关注频道
        UserChannels userChannels = new UserChannels();
        userChannels.setUserId(Long.valueOf(id));
        userChannelsMapper.insertSelective(userChannels);
        return id;
    }

    @Override
    public void updateWechatUser(String telephone, String wechatName, String wechatId, String wechatHead) {
        String nickName = "手机用户_" + telephone.substring(7, 11);
        String headpath = "http://daoyi-content.oss-cn-hangzhou.aliyuncs.com/default_headpath.png";
        Map<String,Object> map = Maps.newHashMap();
        map.put("telephone", telephone);
        User user = userMapper.getUserByMap(map);
        //判断是否是之前绑定的状态
        if(!user.getWechatId().equals(wechatId)){
            //判断用户是否改变过
            if(user.getNickName().equals(nickName)){
                nickName = wechatName;
                user.setNickName(nickName);
            }
            if(user.getHeadPath().equals(headpath)){
                headpath = wechatHead;
                user.setHeadPath(headpath);
            }
            user.setWechatId(wechatId);
            userMapper.updateByPrimaryKeySelective(user);
        }
    }


    @Override
    @Transactional
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
    public int addUserInfo(String telephone, String password) {
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
        int id = userMapper.insertSelective(user);

        //添加用户关注频道
        UserChannels userChannels = new UserChannels();
        userChannels.setUserId(Long.valueOf(id));
        userChannelsMapper.insertSelective(userChannels);

        return id;
    }

    @Override
    public Page<UserVO> listCanBePresentedUser(String keyWord, String channelId, String createDate, BigDecimal money, PageRequest pageRequest) {
        Map map = Maps.newHashMap();
        if(!StringUtils.isEmpty(keyWord)){
            map.put("keyWord", keyWord);
        }
        if(!StringUtils.isEmpty(channelId)){
            map.put("channelId", channelId);
        }
        if(!StringUtils.isEmpty(createDate)){
            map.put("createDate", createDate);
        }
        if(!StringUtils.isEmpty(money)){
            map.put("money", money);
        }
        int total = userMapper.countCanBePresentedUser(map);
        if(!StringUtils.isEmpty(pageRequest)){
            map.put("offset", pageRequest.getOffset());
            map.put("limit", pageRequest.getPageSize());
        }
        List<UserVO> list = userMapper.listCanBePresentedUser(map);
        return PageBuilder.buildPage(pageRequest, list, total);
    }
}
