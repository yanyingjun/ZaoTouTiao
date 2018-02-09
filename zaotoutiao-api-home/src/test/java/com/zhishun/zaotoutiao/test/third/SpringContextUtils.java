package com.zhishun.zaotoutiao.test.third;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("springContextUtils")
public class SpringContextUtils implements ApplicationContextAware {

    public static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        SpringContextUtils.context = context;
    }

    public static <T> T getBean(String beanId, Class<T> clazz) {
        return context.getBean(beanId, clazz);
    }

    public static Object getBean(String name) throws BeansException {
        return context.getBean(name);
    }

    public static ApplicationContext getContext() {
        return context;
    }

}
