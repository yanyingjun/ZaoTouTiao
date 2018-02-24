/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.test.third;

import com.alibaba.fastjson.JSON;
import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.test.common.TestConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试类
 * @author 闫迎军(YanYingJun)
 * @version $Id: ZttTest, v0.1 2018年02月10日 15:57闫迎军(YanYingJun) Exp $
 */

public class ZttTest extends TestConfig{

    @Autowired
    private IUserService userService;

    @Test
    public void testUser(){
        User user1 = userService.selectByPrimaryKey((long)19);
        System.out.println(JSON.toJSON(user1));
    }
}
