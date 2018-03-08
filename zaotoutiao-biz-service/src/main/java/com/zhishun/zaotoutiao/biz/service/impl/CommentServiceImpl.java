/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.ICommentService;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.entity.UserComments;
import com.zhishun.zaotoutiao.core.model.entity.UserGiveLike;
import com.zhishun.zaotoutiao.core.model.vo.InfosVo;
import com.zhishun.zaotoutiao.core.model.vo.UserCommentsVO;
import com.zhishun.zaotoutiao.dal.mapper.InfosMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserCommentsMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserGiveLikeMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author BugMan
 * @version $Id: CommentServiceImpl, v0.1 2018年02月27日 15:01BugMan Exp $
 * 用户评论点赞相关信息
 */

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private InfosMapper infosMapper;

    @Autowired
    private UserGiveLikeMapper userGiveLikeMapper;

    @Autowired
    private UserCommentsMapper userCommentsMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<InfosVo> getNewCommentVO(String infoId, int userId, int pageNo, int pageSize) {
        int startNo = (pageNo-1) * pageSize;
        int endNo = pageNo * pageSize;
        Map<String,Object> map = Maps.newHashMap();
        map.put("infoId", infoId);
        map.put("userId", userId);
        map.put("startNo", startNo);
        map.put("endNo", endNo);

        List<InfosVo> commentVOList = infosMapper.getNewComments(map);

        return commentVOList;
    }

    @Override
    public List<InfosVo> getHotCommentVO(String infoId, int userId, int pageNo, int pageSize) {
        int startNo = (pageNo-1) * pageSize;
        int endNo = pageNo * pageSize;
        Map<String,Object> map = Maps.newHashMap();
        map.put("infoId", infoId);
        map.put("userId", userId);
        map.put("startNo", startNo);
        map.put("endNo", endNo);

        List<InfosVo> commentVOList = infosMapper.getHotComments(map);

        return commentVOList;
    }


    @Override
    public Boolean isMyLike(int userId, int commentsId) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId",userId);
        map.put("commentsId",commentsId);
        UserGiveLike result = userGiveLikeMapper.getUserGiveLike(map);
        //判断自己是否点赞
        if(null != result && result.getIsLike() == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int getCommentsNumByInfoId(String infoId) {
        return userCommentsMapper.getCommentsNumByInfoId(infoId);
    }

    @Override
    public List<UserCommentsVO> getUserListOrByKey(String keyword) {
        List<UserComments> userCommentsList = new ArrayList<UserComments>();
        List<UserCommentsVO> userCommentsVOList = new ArrayList<UserCommentsVO>();
        if("".equals(keyword) || StringUtils.isEmpty(keyword)){
            //没有关键词，直接返回所有评论
            userCommentsList.addAll(userCommentsMapper.getCommentsList());
        }else{
            //根据关键词获取评论
            String key = new StringBuilder().append("%").append(keyword).append("%").toString();
            userCommentsList.addAll(userCommentsMapper.getCommentsByKeywordList(key));
        }
        for(UserComments userComments:userCommentsList){
            //创建评论返回视图类
            UserCommentsVO userCommentsVO = new UserCommentsVO();
            userCommentsVO.setContent(userComments.getContent());
            userCommentsVO.setCreateDate(userComments.getCreateDate());
            userCommentsVO.setId(userComments.getId());

            //假如用户或新闻被删除，需要处理id是null的情况
            User user = userMapper.selectByPrimaryKey(userComments.getUserId());
            if(StringUtils.isEmpty(user)){
                userCommentsVO.setNickName("无该用户");
            }else {
                userCommentsVO.setNickName(user.getNickName());
            }

            String title = infosMapper.getInfoTitle(userComments.getInfoId());
            if(StringUtils.isEmpty(title)){
                userCommentsVO.setTitle("无该新闻");
            }else {
                userCommentsVO.setTitle(title);
            }

            userCommentsVOList.add(userCommentsVO);
        }
        return userCommentsVOList;
    }

}
