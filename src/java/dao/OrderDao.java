package dao;

import entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    int add(Order order) throws SQLException;

    Order findById(String orderId) throws SQLException;

    List<Order> findMyOrder(Integer uid) throws SQLException;

    // 管理员
    List<Order> findAllOrder() throws SQLException;

    int upState(String oid) throws SQLException;

    List<Order> findByNameState(String uname, String ostate) throws SQLException;
}
