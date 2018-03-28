package com.zhishun.zaotoutiao.biz.service;

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
     * 获取内容列表
     * @return
     */
    List<InfosVO> listInfos(ContentQueryVO contentQueryVO);
}
