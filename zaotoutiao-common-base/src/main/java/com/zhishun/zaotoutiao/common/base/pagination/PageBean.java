/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.common.base.pagination;

import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: PageBean, v0.1 2018年02月07日 11:33闫迎军(YanYingJun) Exp $
 */
public class PageBean {

    /**
     * 构建Page对象
     * @param pageRequest
     * @param result
     * @param total
     * @return
     */
    public static <T>Page<T> buildPage(PageRequest pageRequest, List<T> result, int total){
        Page<T> page  = new Page<T>(pageRequest);
        page.setRows(result);
        page.setTotal(total);
        return page;
    }
}
