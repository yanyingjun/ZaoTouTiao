/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.common.base.pagination;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: Page, v0.1 2018年02月07日 11:08闫迎军(YanYingJun) Exp $
 */
public class Page<T> extends PageRequest implements Serializable {


    private static final long serialVersionUID = 2260079638052199252L;


    protected List<T> rows = null;
    protected long total = -1;

    public Page() {
    }

    public Page(PageRequest request) {
        this.pageNo = request.getPageNo();
        this.pageSize = request.getPageSize();
        this.countTotal = request.isCountTotal();
        this.orderBy = request.getOrderBy();
        this.orderDir = request.getOrderDir();
    }

    /**
     * 获得页内的记录列表.
     */
    public List<T> getRows() {
        return rows;
    }

    /**
     * 设置页内的记录列表.
     */
    public void setRows(final List<T> rows) {
        this.rows = rows;
    }

    /**
     * 获得总记录数, 默认值为-1.
     */
    public long getTotal() {
        return total;
    }

    /**
     * 设置总记录数.
     */
    public void setTotal(final long total) {
        this.total = total;
    }

    /**
     * 根据pageSize与totalItems计算总页数.
     */
    public int getTotalPages() {
        return (int) Math.ceil((double) total / (double) getPageSize());

    }

    /**
     * 是否还有下一页.
     */
    public boolean hasNextPage() {
        return (getPageNo() + 1 <= getTotalPages());
    }

    /**
     * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
     */
    public int getNextPage() {
        if (hasNextPage()) {
            return getPageNo() + 1;
        } else {
            return getPageNo();
        }
    }

    /**
     * 是否还有上一页.
     */
    public boolean hasPrePage() {
        return (getPageNo() > 1);
    }

    /**
     * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
     */
    public int getPrePage() {
        if (hasPrePage()) {
            return getPageNo() - 1;
        } else {
            return getPageNo();
        }
    }

    /**
     * 计算以当前页为中心的页面列表,如"首页,23,24,25,26,27,末页"
     *
     * @param count 需要计算的列表大小
     *
     * @return List
     **/
    public List<Integer> getSlider(int count) {
        int halfSize = count / 2;
        int totalPage = getTotalPages();
        int startPageNo = Math.max(getPageNo() - halfSize, 1);

        int endPageNo = Math.min(startPageNo + count - 1, totalPage);
        if (endPageNo - startPageNo < count) {
            startPageNo = Math.max(endPageNo - count, 1);
        }

        List<Integer> result = new ArrayList<Integer>();
        for (int i = startPageNo; i <= endPageNo; i++) {
            result.add(i);

        }
        return result;
    }
}
