package dao;

import entity.Address;

import java.sql.SQLException;
import java.util.List;

public interface AddressDao {
    List<Address> findAllById(int uid) throws SQLException;

    int delete(int naid) throws SQLException;

    int add(String aname, String aphone, String adetail, Integer uid) throws SQLException;

    int setDefault(int aid) throws SQLException;

    int update(Address address) throws SQLException;
}
