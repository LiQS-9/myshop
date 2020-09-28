package dao;

import entity.Cart;

import java.sql.SQLException;
import java.util.List;

public interface CartDao {
    List<Cart> findAll(int uid) throws SQLException;

    int delete(int ncid) throws SQLException;

    int update(int ncid, int ncum, double nprice) throws SQLException;

    int deleteAll(int nuid) throws SQLException;

    int add(int pid, int uid, Double pprice) throws SQLException;
}
