package test;

import dao.impl.AdminDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.TypeDaoImpl;
import entity.*;
import service.impl.*;
import utils.DBConnection;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-18 14:25
 **/
public class Test {
    @org.junit.Test
    public void test01() throws SQLException {
        System.out.println(DBConnection.getConnection());
    }
    @org.junit.Test
    public void test02() throws SQLException {
        TypeDaoImpl dao = new TypeDaoImpl();
        System.out.println(dao.findAll());

    }
    @org.junit.Test
    public void test03() throws SQLException {
        ProductServiceImpl productService = new ProductServiceImpl();
        PageBean pageBean = productService.findAllById(1,1);
        System.out.println(pageBean);

    }
    @org.junit.Test
    public void test04() throws SQLException {
        ProductServiceImpl productService = new ProductServiceImpl();
        System.out.println(productService.findByPId(21));


    }
    @org.junit.Test
    public void test05() throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        System.out.println(userService.findAll());

    }
    @org.junit.Test
    public void test06() throws SQLException {
        AdminDaoImpl adminDao = new AdminDaoImpl();

        System.out.println(adminDao.findAdmin("zhangsan","123456"));

    }

    @org.junit.Test
    public void test07() throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        System.out.println(userService.delUser(4));

    }
    @org.junit.Test
    public void test08() throws SQLException {
        ProductServiceImpl productService = new ProductServiceImpl();
        System.out.println(productService.findAllGoods());
    }
    @org.junit.Test
    public void test09() throws SQLException {
        ProductServiceImpl productService = new ProductServiceImpl();
        Product product = new Product();
        product.setPname("123");
        product.setPimage("wrq");
        product.setTid(1);
        System.out.println(productService.addProduct(product));
    }
    @org.junit.Test
    public void test10(){
        UserServiceImpl userService = new UserServiceImpl();
        boolean zhangsan = userService.check("zhangsan");
        System.out.println(zhangsan);
    }
    @org.junit.Test
    public void test11(){
        UserServiceImpl userService = new UserServiceImpl();
        User user = new User();
        user.setUname("wangwu");
        user.setUsex("男");
        user.setUpassword("123");
        user.setUemail("2729179798@qq.com");
        System.out.println(userService.addUser(user));
    }
    @org.junit.Test
    public void test12(){
        UserServiceImpl userService = new UserServiceImpl();
        System.out.println(userService.login("123", "123456"));

    }
    @org.junit.Test
    public void test13(){
        UserServiceImpl userService = new UserServiceImpl();
        System.out.println(userService.findAllInvalid());
    }
    @org.junit.Test
    public void test14() throws SQLException {
        TypeDaoImpl typeDao = new TypeDaoImpl();
        System.out.println(typeDao.findById(1));

    }
    @org.junit.Test
    public void test15() throws SQLException {
        TypeDaoImpl typeDao = new TypeDaoImpl();
        Type type = new Type(3, "121", "21");
        typeDao.update(type);
    }
    @org.junit.Test
    public void test16(){
        CarServiceImpl carService = new CarServiceImpl();
        System.out.println(carService.findAll(10));
    }
    @org.junit.Test
    public void test17(){
        CarServiceImpl carService = new CarServiceImpl();
        boolean update = carService.update(1, 3, 1999);
    }
    @org.junit.Test
    public void test18(){
        CarServiceImpl carService = new CarServiceImpl();
        boolean clear = carService.clear(10);
        System.out.println(clear);
    }
    @org.junit.Test
    public void test19(){
        AddressServiceImpl addressService = new AddressServiceImpl();
        List<Address> allByUId = addressService.findAllByUId(3);
        System.out.println(allByUId);
    }
    @org.junit.Test
    public void test20() throws SQLException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        System.out.println(orderDao.findMyOrder(10));
    }
    @org.junit.Test
    public void test21(){
        ItemServicesImpl itemServices = new ItemServicesImpl();
        List<Item> allByOid = itemServices.findAllByOid("20200923164554905");
        System.out.println(allByOid);
    }
    @org.junit.Test
    public void test22() throws SQLException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
//        System.out.println(orderDao.upState("20200924100943580"));
        System.out.println(orderDao.findAllOrder().size());
//        System.out.println(orderDao.findById("20200923164554905"));
    }
    @org.junit.Test
    public void test23(){
        AddressServiceImpl addressService = new AddressServiceImpl();
//        System.out.println(addressService.delete(4));
//        System.out.println(addressService.setDefault(2));
//        String aid = request.getParameter("aid");
//        String astate = request.getParameter("astate");
//        String aname = request.getParameter("aname");
//        String aphone = request.getParameter("aphone");
//        String adetail = request.getParameter("adetail");
//        addressService.update()
//        System.out.println(addressService.add("hahah", "13247228394", "湖北黄石", 10));
    }
    @org.junit.Test
    public void test24() throws ParseException {
        String str = "1999-01-09";
        Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(str);
        System.out.println(parse);
    }
}
