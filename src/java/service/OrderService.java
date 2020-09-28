package service;

import entity.Order;

import java.util.List;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-23 12:49
 **/
public interface OrderService {
    boolean create(int nuid, double nsum, int naid, String orderId);

    Order findOrderByOid(String orderId);

    List<Order> findMyOrder(Integer uid);

    // 查询全部订单
    List<Order> showAllOrder();

    boolean upState(String oid);

    List<Order> findByNameState(String uname, String ostate);
}
