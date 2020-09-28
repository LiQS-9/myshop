package dao.impl;

import dao.OrderDao;
import entity.Address;
import entity.Order;
import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import utils.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-23 14:29
 **/
public class OrderDaoImpl implements OrderDao {
    private QueryRunner queryRunner = new QueryRunner();
    @Override
    public int add(Order order) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
                "insert into orders(o_id,u_id,a_id,o_count,o_time,o_state) values(?,?,?,?,?,?) ",
                order.getOid(),order.getUser().getUid(),order.getAddress().getAid(),order.getOcount(),order.getOtime(),order.getOstate());
    }
    // 查询单个订单
    @Override
    public Order findById(String orderId) throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "orders.o_id AS oid,\n" +
                        "orders.u_id AS uid,\n" +
                        "orders.a_id AS aid,\n" +
                        "orders.o_count AS ocount,\n" +
                        "orders.o_time AS otime,\n" +
                        "orders.o_state AS ostate,\n" +
                        "address.a_name AS aname,\n" +
                        "address.a_phone AS aphone,\n" +
                        "address.a_detail AS adetail,\n" +
                        "address.a_state AS astate\n" +
                        "FROM\n" +
                        "orders\n" +
                        "INNER JOIN address ON orders.a_id = address.a_id " +
                        "where o_id = ?\n",
                new ResultSetHandler<Order>() {
                    @Override
                    public Order handle(ResultSet res) throws SQLException {
                        
                        while (res.next()){
                            Order order = new Order();
                            order.setOid(res.getString("oid"));
                            User user = new User();
                            user.setUid(res.getInt("uid"));
                            order.setUser(user);
                            Address address = new Address(
                                    res.getInt("aid"),
                                    res.getInt("uid"),
                                    res.getString("aname"),
                                    res.getString("aphone"),
                                    res.getString("adetail"),
                                    res.getInt("astate")
                            );
                            order.setAddress(address);
                            order.setOtime(res.getTimestamp("otime"));
                            order.setOstate(res.getInt("ostate"));
                            order.setOcount(res.getDouble("ocount"));
                            return order;
                        }
                        return null;
                    }
                }, orderId);
    }

    // 查询用户的全部订单
    @Override
    public List<Order> findMyOrder(Integer uid) throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT orders.o_id , address.a_state,orders.u_id,orders.a_id,orders.o_count, orders.o_time,orders.o_state,address.a_name,address.a_phone,address.a_detail,`user`.u_name,`user`.u_password,`user`.u_email,`user`.u_sex,`user`.u_status,`user`.u_code,`user`.u_role FROM `user` INNER JOIN address ON address.u_id = `user`.u_id INNER JOIN orders ON orders.a_id = address.a_id AND orders.u_id = `user`.u_id where orders.u_id=?",
                new ResultSetHandler<List<Order>>() {
                    @Override
                    public List<Order> handle(ResultSet res) throws SQLException {
                        List<Order> list = new ArrayList<>();
                        while (res.next()){
                            Order order = new Order();
                            order.setOid(res.getString("o_id"));
                            User user = new User(
                                    res.getInt("u_id"),
                                    res.getString("u_name"),
                                    res.getString("u_password"),
                                    res.getString("u_email"),
                                    res.getString("u_sex"),
                                    res.getInt("u_status"),
                                    res.getString("u_code"),
                                    res.getInt("u_role")

                            );

                            order.setUser(user);
                            Address address = new Address(
                                    res.getInt("a_id"),
                                    res.getInt("u_id"),
                                    res.getString("a_name"),
                                    res.getString("a_phone"),
                                    res.getString("a_detail"),
                                    res.getInt("a_state")
                            );
                            order.setAddress(address);
                            order.setOtime(res.getTimestamp("o_time"));
                            order.setOstate(res.getInt("o_state"));
                            order.setOcount(res.getDouble("o_count"));
                            list.add(order);
                        }
                        return list;
                    }
                }, uid);
    }

    // 管理员 查询全部订单
    @Override
    public List<Order> findAllOrder() throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "orders.o_id,\n" +
                        "orders.u_id,\n" +
                        "orders.a_id,\n" +
                        "orders.o_count,\n" +
                        "orders.o_time,\n" +
                        "orders.o_state,\n" +
                        "`user`.u_name\n" +
                        "FROM\n" +
                        "orders\n" +
                        "INNER JOIN `user` ON orders.u_id = `user`.u_id",
                new ResultSetHandler<List<Order>>() {
                    @Override
                    public List<Order> handle(ResultSet res) throws SQLException {
                        List<Order> list = new ArrayList<>();
                        while (res.next()){
                            Order order = new Order();
                            Address address = new Address();
                            User user = new User();

                            order.setOid(res.getString("o_id"));
                            user.setUid(res.getInt("u_id"));
                            user.setUname(res.getString("u_name"));
                            order.setUser(user);

                            address.setAid(res.getInt("a_id"));
                            order.setAddress(address);

                            order.setOtime(res.getTimestamp("o_time"));
                            order.setOstate(res.getInt("o_state"));
                            order.setOcount(res.getDouble("o_count"));
                            list.add(order);

                        }
                        return list;
                    }
                });
    }

    // 修改订单状态
    @Override
    public int upState(String oid) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
                "update orders set o_state = ? where o_id = ?",1,oid);
    }

    // 管理员查询
    @Override
    public List<Order> findByNameState(String uname, String ostate) throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "orders.o_id,\n" +
                        "orders.u_id,\n" +
                        "orders.a_id,\n" +
                        "orders.o_count,\n" +
                        "orders.o_time,\n" +
                        "orders.o_state,\n" +
                        "`user`.u_name\n" +
                        "FROM\n" +
                        "orders\n" +
                        "INNER JOIN `user` ON orders.u_id = `user`.u_id\n" +
                        "WHERE\n" +
                        "orders.o_state = ? AND\n" +
                        "`user`.u_name = ?\n",
                new ResultSetHandler<List<Order>>() {
                    @Override
                    public List<Order> handle(ResultSet res) throws SQLException {
                        List<Order> list = new ArrayList<>();
                        while (res.next()) {
                            Order order = new Order();
                            Address address = new Address();
                            User user = new User();

                            order.setOid(res.getString("o_id"));
                            user.setUid(res.getInt("u_id"));
                            user.setUname(res.getString("u_name"));
                            order.setUser(user);


                            order.setOtime(res.getTimestamp("o_time"));
                            order.setOstate(res.getInt("o_state"));
                            order.setOcount(res.getDouble("o_count"));
                            list.add(order);

                        }
                        return list;
                    }
                },ostate,uname
        );
    }
}
