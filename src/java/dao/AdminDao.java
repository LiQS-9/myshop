package dao;

import entity.User;

import java.sql.SQLException;

public interface AdminDao {
    User findAdmin(String username, String password) throws SQLException;
}
