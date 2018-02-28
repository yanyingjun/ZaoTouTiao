/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.ICommentService;
import com.zhishun.zaotoutiao.core.model.entity.UserGiveLike;
import com.zhishun.zaotoutiao.core.model.vo.InfosVo;
import com.zhishun.zaotoutiao.dal.mapper.InfosMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserGiveLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author BugMan
 * @version $Id: CommentServiceImpl, v0.1 2018年02月27日 15:01BugMan Exp $
 */

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private InfosMapper infosMapper;

    @Autowired
    private UserGiveLikeMapper userGiveLikeMapper;

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
        if(null != result && result.getIsLike() == 1){
            return true;
        }else{
            return false;
        }
    }
}
