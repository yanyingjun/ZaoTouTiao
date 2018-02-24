package com.zhishun.zaotoutiao.web.home.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by luyh on 16/4/27.
 */

@SpringBootApplication
@ComponentScan("com.zhishun.zaotoutiao")
@MapperScan("com.zhishun.zaotoutiao.dal.mapper")
/**
 * 开启事务管理
 */
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);

    }
}
