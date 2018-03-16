/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.thirdVo;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: Browser, v0.1 2018年03月13日 19:48闫迎军(YanYingJun) Exp $
 */
public class Browser {

    /**
     * 浏览器的UA
     */
    private String user_agent;

    /**
     * Getter method for property <tt>user_agent</tt>.
     *
     * @return property value of user_agent
     */
    public String getUser_agent() {
        return user_agent;
    }

    /**
     * Setter method for property <tt>user_agent</tt>.
     *
     * @param user_agent value to be assigned to property user_agent
     */
    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }
}
