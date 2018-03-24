/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.user;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.VideoMsgReq;
import com.zhishun.zaotoutiao.biz.service.IAdvertisementService;
import com.zhishun.zaotoutiao.biz.service.ICommentsService;
import com.zhishun.zaotoutiao.biz.service.IVideoService;
import com.zhishun.zaotoutiao.core.model.entity.Channels;
import com.zhishun.zaotoutiao.core.model.enums.ChannelEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: VideoController, v0.1 2018年02月25日 11:52闫迎军(YanYingJun) Exp $
 */
@RestController
public class VideoController extends BaseController{

    @Autowired
    private IVideoService videoService;

    @Autowired
    private ICommentsService commentsService;

    @Autowired
    private IAdvertisementService advertisementService;

    /**
     * 获取视频分类列表
     * @return
     */
    @RequestMapping(value = VideoMsgReq.VIDEO_CHANNELS_REQ, method = RequestMethod.GET)
    public Map<Object,Object> getVideoChannles(){
        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                List<Channels> list = videoService.listVideoChannels(1, ChannelEnum.VIDEO.getValue());
                dataMap.put("result", "success");
                dataMap.put("msg", "获取视频分类列表成功");
                dataMap.put("data", list);
            }
        });
        return dataMap;
    }

    /**
     * 获取视频新闻列表
     * @return
     *//*
    @RequestMapping(value = VideoMsgReq.VIDEO_GET_REQ, method = RequestMethod.GET)
    public Map<Object,Object> getVideos(final int pageNo, final int pageSize, final int channelId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(pageNo, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(pageSize, ErrorCodeEnum.PARAMETER_ANOMALY);

            }

            @Override
            public void handle() throws Exception {
                List<InfosVO> list = videoService.getInfosByType("video",channelId ,pageNo,pageSize);
                dataMap.put("result", "success");
                dataMap.put("msg", "获取视频新闻列表成功");
                dataMap.put("data", list);
            }
        });

        return dataMap;
    }*/

    /**
     * 获取视频相关内容
     * @param channelId
     * @param infoId
     * @return
     */
    /*@RequestMapping(value = VideoMsgReq.VIDEO_RALATED, method = RequestMethod.GET)
    public Map<Object,Object> videoRalated(final int channelId, final String infoId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                int commentsNum = 0;
                List<Infos> list = videoService.getRandVideoList(channelId);
                for(int i = 0; i< list.size(); i++){
                    Infos infos = list.get(i);
                    if(infos.getHascontent() == 0){
                        infos.setWebUrl(URLDecoder.decode(infos.getH5url()));
                    }
                    String infoId = infos.getInfoid();
                    UserComments userComments = commentsService.getUserCommentsByInfoId(infoId);
                    if(!StringUtils.isEmpty(userComments)){

                        int count = commentsService.countUserComments(infoId);
                        if(count < 1){
                            commentsNum = 0;
                        }else{
                            commentsNum = count;
                        }
                    }
                    if(i == 2){
                        Advertisement advertisement = advertisementService.dealWithAds();
                        dataMap.put("data", advertisement);
                    }else{
                        dataMap.put("data", commentsNum);
                    }
                }

            }
        });

        return dataMap;

    }*/

    /**
     * 视频类新闻详情页-webview
     * @param infoId
     * @param userId
     * @param isShare
     * @return
     */
    /*@RequestMapping(value = VideoMsgReq.VIEW_VIDEO_HTML, method = RequestMethod.GET)
    public Map<Object,Object> viewVideoHtml(final String infoId, final Long userId, final String isShare){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(infoId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(isShare, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                Infos infos = videoService.getInfosByInfoId(infoId);
                if(StringUtils.isEmpty(infos)){
                    dataMap.put("msg", "内容正在处理中......");
                }else{
                    String content = infos.getVideos();
                    String title = infos.getTitle();
                    String publishTime = DateUtil.toString(infos.getPublishtime(), DateUtil.DEFAULT_DATETIME_FORMAT);
                    String source = infos.getSource();
                    String images = infos.getThumbnails();
                    String strHTML = "<!DOCTYPE html>\n" +
                            "<html lang='zh-CN'>\n" +
                            "<head>\n" +
                            "    <meta charset='utf-8'>\n" +
                            "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n" +
                            "    <meta name='viewport' content='width=Device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no' >  \n" +
                            "    <title>"+title+"</title>\n" +
                            "    <link rel='stylesheet' href='https://g.alicdn.com/de/prismplayer/1.7.4/skins/default/index.css' />\n" +
                            "    <link rel='stylesheet' type='text/css' href='http://www.ijquery.cn/css/css.css' media='all' />\n" +
                            "    <script src='https://g.alicdn.com/de/prismplayer/1.7.4/prism-h5-min.js'></script>\n" +
                            "    <script src='http://daoyi-content.oss-cn-hangzhou.aliyuncs.com/js/html5media.js'></script>\n" +
                            "\n" +
                            "    \n" +
                            "    <style type='text/css'>\n" +
                            "\n" +
                            "        /* index *//*n +
                            "        #main_box{\n" +
                            "            min-height: 100%;\n" +
                            "            width: 98%;\n" +
                            "            margin: 0 auto;\n" +
                            "            margin-top: 10px;\n" +
                            "        }\n" +
                            "        #main_box .info-detail-title{\n" +
                            "            background-color: #fff;\n" +
                            "            margin: 0 5px 5px;\n" +
                            "            padding-bottom: 10px;\n" +
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
                            "\n" +
                            "        body,html{\n" +
                            "            margin: 0;\n" +
                            "            padding: 0;\n" +
                            "        }\n" +
                            "\n" +
                            "        #recruit_box{\n" +
                            "            height: 50px;\n" +
                            "            width: 100%;\n" +
                            "            padding: 0;\n" +
                            "            margin: 0;\n" +
                            "            border-bottom: 1px solid #E6E6E6;\n" +
                            "            position: relative;\n" +
                            "            top: 0;\n" +
                            "            background-color: rgba(255,255,255,.95);\n" +
                            "            display: block;\n" +
                            "            z-index:999;\n" +
                            "        }\n" +
                            "        #recruit_box img{\n" +
                            "            width: 35px;\n" +
                            "            height: 35px;\n" +
                            "            border-radius: 3px;\n" +
                            "            margin-top: 7px;\n" +
                            "            margin-left: 7px;\n" +
                            "            display: inline-block;\n" +
                            "            float: left;\n" +
                            "        }\n" +
                            "        #recruit_box span{\n" +
                            "            display: inline-block;\n" +
                            "            float: left;\n" +
                            "            line-height: 50px;\n" +
                            "            margin-left: 10px;\n" +
                            "            font-size: 14px;\n" +
                            "        }\n" +
                            "        #recruit_box button{\n" +
                            "            display: inline-block;\n" +
                            "            float: right;\n" +
                            "            margin-right: 10px;\n" +
                            "            width: 90px;\n" +
                            "            height: 30px;\n" +
                            "            margin-top: 10px;\n" +
                            "            border-radius: 15px;\n" +
                            "            background-color: #D53D3A;\n" +
                            "            color: #fff;\n" +
                            "            border: none;\n" +
                            "            outline: none;\n" +
                            "            font-size: 15px;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .video-wrap {\n" +
                            "            position: relative;\n" +
                            "            width: 100%;\n" +
                            "            margin: 0 auto;\n" +
                            "            padding:0;\n" +
                            "            background-color: #000;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .related_Info{\n" +
                            "            border-top: 10px solid #f5f5f5;\n" +
                            "        }\n" +
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
                            "    \n" +
                            "    \n" +
                            "    <div class='video-wrap'>\n" +
                            "        <div id='J_prismPlayer' class='prism-player'></div>\n" +
                            "    </div>\n" +
                            "    \n" +
                            "    <video controls poster="+images+" style='width:100%;min-height:200px;height:auto;'>\n" +
                            "       <source src="+content+" type='video/mp4'>\n" +
                            "       Your Browser does not support the video tag.\n" +
                            "    </video> \n" +
                            "    \n" +
                            "    <div id='main_box' class='container row'>\n" +
                            "        <div class='info-detail-title'>\n" +
                            "            <h5>"+title+"</h5>\n" +
                            "            <span>"+source+"</span>\n" +
                            "            <span style='float: right;'>2017-11-28 17:57:16</span>\n" +
                            "        </div>\n" +
                            "    </div>\n" +
                            "    \n" +
                            "    <div class='related_Info'>\n" +
                            "        \n" +
                            "    </div>\n" +
                            "    \n" +
                            "<script type='text/javascript'>\n" +
                            "　　var myUrl=document.location.href;\n" +
                            "    if(myUrl.indexOf('is_share') < 0 ){\n" +
                            "        document.getElementById('recruit_box').style.display='none';\n" +
                            "    }\n" +
                            "\n" +
                            "    var userId = myUrl.split('userId=')[1];\n" +
                            "    //判断是否是分享\n" +
                            "        //展示分享页\n" +
                            "    function recruit(){\n" +
                            "        window.location.href='http://www.cloudconfs.com:8080/daoyi_web/general/static_html?name=SHARE_RECRUIT&userId='+userId; \n" +
                            "    }\n" +
                            "    \n" +
                            "    /*\n" +
                            "    \n" +
                            "    // 初始化播放器\n" +
                            "    var player = new prismplayer({\n" +
                            "        id: 'J_prismPlayer', // 容器id\n" +
                            "        source: '"+content+"',  //视频地址\n" +
                            "                //http://flv3.bn.netease.com/videolib3/1711/22/qCWJb6930/SD/qCWJb6930-mobile.mp4\n" +
                            "                \n" +
                            "                \n" +
                            "        cover: '"+images+"',  //播放器封面图\n" +
                            "        autoplay: false,      // 是否自动播放\n" +
                            "        width: '100%',       // 播放器宽度\n" +
                            "        height: '260px',      // 播放器高度\n" +
                            "        playsinline: true,\n" +
                            "        seekable: true,\n" +
                            "        skinLayout: [{\n" +
                            "            'name': 'bigPlayButton',\n" +
                            "            'align': 'cc',\n" +
                            "            //'x': 30,\n" +
                            "            //'y': 80\n" +
                            "        }, {\n" +
                            "            'align': 'blabs',\n" +
                            "            'x': 0,\n" +
                            "            'y': 0,\n" +
                            "            'name': 'controlBar',\n" +
                            "            'children': [\n" +
                            "                {\n" +
                            "                    'align': 'tl',\n" +
                            "                    'x': 15,\n" +
                            "                    'y': 26,\n" +
                            "                    'name': 'playButton'\n" +
                            "                }, {\n" +
                            "                    'align': 'tl',\n" +
                            "                    'x': 10,\n" +
                            "                    'y': 24,\n" +
                            "                    'name': 'timeDisplay'\n" +
                            "                }, {\n" +
                            "                    'align': 'tr',\n" +
                            "                    'x': 20,\n" +
                            "                    'y': 25,\n" +
                            "                    'name': 'fullScreenButton'\n" +
                            "                }, {\n" +
                            "                    'align': 'tr',\n" +
                            "                    'x': 20,\n" +
                            "                    'y': 25,\n" +
                            "                    'name': 'volume'\n" +
                            "                },\n" +
                            "                {\n" +
                            "                    'name': 'progress',\n" +
                            "                    'align': 'tlabs',\n" +
                            "                    'x': 0,\n" +
                            "                    'y': 0\n" +
                            "                }\n" +
                            "            ]\n" +
                            "        }]\n" +
                            "    });\n" +
                            "    \n" +
                            "    *//*n +
                            "\n" +
                            "\n" +
                            "</script>\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "</body>\n" +
                            "\n" +
                            "</html>";
                    /*String strHtml2 = "<link rel='stylesheet' href='https://g.alicdn.com/de/prismplayer/1.7.4/skins/default/index.css' />\n" +
                            "    <link rel='stylesheet' type='text/css' href='http://www.ijquery.cn/css/css.css' media='all' />\n" +
                            "    <script src='https://g.alicdn.com/de/prismplayer/1.7.4/prism-h5-min.js'></script>\n" +
                            "    <script src='http://daoyi-content.oss-cn-hangzhou.aliyuncs.com/js/html5media.js'></script>";
                    dataMap.put("strHTML", strHTML + strHtml2);
                }

            }
        });

        return dataMap;
    }*/


}
