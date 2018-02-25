/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.common.base.pagination;

import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: PageBuilder, v0.1 2018年02月25日 16:53闫迎军(YanYingJun) Exp $
 */
public class PageBuilder {

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
