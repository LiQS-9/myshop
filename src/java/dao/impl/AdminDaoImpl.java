package dao.impl;

import dao.AdminDao;
import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.DBConnection;

import java.sql.SQLException;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-21 16:29
 **/
public class AdminDaoImpl implements AdminDao {
    private QueryRunner queryRunner = new QueryRunner();
    @Override
    public User findAdmin(String username, String password) throws SQLException {
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
                        "`user` where u_name=? and u_password =? and u_role = ? ",
                new BeanHandler<User>(User.class),username,password,0);

    }
}
