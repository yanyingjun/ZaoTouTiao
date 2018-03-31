/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.push;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.UserMsgReq;
import com.zhishun.zaotoutiao.biz.service.IInfosImageService;
import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.common.util.*;
import com.zhishun.zaotoutiao.core.model.entity.InfosImage;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.enums.InfosEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UploadController, v0.1 2018年03月23日 17:33闫迎军(YanYingJun) Exp $
 */
@RestController
public class UploadController extends BaseController{

    private static Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    /**
     * 头像地址
     */
    private static String HEAD_SOURCE = "head/source";


    private static String HEAD_SMALL = "head/small";

    @Autowired
    private IUserService userService;

    /**
     * 上传头像
     * @param pic
     * @return
     */
    @RequestMapping(value = UserMsgReq.UPLOAD_HEAD, method = RequestMethod.POST)
    public Map<Object,Object> uploadListPic(final List<MultipartFile> pic, final Long userId){
        LoggerUtils.info(LOGGER,"上传头像开始");
        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(pic, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                try {
                    //上传大图
                    String name = OSSClientUtil.uploadImgOss(pic.get(0), HEAD_SOURCE, InfosEnum.NEWS.getValue());
                    //String imgUrl = OSSClientUtil.getImgUrl(name, HEAD_SOURCE);

                    //保存小图
                    String rootPath = getClass().getResource("/").getFile().toString();
                    File file1 = ImageUtil.uploadHead(pic.get(0).getInputStream(), rootPath, name);
                    InputStream input = new FileInputStream(file1);
                    String substring = name.substring(name.lastIndexOf(".")).toLowerCase();
                    Random random = new Random();
                    String name1 = random.nextInt(10000) + System.currentTimeMillis() + substring;
                    OSSClientUtil.uploadFile2OSS(input, name1, HEAD_SMALL);
                    String imgUrl = OSSClientUtil.getImgUrl(name1, HEAD_SMALL);
                    //更新用户头像
                    User user = userService.getUserByUserId(userId);
                    user.setHeadPath(imgUrl);
                    userService.updateUser(user);
                    dataMap.put("imgUrl", imgUrl);
                    dataMap.put("result", "success");
                    dataMap.put("msg", "上传图片成功");
                }catch(IOException e){
                    dataMap.put("result", "fail");
                    dataMap.put("msg", "上传图片失败");
                    LoggerUtils.error(LOGGER, "上传图片异常，异常信息：" + e.getMessage());
                }
            }
        });

        return dataMap;

    }
}
