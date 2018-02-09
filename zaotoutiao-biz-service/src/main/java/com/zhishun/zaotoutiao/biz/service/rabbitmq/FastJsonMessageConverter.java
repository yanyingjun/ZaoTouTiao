/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.rabbitmq;
import java.io.UnsupportedEncodingException;

import com.zhishun.zaotoutiao.common.util.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import com.alibaba.fastjson.JSONObject;

/**
 * 消息模型 - 业务层 -替换为fastjson的一个实现
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserServiceImpl, v0.1 2018年02月08日 10:45闫迎军(YanYingJun) Exp $
 */
public class FastJsonMessageConverter extends AbstractMessageConverter {

    /**
     * 日志
     */
    private static Logger LOGGER = LoggerFactory.getLogger(FastJsonMessageConverter.class);

    /**
     * 编码CODE:UTF-8
     */
    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 默认编码方式
     */
    private volatile String defaultCharset = DEFAULT_CHARSET;

    public FastJsonMessageConverter() {
        super();
    }

    public void setDefaultCharset(String defaultCharset) {
        this.defaultCharset = (defaultCharset != null) ? defaultCharset
                : DEFAULT_CHARSET;
    }

    @Override
    public Object fromMessage(Message message)
            throws MessageConversionException {
        Object o = new CommonMessage();
        try{
            o = fromMessage(message, new CommonMessage());
        }catch(Exception e){
            LoggerUtils.error(LOGGER, "queue message error : " + message, e);
        }
        return o;
    }

    @SuppressWarnings("unchecked")
    public <T> T fromMessage(Message message, T t) {
        String json = "";
        try {
            json = new String(message.getBody(), defaultCharset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return (T) JSONObject.parseObject(json, t.getClass());
    }

    @Override
    protected Message createMessage(Object objectToConvert,
                                    MessageProperties messageProperties)
            throws MessageConversionException {
        byte[] bytes = null;
        try {
            String jsonString = JSONObject.toJSONString(objectToConvert);
            bytes = jsonString.getBytes(this.defaultCharset);
        } catch (UnsupportedEncodingException e) {
            throw new MessageConversionException(
                    "Failed to convert Message content", e);
        }
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        messageProperties.setContentEncoding(this.defaultCharset);
        if (bytes != null) {
            messageProperties.setContentLength(bytes.length);
        }
        return new Message(bytes, messageProperties);
    }
}
