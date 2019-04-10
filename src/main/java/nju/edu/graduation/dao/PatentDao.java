package nju.edu.graduation.dao;

import nju.edu.graduation.entity.Patent;
import org.springframework.stereotype.Repository;

@Repository
public interface PatentDao {
    /**
     * 根据专利id获取专利详情
     * @param id 专利id
     * @return 专利实体类，有着专利的详细信息
     */
    Patent GeyById(int id);

    /**
     * 根据专利编码获取专利详细信息
     * @param UID 专利编码
     * @return 专利实体类，有着专利的详细信息
     */
    Patent GetByUid(String UID);
}
