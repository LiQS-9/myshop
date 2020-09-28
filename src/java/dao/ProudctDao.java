package dao;

import entity.Goods;
import entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProudctDao {
    List<Product> findAllById(int ntid,int skip,int page) throws SQLException;

    long count(int ntid) throws SQLException;

    Product findByPid(int npid) throws SQLException;

    List<Goods> findAllGoods() throws SQLException;

    int addProduct(Product product) throws SQLException;

    int update(Product product) throws SQLException;

    int delete(int npid) throws SQLException;

    List<Goods> findByNameTime(String pname, String parse) throws SQLException;
}
