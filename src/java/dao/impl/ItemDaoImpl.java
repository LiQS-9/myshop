package dao.impl;

import dao.ItemDao;
import entity.Item;
import entity.Product;
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
 * @create: 2020-09-23 15:11
 **/
public class ItemDaoImpl implements ItemDao {
    private QueryRunner queryRunner = new QueryRunner();
    @Override
    public int add(Item item) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
                "insert into item(o_id,p_id,i_count,i_num) values(?,?,?,?)",
                item.getOid(),item.getProduct().getPid(),item.getIcount(),item.getInum());
    }

    // 查询订单详情
    @Override
    public List<Item> findAllByOid(String noid) throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "item.i_id,\n" +
                        "item.o_id,\n" +
                        "item.p_id,\n" +
                        "item.i_count,\n" +
                        "item.i_num,\n" +
                        "product.t_id,\n" +
                        "product.p_name,\n" +
                        "product.p_time,\n" +
                        "product.p_image,\n" +
                        "product.p_price,\n" +
                        "product.p_state,\n" +
                        "product.p_info\n" +
                        "FROM\n" +
                        "product\n" +
                        "INNER JOIN item ON item.p_id = product.p_id " +
                        "where item.o_id = ? ",
                new ResultSetHandler<List<Item>>() {
                    @Override
                    public List<Item> handle(ResultSet res) throws SQLException {
                        List<Item> list = new ArrayList<>();
                        while (res.next()){
                            Item item = new Item();
                            item.setIid(res.getInt("i_id"));
                            Product product = new Product(

                                    res.getInt("p_id"),
                                    res.getInt("t_id"),
                                    res.getString("p_name"),
                                    res.getTimestamp("p_time"),
                                    res.getString("p_image"),
                                    res.getDouble("p_price"),
                                    res.getInt("p_state"),
                                    res.getString("p_info")
                            );
                            item.setProduct(product);
                            item.setInum(res.getInt("i_num"));
                            item.setIcount(res.getDouble("i_count"));
                            item.setOid(res.getString("o_id"));
                            list.add(item);
                        }
                        return list;
                    }
                }
                , noid);
    }
}
