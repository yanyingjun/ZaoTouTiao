/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.core.model.vo;

/**
 * @author BugMan
 * @version $Id: ApptypeVo, v0.1 2018年03月30日 19:42BugMan Exp $
 */

public class AppTypeVO {

    /**
     * 导航类别
     */
    private Integer appType;

    /**
     * 类别名称
     */
    private String name;

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
