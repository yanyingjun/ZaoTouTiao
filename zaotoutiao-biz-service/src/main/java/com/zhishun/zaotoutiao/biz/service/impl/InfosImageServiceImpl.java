/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.zhishun.zaotoutiao.biz.service.IInfosImageService;
import com.zhishun.zaotoutiao.core.model.entity.InfosImage;
import com.zhishun.zaotoutiao.dal.mapper.InfosImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: InfosImageServiceImpl, v0.1 2018年03月22日 13:07闫迎军(YanYingJun) Exp $
 */
@Service
public class InfosImageServiceImpl implements IInfosImageService{

    @Autowired
    private InfosImageMapper infosImageMapper;

    @Override
    public int addImage(InfosImage infosImage) {
        return infosImageMapper.insertSelective(infosImage);
    }
}
