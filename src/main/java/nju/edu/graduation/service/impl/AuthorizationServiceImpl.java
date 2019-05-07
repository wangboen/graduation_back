package nju.edu.graduation.service.impl;

import nju.edu.graduation.dao.AuthorizationDao;
import nju.edu.graduation.entity.Authorization;
import nju.edu.graduation.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorizationServiceImpl implements AuthorizationService {
    private final AuthorizationDao authorizationDao;

    @Autowired
    public AuthorizationServiceImpl(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    @Override
    public Authorization GetById(int id) {
        return authorizationDao.GetById(id);
    }

    @Override
    public void AddAuthorization(Authorization authorization) {
        authorizationDao.AddAuthorization(authorization);
    }

    @Override
    public List<Authorization> getList(int to) {
        return authorizationDao.getList(to);
    }

    @Override
    public void confirm(int authorization_id) {
        authorizationDao.confirm(authorization_id);
    }

    @Override
    public void cancel(int authorization_id) {
        authorizationDao.cancel(authorization_id);
    }
}
