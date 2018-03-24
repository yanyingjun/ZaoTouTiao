/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.entity.InfosImage;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IInfosImageService, v0.1 2018年03月22日 13:05闫迎军(YanYingJun) Exp $
 */
public interface IInfosImageService {

    /**
     * 新增图片
     * @param infosImage
     * @return
     */
    int addImage(InfosImage infosImage);
}
