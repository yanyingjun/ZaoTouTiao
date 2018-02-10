package com.zhishun.zaotoutiao.common.base.core.impl;

import com.zhishun.zaotoutiao.common.base.core.BaseCoreService;
import com.zhishun.zaotoutiao.common.base.dal.BaseMapper;

import java.util.List;

public abstract class BaseCoreServiceImpl<VOT, QT, MAPPER extends BaseMapper> implements BaseCoreService<VOT, QT> {

    protected MAPPER mapper;

    public MAPPER getMapper() {
        return mapper;
    }

    /**
     * 注入对应的数据库操作服务
     * @param mapper
     */
    public abstract void setMapper(MAPPER mapper);

    @Override
    public int countPageByParam(QT query) {
        return mapper.countPageByParam(query);
    }

    @Override
    public List<VOT> queryByParam(QT query) {
        return mapper.queryByParam(query);
    }

    @Override
    public int insert(VOT vo) {
        return mapper.insert(vo);
    }

    @Override
    public int update(VOT vo) {
        return mapper.update(vo);
    }

    @Override
    public int delete(VOT vo) {
        return mapper.delete(vo);
    }

    @Override
    public VOT getById(QT query) {
        return (VOT) mapper.getById(query);
    }

    @Override
    public List<VOT> queryPageByParam(QT query){
        return mapper.queryPageByParam(query);
    }

}
