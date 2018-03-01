/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.ICommentsService;
import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageBuilder;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.UserComments;
import com.zhishun.zaotoutiao.core.model.vo.InfosVo;
import com.zhishun.zaotoutiao.dal.mapper.InfosMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserCommentsMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserGiveLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public Page<InfosVo> getMyCommentsList(Long userId, PageRequest pageRequest) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        int count = infosMapper.countMyCommentsList(map);
        if(!StringUtils.isEmpty(pageRequest)){
            map.put("offset", pageRequest.getOffset());
            map.put("limit", pageRequest.getPageSize());
        }
        List<InfosVo> list = infosMapper.getMyCommentsList(map);
        return PageBuilder.buildPage(pageRequest, list, count);
    }

    @Override
    public int delMyComments(Long userId, Long commentsId) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        if(!StringUtils.isEmpty(commentsId)){
            map.put("commentsId", commentsId);
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
}
