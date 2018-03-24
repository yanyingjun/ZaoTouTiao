/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.core.model.vo;

import java.util.Map;

/**
 * @author BugMan
 * @version $Id: NavigationVO, v0.1 2018年03月23日 15:47BugMan Exp $
 */

public class NavigationVO {

    /**
     * Id
     */
    private Long id;
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 导航名
     */
    private String name;

    /**
     * 阅读量
     */
    private Long readNum;

    /**
     * 下一级标签（前三）
     */
    private Map<String,Object> childTabsNameAndId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getReadNum() {
        return readNum;
    }

    public void setReadNum(Long readNum) {
        this.readNum = readNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Map<String, Object> getChildTabsNameAndId() {
        return childTabsNameAndId;
    }

    public void setChildTabsNameAndId(Map<String, Object> childTabsNameAndId) {
        this.childTabsNameAndId = childTabsNameAndId;
    }
}
