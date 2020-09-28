package dao;

import entity.Type;

import java.sql.SQLException;
import java.util.List;

public interface TypeDao {

    List<Type> findAll() throws SQLException;

    int addType(Type type) throws SQLException;

    Type findById(int ntid) throws SQLException;

    int update(Type type) throws SQLException;

    int delete(int ntid) throws SQLException;

    List<Type> findByName(String tname) throws SQLException;
}
