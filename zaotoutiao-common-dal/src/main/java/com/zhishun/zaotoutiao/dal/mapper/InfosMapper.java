package com.zhishun.zaotoutiao.dal.mapper;

import com.zhishun.zaotoutiao.core.model.entity.Infos;
import com.zhishun.zaotoutiao.core.model.vo.InfosVO;

import java.util.List;
import java.util.Map;

public interface InfosMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Infos record);

    int insertSelective(Infos record);

    Infos selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Infos record);

    int updateByPrimaryKey(Infos record);

    List<InfosVO> selectInfosByType(Map map);

    int selectCountByType(String type);

    /**
     * 根据新闻ID获取新闻信息
     * @param map
     * @return
     */
    Infos getInfosByMap(Map<String,Object> map);

    /**
     * 获取我的评论列表
     * @param map
     * @return
     */
    List<InfosVO> getMyCommentsList(Map<String,Object> map);

    /**
     * 获取我的评论总记录数
     * @param map
     * @return
     */
    int countMyCommentsList(Map<String,Object> map);

    /**
     * 获取视频相关内容
     * @param channelId
     * @return
     */
    List<Infos> getRandVideoList(int channelId);

    /**
     * 搜索新闻
     * @param map
     * @return
     */
    List<InfosVO> searchNewsByKeyword(Map<String,Object> map);

    /**
     * 获取24小时热文
     * @return
     */
    List<InfosVO> List24HoursInfos();

    String getInfoTitle(String infoId);
}