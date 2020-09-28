package service;

import entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> findAll(int uid);

    boolean delete(int ncid);

    boolean update(int ncid, int ncum, double nprice);

    boolean clear(int nuid);

    boolean create(int pid, int uid);
}
