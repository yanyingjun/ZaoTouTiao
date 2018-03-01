/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.vo;

import com.zhishun.zaotoutiao.core.model.entity.StaticFaq;

import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: StaticFaqVO, v0.1 2018年02月28日 17:03闫迎军(YanYingJun) Exp $
 */
public class StaticFaqVO extends StaticFaq{

    private List<Ques> listQues;

    /**
     * Getter method for property <tt>listQues</tt>.
     *
     * @return property value of listQues
     */
    public List<Ques> getListQues() {
        return listQues;
    }

    /**
     * Setter method for property <tt>listQues</tt>.
     *
     * @param listQues value to be assigned to property listQues
     */
    public void setListQues(List<Ques> listQues) {
        this.listQues = listQues;
    }
}
