/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.article;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.ArticleMsgReq;
import com.zhishun.zaotoutiao.api.home.request.UserMsgReq;
import com.zhishun.zaotoutiao.biz.service.*;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.core.model.entity.*;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.InfosVO;
import com.zhishun.zaotoutiao.core.model.vo.Ques;
import com.zhishun.zaotoutiao.core.model.vo.StaticFaqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ArticleController, v0.1 2018年02月26日 17:41闫迎军(YanYingJun) Exp $
 */

@RestController
public class ArticleController extends BaseController{

    @Autowired
    private IArticleService articleService;

    @Autowired
    private INewsService newsService;

    @Autowired
    private ICommentsService commentsService;

    @Autowired
    private IUserCollectService collectService;

    @Autowired
    private IJpushService jpushService;

    @Autowired
    private IFilterNewsService filterNewsService;

    /**
     * 获取文章相关内容
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.ARTICLE_RELATED_REQ, method = RequestMethod.POST)
    public Map<Object,Object> articleRelated(){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
            }

            @Override
            public void handle() throws Exception {

                dataMap.put("result", "success");
                dataMap.put("msg", "返回相关信息成功");
            }
        });

        return dataMap;

    }

    /**
     * 评论点赞和取消点赞
     * @param userId
     * @param commentsId 评论id is_like:表示用户当前是否已点赞 \n like_num:表示该评论点赞人数  \n data:false表示评论已不存在
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.GIVE_LIKE_SET)
    public Map<Object,Object> giveLikeSet(final Long userId, final Long commentsId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotZero(commentsId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                dataMap.put("result", "success");
                //判断评论是否存在
                UserComments userComments = articleService.getUserCommonets(commentsId);
                if(!StringUtils.isEmpty(userComments)){
                   //判断是否点过赞
                   UserGiveLike userGiveLike = articleService.getUserGiveLike(null, commentsId);
                   if(!StringUtils.isEmpty(userGiveLike)){
                       //判断是否自己点赞
                       UserGiveLike userGiveLike1 = articleService.getUserGiveLike(userId, commentsId);
                       if(!StringUtils.isEmpty(userGiveLike1)){
                           //判断点赞或取消点赞
                           if(userGiveLike1.getIsLike() == 0){
                               userGiveLike1.setIsLike((byte)1);
                               articleService.upateUserGiveLike(userGiveLike1);
                               //返回点赞总人数
                               int count = articleService.countUserGiveLike(commentsId);
                               //修改点赞人数
                               userComments.setLikes(count);
                               articleService.updateUserComments(userComments);
                               //获取点赞详情
                               UserComments commentsInfo = articleService.getUserCommonetsInfo(commentsId,userId);
                               dataMap.put("msg", "已点赞");
                               dataMap.put("isLike", "true");
                               dataMap.put("likeNum", count);
                               dataMap.put("data", commentsInfo);
                           }else {
                               //取消点赞
                               userGiveLike1.setIsLike((byte)0);
                               articleService.upateUserGiveLike(userGiveLike1);
                               //返回点赞总人数
                               int count = articleService.countUserGiveLike(commentsId);
                               //修改点赞人数
                               userComments.setLikes(count);
                               articleService.updateUserComments(userComments);
                               //获取点赞详情
                               UserComments commentsInfo = articleService.getUserCommonetsInfo(commentsId,userId);
                               dataMap.put("msg", "已取消点赞");
                               dataMap.put("isLike", "false");
                               dataMap.put("data", commentsInfo);
                           }
                       }else{
                           //不是自己点赞
                           articleService.addUserGiveLike(userId, commentsId);
                           //返回点赞总人数
                           int count = articleService.countUserGiveLike(commentsId);
                           //修改点赞人数
                           userComments.setLikes(count);
                           articleService.updateUserComments(userComments);
                           //获取点赞详情
                           UserComments commentsInfo = articleService.getUserCommonetsInfo(commentsId,userId);
                           dataMap.put("msg", "已点赞");
                           dataMap.put("isLike", "true");
                           dataMap.put("likeNum", count);
                           dataMap.put("data", commentsInfo);
                           //是否获得奖励
                           articleService.isCommentsLikeGold(commentsId, count);

                       }
                   }else{
                       //直接点赞，添加点赞
                       articleService.addUserGiveLike(userId, commentsId);
                       //返回点赞总人数
                       int count = articleService.countUserGiveLike(commentsId);
                       //修改点赞人数
                       userComments.setLikes(count);
                       articleService.updateUserComments(userComments);
                       //获取点赞详情
                       UserComments commentsInfo = articleService.getUserCommonetsInfo(commentsId,userId);
                       dataMap.put("msg", "已点赞");
                       dataMap.put("isLike", "true");
                       dataMap.put("likeNum", count);
                       dataMap.put("data", commentsInfo);

                   }
                }else{
                    dataMap.put("msg", "该评论不存在");
                }
            }
        });

        return dataMap;
    }

    /**
     * 文章类新闻详情页
     * @param infoId
     * @param userId
     * @param isShare 本次是否是分享，不传代表不是分享  \n 分享时必须传userId
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.VIEW_ARTICLE_HTML)
    public Map<Object,Object> viewArticleHtml(final Long infoId, final Long userId, final String isShare){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(infoId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(isShare, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {

                Content content = articleService.getContent(infoId);
                Infos infos = newsService.getInfosByMap(infoId);
                if(StringUtils.isEmpty(content)){
                    dataMap.put("msg", "内容正在处理中......");
                    return;
                }
                String contentHtml = content.getHtmlContent().replace("padding-bottom: ","x-style")
                        .replace("height: ","x-style").replace("width: ","x-style");
                String title = infos.getTitle();
                String publishTime = infos.getPublishTime();
                String source = infos.getSource();
                String strHtml = "<!DOCTYPE html>\n" +
                        "<html lang='zh-CN'>\n" +
                        "<head>\n" +
                        "    <meta charset='utf-8'>\n" +
                        "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n" +
                        "    <meta name='viewport' content='width=Device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no' >  \n" +
                        "    <title>"+title+"</title>\n" +
                        "    <style type='text/css'>\n" +
                        "\n" +
                        "            /* index */\n" +
                        "            #main_box{\n" +
                        "        min-height: 100%;\n" +
                        "        width: 96%;\n" +
                        "        margin: 0 auto;\n" +
                        "        margin-top: 5px;\n" +
                        "    }\n" +
                        "        #main_box .info-detail-title{\n" +
                        "        background-color: #fff;\n" +
                        "        margin: 0 5px 5px;\n" +
                        "        padding-bottom: 10px;\n" +
                        "        border-bottom: 1px solid #ddd;\n" +
                        "    }\n" +
                        "        .info-detail-content{\n" +
                        "        font-size: 18px;\n" +
                        "        color: #333;\n" +
                        "        line-height: 28px;\n" +
                        "        background-color: #fff;\n" +
                        "        padding: 5px 16px;\n" +
                        "    }\n" +
                        "        .info-detail-title h5{\n" +
                        "        font-family: PingFangSC-Medium;\n" +
                        "        line-height: 33px;\n" +
                        "        font-size: 24px;\n" +
                        "        color: #333;\n" +
                        "        letter-spacing: 0;\n" +
                        "        margin: 0;\n" +
                        "        padding-bottom:10px;\n" +
                        "        text-align:justify;\n" +
                        "    }\n" +
                        "        .info-detail-content,\n" +
                        "            .info-detail-title span{\n" +
                        "        font-family: PingFangSC-Regular;\n" +
                        "        letter-spacing: 0;\n" +
                        "    }\n" +
                        "        .info-detail-title span {\n" +
                        "        font-size: 14px;\n" +
                        "        color: #9b9b9b;\n" +
                        "        line-height: 20px;\n" +
                        "    }\n" +
                        "        .info-detail-content img{\n" +
                        "        width:100%;\n" +
                        "    }\n" +
                        "\n" +
                        "    body,html{\n" +
                        "        margin: 0;\n" +
                        "        padding: 0;\n" +
                        "    }\n" +
                        "\n" +
                        "        #recruit_box{\n" +
                        "        height: 50px;\n" +
                        "        width: 100%;\n" +
                        "        padding: 0;\n" +
                        "        margin: 0;\n" +
                        "        border-bottom: 1px solid #E6E6E6;\n" +
                        "        position: relative;\n" +
                        "        top: 0;\n" +
                        "        background-color: rgba(255,255,255,.95);\n" +
                        "        display: none;\n" +
                        "    }\n" +
                        "        #recruit_box img{\n" +
                        "        width: 35px;\n" +
                        "        height: 35px;\n" +
                        "        border-radius: 3px;\n" +
                        "        margin-top: 7px;\n" +
                        "        margin-left: 7px;\n" +
                        "        display: inline-block;\n" +
                        "        float: left;\n" +
                        "    }\n" +
                        "        #recruit_box span{\n" +
                        "        display: inline-block;\n" +
                        "        float: left;\n" +
                        "        line-height: 50px;\n" +
                        "        margin-left: 10px;\n" +
                        "        font-size: 14px;\n" +
                        "    }\n" +
                        "        #recruit_box button{\n" +
                        "        display: inline-block;\n" +
                        "        float: right;\n" +
                        "        margin-right: 10px;\n" +
                        "        width: 90px;\n" +
                        "        height: 30px;\n" +
                        "        margin-top: 10px;\n" +
                        "        border-radius: 15px;\n" +
                        "        background-color: #D53D3A;\n" +
                        "        color: #fff;\n" +
                        "        border: none;\n" +
                        "        outline: none;\n" +
                        "        font-size: 15px;\n" +
                        "    }\n" +
                        "\n" +
                        "    </style>\n" +
                        "\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <!-- 中间内容 -->\n" +
                        "    <div id='recruit_box'>\n" +
                        "        <img src='http://daoyi-content.oss-cn-hangzhou.aliyuncs.com/logo.png'>\n" +
                        "        <span>轻松阅读赚零钱</span>\n" +
                        "        <button onclick='recruit()'>领红包</button>\n" +
                        "    </div>\n" +
                        "    <div id='main_box' class='container row'>\n" +
                        "        <div class='info-detail-title'>\n" +
                        "            <h5>$title</h5>\n" +
                        "            <span>"+source+"</span>\n" +
                        "            <span style='float: right;'>"+publishTime+"</span>\n" +
                        "        </div>\n" +
                        "        \n" +
                        "        <div class='info-detail-content'>\n" +
                        "            <div style='text-align:left;margin:5px -15px'>"+content+"</div>\n" +
                        "    </div>\n" +
                        "<script type='text/javascript'>\n" +
                        "            　　var myUrl=document.location.href;\n" +
                        "    if(myUrl.indexOf('is_share') > 0 ){\n" +
                        "        document.getElementById('recruit_box').style.display='block';\n" +
                        "    }\n" +
                        "\n" +
                        "    var userId = myUrl.split('userId=')[1];\n" +
                        "    //判断是否是分享\n" +
                        "    //展示分享页\n" +
                        "    function recruit(){\n" +
                        "        window.location.href='http://www.cloudconfs.com:8080/daoyi_web/general/static_html?name=SHARE_RECRUIT&userId='+userId;\n" +
                        "    }\n" +
                        "\n" +
                        "</script>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>'";
                strHtml = strHtml.replace("&quot;", "\"" ).replace("data-src","src")
                        .replace("padding-bottom: 66.66666666666666%; height: 0; width: 100%","")
                        .replace("padding-bottom: 150%; height: 0; width: 100%","")
                        .replace("padding-bottom: 66.625%; height: 0; width: 100%","")
                        .replace("padding-bottom: 66.5%; height: 0; width: 100%","")
                        .replace("padding-bottom: 98.16666666666667%; height: 0; width: 100%","");
                dataMap.put("strHtml", strHtml);
            }
        });

        return dataMap;
    }

    /**
     * 文章类新闻详情页JSON
     * @param infoId
     * @param userId
     * @param isShare
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.GET_ARTICLE_REQ)
    public Map<Object,Object> getArticleJson(final Long infoId, final Long userId, final String isShare){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(infoId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(isShare, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {

                Content content = articleService.getContent(infoId);
                Infos infos = newsService.getInfosByMap(infoId);
                if(StringUtils.isEmpty(content)){
                    dataMap.put("result", "success");
                    dataMap.put("msg", "返回信息成功");
                    dataMap.put("content", "内容正在处理中......");
                }else{
                    String contentHtml = content.getHtmlContent();
                    contentHtml.replace("padding-bottom: ","x-style:")
                            .replace("height: ","x-style:")
                            .replace("width: ","x-style:")
                            .replace("&quot;","\"")
                            .replace("&amp;","&")
                            .replace("data-src","src");

                    String title = infos.getTitle();
                    String publishTime = infos.getPublishTime();
                    String source = infos.getSource();
                    //String[] images = {};
                }


            }
        });

        return dataMap;
    }

    /**
     * 常见问题
     * @return
     */
    @RequestMapping(value = UserMsgReq.FAQ_GET, method = RequestMethod.GET)
    public Map<Object,Object> faqGet(){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                //String articleType = "article";
                //String videoType = "video";
                List<StaticFaqVO> list = articleService.listFaq();
                List<Ques> listQus = Lists.newArrayList();
                for(StaticFaqVO faq : list){
                    if(faq.getType().equals("skip")){
                        String[] ans = faq.getAnswer().split(",");
                        String[] urls = faq.getSkipUrl().split(",");

                        for(int i = 0;i <ans.length;i++){
                            Ques ques = new Ques();
                            ques.setAn(ans[i]);
                            ques.setUrl(urls[i]);
                            listQus.add(ques);
                        }
                        faq.setListQues(listQus);
                    }
                }
                dataMap.put("result", "success");
                dataMap.put("msg", "相关信息返回成功");
                dataMap.put("faqArticle", list);
                //dataMap.put("faqVideo", );

            }
        });

        return dataMap;
    }

    /**
     * 怎么获得金币奖励
     * @return
     */
    @RequestMapping(value = UserMsgReq.HOW_GET_GOLD, method = RequestMethod.GET)
    public Map<Object,Object> howGetGold(){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                List<StaticGetGoldMethod> list = articleService.listGoldMethod();
                dataMap.put("result", "success");
                dataMap.put("msg", "返回信息成功");
                dataMap.put("data", list);

            }
        });

        return dataMap;
    }

    /**
     * 随机推荐文章
     * @param name
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.RANDOM_ARTICLE, method = RequestMethod.GET)
    public Map<Object,Object> randomArticle(final String name){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(name, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {

                StaticHtml staticHtml = newsService.getStaticHtml(name);
                String title = staticHtml.getTitle();
                String content = staticHtml.getContent();
                String publishTime = staticHtml.getCreateDate();

                String strHTML = "<!DOCTYPE html>\n" +
                        "<html lang='zh-CN'>\n" +
                        "<head>\n" +
                        "    <meta charset='utf-8'>\n" +
                        "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n" +
                        "    <meta name='viewport' content='width=Device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no' >  \n" +
                        "    <title>"+title+"</title>\n" +
                        "    <style type='text/css'>\n" +
                        "\n" +
                        "        /* index */\n" +
                        "        .main_box{\n" +
                        "            height: 100%;\n" +
                        "        }\n" +
                        "        .main_box .info-detail-title{\n" +
                        "            background-color: #fff;\n" +
                        "            margin: 0 5px 5px;\n" +
                        "            padding-bottom: 10px;\n" +
                        "            border-bottom: 1px solid #ddd;\n" +
                        "        }\n" +
                        "        .info-detail-content{\n" +
                        "            font-size: 18px;\n" +
                        "            color: #333;\n" +
                        "            line-height: 28px;\n" +
                        "            background-color: #fff;\n" +
                        "            padding: 5px 16px;\n" +
                        "        }\n" +
                        "        .info-detail-title h5{\n" +
                        "            font-family: PingFangSC-Medium;\n" +
                        "            line-height: 33px;\n" +
                        "            font-size: 24px;\n" +
                        "            color: #333;\n" +
                        "            letter-spacing: 0;\n" +
                        "            margin: 0;\n" +
                        "            padding-bottom:10px;\n" +
                        "            text-align:justify;\n" +
                        "        }\n" +
                        "        .info-detail-content, \n" +
                        "        .info-detail-title span{\n" +
                        "            font-family: PingFangSC-Regular;\n" +
                        "            letter-spacing: 0;\n" +
                        "        }\n" +
                        "        .info-detail-title span {\n" +
                        "            font-size: 14px;\n" +
                        "            color: #9b9b9b;\n" +
                        "            line-height: 20px;\n" +
                        "        }\n" +
                        "     \n" +
                        "\n" +
                        "    </style>\n" +
                        "\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <!-- 中间内容 -->\n" +
                        "    <div class='main_box container row'>\n" +
                        "        <div class='info-detail-title'>\n" +
                        "            <h5>"+title+"</h5>\n" +
                        "            <span>$source</span>\n" +
                        "            <span style='float: right;'>"+publishTime+"</span>\n" +
                        "        </div>\n" +
                        "        <div class='info-detail-content'>\n" +
                        "            <div style='text-align:left;margin:5px -15px'>"+content+"</div>\n" +
                        "    </div>\n" +
                        "\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>";
                dataMap.put("strHTML", strHTML);
            }
        });

        return dataMap;

    }

    /**
     * 获取webview内容(name: ABOUT_US 关于我们;  \n AGREEMENT 隐私协议;  \n NEWBIE 新手必看;  \n WAKE_UP 唤醒规则;  \n SHARE_RECRUIT 分享收徒 \n SHARE_WAKE_UP 唤醒徒弟(需要传入apprenticeId))
     * @param userId
     * @param name
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.STATIC_HTML, method = RequestMethod.GET)
    public Map<Object,Object> staticHtml(final Long userId, final String name){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(name, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                StaticHtml staticHtml = newsService.getStaticHtml(name);
                String title = staticHtml.getTitle();
                String content = staticHtml.getContent();
                String publishTime = staticHtml.getCreateDate();
                String strHTML = "<!DOCTYPE html>\n" +
                        "<html lang='zh-CN'>\n" +
                        "<head>\n" +
                        "    <meta charset='utf-8'>\n" +
                        "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n" +
                        "    <meta name='viewport' content='width=Device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no' >  \n" +
                        "    <title>"+title+"</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <!-- 中间内容 --> "+content+"</body>\n" +
                        "\n" +
                        "</html>";
                dataMap.put("strHTML", strHTML);
            }
        });

        return dataMap;

    }

    /**
     * 文章评论人数和是否收藏
     * @param userId
     * @param infoId
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.COLLECT_COMMENTS_GET, method = RequestMethod.GET)
    public Map<Object,Object> conllectCommentsGet(final Long userId, final String infoId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(infoId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {

                UserComments userComments = commentsService.getUserCommentsByInfoId(infoId);
                boolean isCollect = collectService.isCollect(userId, infoId);
                Map map = Maps.newHashMap();
                map.put("commentsNum", userComments);
                map.put("isCollect", isCollect);
                dataMap.put("result", "success");
                dataMap.put("msg", "文章评论人数和是否收藏获取成功");
                dataMap.put("data", map);

            }
        });

        return dataMap;

    }

    /**
     * 获取24小时热文
     * @param userId
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.HOT24HOURS_GET, method = RequestMethod.GET)
    public Map<Object,Object> hot24HoursGet(final Long userId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                List<InfosVO> list = newsService.List24HoursInfos();
                List<InfosVO> isToday = Lists.newArrayList();
                List<InfosVO> yesterday = Lists.newArrayList();
                for(InfosVO infos : list){
                    //判断当前用户是否已阅读
                    UserJpush userJpush = jpushService.getUserJpush(userId, infos.getInfoId());
                    int read = userJpush.getIsRead();
                    infos.setIsRead(read);
                    int day = jpushService.isToday(infos.getPushDate());
                    if(day == 1){
                        //是今天
                        isToday.add(infos);
                    }else{
                        yesterday.add(infos);
                    }
                }
                dataMap.put("result", "success");
                dataMap.put("msg", "相关信息返回成功");
                dataMap.put("today", isToday);
                dataMap.put("yesterday", yesterday);
            }
        });

        return dataMap;

    }

    /**
     * 用户讨厌文章设置
     * @param userId
     * @param type
     * @param infoId
     * @param source
     * @param title
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.HATE_NEWS_SET, method = RequestMethod.GET)
    public Map<Object,Object> hateNewsSet(final Long userId, final String type, final String infoId,
                                          final String source, final String title){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(type, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(infoId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(source, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(title, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {

                String[] types = type.replace(" ","").split(",");
                for(int i=0;i<types.length;i++){
                    int hade = filterNewsService.countUserFilterNewsByParam(userId, infoId, types[i]);
                    if(hade == 0){
                        //之前还没有添加过该条数据
                        if(types[i].equals("AUTHOR")){
                            //讨厌该作者
                            filterNewsService.addUserFilterNews(userId, infoId, types[i], source);
                        }else if(types[i].equals("NEWSTYPE")){
                            //讨厌新闻类型
                            filterNewsService.addUserFilterNews(userId, infoId, types[i], title);
                        }else if(types[i].equals("HISTORY")){
                            //已经看过
                            filterNewsService.addUserFilterNews(userId, infoId, types[i], null);
                        }else{
                            filterNewsService.addUserFilterNews(userId, infoId, "HISTORY", null);
                        }
                    }
                }
                dataMap.put("result", "success");
                dataMap.put("msg", "已屏蔽该类型新闻");
            }
        });

        return dataMap;
    }






}
