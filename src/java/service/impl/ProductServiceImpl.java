package service.impl;

import dao.ProudctDao;
import dao.impl.ProductDaoImpl;
import entity.Goods;
import entity.PageBean;
import entity.Product;
import service.ProductSevice;
import utils.Constants;
import utils.DBConnection;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-21 15:12
 **/
public class ProductServiceImpl implements ProductSevice {
    private ProudctDao proudctDao = new ProductDaoImpl();

    /*==========用户模块============*/
    // 商品分页展示
    @Override
    public PageBean findAllById(int ntid, int page) {

        try {
            int skip = (page-1)*Constants.PAGE_SIZE;
            List<Product> list = proudctDao.findAllById(ntid, skip, Constants.PAGE_SIZE);
            long count = proudctDao.count(ntid);
            return new PageBean(count,list,page);
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

    // 根据商品的id查找商品
    @Override
    public Product findByPId(int npid) {
        try {
            return  proudctDao.findByPid(npid);
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

    /*==========管理员模块============*/
    // 连表查询展示全部商品
    @Override
    public List<Goods> findAllGoods() {

        try {
            return proudctDao.findAllGoods();
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
    // 添加商品
    @Override
    public int addProduct(Product product) {
        try {
            return proudctDao.addProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    // 修改商品
    @Override
    public boolean update(Product product) {
        try {
            int update = proudctDao.update(product);
            System.out.println(update);

            return  update> 0;
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

    // 删除商品
    @Override
    public boolean delete(int npid) {
        try {
            return proudctDao.delete(npid)>0;
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

    // 查询指定商品
    @Override
    public List<Goods> findByNameTime(String pname, String parse) {
        try {
            return proudctDao.findByNameTime(pname,parse);
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
