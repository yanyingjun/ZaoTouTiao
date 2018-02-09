package com.zhishun.zaotoutiao.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * HTTP请求工具类
 *
 * @author 朱思雷
 * @version $Id: HttpUtil, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2017年09月07日 16:38
 */
public class HttpUtil {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 字符串商量:application/json
     */
    private static final String APPLICATION_JSON = "application/json";

    /**
     * 字符串商量:https
     */
    private static final String HTTPS = "https";

    /**
     * 获取数据
     */
    public static String getMsg(String url, Map<String, Object> params) {
        return "";
    }

    public static String postByJson(String url, String json) {
        if (StringUtils.startsWithIgnoreCase(url, HTTPS)) {
            return doPostSSL(url, json);
        } else {
            return doPost(url, json);
        }
    }

    //////////////////////////////////添加-------------------////////////////////////////////////////

    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 450000;

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        requestConfig = configBuilder.build();
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式
     *
     * @param apiUrl
     * @param json   json对象
     * @return
     */
    public static String doPost(String apiUrl, Object json) {
        long startTime = System.currentTimeMillis();
        LoggerUtils.info(LOGGER, "http post请求地址为: "+ apiUrl +"，请求参数为:" + json);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            //解决中文乱码问题
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
            stringEntity.setContentType(APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            LoggerUtils.error(LOGGER, "http post请求异常", e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    LoggerUtils.error(LOGGER, "http post请求异常", e);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        LoggerUtils.info(LOGGER, "耗时：" + (endTime - startTime) + ",http post请求地址为: "+ apiUrl +"，请求返回结果为:" + httpStr);
        return httpStr;
    }

    /**
     * 发送 SSL POST 请求（HTTPS），JSON形式
     *
     * @param apiUrl API接口URL
     * @param json   JSON对象
     * @return
     */
    public static String doPostSSL(String apiUrl, Object json) {
        LoggerUtils.info(LOGGER, "https post请求地址为:"+ apiUrl +"，请求参数为:" + json);
        CloseableHttpClient httpClient = createSSLClientDefault();
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpPost.setConfig(requestConfig);
            //解决中文乱码问题
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
            stringEntity.setContentType(APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            LoggerUtils.error(LOGGER, "https post请求异常", e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    LoggerUtils.error(LOGGER, "https post请求异常", e);
                }
            }
        }
        LoggerUtils.info(LOGGER, "https post请求地址为:"+ apiUrl +"，请求返回结果为:" + httpStr);
        return httpStr;
    }

    public static JSONObject postMethodUrl(String url, String body, Map<String, Object> paramMap, Map<String, String> headerParamMap, CloseableHttpClient closeableHttpClient) {
        JSONObject jsonObject = null;
        try {
            CloseableHttpResponse execute;
            CloseableHttpClient client;

            System.getProperties().put("proxySet", "true");
            System.getProperties().put("proxyHost", "10.168.96.111");
            System.getProperties().put("proxyPort", "1888");
            HttpPost post = new HttpPost(url);

            if (headerParamMap != null && headerParamMap.size() > 0) {
                for (String key : headerParamMap.keySet()) {
                    post.addHeader(key, headerParamMap.get(key));
                }
            }
            if (paramMap != null && paramMap.size() > 0) {
                List<NameValuePair> nvps = new ArrayList<>();
                for (String key : paramMap.keySet()) {
                    Object value = paramMap.get(key);
                    if (value != null) {
                        nvps.add(new BasicNameValuePair(key, String.valueOf(value)));
                    }
                }
                post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            }
            if (body != null && !"".equals(body)) {
                post.setEntity(new StringEntity(body, "UTF-8"));
            }
            if (closeableHttpClient == null) {
                client = HttpClients.custom()
                        .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0")
                        .setDefaultRequestConfig(
                                RequestConfig.custom()
                                        .setSocketTimeout(60000)
                                        .setConnectTimeout(60000)
                                        .setConnectionRequestTimeout(60000)
                                        .build()).build();
                if (url.startsWith(HTTPS)) {
                    client = createSSLClientDefault();
                }
            } else {
                client = closeableHttpClient;
            }

            execute = client.execute(post);
            String content = EntityUtils.toString(execute.getEntity());
            jsonObject = JSONObject.parseObject(content);
            Charset charset = ContentType.getOrDefault(execute.getEntity()).getCharset();
            execute.close();
            client.close();
            System.getProperties().put("proxySet", "false");
        } catch (Exception e) {
            LoggerUtils.error(LOGGER, "http请求异常", e);
            return null;
        }
        return jsonObject;
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public static String doGet(String url, Map<String, Object> params) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0){
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        HttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            LoggerUtils.error(LOGGER, "http get请求异常", e);
        }
        return result;
    }

    /**
     * 关闭连接
     *
     * @param httpclient
     */
    public static void close(CloseableHttpClient httpclient) {
        if (httpclient != null) {
            try {
                httpclient.close();
            } catch (IOException e) {
                LoggerUtils.error(LOGGER, "关闭httpclient请求异常", e);
            }
        }
    }

    public static void close(CloseableHttpResponse httpResp) {
        if (httpResp != null) {
            try {
                httpResp.close();
            } catch (IOException e) {
                LoggerUtils.error(LOGGER, "关闭httpResp请求异常", e);
            }
        }
    }

    private static String invoke(CloseableHttpClient httpclient,
                                 HttpUriRequest httpost) {

        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);

        return body;
    }

    private static HttpResponse sendRequest(CloseableHttpClient httpclient,
                                            HttpUriRequest httpost) {
        HttpResponse response = null;

        try {
            response = httpclient.execute(httpost);
        } catch (ClientProtocolException e) {
            LoggerUtils.error(LOGGER, "发送请求异常", e);
        } catch (IOException e) {
            LoggerUtils.error(LOGGER, "发送请求异常", e);
        }
        return response;
    }

    private static String paseResponse(HttpResponse response) {
        if (response == null) {
            return null;
        }
        HttpEntity entity = response.getEntity();
        String body = null;
        try {
            body = EntityUtils.toString(entity);
        } catch (ParseException e) {
            LoggerUtils.error(LOGGER, "转换http请求返回异常", e);
        } catch (IOException e) {
            LoggerUtils.error(LOGGER, "转换http请求返回异常", e);
        }

        return body;
    }

    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain,
                                         String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients
                    .custom()
                    .setSSLSocketFactory(sslsf)
                    .setDefaultRequestConfig(RequestConfig.custom()
                            .setSocketTimeout(10000)
                            .setConnectTimeout(10000)
                            .setConnectionRequestTimeout(10000)
                            .build())
                    .build();
        } catch (KeyManagementException e) {
            LoggerUtils.error(LOGGER, "执行createSSLClientDefault异常", e);
        } catch (NoSuchAlgorithmException e) {
            LoggerUtils.error(LOGGER, "执行createSSLClientDefault异常", e);
        } catch (KeyStoreException e) {
            LoggerUtils.error(LOGGER, "执行createSSLClientDefault异常", e);
        }
        return null;
    }

    public static String sendFormPost(String url, Map<String,String> map){
        String charset="utf-8";
        CloseableHttpClient httpClient;
        HttpPost httpPost;
        String result = null;
        try{
            LoggerUtils.info(LOGGER, "****统一返回结果为****:"+ JSON.toJSONString(map));
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity resEntity = response.getEntity();
                    if(resEntity != null){
                        result = EntityUtils.toString(resEntity,charset);
                    }
                } else {
                    LoggerUtils.error(LOGGER, "响应失败:"+response.getStatusLine().getStatusCode());
                }
            }
            httpClient.close();
        }catch(Exception ex){
            LoggerUtils.error(LOGGER, "发送请求异常:",ex);
        }
        return result;
    }
}
