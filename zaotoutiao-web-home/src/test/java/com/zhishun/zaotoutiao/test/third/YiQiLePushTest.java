package com.zhishun.zaotoutiao.test.third;

import com.alibaba.fastjson.JSON;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.service.user.UserBizService;
import com.zhishun.zaotoutiao.test.common.TestConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 亿奇乐定时任务推送业务测试类
 *
 * @author 朱思雷
 * @version $Id: YiQiLePushTest, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2017年09月23日 13:41
 */
public class YiQiLePushTest extends TestConfig {

    /*@Autowired
    private UserBizService userBizService;*/

    /*@Test
    public void testUser(){
        User user = new User();
        user.setUserId((long)19);
        User user1 = userBizService.getById(user);
        System.out.println(JSON.toJSON(user1));
    }*/

    public static void main(String[] args) throws Exception {
        //读取配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/spring-dubbo-service.xml"});
        //获取在zookeeper注册的服务接口
        UserBizService helloService = (UserBizService)context.getBean("userBizService");
        //调用接口
        User user = new User();
        user.setUserId((long)19);
        User user1 = helloService.getById(user);
        System.out.println("HelloService = " + JSON.toJSONString(user1));
        //不让控制台消失，按任意键结束
        System.in.read();
    }

}
