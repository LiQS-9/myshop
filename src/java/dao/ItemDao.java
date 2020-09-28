package dao;

import entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao {
    int add(Item item) throws SQLException;

    List<Item> findAllByOid(String noid) throws SQLException;
}
