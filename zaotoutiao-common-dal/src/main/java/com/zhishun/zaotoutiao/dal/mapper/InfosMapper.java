package com.zhishun.zaotoutiao.dal.mapper;

import com.zhishun.zaotoutiao.core.model.entity.Infos;
import com.zhishun.zaotoutiao.core.model.vo.InfosVO;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 查询内容列表
     * @param map
     * @return
     */
    List<InfosVO> getInfosPageByMap(Map map);

    /**
     * 删除内容
     * @param infoId
     * @return
     */
    int delInfos(String infoId);



    /**
     * 根据infoId获取前30新闻或视频数据
     * @param infoIdList
     * @return
     */
    List<InfosVO> listOfTot(@Param("infoIdList") List<String> infoIdList);

    /**
     * 根据id获得新闻内容
     * @param id
     * @return
     */
    String getNewsContent(Long id);

    /**
     * 根据id获得视频url
     * @param id
     * @return
     */
    String getVideoUrl(Long id);
}