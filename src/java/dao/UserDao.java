package dao;

import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> findAll() throws SQLException;

    int delUser(int nid) throws SQLException;

    List<User> findByNameSex(String username, String gender) throws SQLException;

    User check(String username) throws SQLException;

    int addUser(User user) throws SQLException;

    int activeUser(String decode) throws SQLException;

    User login(String username, String password) throws SQLException;

    List<User> findAllInvalid() throws SQLException;
}
