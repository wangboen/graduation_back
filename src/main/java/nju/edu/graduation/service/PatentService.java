package nju.edu.graduation.service;

import nju.edu.graduation.entity.Patent;

public interface PatentService {
    Patent info(String UID);

    Patent GetById(int parent_id);

    Patent GetByName(String name);
}
