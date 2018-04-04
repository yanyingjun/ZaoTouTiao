/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.core.model.vo;

/**
 * @author BugMan
 * @version $Id: labelVO, v0.1 2018年04月03日 15:10BugMan Exp $
 */

public class LabelVO {
    /**
     * 关键字
     */
    private String label;

    /**
     * 阅读量
     */
    private Long readNum;

    /**
     * 类型
     */
    private String infoType;


    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getReadNum() {
        return readNum;
    }

    public void setReadNum(Long readNum) {
        this.readNum = readNum;
    }
}
