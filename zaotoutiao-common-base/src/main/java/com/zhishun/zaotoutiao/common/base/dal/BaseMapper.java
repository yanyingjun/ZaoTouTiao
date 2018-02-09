package com.zhishun.zaotoutiao.common.base.dal;

import java.util.List;


public interface BaseMapper<VOT, QT> {
    /**
     * 统计
     *
     * @param query Query实体
     * @return 统计结果
     */
    int countPageByParam(QT query);

    /**
     * 分页查询集合
     *
     * @param query Query实体
     * @return VO实体集合
     */
    List<VOT> queryPageByParam(QT query);

    /**
     * 列表
     *
     * @param query Query类
     * @return VO类集合
     */
    List<VOT> queryByParam(QT query);

    /**
     * 添加
     *
     * @param vo VO实体
     * @return 添加结果
     */
    int insert(VOT vo);

    /**
     * 修改
     *
     * @param vo VO实体
     * @return 修改结果
     */
    int update(VOT vo);

    /**
     * 删除
     *
     * @param vo VO实体
     * @return 删除结果
     */
    int delete(VOT vo);

    /**
     * 通过主键ID查询
     *
     * @param query Query类
     * @return VO类
     */
    VOT getById(QT query);

    /**
     * 通过三方appId和appUserId查询
     *
     * @param query Query类
     * @return VO类
     */
    VOT getByAppUser(QT query);
}
