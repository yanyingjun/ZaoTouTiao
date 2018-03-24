/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
     * 发送post请求
     * @param url
     * @param params
     * @return
     */
    public static JSONObject httpPost(String url, Map<String,Object> params){
        String reqJson = JSONObject.toJSONString(params);
        String resStr = null;
        JSONObject respJson = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(reqJson, ContentType.create("application/json", "UTF-8")));
            System.out.println(reqJson);
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            resStr = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            respJson = JSON.parseObject(resStr);
            if(respJson.getString("success").equals("false")){
                LoggerUtils.info(LOGGER, "广告位请求失败，失败原因："+ respJson);
                return null;
            }else{
                return respJson;
            }
        }catch (Exception e){
            LoggerUtils.error(LOGGER, "广告位请求异常");
            return null;
        }
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

    /**
     * 发送get请求不返回数据
     * @param HTTP_URL
     * @param params
     * @return
     */
    public static Boolean doGet(String HTTP_URL, Map<String,Object> params) {
        StringBuffer httpUrl = new StringBuffer(HTTP_URL);
        HttpURLConnection connection = null;
        try {
            Object object = mapToObject(params, Object.class);
            System.out.println(httpUrl.toString());
            URL url = new URL(httpUrl.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 请求方式设置 POST
            connection.setRequestMethod("GET");
            // 设置维持长连接
            connection.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            connection.setRequestProperty("Charset", "UTF-8");

            //根据需求设置读超时的时间
            connection.setReadTimeout(50);
            // 开始连接请求
            connection.connect();
            OutputStream out = connection.getOutputStream();
            out.write((object.toString()).getBytes());
            out.flush();
            out.close();
            if (connection.getResponseCode() == 200) {
                LoggerUtils.info(LOGGER, "连接成功,传送数据...");
                return true;
            } else {
                LoggerUtils.info(LOGGER, "连接失败,错误代码:" + connection.getResponseCode());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 随机数
     * @param min
     * @param max
     * @return
     */
    public static int randomInt(int min, int max) {
        Random random = new Random();
        int n = random.nextInt(max) % (max - min + 1) + min;
        return n;
    }

    // 生成参数签名（重要）
    public static String makeSign(Map<String, String> reqParams, String sk) {
        // 按照参数name排序
        String[] keys = reqParams.keySet().toArray(new String[] {});
        Arrays.sort(keys);
        // 连接成a=1&b=2&c=3的格式
        StringBuffer sb = new StringBuffer();
        for (int i = 0, len = keys.length; i < len; i++) {
            String k = keys[i];
            sb.append(k)
                    .append("=")
                    .append(reqParams.get(k));
            if (i < len - 1) {
                sb.append("&");
            }
        }
        String sign = MD5(sb.toString() + sk);
        return sign;
    }

    public static String httpRequest(String reqUrl) {
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(false);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return buffer.toString();
    }

    //生成queryString
    public static String buildQueryString(Map<String, String> reqParams) {
        Set<Map.Entry<String,String>> entrys = reqParams.entrySet();
        // 连接成a=1&b=2&c=3的格式
        int i = 0;
        int size = entrys.size();
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : entrys) {
            try {
                sb.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) { }
            if (i < size - 1) {
                sb.append("&");
            }
            i++;
        }
        return sb.toString();
    }

    public static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes).toLowerCase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {
        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null){
            return null;
        }
        Object obj = beanClass.newInstance();
        BeanUtils.populate(obj, map);
        return obj;
    }


}
