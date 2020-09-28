package service;

import entity.Cart;
import entity.Item;

import java.util.List;

public interface ItemService {
    boolean add(List<Cart> cartList,String orderId);

    List<Item> findAllByOid(String noid);
}
