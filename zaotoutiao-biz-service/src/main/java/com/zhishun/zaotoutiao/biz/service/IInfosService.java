package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.entity.Infos;
import com.zhishun.zaotoutiao.core.model.vo.ContentQueryVO;
import com.zhishun.zaotoutiao.core.model.vo.InfosVO;

import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IInfosService, v0.1 2018年03月23日 22:02闫迎军(YanYingJun) Exp $
 */
public interface IInfosService {

    /**
     * 新增新闻或视频
     * @param infosVO
     * @return
     */
    int addInfos(InfosVO infosVO);

    /**
     * 返回新闻内容或视频链接
     * @param id
     * @param infoType
     * @return
     */
    String getConOrUrlById(Long id, String infoType);

    /**
     * 获取内容列表
     * @return
     */
    List<InfosVO> listInfos(ContentQueryVO contentQueryVO);

    /**
     * 删除内容
     * @param infoId
     * @return
     */
    int delInfos(Long infoId);

    /**
     * 修改内容
     * @param infosVO
     * @return
     */
    int updateInfos(InfosVO infosVO);

    /**
     * 查询内容信息
     * @param infoId
     * @return
     */
    Infos getInfos(String infoId);

    /**
     * 根据ID获取新闻
     * @param id
     * @return
     */
    InfosVO getInfosById(Long id);
}
