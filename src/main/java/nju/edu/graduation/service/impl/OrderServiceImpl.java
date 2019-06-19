package nju.edu.graduation.service.impl;

import nju.edu.graduation.dao.OrderDao;
import nju.edu.graduation.entity.Order;
import nju.edu.graduation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order GetById(int id) {
        return orderDao.GetById(id);
    }

    @Override
    public List<Order> GetByPatent(int patent) {
        return orderDao.GetByPatent(patent);
    }

    @Override
    public void AddOrder(Order order) {
        orderDao.AddOrder(order);
    }

    @Override
    public List<Order> getList(int to) {
        return orderDao.getList(to);
    }

    @Override
    public List<Order> list(int patent) {
        return orderDao.list(patent);
    }

    @Override
    public void confirm(int order_id, int patent_id) {
        orderDao.confirm1(patent_id);
        orderDao.confirm2(order_id);
    }

    @Override
    public void cancel(int order_id) {
        orderDao.cancel(order_id);
    }
}
