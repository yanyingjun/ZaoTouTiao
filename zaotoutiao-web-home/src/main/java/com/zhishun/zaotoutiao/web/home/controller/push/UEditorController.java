/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.push;

import com.baidu.ueditor.ActionEnter;
import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.common.util.LoggerUtils;
import com.zhishun.zaotoutiao.common.util.OSSClientUtil;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.web.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.web.home.constant.request.ZttWebMsgReq;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UEditorController, v0.1 2018年03月22日 14:12闫迎军(YanYingJun) Exp $
 */
@RestController
public class UEditorController extends BaseController{

    /**
     * 新闻图片路径
     */
    private static String NEWS_IMAGE_DIR = "news/image/";

    @RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");

        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec); writer.flush(); writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 编辑器图片上传
     * @param file
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_UPLOAD_IMAGE_REQ)
    public Map<Object,Object> uploadImage(MultipartFile upfile){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(upfile, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                try {
                    String name = OSSClientUtil.uploadImgOss(upfile, NEWS_IMAGE_DIR);
                    String imgUrl = OSSClientUtil.getImgUrl(name, NEWS_IMAGE_DIR);
                    dataMap.put("url", imgUrl);
                    dataMap.put("state", "SUCCESS");
                    dataMap.put("msg", "上传图片成功");
                }catch(IOException e){
                    dataMap.put("state", "FAIL");
                    dataMap.put("msg", "上传图片失败");
                    LoggerUtils.error(LOGGER, "上传图片异常，异常信息：" + e.getMessage());
                }
            }
        });

        return dataMap;
    }




}
