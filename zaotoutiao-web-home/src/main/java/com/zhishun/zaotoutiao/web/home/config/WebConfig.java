/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: WebConfig, v0.1 2018年02月24日 14:25闫迎军(YanYingJun) Exp $
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }


}
