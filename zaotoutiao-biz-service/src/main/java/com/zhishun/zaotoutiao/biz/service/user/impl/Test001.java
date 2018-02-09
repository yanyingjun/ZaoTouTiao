/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.user.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: Test001, v0.1 2018年02月08日 19:03闫迎军(YanYingJun) Exp $
 */
public class Test001 {

    public static void main(String[] args) throws Exception {
        //读取配置文件
        new ClassPathXmlApplicationContext(new String[]{"spring/spring-dubbo-service.xml"});
        System.out.println("provider服务已注册");
        //使线程阻塞
        System.in.read();
    }
}
