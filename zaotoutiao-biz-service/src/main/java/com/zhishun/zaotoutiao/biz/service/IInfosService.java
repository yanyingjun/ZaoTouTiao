package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.vo.InfosVO;

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
}
