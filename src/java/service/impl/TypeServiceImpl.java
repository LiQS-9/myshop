package service.impl;

import dao.TypeDao;
import dao.impl.TypeDaoImpl;
import entity.Type;
import service.TypeService;
import utils.DBConnection;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-21 14:27
 **/
public class TypeServiceImpl implements TypeService {
    private TypeDao typeDao = new TypeDaoImpl();
    /*=========用户模块=======*/
    // 展示全部类型
    @Override
    public List<Type> findAll() {
        try {
            return typeDao.findAll();
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

    // 添加商品类型
    @Override
    public int addType(Type type) {
        try {
            return typeDao.addType(type);
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

    // 查询单个商品类型
    @Override
    public Type findById(int ntid) {
        try {
            return typeDao.findById(ntid);
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

    // 修改商品类型
    @Override
    public int update(Type type) {
        try {
            return typeDao.update(type);
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

    // 删除类型
    @Override
    public boolean delete(int ntid) {
        try {
            return typeDao.delete(ntid)>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Type> findByName(String tname) {
        try {
            return typeDao.findByName(tname);
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
