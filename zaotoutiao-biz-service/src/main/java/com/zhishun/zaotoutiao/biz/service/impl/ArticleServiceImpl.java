/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IArticleService;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.core.model.entity.*;
import com.zhishun.zaotoutiao.core.model.vo.StaticFaqVO;
import com.zhishun.zaotoutiao.dal.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ArticleServiceImpl, v0.1 2018年02月26日 18:36闫迎军(YanYingJun) Exp $
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private UserCommentsMapper userCommentsMapper;

    @Autowired
    private UserGiveLikeMapper userGiveLikeMapper;

    @Autowired
    private UserGoldRecordMapper userGoldRecordMapper;

    @Autowired
    private StaticGoldConfigMapper staticGoldConfigMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private StaticFaqMapper staticFaqMapper;

    @Autowired
    private StaticGetGoldMethodMapper staticGetGoldMethodMapper;

    @Override
    public int addComments(UserComments userComments) {
        userComments.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        userComments.setLikes(0);
        return userCommentsMapper.insertSelective(userComments);
    }

    @Override
    public UserComments getUserCommonets(Long id) {
        return userCommentsMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserGiveLike getUserGiveLike(Long userId, Long commentsId) {
        Map<String,Object> map = Maps.newHashMap();
        if(!StringUtils.isEmpty(userId)){
            map.put("userId", userId);
        }
        if(!StringUtils.isEmpty(commentsId)){
            map.put("commentsId", commentsId);
        }
        return userGiveLikeMapper.getUserGiveLike(map);
    }

    @Override
    public int upateUserGiveLike(UserGiveLike userGiveLike) {
        return userGiveLikeMapper.updateByPrimaryKeySelective(userGiveLike);
    }

    @Override
    public int countUserGiveLike(Long commentsId) {
        return userGiveLikeMapper.countUserGiveLike(commentsId);
    }

    @Override
    public int updateUserComments(UserComments userComments) {
        return userCommentsMapper.updateByPrimaryKeySelective(userComments);
    }

    @Override
    public UserComments getUserCommonetsInfo(Long id, Long userId) {
        Map<String,Object> map = Maps.newHashMap();
        if(!StringUtils.isEmpty(id)){
            map.put("id", id);
        }
        if(!StringUtils.isEmpty(userId)){
            map.put("userId", userId);
        }
        return userCommentsMapper.getUserCommnetsInfo(map);
    }

    @Override
    public int addUserGiveLike(Long userId, Long commentsId) {
        UserGiveLike userGiveLike = new UserGiveLike();
        userGiveLike.setUserid(userId);
        userGiveLike.setCommentsid(commentsId);
        userGiveLike.setIsLike((byte)1);
        userGiveLike.setCreatedate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        return userGiveLikeMapper.insertSelective(userGiveLike);
    }

    @Override
    public void isCommentsLikeGold(Long commentsId, int likes) {
        if(likes >= 2){
            //查询评论字数
            UserComments userComments = userCommentsMapper.selectByPrimaryKey(commentsId);
            Long authorId = userComments.getUserId();
            String infoId = userComments.getInfoId();
            String content = userComments.getContent();
            if(!StringUtils.isEmpty(content) && content.length() > 10){
                //判断当前评论是否获得过奖励
                Map<String,Object> map = Maps.newHashMap();
                map.put("userId", authorId);
                map.put("infoId", infoId);
                UserGoldRecord userGoldRecord = userGoldRecordMapper.getCommentsHasGold(map);
                if(StringUtils.isEmpty(userGoldRecord)){
                    //查询金币配置
                    StaticGoldConfig staticGoldConfig = staticGoldConfigMapper.listGoldConfig().get(0);
                    int highestGold = staticGoldConfig.getHighest();
                    //查询作者今天评论获得的金币数
                    int gold = userGoldRecordMapper.getTodayCommentsGold(authorId);
                    if(gold < highestGold){
                        //添加评论奖励记录
                        String goldNum = staticGoldConfig.getValue();
                        //新闻评论类型
                        int source = 5;
                        userGoldRecord = new UserGoldRecord();
                        userGoldRecord.setGold(Long.valueOf(goldNum));
                        userGoldRecord.setUserId(authorId);
                        userGoldRecord.setInfoId(infoId);
                        userGoldRecord.setType((byte)1);
                        userGoldRecord.setSource(source);
                        userGoldRecordMapper.insertSelective(userGoldRecord);

                        //更新用户金币数量
                        User user = userMapper.selectByPrimaryKey(authorId);
                        BigDecimal newGold = new BigDecimal(goldNum).add(new BigDecimal(user.getGold()));
                        user.setGold(newGold.longValue());
                        userMapper.updateByPrimaryKeySelective(user);
                    }
                }
            }
        }
    }

    @Override
    public Content getContent(Long infoId) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("infoId",infoId);
        return contentMapper.getContent(map);
    }

    @Override
    public List<StaticFaqVO> listFaq() {
        return staticFaqMapper.listFaq();
    }

    @Override
    public List<StaticGetGoldMethod> listGoldMethod() {
        return staticGetGoldMethodMapper.listGoldMethod();
    }
}
