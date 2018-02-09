package com.zhishun.zaotoutiao.core.service.user;

import com.zhishun.zaotoutiao.common.base.core.BaseCoreService;
import com.zhishun.zaotoutiao.core.model.entity.User;

import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserService, v0.1 2018年02月08日 10:40闫迎军(YanYingJun) Exp $
 */

public interface UserBizService {

    /**
     * 统计
     *
     * @param user Query实体
     * @return 统计结果
     */
    int countPageByParam(User user);

    /**
     * 分页查询集合
     *
     * @param user Query实体
     * @return VO实体集合
     */
    List<User> queryPageByParam(User user);

    /**
     * 列表
     *
     * @param user Query类
     * @return VO类集合
     */
    List<User> queryByParam(User user);

    /**
     * 添加
     *
     * @param user VO实体
     * @return 添加结果
     */
    int insert(User user);

    /**
     * 修改
     *
     * @param user VO实体
     * @return 修改结果
     */
    int update(User user);

    /**
     * 删除
     *
     * @param user VO实体
     * @return 删除结果
     */
    int delete(User user);

    /**
     * 通过主键ID查询
     *
     * @param user Query类
     * @return VO类
     */
    User getById(User user);

}
