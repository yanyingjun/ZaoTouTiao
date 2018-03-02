/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.common.util;

import com.alibaba.fastjson.JSON;
import com.zhishun.zaotoutiao.core.model.common.QueryRedPack;
import com.zhishun.zaotoutiao.core.model.common.SendRedPack;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.URL;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: RedPackUtil, v0.1 2018年03月01日 14:53闫迎军(YanYingJun) Exp $
 */
public class RedPackUtil {

    public static String MCH_ID = "1496821682";
    public static String API_KEY = "ozIWq0a2gKaFQ7gRRVQ5EZoUZSJw1234";

    public RedPackUtil() {
    }

    public static String createSendRedPackOrderSign(SendRedPack redPack) {
        StringBuffer sign = new StringBuffer();
        sign.append("act_name=").append(redPack.getAct_name());
        sign.append("&client_ip=").append(redPack.getClient_ip());
        sign.append("&mch_billno=").append(redPack.getMch_billno());
        sign.append("&mch_id=").append(redPack.getMch_id());
        sign.append("&nonce_str=").append(redPack.getNonce_str());
        sign.append("&re_openid=").append(redPack.getRe_openid());
        sign.append("&remark=").append(redPack.getRemark());
        sign.append("&send_name=").append(redPack.getSend_name());
        sign.append("&total_amount=").append(redPack.getTotal_amount());
        sign.append("&total_num=").append(redPack.getTotal_num());
        sign.append("&wishing=").append(redPack.getWishing());
        sign.append("&wxappid=").append(redPack.getWxappid());
        sign.append("&key=").append(API_KEY);
        return DigestUtils.md5Hex(sign.toString()).toUpperCase();
    }

    public static String createQueryRedPackOrderSign(QueryRedPack redPack) {
        StringBuffer sign = new StringBuffer();
        sign.append("appid=").append(redPack.getAppid());
        sign.append("&bill_type=").append(redPack.getBill_type());
        sign.append("&mch_billno=").append(redPack.getMch_billno());
        sign.append("&mch_id=").append(redPack.getMch_id());
        sign.append("&nonce_str=").append(redPack.getNonce_str());
        sign.append("&key=").append(API_KEY);
        System.out.println(sign.toString());
        return DigestUtils.md5Hex(sign.toString()).toUpperCase();
    }

    private static String ssl(String url, String data) {
        StringBuffer message = new StringBuffer();

        try {
            String mchId = MCH_ID;
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            URL fileUrl = RedPackUtil.class.getClassLoader().getResource("cert/apiclient_cert.p12");
            File file = new File(fileUrl.getFile());
            String certFilePath = file.getAbsolutePath();
            FileInputStream instream = new FileInputStream(new File(certFilePath));
            keyStore.load(instream, mchId.toCharArray());
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mchId.toCharArray()).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, (String[])null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            HttpPost httpost = new HttpPost(url);
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("user-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data, "UTF-8"));
            System.out.println("executing request" + httpost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpost);

            try {
                HttpEntity entity = response.getEntity();
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));

                    String text;
                    while((text = bufferedReader.readLine()) != null) {
                        message.append(text);
                    }
                }

                EntityUtils.consume(entity);
            } catch (IOException var21) {
                var21.printStackTrace();
            } finally {
                response.close();
            }
        } catch (Exception var23) {
            var23.printStackTrace();
        }

        return message.toString();
    }

    public static void main(String[] args) {
        String result = queryRedPack("149682168220180118210644");
        System.out.println(result);
    }

    public static String queryRedPack(String mch_billno) {
        String url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gethbinfo";
        QueryRedPack sendRedPack = new QueryRedPack();
        sendRedPack.setNonce_str("5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
        sendRedPack.setMch_id(MCH_ID);
        sendRedPack.setMch_billno(mch_billno);
        sendRedPack.setAppid("wxbaf3266efe745c38");
        String sign = createQueryRedPackOrderSign(sendRedPack);
        sendRedPack.setSign(sign);
        XMLUtil xmlUtil = new XMLUtil();
        xmlUtil.xstream().alias("xml", sendRedPack.getClass());
        String xml = xmlUtil.xstream().toXML(sendRedPack);
        System.out.println(xml);
        String response = ssl(url, xml);
        String result;
        try {
            Map<String, String> map = xmlUtil.parseXml(response);
            result = JSON.toJSONString(map);
            return result;
        } catch (Exception var9) {
            HashMap map = new HashMap();
            var9.printStackTrace();
            map.put("result", "failure");
            map.put("msg", var9.getMessage());
            result = JSON.toJSONString(map);
            return result;
        }
    }

    public static String sendRedPack(String openId, int amount, String bill_no, String sendName, String actName, String wishing, String remark, String ip) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.format(date);
        SendRedPack sendRedPack = new SendRedPack();
        sendRedPack.setNonce_str("5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
        sendRedPack.setMch_id(MCH_ID);
        sendRedPack.setMch_billno(bill_no);
        sendRedPack.setWxappid("wxbaf3266efe745c38");
        sendRedPack.setSend_name(sendName);
        sendRedPack.setTotal_num(1);
        sendRedPack.setAct_name(actName);
        sendRedPack.setWishing(wishing);
        sendRedPack.setRemark(remark);
        sendRedPack.setClient_ip(ip);
        sendRedPack.setRe_openid(openId);
        sendRedPack.setTotal_amount(amount);
        String sign = createSendRedPackOrderSign(sendRedPack);
        sendRedPack.setSign(sign);
        XMLUtil xmlUtil = new XMLUtil();
        xmlUtil.xstream().alias("xml", sendRedPack.getClass());
        String xml = xmlUtil.xstream().toXML(sendRedPack);
        String url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
        String response = ssl(url, xml);
        String result;
        try {
            Map<String, String> map = xmlUtil.parseXml(response);
            result = JSON.toJSONString(map);
            return result;
        } catch (Exception var19) {
            HashMap map = new HashMap();
            var19.printStackTrace();
            map.put("result", "failure");
            map.put("msg", var19.getMessage());
            result = JSON.toJSONString(map);
            return result;
        }
    }
}
