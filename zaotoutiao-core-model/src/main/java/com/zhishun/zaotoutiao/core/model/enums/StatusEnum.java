package com.zhishun.zaotoutiao.core.model.enums;

/**
 * 状态枚举
 *
 */
public enum StatusEnum {

    /**
     * 成功
     */
    SUCCESS("1", "成功"),

    /**
     * 失败
     */
    FAILURE("2", "失败"),

    /**
     * 处理中
     */
    DOING("3", "处理中"),

    /**
     * 未知
     */
    UNKNOWN("4", "未知");

    /**
     * 状态值
     */
    private String status;

    /**
     * 状态名称
     */
    private String name;

    StatusEnum(String status, String name) {
        this.status = status;
        this.name = name;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for property <tt>statue</tt>.
     *
     * @return property value of statue
     */
    public String getStatus() {
        return status;
    }
}
