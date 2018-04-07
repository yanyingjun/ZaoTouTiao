/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.core.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author BugMan
 * @version $Id: IncomeAndImgUrlVO, v0.1 2018年04月04日 19:43BugMan Exp $
 */

public class IncomeAndImgUrlVO implements Serializable {


    private static final long serialVersionUID = 506262389301318503L;
    /**
     * 赚取的零钱
     */
    private BigDecimal money;

    /**
     * 图片地址
     */
    private List<String> imgUrls;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }
}
