package nju.edu.graduation.dao;

import nju.edu.graduation.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    /**
     * 根据用户id获取用户详情
     * @param id 用户id
     * @return 用户实体类，有着用户的详细信息
     */
    User GetById(int id);
}
