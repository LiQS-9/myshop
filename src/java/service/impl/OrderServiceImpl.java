package service.impl;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import entity.Address;
import entity.Order;
import entity.User;
import service.OrderService;
import utils.DBConnection;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-23 12:49
 **/
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    @Override
    public boolean create(int nuid, double nsum, int naid, String orderId) {
        // 补全order对象
        Order order = new Order();
        order.setOid(orderId);
        User user = new User();
        user.setUid(nuid);
        order.setUser(user);

        Address address = new Address();
        address.setAid(naid);
        order.setAddress(address);

        order.setOcount(nsum);
        order.setOtime(new Date());
//        订单状态 0 未付款，1已经付款未发货 2发货待收货 3 收货待评价 4订单完成 5 退货状态
        order.setOstate(0);

        try {
            return orderDao.add(order)>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // 查询订单
    @Override
    public Order findOrderByOid(String orderId) {
        try {
            return orderDao.findById(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 展示用户全部订单
    @Override
    public List<Order> findMyOrder(Integer uid) {
        try {
            return orderDao.findMyOrder(uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 管理员 查询全部订单
    @Override
    public List<Order> showAllOrder() {
        try {
            return orderDao.findAllOrder();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 修改订单状态
    @Override
    public boolean upState(String oid) {
        try {
            return orderDao.upState(oid)>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // 管理员查询
    @Override
    public List<Order> findByNameState(String uname, String ostate) {
        try {
            return orderDao.findByNameState(uname,ostate);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
