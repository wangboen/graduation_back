package nju.edu.graduation.service;

import nju.edu.graduation.entity.User;

import java.util.Map;

public interface UserService {
    User GetById(int id);

    User Login(User user);

    void deposit(Map<String,Object> map);

    void get(Map<String,Object> map);
}
