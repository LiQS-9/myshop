package controller;

import entity.*;
import service.AddressService;
import service.CartService;
import service.ItemService;
import service.OrderService;
import service.impl.AddressServiceImpl;
import service.impl.CarServiceImpl;
import service.impl.ItemServicesImpl;
import service.impl.OrderServiceImpl;
import utils.RandomUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order.do")
public class OrderServlet extends BaseServlet {
   private OrderService orderService = new OrderServiceImpl();
   private AddressService addressService = new AddressServiceImpl();
   private CartService cartService = new CarServiceImpl();
   private ItemService itemService = new ItemServicesImpl();
   // 预生成 需要先查询地址 以及订单信息
    public void preView(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uid = request.getParameter("uid");
        int nuid = Integer.parseInt(uid);

        List<Address> address = addressService.findAllByUId(nuid);
        request.getSession().setAttribute("addressList",address);

        List<Cart> cartList = cartService.findAll(nuid);
        request.getSession().setAttribute("cartList",cartList);

        // 购物车为空无法提交订单
        if(cartList.size()==0){
            request.setAttribute("msg","购物车为空无法下单！");
            request.getRequestDispatcher("cart.do?method=list").forward(request,response);
        }else {
            // 计算订单的总金额
            int sum = 0;
            for(Cart c :cartList){
                sum += c.getCcount();
            }

            request.setAttribute("sum",sum);
            request.getRequestDispatcher("order.jsp").forward(request,response);
        }

    }

    // 生成订单
    public void create(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        int nuid = Integer.parseInt(uid);
        String sum = request.getParameter("sum");
        double nsum = Double.parseDouble(sum);
        String aid = request.getParameter("aid");
        int naid = Integer.parseInt(aid);

        String orderId = RandomUtils.createOrderId();

        // 生成订单表
        boolean b = orderService.create(nuid,nsum,naid,orderId);
        // 生成订单商品表
        List<Cart> cartList = (List<Cart>) request.getSession().getAttribute("cartList");
        boolean addItem = itemService.add(cartList,orderId);
        if(b&&addItem){
//            System.out.println("提交成功");
            // 查询订单信息跳转订单成功页面
           Order order =  orderService.findOrderByOid(orderId);
           request.setAttribute("order",order);
           request.getRequestDispatcher("orderSuccess.jsp").forward(request,response);
//            response.sendRedirect();
        }else {
            // 订单支付失败
            response.getWriter().write("提交订单失败");
        }
    }
    // 我的订单
    public void showMineList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer uid = loginUser.getUid();
        List<Order> orders = null;
        if(uid == null){
            String puid = request.getParameter("uid");
            int nuid = Integer.parseInt(puid);
             orders = orderService.findMyOrder(nuid);

        }else {
             orders = orderService.findMyOrder(uid);
        }
        request.getSession().setAttribute("ordersList",orders);
        response.sendRedirect("orderList.jsp");

    }
    // 订单详情
    public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");

        // 查询adddress 以及order信息
        Order order = orderService.findOrderByOid(oid);
        request.setAttribute("order",order);
        // 查询order中的items
        List<Item> list = itemService.findAllByOid(oid);
        request.setAttribute("itemList",list);

        request.getRequestDispatcher("orderDetail.jsp").forward(request,response);
    }

}