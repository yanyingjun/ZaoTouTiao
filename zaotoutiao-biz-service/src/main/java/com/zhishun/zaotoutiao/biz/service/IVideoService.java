package com.zhishun.zaotoutiao.biz.service;

        import com.zhishun.zaotoutiao.core.model.entity.Infos;
        import com.zhishun.zaotoutiao.core.model.entity.VideoChannels;
        import com.zhishun.zaotoutiao.core.model.vo.InfosVo;

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
    List<VideoChannels> listVideoChannels();

    /**
     * 根据类型查找视频或新闻
     * @param type
     * @param channelId
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<InfosVo> getInfosByType(String type, int channelId, int pageNo, int pageSize);

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
