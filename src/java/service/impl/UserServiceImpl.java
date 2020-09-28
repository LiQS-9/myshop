package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import service.UserService;
import utils.DBConnection;
import utils.EmailUtils;
import utils.MD5Utils;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-21 16:44
 **/
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    /*=========管理员模块===========*/
    // 查询全部激活会员信息
    @Override
    public List<User> findAll() {
        try {
            return userDao.findAll();
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
    // 删除用户会员
    @Override
    public int delUser(int nid) {
        try {
            return userDao.delUser(nid);
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

   // 模糊查询用户信息
    @Override
    public List<User> findByNameSex(String username, String gender) {
        try {
            return userDao.findByNameSex(username,gender);
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
    // 查询没有 激活的会员
    @Override
    public List<User> findAllInvalid() {
        try {
            return userDao.findAllInvalid();
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

    /*===========用户模块============*/
    // 查询用户名是否存在
    @Override
    public boolean check(String username) {
        User user = null;
        try {
            user = userDao.check(username);
            if(user != null){
                return true;
            }
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
    // 用户注册
    @Override
    public int addUser(User user) {
        // 给用户密码加密并存贮
        user.setUpassword(MD5Utils.md5(user.getUpassword()));
        // 为默认字段赋值
        user.setUcode(UUID.randomUUID().toString().replace("-",""));
        user.setUstatus(0);// 0为没有激活
        user.setUrole(1);//1为会员
        System.out.println(user);
        // 发送邮箱
        EmailUtils.sendEmail(user);
        // 添加用户
        try {
            return userDao.addUser(user);
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
    // 用户激活
    @Override
    public int activeUser(String decode) {
        try {
            return userDao.activeUser(decode);
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
    // 用户登录
    @Override
    public User login(String username, String password) {
        String md5 = MD5Utils.md5(password);
        try {

            return userDao.login(username,md5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
