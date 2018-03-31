package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.InfosImage;

import java.util.List;
import java.util.Map;

public interface InfosImageMapper {

    int deleteByPrimaryKey(Byte id);

    int insert(InfosImage record);

    int insertSelective(InfosImage record);

    InfosImage selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(InfosImage record);

    int updateByPrimaryKey(InfosImage record);

    /**
     * 删除图片
     * @param infoId
     * @return
     */
    int delInfosImage(String infoId);

    /**
     * 获取图片列表
     * @return
     */
    List<InfosImage> listInfosImage(Map map);

}