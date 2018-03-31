package com.zhishun.zaotoutiao.dal.mapper;


import com.zhishun.zaotoutiao.core.model.entity.UserReadRecord;
import com.zhishun.zaotoutiao.core.model.vo.InfosVO;
import com.zhishun.zaotoutiao.core.model.vo.NavigationVO;
import com.zhishun.zaotoutiao.core.model.vo.UserReadRecordVO;
import org.apache.ibatis.annotations.Param;

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
     * 根据参数获取新闻
     * @param infoId
     * @return
     */
    UserReadRecord getUserReadRecordByInfoId(String infoId);

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

    /**
     * 判断用户是否浏览过该新闻
     * @param map
     * @return
     */
    int isRead(Map map);

    /**
     * 判断昨天是否阅读
     */
    List<UserReadRecord> isContinuousReadYesterday(Long userId);

    /**
     * 新增阅读记录
     * @param map
     */
    void addLookRecord(Map<String, Object> map);

    /**
     * 判断今天是否阅读
     * @param userId
     * @return
     */
    int isContinuousReadToday(Long userId);

    /**
     * 获取用户历史记录
     * @param map
     * @return
     */
    List<UserReadRecord> listLookRecordPage(Map<String,Object> map);

    /**
     * 获取用户历史总记录数
     * @param map
     * @return
     */
    int countLookRecord(Map<String,Object> map);

    /**
     * 获取最大的一条记录
     * @param userId
     * @return
     */
    UserReadRecord maxReadRecord(Long userId);

    /**
     * 根据条件，查询二级标签信息
     * @param map
     * @return
     */
    List<NavigationVO> list4Nav(Map<String,Object> map);

    /**
     * 获取某个导航下的所有阅读数
     * @param map
     * @return
     */
    Long getNavReadNum(Map<String,Object> map);

    /**
     * 获取某个一级标签下的所有阅读
     * @param map
     * @return
     */
    Long getFirstTabNum(Map<String,Object> map);

    /**
     * 获取我的评论列表
     * @param map
     * @return
     */
    List<UserReadRecordVO> getMyCommentsList(Map<String,Object> map);

    /**
     * 获取某个二级标签下的所有阅读
     * @param map
     * @return
     */
    Long getSecondTabNum(Map<String,Object> map);

    /**
     * 获得导航阅读前30infoId
     * @param map 导航Id navId
     * @return
     */
    List<String> getInfoId2RankTop30(Map<String,Object> map);

    /**
     * 获得一级标签阅读前30infoId
     * @param map 一级标签Id navId
     * @return
     */
    List<String> getInfoId2RankTop30ByFirst(Map<String,Object> map);

    /**
     * 获得二级标签阅读前30infoId
     * @param map 二级标签Id navId
     * @return
     */

    List<String> getInfoId2RankTop30BySecond(Map<String,Object> map);
    /**
     * 获取单个新闻的阅读数
     * @param map infoId
     * @return
     */
    Long getReadNumByOneInfo(Map<String,Object> map);
}