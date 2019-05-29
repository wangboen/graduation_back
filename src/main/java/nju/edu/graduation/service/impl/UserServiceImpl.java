package nju.edu.graduation.service.impl;

import nju.edu.graduation.dao.UserDao;
import nju.edu.graduation.entity.User;
import nju.edu.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User GetById(int id) {
        return userDao.GetById(id);
    }

    @Override
    public User Login(User user) {
        return userDao.Login(user);
    }

    @Override
    public void deposit(Map<String, Object> map) {
        userDao.pay(map);
    }

    @Override
    public void get(Map<String, Object> map) {
        userDao.get(map);
    }

    @Override
    public List<User> getOther(int id) {
        return userDao.getOther(id);
    }
}
