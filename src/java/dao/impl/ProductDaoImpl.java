package dao.impl;

import dao.ProudctDao;
import entity.Goods;
import entity.Product;
import entity.Type;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
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
 * @create: 2020-09-21 15:13
 **/
public class ProductDaoImpl implements ProudctDao {
    private QueryRunner queryRunner = new QueryRunner();
    /* ===================用户模块==================*/
    @Override
    public List<Product> findAllById(int ntid,int skip,int page) throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "product.p_id AS pid,\n" +
                        "product.t_id AS tid,\n" +
                        "product.p_name AS pname,\n" +
                        "product.p_time AS ptime,\n" +
                        "product.p_image AS pimage,\n" +
                        "product.p_price AS pprice,\n" +
                        "product.p_state AS pstate,\n" +
                        "product.p_info AS pinfo\n" +
                        "FROM\n" +
                        "product\n" +
                        "WHERE\n" +
                        "product.t_id = ?\n"+
                        "LIMIT ?,?",new BeanListHandler<Product>(Product.class),
                ntid,skip,page);

    }

    @Override
    public long count(int ntid) throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "select count(1) from product where t_id = ?",
                new ScalarHandler<>(),ntid);
    }

    @Override
    public Product findByPid(int npid) throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "product.p_id AS pid,\n" +
                        "product.t_id AS tid,\n" +
                        "product.p_name AS pname,\n" +
                        "product.p_time AS ptime,\n" +
                        "product.p_image AS pimage,\n" +
                        "product.p_price AS pprice,\n" +
                        "product.p_state AS pstate,\n" +
                        "product.p_info AS pinfo\n" +
                        "FROM\n" +
                        "product\n" +
                        "WHERE\n" +
                        "product.p_id = ?\n",
                new BeanHandler<Product>(Product.class),npid);
    }

    /* ===================管理员模块==================*/
    @Override
    public List<Goods> findAllGoods() throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "product.p_id,\n" +
                        "product.t_id,\n" +
                        "product.p_name,\n" +
                        "product.p_time,\n" +
                        "product.p_image,\n" +
                        "product.p_price,\n" +
                        "product.p_state,\n" +
                        "product.p_info,\n" +
                        "type.t_name,\n" +
                        "type.t_info\n" +
                        "FROM\n" +
                        "type\n" +
                        "INNER JOIN product ON product.t_id = type.t_id",
                new ResultSetHandler<List<Goods>>() {
                    @Override
                    public List<Goods> handle(ResultSet res) throws SQLException {
                        List<Goods> list = new ArrayList<>();
                        while (res.next()){
                            list.add(
                                    new Goods(res.getInt("p_id"),
                                            new Type(res.getInt("t_id"),res.getString("t_name"),res.getString("t_info")),
                                            res.getString("p_name"),
                                            res.getTimestamp("p_time"),
                                            res.getString("p_image"),
                                            res.getDouble("p_price"),
                                            res.getInt("p_state"),
                                            res.getString("p_info")
                                    )
                            );

                        }
                        return list;
                    }
                });
    }

    // 添加商品
    @Override
    public int addProduct(Product product) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
                "insert into product(p_name,p_info,p_image,p_price,p_state,p_time,t_id)" +
                        "values(?,?,?,?,?,?,?)",
                product.getPname(),product.getPinfo(),product.getPimage(),product.getPprice(),product.getPstate(),product.getPtime(),product.getTid());
    }

    // 修改商品
    @Override
    public int update(Product product) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
                "update product set p_name=?,p_info=?,p_image=?,p_price=?,p_state=?,p_time=? ,t_id=? where p_id=?",
                product.getPname(),product.getPinfo(),product.getPimage(),product.getPprice(),product.getPstate(),product.getPtime(),product.getTid(),product.getPid());
    }

    // 删除商品
    @Override
    public int delete(int npid) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
                "delete from product where p_id= ?",
                npid);
    }
    // 查询商品
    @Override
    public List<Goods> findByNameTime(String pname, String parse) throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "product.p_id,\n" +
                        "product.t_id,\n" +
                        "product.p_name,\n" +
                        "product.p_time,\n" +
                        "product.p_image,\n" +
                        "product.p_price,\n" +
                        "product.p_state,\n" +
                        "product.p_info,\n" +
                        "type.t_name,\n" +
                        "type.t_info\n" +
                        "FROM\n" +
                        "type\n" +
                        "INNER JOIN product ON product.t_id = type.t_id where p_name= ? and p_time= ?",
                new ResultSetHandler<List<Goods>>() {
                    @Override
                    public List<Goods> handle(ResultSet res) throws SQLException {
                        List<Goods> list = new ArrayList<>();
                        while (res.next()){
                            list.add(
                                    new Goods(res.getInt("p_id"),
                                            new Type(res.getInt("t_id"),res.getString("t_name"),res.getString("t_info")),
                                            res.getString("p_name"),
                                            res.getTimestamp("p_time"),
                                            res.getString("p_image"),
                                            res.getDouble("p_price"),
                                            res.getInt("p_state"),
                                            res.getString("p_info")
                                    )
                            );

                        }
                        return list;
                    }
                },pname,parse);
    }
}
