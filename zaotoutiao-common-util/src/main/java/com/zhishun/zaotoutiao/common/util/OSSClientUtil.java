/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.common.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.zhishun.zaotoutiao.core.model.enums.InfosEnum;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Random;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: OSSClientUtil, v0.1 2018年03月22日 11:29闫迎军(YanYingJun) Exp $
 */
public class OSSClientUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(OSSClientUtil.class);

    /**
     * 阿里云OSS地址
     */
    protected static String ENDPOINT = "http://oss-cn-hangzhou.aliyuncs.com";

    /**
     * 阿里云ACCESSKEYID
     */
    protected static String ACCESSKEYID = "LTAI1EEK5RB8gwZ4";

    /**
     * 阿里云ACCESSKEYSECRET
     */
    protected static String ACCESSKEYSECRET = "4tkVp0vDuPuGynps4mPr0spRuYcDTO";

    /**
     * 阿里云存储包名
     */
    protected static String BUCKETNAME = "zs-static";

    /**
     * 视频图片路径
     */
    private static String VIDEO_IMAGE_DIR = "video/video_image/";

    /**
     * 视频路径
     */
    private static String VODEO_DIR = "video/videos/";

    private static OSSClient ossClient = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);

    /**
     * 上传图片
     * @param file
     * @return
     * @throws Exception
     */
    public static String uploadImgOss(MultipartFile file, String path, String infoType) throws Exception {
        if(infoType.equals(InfosEnum.NEWS.getValue())){
            if (file.getSize() > 1024 * 1024) {
                throw new Exception("上传图片大小不能超过1M！");
            }
        }
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        try {
            InputStream inputStream = file.getInputStream();
            uploadFile2OSS(inputStream, name, path);
            return name;
        } catch (Exception e) {
            throw new Exception("上传失败");
        }
    }

    /**
     * 上传到OSS服务器  如果同名文件会覆盖服务器上的
     *
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 出错返回"" ,唯一MD5数字签名
     */
    public static String uploadFile2OSS(InputStream instream, String fileName, String path) {
        String ret = "";
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            PutObjectResult putResult = ossClient.putObject(BUCKETNAME, path + fileName, instream, objectMetadata);
            ret = putResult.getETag();
        } catch (IOException e) {
            LoggerUtils.error(LOGGER, "上传OSS服务器异常，异常信息：" + e.getMessage());
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 获得图片路径
     *
     * @param fileUrl
     * @return
     */
    public static String getImgUrl(String fileUrl, String path) {
        if (!StringUtils.isEmpty(fileUrl)) {
            String[] split = fileUrl.split("/");
            return getUrl(path + split[split.length - 1]);
        }
        return null;
    }



    /**
     * 判断OSS服务文件上传时文件的类型
     * @param fileNameExtension
     * @return
     */
    public static String getContentType(String fileNameExtension){
        if(fileNameExtension.equalsIgnoreCase(".bmp")){
            return "image/bmp";
        }
        if (fileNameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (fileNameExtension.equalsIgnoreCase(".jpeg") ||
                fileNameExtension.equalsIgnoreCase(".jpg") ||
                fileNameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (fileNameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (fileNameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (fileNameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (fileNameExtension.equalsIgnoreCase(".pptx") ||
                fileNameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (fileNameExtension.equalsIgnoreCase(".docx") ||
                fileNameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (fileNameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public static String getUrl(String key) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(BUCKETNAME, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }


}
