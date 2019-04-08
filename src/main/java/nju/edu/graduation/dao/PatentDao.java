package nju.edu.graduation.dao;

import nju.edu.graduation.entity.Patent;
import org.springframework.stereotype.Repository;

@Repository
public interface PatentDao {
    /**
     * 根据专利id获取专利详情
     * @param id
     * @return
     */
    Patent GeyById(int id);
}
