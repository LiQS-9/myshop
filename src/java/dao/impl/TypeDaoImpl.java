package dao.impl;

import dao.TypeDao;
import entity.Type;
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
 * @create: 2020-09-21 14:28
 **/
public class TypeDaoImpl implements TypeDao {
    private QueryRunner queryRunner = new QueryRunner();
    // 查询全部商品类型
    @Override
    public List<Type> findAll() throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "type.t_id AS tid,\n" +
                        "type.t_name AS tname,\n" +
                        "type.t_info AS tinfo\n" +
                        "FROM\n" +
                        "type\n" ,
                new BeanListHandler<Type>(Type.class));
    }

    // 添加商品类型
    @Override
    public int addType(Type type) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
                "insert into type(t_name,t_info) values(?,?)",
                type.getTname(),type.getTinfo());
    }

    @Override
    public Type findById(int ntid) throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "type.t_id AS tid,\n" +
                        "type.t_name AS tname,\n" +
                        "type.t_info AS tinfo\n" +
                        "FROM\n" +
                        "type where t_id=?\n" ,
                new BeanHandler<Type>(Type.class),ntid);
    }

    // 修改商品类型
    @Override
    public int update(Type type) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
                "update type set t_name=?,t_info=? where t_id=?",
                type.getTname(),type.getTinfo(),type.getTid());
    }

    // 删除类型
    @Override
    public int delete(int ntid) throws SQLException {
        return queryRunner.update(DBConnection.getConnection(),
                "delete from type where t_id= ?",
                ntid);
    }

    // 查询指定类型
    @Override
    public List<Type> findByName(String tname) throws SQLException {
        return queryRunner.query(DBConnection.getConnection(),
                "SELECT\n" +
                        "type.t_id AS tid,\n" +
                        "type.t_name AS tname,\n" +
                        "type.t_info AS tinfo\n" +
                        "FROM\n" +
                        "type where t_name=?\n" ,
                 new BeanListHandler<Type>(Type.class),tname);
    }
}
