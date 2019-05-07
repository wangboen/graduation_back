package nju.edu.graduation.dao;

import nju.edu.graduation.entity.Authorization;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorizationDao {
    /**
     * 根据授权申请id获取授权申请详情
     * @param id 授权申请id
     * @return 授权类，有着授权申请的详细信息
     */
    Authorization GetById(int id);

    /**
     * 添加新的授权许可申请
     * @param authorization 新的授权许可申请
     */
    void AddAuthorization(Authorization authorization);

    /**
     * 获取发送给当前登录用户的所有授权申请
     * @param to 当前登录用户ID
     * @return 交易申请
     */
    List<Authorization> getList(int to);

    void confirm(int id);

    void cancel(int id);
}
