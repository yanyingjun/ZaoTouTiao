package com.zhishun.zaotoutiao.test.common;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 运行JUnit的配置
 * 
 * @author WangYaL
 * @dateTime 2017年4月19日 下午3:06:16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:spring/spring-config.xml")
public abstract class TestConfig {

}
