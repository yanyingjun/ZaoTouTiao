/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.web.home.controller.user;

import com.zhishun.zaotoutiao.biz.service.ICommentsService;
import com.zhishun.zaotoutiao.core.model.vo.UserCommentsVO;
import com.zhishun.zaotoutiao.web.home.constant.request.CommentMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.CommentMsgView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author BugMan
 * @version $Id: CommentController, v0.1 2018年03月07日 11:06BugMan Exp $
 */
@Controller
public class CommentController {

    @Autowired
    private ICommentsService iCommentService;

    /**
     * 评论管理
     * @return
     */
    @RequestMapping(value = CommentMsgReq.COMMENT_MANAGEMENT_REQ)
    public String review(){
        return CommentMsgView.COMMENT_REVIEW_VIEW;
    }

    /**
     * 获取评论列表
     * @param keyword
     * @return
     */
    @RequestMapping(value = CommentMsgReq.COMMENTS_LIST_REQ)
    @ResponseBody
    public List<UserCommentsVO> exchangeRate(@RequestParam(value="keyword",defaultValue = "") String keyword){
        return iCommentService.getUserListOrByKey(keyword);
    }

    /**
     * 根据评论删除用户留言
     * @param id
     * @return
     */
    @RequestMapping(value = CommentMsgReq.COMMENTS_DELETE_BY_ID)
    @ResponseBody
    public int delComments(String id){
        Long getId = Long.valueOf(id);
        return iCommentService.delComments(getId);
    }
}
