/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IInfosService;
import com.zhishun.zaotoutiao.common.util.BeanMapUtil;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.common.util.RandomUtil;
import com.zhishun.zaotoutiao.core.model.entity.Infos;
import com.zhishun.zaotoutiao.core.model.entity.InfosImage;
import com.zhishun.zaotoutiao.core.model.enums.InfosEnum;
import com.zhishun.zaotoutiao.core.model.vo.ContentQueryVO;
import com.zhishun.zaotoutiao.core.model.vo.InfosVO;
import com.zhishun.zaotoutiao.dal.mapper.InfosImageMapper;
import com.zhishun.zaotoutiao.dal.mapper.InfosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
        Infos infos = new Infos();
        infos.setInfoId(String.valueOf(RandomUtil.getSecurityCode(12, RandomUtil.SecurityCodeLevel.Hard, false)));
        infos.setInfoType(infosVO.getInfoType());
        infos.setChannelId(infosVO.getChannelId());
        infos.setCatInfoName(infosVO.getCatInfoName());
        infos.setPublishTime(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        infos.setSource("早头条");

        //新增图片
        String thumbnails = "";
        for(InfosImage infosImage : infosVO.getPicList()){
            infosImage.setInfoId(infos.getInfoId());
            infosImage.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
            infosImage.setPicType("1");
            infosImageMapper.insertSelective(infosImage);
            thumbnails += infosImage.getPicUrl() + ",";
        }

        if(infosVO.getInfoType().equals(InfosEnum.NEWS.getValue())){
            infos.setThumbnails(thumbnails);
            infos.setContent(infosVO.getContent());
        }else{
            //新增视频
            InfosImage infosVideo = new InfosImage();
            infosVideo.setPicUrl(infosVO.getVideoList().get(0).getPicUrl());
            infosVideo.setPicName(infosVO.getVideoList().get(0).getPicName());
            infosVideo.setInfoId(infos.getInfoId());
            infosVideo.setPicType("2");
            infosVideo.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
            infosImageMapper.insertSelective(infosVideo);
            infos.setThumbnails(thumbnails);
            infos.setVideos(infosVO.getVideoList().get(0).getPicUrl());
        }

        infos.setTitle(infosVO.getTitle());
        infos.setUpdateTime(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        infos.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        infos.setIsHot((byte)0);
        infos.setFirstLevel(infosVO.getFirstLevel());
        infos.setTwoLevel(infosVO.getTwoLevel());

        return infosMapper.insertSelective(infos);
    }

    @Override
    public List<InfosVO> listInfos(ContentQueryVO contentQueryVO) {
        Map<String,Object> map = Maps.newHashMap();
        if(!StringUtils.isEmpty(contentQueryVO.getKeyWord())){
            map.put("keyWord", contentQueryVO.getKeyWord());
        }
        if(!StringUtils.isEmpty(contentQueryVO.getInfoType())){
            map.put("infoType", contentQueryVO.getInfoType());
        }
        if(!StringUtils.isEmpty(contentQueryVO.getChannelId())){
            map.put("channelId", contentQueryVO.getChannelId());
        }
        if(!StringUtils.isEmpty(contentQueryVO.getFirstLevel())){
            map.put("firstLevel", contentQueryVO.getFirstLevel());
        }
        if(!StringUtils.isEmpty(contentQueryVO.getTwoLevel())){
            map.put("towLevel", contentQueryVO.getTwoLevel());
        }
        if(!StringUtils.isEmpty(contentQueryVO.getSource()) && Integer.parseInt(contentQueryVO.getSource()) != 0){
            map.put("source", contentQueryVO.getSource());
        }
        if(!StringUtils.isEmpty(contentQueryVO.getBrowsingVolumeMin())){
            map.put("browsingVolumeMin", contentQueryVO.getBrowsingVolumeMin());
        }
        if(!StringUtils.isEmpty(contentQueryVO.getBrowsingVolumeMax())){
            map.put("browsingVolumeMax", contentQueryVO.getBrowsingVolumeMax());
        }
        if(!StringUtils.isEmpty(contentQueryVO.getForwardingAmountMin())){
            map.put("forwardingAmountMin", contentQueryVO.getForwardingAmountMin());
        }
        if(!StringUtils.isEmpty(contentQueryVO.getForwardingAmountMax())){
            map.put("forwardingAmountMax", contentQueryVO.getForwardingAmountMax());
        }
        if(!StringUtils.isEmpty(contentQueryVO.getCollentAmountMin())){
            map.put("collentAmountMin", contentQueryVO.getCollentAmountMin());
        }
        if(!StringUtils.isEmpty(contentQueryVO.getCollentAmountMax())){
            map.put("collentAountMax", contentQueryVO.getCollentAmountMax());
        }
        if(!StringUtils.isEmpty(contentQueryVO.getCommentsNumberMin())){
            map.put("commentsNumberMin", contentQueryVO.getCommentsNumberMin());
        }
        if(!StringUtils.isEmpty(contentQueryVO.getCommentsNumberMax())){
            map.put("commentsNumberMax", contentQueryVO.getCommentsNumberMax());
        }
        return infosMapper.getInfosPageByMap(map);
    }

    @Override
    public int delInfos(Long id) {
        int num = infosMapper.deleteByPrimaryKey(id);
        Infos infos = infosMapper.selectByPrimaryKey(id);
        //删除对应的图片
        infosImageMapper.delInfosImage(infos.getInfoId());
        return num;
    }

    @Override
    public int updateInfos(InfosVO infosVO) {
        //更新内容
        //删除对应的图片
        infosImageMapper.delInfosImage(infosVO.getInfoId());
        Map map = Maps.newHashMap();
        map.put("infoId", infosVO.getInfoId());
        Infos infos = infosMapper.getInfosByMap(map);
        infos.setTitle(infosVO.getTitle());
        String thumbnails = "";
        for(InfosImage infosImage : infosVO.getPicList()){
            infosImage.setInfoId(infos.getInfoId());
            infosImage.setPicType("1");
            infosImage.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
            infosImageMapper.insertSelective(infosImage);
            thumbnails += infosImage.getPicUrl() + ",";
        }
        infos.setThumbnails(thumbnails);
        infos.setChannelId(infosVO.getChannelId());
        infos.setFirstLevel(infosVO.getFirstLevel());
        infos.setTwoLevel(infosVO.getTwoLevel());
        infos.setCatInfoName(infosVO.getCatInfoName());

        if(infosVO.getInfoType().equals(InfosEnum.NEWS.getValue())){
            infos.setContent(infosVO.getContent());
        }else{
            //新增视频
            InfosImage infosVideo = new InfosImage();
            infosVideo.setPicUrl(infosVO.getVideoList().get(0).getPicUrl());
            infosVideo.setPicName(infosVO.getVideoList().get(0).getPicName());
            infosVideo.setInfoId(infos.getInfoId());
            infosVideo.setPicType("2");
            infosVideo.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
            infosImageMapper.insertSelective(infosVideo);
            infos.setVideos(infosVO.getVideoList().get(0).getPicUrl());
        }
        return infosMapper.updateByPrimaryKeySelective(infos);
    }

    @Override
    public Infos getInfos(String infoId) {
        Map map = Maps.newHashMap();
        map.put("infoId", infoId);
        return infosMapper.getInfosByMap(map);
    }

    @Override
    public InfosVO getInfosById(Long id) {
        Infos infos = infosMapper.selectByPrimaryKey(id);
        InfosVO infosVO = new InfosVO();
        BeanMapUtil.copy(infos, infosVO);
        Map map = Maps.newHashMap();
        map.put("infoId", infos.getInfoId());
        map.put("picType", "1");
        List<InfosImage> list = infosImageMapper.listInfosImage(map);
        infosVO.setPicList(list);
        Map mapVideo = Maps.newHashMap();
        mapVideo.put("infoId", infos.getInfoId());
        mapVideo.put("picType", "2");
        List<InfosImage> listVideo = infosImageMapper.listInfosImage(mapVideo);
        infosVO.setVideoList(listVideo);
        return infosVO;
    }

    @Override
    public String getConOrUrlById(Long id, String infoType) {
        if("news".equals(infoType)){
            return infosMapper.getNewsContent(id);
        }else if("video".equals(infoType)){
            return infosMapper.getVideoUrl(id);
        }
        return null;
    }


}
