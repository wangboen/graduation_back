package nju.edu.graduation.dao;

import nju.edu.graduation.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {
    /**
     * 添加新的交易申请
     * @param order 新的交易申请
     */
    void AddOrder(Order order);
}
