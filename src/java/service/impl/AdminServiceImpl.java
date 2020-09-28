package service.impl;

import dao.AdminDao;
import dao.impl.AdminDaoImpl;
import entity.User;
import service.AdminService;
import utils.DBConnection;

import java.sql.SQLException;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-21 16:28
 **/
public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    // 管理员登录
    @Override
    public User findAdmin(String username, String password) {
        try {
            return adminDao.findAdmin(username,password);
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
