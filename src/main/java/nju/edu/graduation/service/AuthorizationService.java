package nju.edu.graduation.service;

import nju.edu.graduation.entity.Authorization;

import java.util.List;

public interface AuthorizationService {
    Authorization GetById(int id);

    void AddAuthorization(Authorization authorization);

    List<Authorization> getList(int to);

    void confirm(int authorization_id);

    void cancel(int authorization_id);
}
