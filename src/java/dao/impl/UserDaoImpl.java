package dao.impl;

import dao.UserDao;
import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DBConnection;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-21 16:50
 **/
public class UserDaoImpl implements UserDao {
    private QueryRunner queryRunner = new QueryRunner();
    /*=========管理员模块==============*/
    // 查询全部已经激活的会员信息
    @Override
    public List<User> findAll() throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "`user`.u_id AS uid,\n" +
                        "`user`.u_name AS uname,\n" +
                        "`user`.u_password AS upassword,\n" +
                        "`user`.u_email AS uemail,\n" +
                        "`user`.u_sex AS usex,\n" +
                        "`user`.u_status AS ustatus,\n" +
                        "`user`.u_code AS ucode,\n" +
                        "`user`.u_role AS urole\n" +
                        "FROM\n" +
                        "`user` where u_role=? and u_status=?",
                new BeanListHandler<User>(User.class),1,1);
    }

    // 删除用户
    @Override
    public int delUser(int nid) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
                "delete from user where u_id = ?",nid);
    }

    // 模糊查询
    @Override
    public List<User> findByNameSex(String username, String gender) throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "`user`.u_id AS uid,\n" +
                        "`user`.u_name AS uname,\n" +
                        "`user`.u_password AS upassword,\n" +
                        "`user`.u_email AS uemail,\n" +
                        "`user`.u_sex AS usex,\n" +
                        "`user`.u_status AS ustatus,\n" +
                        "`user`.u_code AS ucode,\n" +
                        "`user`.u_role AS urole\n" +
                        "FROM\n" +
                        "`user` where u_name= ? and u_sex= ?",
                new BeanListHandler<User>(User.class),username,gender);
    }

    // 查询没有激活的会员
    @Override
    public List<User> findAllInvalid() throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "`user`.u_id AS uid,\n" +
                        "`user`.u_name AS uname,\n" +
                        "`user`.u_password AS upassword,\n" +
                        "`user`.u_email AS uemail,\n" +
                        "`user`.u_sex AS usex,\n" +
                        "`user`.u_status AS ustatus,\n" +
                        "`user`.u_code AS ucode,\n" +
                        "`user`.u_role AS urole\n" +
                        "FROM\n" +
                        "`user` where u_role=? and u_status=?",
                new BeanListHandler<User>(User.class),1,0);
    }
    /*=============用户模块===========*/
    @Override
    public User check(String username) throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "`user`.u_id AS uid,\n" +
                        "`user`.u_name AS uname,\n" +
                        "`user`.u_password AS upassword,\n" +
                        "`user`.u_email AS uemail,\n" +
                        "`user`.u_sex AS usex,\n" +
                        "`user`.u_status AS ustatus,\n" +
                        "`user`.u_code AS ucode,\n" +
                        "`user`.u_role AS urole\n" +
                        "FROM\n" +
                        "`user` where u_name= ? and u_role= ?",
                new BeanHandler<User>(User.class),username,1);
    }

    // 用户注册
    @Override
    public int addUser(User user) throws SQLException {

        return queryRunner.update(DBConnection.getConnection(),
                "insert into user(u_name,u_password,u_email,u_sex,u_status,u_code,u_role) values(?,?,?,?,?,?,?)",
                user.getUname(),user.getUpassword(),user.getUemail(),user.getUsex(),user.getUstatus(),user.getUcode(),user.getUrole());
    }
    // 用户激活
    @Override
    public int activeUser(String decode) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
                "update user set u_status = ? where u_code = ?",
                1,decode);
    }
    // 用户登录
    @Override
    public User login(String username, String password) throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "`user`.u_id AS uid,\n" +
                        "`user`.u_name AS uname,\n" +
                        "`user`.u_password AS upassword,\n" +
                        "`user`.u_email AS uemail,\n" +
                        "`user`.u_sex AS usex,\n" +
                        "`user`.u_status AS ustatus,\n" +
                        "`user`.u_code AS ucode,\n" +
                        "`user`.u_role AS urole\n" +
                        "FROM\n" +
                        "`user` where u_name= ? and u_password=? and u_role= ?",
                new BeanHandler<User>(User.class),username,password,1);
    }

}
