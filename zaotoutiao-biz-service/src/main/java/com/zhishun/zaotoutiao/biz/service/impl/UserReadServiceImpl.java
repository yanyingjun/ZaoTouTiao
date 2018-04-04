/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.zhishun.zaotoutiao.biz.service.IUserReadService;
import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.core.model.entity.*;
import com.zhishun.zaotoutiao.core.model.enums.ChannelEnum;
import com.zhishun.zaotoutiao.core.model.enums.GoldSourceEnum;
import com.zhishun.zaotoutiao.core.model.vo.InfoRankVO;
import com.zhishun.zaotoutiao.core.model.vo.InfosVO;
import com.zhishun.zaotoutiao.core.model.vo.LabelVO;
import com.zhishun.zaotoutiao.core.model.vo.NavigationVO;
import com.zhishun.zaotoutiao.dal.mapper.*;
import groovy.util.MapEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author BugMan
 * @version $Id: UserReadServiceImpl, v0.1 2018年03月02日 17:28BugMan Exp $
 */

@Service
public class UserReadServiceImpl implements IUserReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserReadServiceImpl.class);

    @Autowired
    private UserJpushMapper userJpushMapper;

    @Autowired
    private UserInformationMapper userInformationMapper;

    @Autowired
    private UserReadRecordMapper userReadRecordMapper;

    @Autowired
    private ExchangeRateMapper exchangeRateMapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private ChannelsMapper channelsMapper;

    @Autowired
    private InfosMapper infosMapper;

    @Autowired
    private UserShareMapper userShareMapper;

    @Autowired
    private UserCollectMapper userCollectMapper;

    @Autowired
    private UserCommentsMapper userCommentsMapper;

    @Override
    public boolean isUserRead(String id, Long userId, String type) {
        if ("news".equals(type) || "video".equals(type)){
            Map<String,Object> map = Maps.newHashMap();
            map.put("infoId",id);
            map.put("userId",userId);
            int result = userJpushMapper.updateUserReadByInfoId(map);
            if(0 < result){
                return true;
            }
            return false;
        }else if("MSG".equals(type) || "NOTICE".equals(type)){
            Map<String,Object> map = Maps.newHashMap();
            map.put("informationId",Integer.parseInt(id));
            map.put("userId",userId);
            int res = userInformationMapper.updateInformationById(map);
            int result = userJpushMapper.updateUserReadByInformationId(map);
            if(0 < result && 0 < res){
                //加入更新都成功返回true
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public int readRecordAdd(UserReadRecord userReadRecord) {
            return userReadRecordMapper.insertSelective(userReadRecord);

        //判断用户是否浏览过该新闻
        //if(!isRead(userId,infoId)){
        //    //新手任务
        //    //判断是否超过活动日期
        //    //判断是否超过活动天数newbie_read_time < 0为该活动永不过期
        //    int newbieReadTime = getNewbieReadTime();
        //    Map<String,Object> map = Maps.newHashMap();
        //    List<UserReadRecord> userReadRecordList = userReadRecordMapper.isContinuousReadYesterday(userId);
        //    map.put("readActivityDays",newbieReadTime);
        //    map.put("userId",userId);
        //    Map<String,Object> maps = Maps.newHashMap();
        //    maps.put("userId",userId);
        //    maps.put("infoId",infoId);
        //    maps.put("infoType",infoType);
        //    maps.put("channelId",channelId);
        //    maps.put("label",label);
        //    maps.put("title",title);
        //    maps.put("source",source);
        //    int readContinuousDay = 0;
        //    //查询连续阅读天数
        //    if(userReadRecordList.size() > 0){
        //        readContinuousDay = userReadRecordList.get(0).getReadContinuousDay();
        //    }
        //    if(userMapper.isSurpassingActivity(map) > 0 ||  newbieReadTime < 0){
        //        //查询昨天是否已经阅读
        //        if(userReadRecordList.size() <= 0){
        //            if(readContinuousDay >= 3 ){
        //                //如果超过三天，则从三天继续
        //                maps.put("readContinuousDay",readContinuousDay+1);
        //                userReadRecordMapper.addLookRecord(maps);
        //            }else{
        //                //没有连续阅读,重头开始
        //                maps.put("readContinuousDay",1);
        //                userReadRecordMapper.addLookRecord(maps);
        //            }
        //        }else{
        //            //判断今天是否已阅读
        //            if(userReadRecordMapper.isContinuousReadToday(userId) > 0){
        //                maps.put("readContinuousDay",readContinuousDay);
        //                userReadRecordMapper.addLookRecord(maps);
        //            }else{
        //                //天数 +1
        //                maps.put("readContinuousDay",readContinuousDay+1);
        //                userReadRecordMapper.addLookRecord(maps);
        //            }
        //        }
        //    }else{
        //        //活动过期，按照阅读天数继续添加
        //        //添加到浏览记录
        //        //添加到历史记录列表
        //        //查询连续阅读天数
        //        maps.put("readContinuousDay",readContinuousDay);
        //        userReadRecordMapper.addLookRecord(maps);
        //    }
        //    return true;
        //}else{
        //    return false;
        //}
    }

    @Override
    public UserReadRecord getUserReadRecord(Map map) {
        return userReadRecordMapper.getUserReadRecord(map);
    }

    /**
     * 判断用户是否浏览过该新闻
     * @param userId
     * @param infoId
     * @return
     */
    private Boolean isRead(Long userId, String infoId){
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId",userId);
        map.put("infoId",infoId);
        int res=  userReadRecordMapper.isRead(map);
        if(1 > res){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 获得活动天数
     * @return
     */
    private int getNewbieReadTime(){
        return exchangeRateMapper.getNewbieReadTime();
    }

    @Override
    public int CountReadRecord(Long userId){
        return userReadRecordMapper.isContinuousReadToday(userId);
    }

    @Override
    public UserReadRecord maxReadRecord(Long userId) {
        return userReadRecordMapper.maxReadRecord(userId);
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public void readAddGold(Long userId, int gold, User user, ExchangeRate exchangeRate, UserReadRecord userReadRecord){
        //添加自己阅读金币和金币记录
        //新闻阅读奖励类型

        int source = GoldSourceEnum.NEWS_READING.getValue();
        userService.addUserGoldRecord(source, userId, gold, null);

        //更新用户金币
        userService.updateUserInfo(userId, gold);
        //添加阅读记录
        userReadRecord.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        userReadRecordMapper.insertSelective(userReadRecord);

        //判断是否有师傅
        Long parentId = user.getParentId();
        if(!StringUtils.isEmpty(parentId) && parentId != 0){
            //判断是否已经阅读超过五篇文章，确定是否有师徒关系
            //List<UserReadRecord> listURR = userService.isReadFive(userId);

            //为父类添加金币

            //添加金币和金币记录
            //这里把父类id当作用户id,用户id当作徒弟id,添加的金币作为阅读进贡
            //添加徒弟阅读进贡金币记录
            //徒弟新闻阅读进贡奖励类型
            userService.addUserGoldRecord(GoldSourceEnum.APPRENTICE_READ_TRIBUTE.getValue(),parentId, gold, userId);
            //更新用户金币
            userService.updateUserInfo(parentId, gold);

            //--------收徒奖励 --------
            //获取配置,判断师傅是否是首次收徒
            List<User> user4 = userService.isParentFirstRecruit(parentId);
            if(StringUtils.isEmpty(user4)){
                //给师傅添加收徒奖励和奖励记录（首次）
                Integer parentGoldNum = exchangeRate.getNewbieRecruitGold();
                BigDecimal parentMoneyNum = exchangeRate.getNewbieRecruitMoney();
                //添加金币和金币记录
                userService.addUserGoldRecord(GoldSourceEnum.FOR_THE_FIRST_TIME_AN_APPRENTICE.getValue(),parentId, parentGoldNum, userId);
                //更新用户金币
                userService.updateUserInfo(parentId, parentGoldNum);
                //添加零钱和零钱记录
                userService.addUserMoneyRecord(GoldSourceEnum.SIGN.getValue(), parentId, parentMoneyNum, userId);
                userService.updateUserMoneyRecord(parentId, parentMoneyNum);
            }else{
                //判断师傅已经收到自己的收徒奖励
                UserGoldRecord userGoldRecord1 = userService.isGiveParentRecruitGold(userId, parentId);
                if(StringUtils.isEmpty(userGoldRecord1)){
                    //给师傅添加收徒奖励和奖励记录（普通）
                    BigDecimal goldNum = new BigDecimal(gold).multiply(new BigDecimal(2));
                    userService.addUserGoldRecord(GoldSourceEnum.AN_APPRENTICE.getValue(),parentId, goldNum.intValue(), userId);
                    userService.updateUserInfo(parentId, goldNum.intValue());
                }
            }
            //判断三天内是否添加过唤醒金币
            UserGoldRecord userGoldRecord = userService.getWeekupThreeDayGetGold(userId, GoldSourceEnum.BE_AWAKENED.getValue());
            if(StringUtils.isEmpty(userGoldRecord)){
                //判断三天内是否有师傅唤醒自己
                // 被唤醒类型
                String type = "AWAKEN";
                UserShare userShare = userService.getWeekupThreeDay(userId, type);
                if(!StringUtils.isEmpty(userShare)){
                    //判断当天内被唤醒徒弟阅读大于三篇新闻
                    List<UserReadRecord> listRR = userReadRecordMapper.isReadThreeToday(userId);
                    if(listRR !=null && !listRR.isEmpty() && listRR.size() >= 3){
                        //添加金币和金币记录
                        //给师傅添加唤醒徒弟奖励金币
                        userService.addUserGoldRecord(GoldSourceEnum.Wake_up_the_apprentice.getValue(),parentId, exchangeRate.getAwakenParentGold(), userId);
                        userService.updateUserInfo(parentId, exchangeRate.getAwakenParentGold().intValue());
                        //给师徒弟加被唤醒奖励金币
                        userService.addUserGoldRecord(GoldSourceEnum.BE_AWAKENED.getValue(),userId, exchangeRate.getAwakenUserGold(), userId);
                        userService.updateUserInfo(userId, exchangeRate.getAwakenUserGold().intValue());
                    }
                }
            }
        }

    }

    /**
     * 获得导航排行数据
     * @param dateNum
     * @param date
     * @param appType
     * @return
     */
    @Override
    public List<NavigationVO> getNavList(Integer dateNum, String date, Integer appType){
        //时间类型转换
        Date date1 = DateUtil.toDate(date,DateUtil.DEFAULT_DATE_FORMAT);
        Map<String,Object> map = Maps.newHashMap();
        map.put("dateNum",dateNum);
        map.put("date",date1);
        map.put("appType",appType);
        List<NavigationVO> navigationVOList = Lists.newArrayList();
        //获取特定infoType的导航
        List<Channels> channelsList = channelsMapper.channelListByInfoType(appType);
        for(Channels channels : channelsList){
            map.put("parentId",channels.getChannelId());
            NavigationVO navigationVO = new NavigationVO();
            navigationVO.setId(channels.getId());
            navigationVO.setName(channels.getName());
            navigationVO.setAppType(appType);
            navigationVO.setChannelId(channels.getChannelId());
            navigationVO.setParentId(channels.getParentId());
            navigationVO.setReadNum(userReadRecordMapper.getNavReadNum(map));
            ArrayList<Map.Entry> firstTabsNumAndId = Lists.newArrayList();
            String parentId = channels.getChannelId();
            //获取一级标签列表
            List<Channels> childTabList = channelsMapper.getChildTabList(parentId);
            for(Channels channels1 : childTabList){
                map.put("parentId",channels1.getChannelId());
                //获取一级标签下的阅读数
                Map.Entry mapEntry = new MapEntry(channels1.getId(),userReadRecordMapper.getFirstTabNum(map));
                firstTabsNumAndId.add(mapEntry);
            }
            //阅读量
            firstTabsNumAndId.sort(new Comparator<Map.Entry>() {
                @Override
                public int compare(Map.Entry o1, Map.Entry o2) {
                    int o1Num = Integer.valueOf(o1.getValue().toString());
                    int o2Num = Integer.valueOf(o2.getValue().toString());
                    return o2Num - o1Num;
                }
            });
            //插入前三的数据
            StringBuilder channelsTop3 = getChannelsTop3(firstTabsNumAndId);
            navigationVO.setChannelsTop3(channelsTop3.toString());
            navigationVOList.add(navigationVO);
        }
        //总阅读量
        navigationVOList.sort(new Comparator<NavigationVO>() {
            @Override
            public int compare(NavigationVO o1, NavigationVO o2) {
                int o1readNum = o1.getReadNum().intValue();
                int o2readNum = o2.getReadNum().intValue();
                return o2readNum - o1readNum;
            }
        });
        return navigationVOList;
    }

    /**
     * 获得一级标签排行数据
     * @param dateNum
     * @param date
     * @param appType
     * @param parentId
     * @return
     */
    @Override
    public List<NavigationVO> getFirstTabList(Integer dateNum, String date, Integer appType, String parentId){
        if("".equals(parentId)){
            parentId = null;
        }
        //时间类型转换
        Date date1 = DateUtil.toDate(date,DateUtil.DEFAULT_DATE_FORMAT);
        Map<String,Object> map = Maps.newHashMap();
        map.put("dateNum",dateNum);
        map.put("date",date1);
        map.put("appType",appType);
        map.put("parentId",parentId);
        List<NavigationVO> navigationVOList = Lists.newArrayList();
        //获得一级标签列表,默认为文章下的
        List<Channels> channelsList = channelsMapper.getFirstTabListByParentIdAndInfoType(map);
        for(Channels channels : channelsList){
            map.put("parentId",channels.getChannelId());
            NavigationVO navigationVO = new NavigationVO();
            navigationVO.setId(channels.getId());
            navigationVO.setName(channels.getName());
            navigationVO.setAppType(appType);
            navigationVO.setChannelId(channels.getChannelId());
            navigationVO.setParentId(channels.getParentId());
            navigationVO.setReadNum(userReadRecordMapper.getFirstTabNum(map));
            ArrayList<Map.Entry> secondTabsNumAndId = Lists.newArrayList();
            String parentId2 = channels.getChannelId();
            //获取二级标签列表
            List<Channels> childTabList = channelsMapper.getChildTabList(parentId2);
            for(Channels channels2 : childTabList){
                map.put("channelId",channels2.getChannelId());
                //获取二级标签下的阅读数
                Map.Entry mapEntry = new MapEntry(channels2.getId(),userReadRecordMapper.getSecondTabNum(map));
                secondTabsNumAndId.add(mapEntry);
            }
            secondTabsNumAndId.sort(new Comparator<Map.Entry>() {
                @Override
                public int compare(Map.Entry o1, Map.Entry o2) {
                    int o1Num = Integer.valueOf(o1.getValue().toString());
                    int o2Num = Integer.valueOf(o2.getValue().toString());
                    return o2Num - o1Num;
                }
            });
            //插入前三的数据
            StringBuilder channelsTop3 = getChannelsTop3(secondTabsNumAndId);
            navigationVO.setChannelsTop3(channelsTop3.toString());
            navigationVOList.add(navigationVO);
        }
        //总阅读量
        navigationVOList.sort(new Comparator<NavigationVO>() {
            @Override
            public int compare(NavigationVO o1, NavigationVO o2) {
                int o1readNum = o1.getReadNum().intValue();
                int o2readNum = o2.getReadNum().intValue();
                return o2readNum - o1readNum;
            }
        });
        return navigationVOList;
    }

    /**
     * 获得二级标签排行数据
     * @param dateNum
     * @param date
     * @param appType
     * @param parentId
     * @return
     */
    @Override
    public List<NavigationVO> getSecondTabList(Integer dateNum, String date, Integer appType, String parentId){
        if("".equals(parentId)){
            parentId = null;
        }
        //时间类型转换
        Date date1 = DateUtil.toDate(date,DateUtil.DEFAULT_DATE_FORMAT);
        Map<String,Object> map = Maps.newHashMap();
        map.put("dateNum",dateNum);
        map.put("date",date1);
        map.put("appType",appType);
        map.put("parentId",parentId);
        List<NavigationVO> navigationVOList = Lists.newArrayList();
        //获得二级标签列表,默认为文章下的
        List<Channels> channelsList = channelsMapper.getSecondTabListByParentIdAndInfoType(map);
        for(Channels channels : channelsList){
            NavigationVO navigationVO = new NavigationVO();
            navigationVO.setId(channels.getId());
            navigationVO.setName(channels.getName());
            navigationVO.setAppType(appType);
            navigationVO.setChannelId(channels.getChannelId());
            navigationVO.setParentId(channels.getParentId());
            map.put("channelId",channels.getChannelId());
            navigationVO.setReadNum(userReadRecordMapper.getSecondTabNum(map));
            navigationVOList.add(navigationVO);
        }
        //总阅读量
        navigationVOList.sort(new Comparator<NavigationVO>() {
            @Override
            public int compare(NavigationVO o1, NavigationVO o2) {
                int o1readNum = o1.getReadNum().intValue();
                int o2readNum = o2.getReadNum().intValue();
                return o2readNum - o1readNum;
            }
        });
        return navigationVOList;
    }

    /**
     * 插入标签前三的数据
     * @param tabsNumAndId
     * @return
     */
    private StringBuilder getChannelsTop3(ArrayList<Map.Entry> tabsNumAndId){
        StringBuilder channelsTop3 = new StringBuilder();
        if(0 != tabsNumAndId.size()){
            //如果得到的数据大于3条
            if(tabsNumAndId.size() >= 3){
                for (int i = 0; i < 3; i++){
                    Long tabId = Long.valueOf(tabsNumAndId.get(i).getKey().toString());
                    Long readN = Long.valueOf(tabsNumAndId.get(i).getValue().toString());
                    String tabName = channelsMapper.selectByPrimaryKey(tabId).getName();
                    channelsTop3.append(tabName).append("[").append(readN).append("]").append(" ");
                }
            }else{
                //如果得到的数据小于3条
                for(Map.Entry mapEntry : tabsNumAndId){
                    Long tabId = Long.valueOf(mapEntry.getKey().toString());
                    Long readN = Long.valueOf(mapEntry.getValue().toString());
                    String tabName = channelsMapper.selectByPrimaryKey(tabId).getName();
                    channelsTop3.append(tabName).append("[").append(readN).append("]").append(" ");
                }
            }
        }
        return channelsTop3;
    }


    /**
     * 获得info排行前30
     * @param navChannelId
     * @param theClass  判断是导航还是几级标签
     * @return
     */
    @Override
    public List<InfoRankVO> getInfoRankVOList(String navChannelId,int theClass,Integer dateNum, String date) {
        List<InfoRankVO> infoRankVOList = Lists.newArrayList();
        String channelName = "";
        //判断获取infoIdList的途径
        List<String> infoIdList = Lists.newArrayList();
        Map<String,Object> map = Maps.newHashMap();
        if("".equals(date)){
            date = null;
        }
        map.put("navChannelId",navChannelId);
        map.put("dateNum",dateNum);
        map.put("date",date);
        if(theClass == 0) {
            //导航
            infoIdList = userReadRecordMapper.getInfoId2RankTop30(map);
            channelName = channelsMapper.selectByPrimaryKey(channelsMapper.getIdByChannelId(navChannelId)).getName();
        }else if(theClass == 1){
            //一级标签
            infoIdList = userReadRecordMapper.getInfoId2RankTop30ByFirst(map);
            channelName = channelsMapper.selectByPrimaryKey(channelsMapper.getIdByChannelId(channelsMapper.selectByPrimaryKey(channelsMapper.getIdByChannelId(navChannelId)).getParentId())).getName();
        }else if(theClass == 2){
            //二级标签
            infoIdList = userReadRecordMapper.getInfoId2RankTop30BySecond(map);
            channelName = channelsMapper.getNavNameByChannelId(navChannelId);
        }
        List<InfosVO> infosVOList30 = Lists.newArrayList();
        //根据infoId获得前30的infosVO
        if(infoIdList.size() > 0){
            infosVOList30 = infosMapper.listOfTot(infoIdList);
        }
        //根据得到的infosVO生成InfoRankVO
        if(!infosVOList30.isEmpty()) {
            getRankVOByInfoVO(infoRankVOList,infosVOList30,map,channelName);
            if(infoRankVOList.size()>1){
                //根据阅读量
                sortInfoRankVOList(infoRankVOList);
            }
        }
        return infoRankVOList;
    }


    /**
     * 获得info总排行前100
     * @param dateNum
     * @param date
     * @param appType
     * @return
     */
    @Override
    public List<InfoRankVO> getAllInfoRankVOList(Integer dateNum, String date, Integer appType){
        List<InfoRankVO> infoRankVOList100 = Lists.newArrayList();
        Map<String,Object> map = Maps.newHashMap();
        if("".equals(date)){
            date = null;
        }
        map.put("dateNum",dateNum);
        map.put("date",date);
        map.put("appType",appType);
        List<String> infoIdList = userReadRecordMapper.getInfoId2RankTop100(map);
        List<InfosVO> infoVOList100 = Lists.newArrayList();
        //根据infoId获得前100的infosVO
        if(infoIdList.size() > 0){
            infoVOList100 = infosMapper.listOfTot(infoIdList);
        }
        //根据得到的infosVO生成InfoRankVO
        if(!infoVOList100.isEmpty()){
            getRankVOByInfoVO(infoRankVOList100,infoVOList100,map,null);
        }
        if(infoRankVOList100.size()>1){
            //根据阅读量
            sortInfoRankVOList(infoRankVOList100);
        }
        return infoRankVOList100;
    }

    /**
     * 关键词排行（前100）
     * @param dateNum
     * @param date
     * @param appType
     * @return
     */
    @Override
    public List<LabelVO> getLabelVOList(Integer dateNum, String date, Integer appType) {
        if("".equals(date)){
            date = null;
        }
        List<LabelVO> labelVOList = Lists.newArrayList();
        String infoType = "";
        if(Objects.equals(appType, ChannelEnum.NEWS.getValue())){
            infoType = ChannelEnum.NEWS.getName();
        }
        if(Objects.equals(appType, ChannelEnum.VIDEO.getValue())){
            infoType = ChannelEnum.VIDEO.getName();
        }
        Map<String,Object> map = Maps.newHashMap();
        map.put("dateNum",dateNum);
        map.put("date",date);
        map.put("appType",appType);
        //关键词字段列表
        List<String> keyWordsList = userReadRecordMapper.getKeyWordsList(map);
        //关键词集合
        Set<String> setKeyWords = Sets.newHashSet();
        //关键词和数量映射
        Map<String,Long> mapKeys = Maps.newHashMap();
        if(keyWordsList.size() > 0) {
            for (String keyWords : keyWordsList) {
                //分割每个字段的关键词
                if (null != keyWords) {
                    String[] strArray = keyWords.split("\\|");
                    for (String str : strArray) {
                        if (null == mapKeys.get(str)) {
                            mapKeys.put(str, 1L);
                        } else {
                            Long value = mapKeys.get(str);
                            mapKeys.put(str, value + 1L);
                        }
                    }
                }
            }
        }else{
            return labelVOList;
        }
        //排序
        List<Map.Entry<String,Long>> entryList = Lists.newArrayList(mapKeys.entrySet());
        entryList.sort(new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                int n1 = o1.getValue().intValue();
                int n2 = o2.getValue().intValue();
                return n2 - n1;
            }
        });
        //返回列表
        if(entryList.size() <= 100){
            for(Map.Entry<String,Long> entry : entryList){
                LabelVO labelVO = new LabelVO();
                labelVO.setLabel(entry.getKey());
                labelVO.setReadNum(entry.getValue());
                labelVO.setInfoType(infoType);
                labelVOList.add(labelVO);
            }
        }else{
            for(int i=0; i<100; i++){
                LabelVO labelVO = new LabelVO();
                labelVO.setLabel(entryList.get(i).getKey());
                labelVO.setReadNum(entryList.get(i).getValue());
                labelVO.setInfoType(infoType);
                labelVOList.add(labelVO);
            }
        }
        return labelVOList;
    }

    /**
     * 根据阅读量排序
     * @param infoRankVOList
     */
    private void sortInfoRankVOList(List<InfoRankVO> infoRankVOList){
        //根据阅读量
        infoRankVOList.sort(new Comparator<InfoRankVO>() {
            @Override
            public int compare(InfoRankVO o1, InfoRankVO o2) {
                int o1readNum = o1.getReadNum().intValue();
                int o2readNum = o2.getReadNum().intValue();
                return o2readNum - o1readNum;
            }
        });
    }

    /**
     * 获得InfoRankVO的视图
     * @param infoRankVOList
     * @param infoVOList
     * @param map
     * @param channelName
     * @return
     */
    private void getRankVOByInfoVO(List<InfoRankVO> infoRankVOList, List<InfosVO> infoVOList, Map<String,Object> map, String channelName){
        for(InfosVO infosVO : infoVOList){
            InfoRankVO infoRankVO = new InfoRankVO();
            infoRankVO.setId(infosVO.getId());
            infoRankVO.setInfoType(infosVO.getInfoType());
            infoRankVO.setTitle(infosVO.getTitle());
            infoRankVO.setThumbnails(infosVO.getThumbnails());
            infoRankVO.setSource(infosVO.getSource());

            infoRankVO.setUpdateTime(infosVO.getUpdateTime());
            //导航名
            if(null !=channelName){
                infoRankVO.setChannelName(channelName);
            }else{
                String infoChannelId = infosVO.getChannelId();
                String navName = channelsMapper.getNavNameByChannelId(infoChannelId);
                infoRankVO.setChannelName(navName);
            }
            //获取标签
            if (infosVO.getFirstLevel() != null) {
                infoRankVO.setFirstTabName(infosVO.getFirstLevel());
            }
            if (infosVO.getTwoLevel() != null) {
                infoRankVO.setSecondTabName(infosVO.getTwoLevel());
            }
            map.put("infoId",infosVO.getInfoId());
            //获取阅读数
            infoRankVO.setReadNum(userReadRecordMapper.getReadNumByOneInfo(map));
            //获取分享转发数
            infoRankVO.setShareNum(userShareMapper.getNumByInfoId(map));
            //获取收藏数
            infoRankVO.setCollectNum(userCollectMapper.getNumByInfoId(map));
            //获取评论数
            infoRankVO.setCommentNum((long) userCommentsMapper.getCommentsNumByInfoIdAndTime(map));
            infoRankVOList.add(infoRankVO);
        }
    }
}
