package dao.impl;

import dao.AddressDao;
import entity.Address;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DBConnection;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-23 12:57
 **/
public class AddressDaoImpl implements AddressDao {
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public List<Address> findAllById(int uid) throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "address.a_id AS aid,\n" +
                        "address.u_id AS uid,\n" +
                        "address.a_name AS aname,\n" +
                        "address.a_phone AS aphone,\n" +
                        "address.a_detail AS adetail,\n" +
                        "address.a_state AS astate\n" +
                        "FROM\n" +
                        "address where u_id = ?\n",
                new BeanListHandler<Address>(Address.class),
                uid);
    }

    // 删除地址
    @Override
    public int delete(int naid) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
                "delete from address where a_id = ?",
                naid);
    }

    // 添加地址
    @Override
    public int add(String aname, String aphone, String adetail, Integer uid) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
                "insert into address(a_name,a_phone,a_detail,a_state,u_id) values(?,?,?,?,?)",
                aname,aphone,adetail,0,uid);
    }

    // 设默认
    @Override
    public int setDefault(int aid) throws SQLException {

        // 先设默认
        long i = queryRunner.update(DBConnection.getConnection(),
                "update address set a_state =? where a_id =?",
                        1,aid);
        // 再把其他设置成不是默认的
        long m = queryRunner.update(DBConnection.getConnection(),
                        "update address set a_state=? where a_id !=?",0,aid);
        if(i+m>=1){
            return 1;
        }else {
            return 0;
        }

    }

    // 修改地址
    @Override
    public int update(Address address) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
                "update address set a_name=?,a_phone=?,a_detail=? where a_id =?",
                address.getAname(),address.getAphone(),address.getAdetail(),address.getAid());
    }
}
