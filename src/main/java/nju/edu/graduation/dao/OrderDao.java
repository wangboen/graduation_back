package nju.edu.graduation.dao;

import nju.edu.graduation.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    /**
     * 根据交易申请id获取交易申请详情
     * @param id 交易申请id
     * @return 交易类，有着交易申请的详细信息
     */
    Order GetById(int id);

    List<Order> GetByPatent(int patent);

    /**
     * 添加新的交易申请
     * @param order 新的交易申请
     */
    void AddOrder(Order order);

    /**
     * 获取发送给当前登录用户的所有交易申请
     * @param to 当前登录用户ID
     * @return 交易申请
     */
    List<Order> getList(int to);

    List<Order> list(int patent);

    void confirm1(int patent);

    void confirm2(int id);

    void cancel(int patent);
}
