/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Random;

/**
 * 360接口工具类
 * @author 闫迎军(YanYingJun)
 * @version $Id: Md5Util, v0.1 2018年02月25日 11:00闫迎军(YanYingJun) Exp $
 */
public class ApiUtil {

    /**
     * 日志
     */
    private static Logger LOGGER = LoggerFactory.getLogger(ApiUtil.class);

    /**
     * 生成随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) { // length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取本次请求的校验码
     * @param ts
     * @param rn
     * @param ap
     * @param sign
     * @param pn
     * @param secret
     * @return
     */
    public static String getChs(String ts, String rn, String ap, String sign, String pn, String secret){
        String str=ts + rn + ap + sign + pn + secret;
        String chc = null;
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            chc = getFormattedText(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return chc;
    }

    public static String getFormattedText(byte[] bytes){
        char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }


    /**
     * 发送get请求
     * @param url
     * @param params
     * @return
     */
    public static JSONObject httpGet(String url, Map<String,Object> params){
        JSONObject json = null;
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0){
                param.append("?");
            }else{
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 设置参数
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            // 发送请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            // 获取返回的数据
            HttpEntity entity = httpResponse.getEntity();
            if(entity != null){
                String result = EntityUtils.toString(entity);
                json = JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            LoggerUtils.error(LOGGER, "360信息流请求异常");
        }
        return json;
    }
}
