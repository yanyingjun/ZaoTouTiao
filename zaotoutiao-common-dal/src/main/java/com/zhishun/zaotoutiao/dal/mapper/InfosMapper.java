package com.zhishun.zaotoutiao.dal.mapper;

import com.zhishun.zaotoutiao.core.model.entity.Infos;
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

    List<Infos> selectInfosByType(Map map);

    int selectCountByType(String type);
}