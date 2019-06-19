package nju.edu.graduation.service;

import com.sun.org.apache.xpath.internal.operations.Or;
import nju.edu.graduation.entity.Order;

import java.util.List;

public interface OrderService {
    Order GetById(int id);

    List<Order> GetByPatent(int patent);

    void AddOrder(Order order);

    List<Order> getList(int to);

    List<Order> list(int patent);

    void confirm(int order_id,int patent_id);

    void cancel(int order_id);
}
