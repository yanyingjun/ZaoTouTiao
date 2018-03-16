/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.thirdVo;

/**
 * app对象
 * @author 闫迎军(YanYingJun)
 * @version $Id: App, v0.1 2018年03月13日 16:03闫迎军(YanYingJun) Exp $
 */
public class App {

    /**
     * app的包名
     */
    private String package_name;

    /**
     * app的版本号
     */
    private String app_version;

    /**
     * Getter method for property <tt>package_name</tt>.
     *
     * @return property value of package_name
     */
    public String getPackage_name() {
        return package_name;
    }

    /**
     * Setter method for property <tt>package_name</tt>.
     *
     * @param package_name value to be assigned to property package_name
     */
    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    /**
     * Getter method for property <tt>app_version</tt>.
     *
     * @return property value of app_version
     */
    public String getApp_version() {
        return app_version;
    }

    /**
     * Setter method for property <tt>app_version</tt>.
     *
     * @param app_version value to be assigned to property app_version
     */
    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }
}
