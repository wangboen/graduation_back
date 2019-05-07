package nju.edu.graduation.service.impl;

import nju.edu.graduation.dao.PatentDao;
import nju.edu.graduation.entity.Patent;
import nju.edu.graduation.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatentServiceImpl implements PatentService {

    private final PatentDao patentDao;

    @Autowired
    public PatentServiceImpl(PatentDao patentDao) {
        this.patentDao = patentDao;
    }

    @Override
    public Patent info(String UID) {
        return patentDao.GetByUid(UID);
    }

    @Override
    public Patent GetById(int parent_id) {
        return patentDao.GetById(parent_id);
    }

    @Override
    public Patent GetByName(String name) {
        return patentDao.GetByName(name);
    }
}
