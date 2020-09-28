package service.impl;

import dao.ItemDao;
import dao.impl.ItemDaoImpl;
import entity.Cart;
import entity.Item;
import service.ItemService;
import utils.DBConnection;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-23 15:08
 **/
public class ItemServicesImpl implements ItemService {
    private ItemDao itemDao = new ItemDaoImpl();
    @Override
    public boolean add(List<Cart> cartList, String orderId) {
        int sum = 0;
        int size = cartList.size();
        // 添加一个个item
        for(Cart cart:cartList){
            Item item = new Item();
            item.setProduct(cart.getProduct());
            item.setOid(orderId);
            item.setIcount(cart.getCcount());
            item.setInum(cart.getCnum());

            try {
                  sum += itemDao.add(item) ;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return size==sum;
    }

    // 查询订单详情
    @Override
    public List<Item> findAllByOid(String noid) {
        try {
            return itemDao.findAllByOid(noid);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
