/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.zhishun.zaotoutiao.biz.service.IInfosService;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.common.util.RandomUtil;
import com.zhishun.zaotoutiao.core.model.entity.Infos;
import com.zhishun.zaotoutiao.core.model.entity.InfosImage;
import com.zhishun.zaotoutiao.core.model.vo.InfosVO;
import com.zhishun.zaotoutiao.dal.mapper.InfosImageMapper;
import com.zhishun.zaotoutiao.dal.mapper.InfosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: InfosServiceImpl, v0.1 2018年03月23日 22:03闫迎军(YanYingJun) Exp $
 */
@Service
public class InfosServiceImpl implements IInfosService{

    @Autowired
    private InfosMapper infosMapper;

    @Autowired
    private InfosImageMapper infosImageMapper;

    @Override
    public int addInfos(InfosVO infosVO) {
        //新增图片
        String thumbnails = "";
        for(InfosImage infosImage : infosVO.getPicList()){
            infosImage.setInfoId("news");
            infosImage.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
            infosImageMapper.insertSelective(infosImage);
            thumbnails += infosImage.getPicUrl();
        }
        Infos infos = new Infos();
        infos.setInfoId(String.valueOf(RandomUtil.getSecurityCode(12, RandomUtil.SecurityCodeLevel.Hard, false)));
        infos.setInfoType("news");
        infos.setChannelId(infosVO.getChannelId());
        infos.setCatInfoName(infosVO.getCatInfoName());
        infos.setPublishTime(new Date());
        infos.setSource("早头条");
        infos.setThumbnails(thumbnails);
        infos.setTitle(infosVO.getTitle());
        infos.setUpdateTime(new Date());
        infos.setContent(infosVO.getContent());
        infos.setCreateDate(new Date());
        infos.setIsHot((byte)0);
        infos.setFirstLevel(infosVO.getFirstLevel());
        infos.setTwoLevel(infosVO.getTwoLevel());

        return infosMapper.insertSelective(infos);
    }
}
