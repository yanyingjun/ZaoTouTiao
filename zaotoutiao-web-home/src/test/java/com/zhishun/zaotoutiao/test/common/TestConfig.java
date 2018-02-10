package com.zhishun.zaotoutiao.test.common;


import com.zhishun.zaotoutiao.web.home.application.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 运行JUnit的配置
 * 
 * @author WangYaL
 * @dateTime 2017年4月19日 下午3:06:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public abstract class TestConfig {

}
