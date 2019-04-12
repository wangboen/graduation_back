package nju.edu.graduation.dao;

import nju.edu.graduation.entity.Authorization;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationDao {
    /**
     * 添加新的授权许可申请
     * @param authorization 新的授权许可申请
     */
    void AddAuthorization(Authorization authorization);
}
