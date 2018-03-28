/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.ICommentsService;
import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageBuilder;
import com.zhishun.zaotoutiao.common.base.pagination.PageNoAndSize;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.entity.UserComments;
import com.zhishun.zaotoutiao.core.model.entity.UserGiveLike;
import com.zhishun.zaotoutiao.core.model.vo.InfosVO;
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
 * @author 闫迎军(YanYingJun)
 * @version $Id: CommentsServiceImpl, v0.1 2018年02月28日 18:11闫迎军(YanYingJun) Exp $
 */
@Service
public class CommentsServiceImpl implements ICommentsService{

    @Autowired
    private InfosMapper infosMapper;

    @Autowired
    private UserCommentsMapper userCommentsMapper;

    @Autowired
    private UserGiveLikeMapper userGiveLikeMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<InfosVO> getMyCommentsList(Long userId, PageRequest pageRequest) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        int count = infosMapper.countMyCommentsList(map);
        if(!StringUtils.isEmpty(pageRequest)){
            map.put("offset", pageRequest.getOffset());
            map.put("limit", pageRequest.getPageSize());
        }
        List<InfosVO> list = infosMapper.getMyCommentsList(map);
        return PageBuilder.buildPage(pageRequest, list, count);
    }

    @Override
    public int delMyComments(Long userId, String infoId) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        if(!StringUtils.isEmpty(infoId)){
            map.put("infoId", infoId);
        }
        return userCommentsMapper.delUserComments(map);
    }

    @Override
    public int delUserGiveLike(Long userId, Long commentsId) {
        Map map = Maps.newHashMap();
        map.put("userId", userId);
        if(!StringUtils.isEmpty(commentsId)){
            map.put("commentsId", commentsId);
        }
        return userGiveLikeMapper.delUserGiveLike(map);
    }

    @Override
    public UserComments getUserCommentsByInfoId(String infoId) {
        return userCommentsMapper.getUserCommentsByInfoId(infoId);
    }

    @Override
    public int countUserComments(String infoId) {
        return userCommentsMapper.countUserComments(infoId);
    }

    @Override
    public int addComments(UserComments userComments) {
        return userCommentsMapper.insertSelective(userComments);
    }

    @Override
    public List<UserCommentsVO> getNewCommentVO(String infoId, int userId, int pageNo, int pageSize) {Map pageMap = PageNoAndSize.getNum(pageNo,pageSize);
        Map<String,Object> map = Maps.newHashMap();
        map.put("infoId", infoId);
        map.put("userId", userId);
        map.put("startNo", pageMap.get("startNo"));
        map.put("endNo", pageMap.get("endNo"));

        List<UserCommentsVO> commentVOList = userCommentsMapper.getNewComments(map);

        return commentVOList;
    }

    @Override
    public List<UserCommentsVO> getHotCommentVO(String infoId, int userId, int pageNo, int pageSize) {
        Map pageMap = PageNoAndSize.getNum(pageNo,pageSize);
        Map<String,Object> map = Maps.newHashMap();
        map.put("infoId", infoId);
        map.put("userId", userId);
        map.put("startNo", pageMap.get("startNo"));
        map.put("endNo", pageMap.get("endNo"));
        List<UserCommentsVO> commentVOList = userCommentsMapper.getHotComments(map);

        return commentVOList;
    }


    @Override
    public Boolean isMyLike(int userId, Long commentsId) {
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

    @Override
    public int delComments(Long id) {
        return userCommentsMapper.deleteByPrimaryKey(id);
    }
}
