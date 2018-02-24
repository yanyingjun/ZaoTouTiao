/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.common.client.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: WebConfig, v0.1 2018年02月24日 14:25闫迎军(YanYingJun) Exp $
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置静态资源模版路径
        registry.addResourceHandler("/**").addResourceLocations("classpath:/");
        registry.addResourceHandler("/static").addResourceLocations("classpath:/static");
    }
}
