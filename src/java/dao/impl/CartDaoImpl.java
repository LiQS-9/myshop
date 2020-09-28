package dao.impl;

import dao.CartDao;
import entity.Cart;
import entity.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-22 19:59
 **/
public class CartDaoImpl implements CartDao {
    private QueryRunner queryRunner = new QueryRunner();

    // 查询购物车列表
    @Override
    public List<Cart> findAll(int uid) throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "cart.c_id AS cid,\n" +
                        "cart.u_id AS uid,\n" +
                        "cart.p_id AS pid,\n" +
                        "cart.c_count AS ccount,\n" +
                        "cart.c_num AS cnum,\n" +
                        "product.t_id AS tid,\n" +
                        "product.p_name AS pname,\n" +
                        "product.p_time AS ptime,\n" +
                        "product.p_image AS pimage,\n" +
                        "product.p_price AS pprice,\n" +
                        "product.p_state AS pstate,\n" +
                        "product.p_info AS pinfo\n" +
                        "FROM\n" +
                        "cart\n" +
                        "INNER JOIN product ON cart.p_id = product.p_id\n" +
                        "WHERE\n" +
                        "cart.u_id = ? ",
                new ResultSetHandler<List<Cart>>() {
                    @Override
                    public List<Cart> handle(ResultSet res) throws SQLException {
                        List<Cart> carts = new ArrayList<>();
                        while (res.next()){

                            Cart cart = new Cart();
                            cart.setCid(res.getInt("cid"));
                            cart.setUid(res.getInt("uid"));
                            Product product = new Product();
                            product.setPid(res.getInt("pid"));
                            product.setPprice(res.getDouble("pprice"));
                            product.setPname(res.getString("pname"));
                            product.setTid(res.getInt("tid"));
                            cart.setProduct(product);
                            cart.setCcount(res.getDouble("ccount"));
                            cart.setCnum(res.getInt("cnum"));

                            carts.add(cart);
                        }

                        return carts;
                    }
                }, uid);
    }
    // 删除购物车记录
    @Override
    public int delete(int ncid) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
                "delete from cart where c_id = ?",
                ncid);
    }
    // 修改购物车记录
    @Override
    public int update(int ncid, int ncum, double nprice) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
                "update cart set c_num = ?,c_count=? where c_id=?",
                ncum,nprice*ncum,ncid);
    }

    // 清空购物车
    @Override
    public int deleteAll(int nuid) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
            "delete from cart where u_id =?",
                nuid);
    }

    // 添加购物车
    @Override
    public int add(int pid, int uid, Double pprice) throws SQLException {
        // 先判断用户是否添加过次商品
        long i = queryRunner.query(DBConnection.getConnection(),
                "select count(1) from cart where u_id =? and p_id=?",
                new ScalarHandler<>(),uid,pid);
        if(i>0){
            return  queryRunner.update(DBConnection.getConnection(),
                    "update cart set c_count =c_count+?,c_num=c_num+? where u_id =? and p_id =?",
                    pprice,1,uid,pid);
        }else {
            return queryRunner.update(DBConnection.getConnection(),
                    "insert into cart(u_id,p_id,c_count,c_num) values(?,?,?,?)",
                    uid,pid,pprice,1);
        }

    }
}
