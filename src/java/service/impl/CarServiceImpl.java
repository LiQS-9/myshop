package service.impl;

import dao.CartDao;
import dao.impl.CartDaoImpl;
import entity.Cart;
import entity.Product;
import service.CartService;
import service.ProductSevice;
import utils.DBConnection;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-22 19:58
 **/
public class CarServiceImpl implements CartService {
    private CartDao cartDao = new CartDaoImpl();
    private ProductSevice productSevice = new ProductServiceImpl();
    @Override
    public List<Cart> findAll(int uid) {
        try {
            return  cartDao.findAll(uid);
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

    // 删除商品
    @Override
    public boolean delete(int ncid) {
        try {
            return cartDao.delete(ncid)>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // 修改购物车记录
    @Override
    public boolean update(int ncid, int ncum, double nprice) {
        try {
            return cartDao.update(ncid,ncum,nprice)>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // 清空购物车
    @Override
    public boolean clear(int nuid) {
        try {
            return cartDao.deleteAll(nuid) >0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // 添加购物车
    @Override
    public boolean create(int pid, int uid) {

        // 先根据pid查找商品的价格
        Product product = productSevice.findByPId(pid);
        try {
            return cartDao.add(pid,uid,product.getPprice())>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
