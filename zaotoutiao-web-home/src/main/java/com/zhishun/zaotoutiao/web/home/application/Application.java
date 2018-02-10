package com.zhishun.zaotoutiao.web.home.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by luyh on 16/4/27.
 */

@SpringBootApplication
@ComponentScan("com.zhishun.zaotoutiao")
@MapperScan("com.zhishun.zaotoutiao.dal.mapper")
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);

    }
}
