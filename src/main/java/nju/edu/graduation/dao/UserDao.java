package nju.edu.graduation.dao;

import nju.edu.graduation.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserDao {
    /**
     * 根据用户id获取用户详情
     * @param id 用户id
     * @return 用户实体类，有着用户的详细信息
     */
    User GetById(int id);

    /**
     * 根据用户名和密码查询账户
     * @param user 用户名和密码
     * @return 查询结果
     */
    User Login(User user);

    /**
     * 付钱
     * @param map
     */
    void pay(Map<String,Object> map);

    /**
     * 收钱
     * @param map
     */
    void get(Map<String,Object> map);
}
