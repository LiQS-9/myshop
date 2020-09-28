package service;

import entity.Goods;
import entity.PageBean;
import entity.Product;

import java.util.List;

public interface ProductSevice {
    PageBean findAllById(int ntid, int page);

    Product findByPId(int npid);

    List<Goods> findAllGoods();

    int addProduct(Product product);

    boolean update(Product product);

    boolean delete(int npid);

    List<Goods> findByNameTime(String pname, String parse);
}
