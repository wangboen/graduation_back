package nju.edu.graduation.service.impl;

import nju.edu.graduation.dao.UserDao;
import nju.edu.graduation.entity.User;
import nju.edu.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
