package com.zhishun.zaotoutiao.biz.service;

        import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
        import com.zhishun.zaotoutiao.core.model.entity.Channels;
        import com.zhishun.zaotoutiao.core.model.entity.Infos;
        import com.zhishun.zaotoutiao.core.model.vo.InfosVO;

        import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IVideoService, v0.1 2018年02月25日 12:15闫迎军(YanYingJun) Exp $
 */
public interface IVideoService {

    /**
     * 获取视频分类列表
     * @return
     */
    List<Channels> listVideoChannels(Integer status, Integer appType);

    /**
     * 根据类型查找视频或新闻
     * @param type
     * @param channelId
     * @param pageRequest
     * @return
     */
    List<InfosVO> getInfosByType(String type, int channelId, PageRequest pageRequest);

    /**
     * 获取视频相关内容
     * @param channelId
     * @return
     */
    List<Infos> getRandVideoList(int channelId);

    /**
     * 获取新闻
     * @param infoId
     * @return
     */
    Infos getInfosByInfoId(String infoId);
}
