/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.vo;

import com.zhishun.zaotoutiao.core.model.entity.StaticIndustrys;
import com.zhishun.zaotoutiao.core.model.entity.StaticJobs;

import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: StaticIndustrysVO, v0.1 2018年02月28日 16:34闫迎军(YanYingJun) Exp $
 */
public class StaticIndustrysVO extends StaticIndustrys{


    private List<StaticJobs> listJobs;

    /**
     * Getter method for property <tt>listJobs</tt>.
     *
     * @return property value of listJobs
     */
    public List<StaticJobs> getListJobs() {
        return listJobs;
    }

    /**
     * Setter method for property <tt>listJobs</tt>.
     *
     * @param listJobs value to be assigned to property listJobs
     */
    public void setListJobs(List<StaticJobs> listJobs) {
        this.listJobs = listJobs;
    }
}
