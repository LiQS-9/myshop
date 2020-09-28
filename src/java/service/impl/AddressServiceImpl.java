package service.impl;

import dao.AddressDao;
import dao.impl.AddressDaoImpl;
import entity.Address;
import service.AddressService;
import utils.DBConnection;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-23 09:53
 **/
public class AddressServiceImpl implements AddressService {

    private AddressDao addressDao = new AddressDaoImpl();
    // 根据UId查找该用户的全部地址
    @Override
    public List<Address> findAllByUId(int uid) {
        try {
            return addressDao.findAllById(uid);
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

    // 删除地址
    @Override
    public boolean delete(int naid) {
        try {
            return addressDao.delete(naid)>0;
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

    // 添加地址
    @Override
    public boolean add(String aname, String aphone, String adetail, Integer uid) {
        try {
            return addressDao.add(aname,aphone,adetail,uid) >0;
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

    // 设为默认
    @Override
    public boolean setDefault(int aid) {
        try {
            return addressDao.setDefault(aid)>0;
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

    // 修改
    @Override
    public boolean update(Address address) {
        try {
            return addressDao.update(address)>0;
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
