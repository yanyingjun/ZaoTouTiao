/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.template;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ArticleTemplate, v0.1 2018年02月27日 17:48闫迎军(YanYingJun) Exp $
 */
public class ArticleTemplate {

    public static String ARTICLE_TEMPLATE = "<!DOCTYPE html>\n" +
            "<html lang='zh-CN'>\n" +
            "<head>\n" +
            "    <meta charset='utf-8'>\n" +
            "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n" +
            "    <meta name='viewport' content='width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no' >  \n" +
            "    <title>$title</title>\n" +
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
            "            <span>$source</span>\n" +
            "            <span style='float: right;'>$publishTime</span>\n" +
            "        </div>\n" +
            "        \n" +
            "        <div class='info-detail-content'>\n" +
            "            <div style='text-align:left;margin:5px -15px'>\n" +
            "    $content\n" +
            "            </div>\n" +
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

}
