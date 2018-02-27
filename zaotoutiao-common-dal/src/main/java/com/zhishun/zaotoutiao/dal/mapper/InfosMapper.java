package com.zhishun.zaotoutiao.dal.mapper;

import com.zhishun.zaotoutiao.core.model.entity.Infos;
import com.zhishun.zaotoutiao.core.model.vo.InfosVo;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface InfosMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Infos record);

    int insertSelective(Infos record);

    Infos selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Infos record);

    int updateByPrimaryKey(Infos record);

    List<InfosVo> selectInfosByType(Map map);

    int selectCountByType(String type);

    /**
     * 获取用户历史记录
     * @param map
     * @return
     */
    List<InfosVo> listLookRecordPage(Map<String,Object> map);

    /**
     * 获取用户历史总记录数
     * @param map
     * @return
     */
    int countLookRecord(Map<String,Object> map);
}