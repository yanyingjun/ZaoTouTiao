package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.UserReadRecord;

import java.util.List;
import java.util.Map;

public interface UserReadRecordMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserReadRecord record);

    int insertSelective(UserReadRecord record);

    UserReadRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserReadRecord record);

    int updateByPrimaryKey(UserReadRecord record);

    /**
     * 根据参数获取用户阅读的新闻
     * @param map
     * @return
     */
    UserReadRecord getUserReadRecord(Map<String,Object> map);

    /**
     * 更新是否请求过阅读金币的状态
     * @param record
     * @return
     */
    int updateReadRecord(UserReadRecord record);

    /**
     * 判断当天阅读是否大于三篇
     * @param userId
     * @return
     */
    List<UserReadRecord> isReadThreeToday(Long userId);

    /**
     * 判断昨天是否阅读
     * @param userId
     * @return
     */
    List<UserReadRecord> isReadFive(Long userId);

    /**
     * 清空用户历史记录
     * @param userId
     * @return
     */
    int delUserReadRecord(Long userId);
}