package controller;

import entity.Cart;
import entity.User;
import service.CartService;
import service.impl.CarServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart.do")
public class CartSerlvet extends BaseServlet {

    private CartService cartService = new CarServiceImpl();

    // 查询购物列表
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
       int uid =  -1;
       if(loginUser!=null){
           uid = loginUser.getUid();
           List<Cart> list = cartService.findAll(uid);
           request.getSession().setAttribute("list",list);
           request.getRequestDispatcher("cart.jsp").forward(request,response);
       }else {

        String nuid = request.getParameter("uid");

         if(nuid == null || nuid.equals("")){
             response.sendRedirect("login.jsp");

         }else {
             uid = Integer.parseInt(nuid);
             List<Cart> list = cartService.findAll(uid);
             request.getSession().setAttribute("list",list);
             request.getRequestDispatcher("cart.jsp").forward(request,response);
         }
       }
    }
    // 购物车添加商品
    public void create(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String pid = request.getParameter("pid");
        int npid = Integer.parseInt(pid);
        User userLogin = (User) request.getSession().getAttribute("loginUser");
        int uid = userLogin.getUid();
        boolean b = cartService.create(npid,uid);

        if(b){
//            System.out.println("添加成功跳转到添加成功页面");
            response.sendRedirect("cartSuccess.jsp");
        }else {
            System.out.println("失败");
        }

    }
    // 删除一条购物车记录商品
    public void delete(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String cid = request.getParameter("cid");
        int ncid = Integer.parseInt(cid);

       boolean  b = cartService.delete(ncid);
       if(b){
           response.sendRedirect("cart.do?method=list");
       }else {
           System.out.println("删除失败");
       }

    }
    // 更新购物车记录
    public void update(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String cid = request.getParameter("cid");
        int ncid = Integer.parseInt(cid);
        String cnum = request.getParameter("cnum");
        int ncum = Integer.parseInt(cnum);
        String price = request.getParameter("price");
        double nprice = Double.parseDouble(price);

        boolean b = cartService.update(ncid,ncum,nprice);

        if(b){

//            System.out.println("当前用户为"+request.getSession().getAttribute("loginUser"));
//            User loginUser = (User) request.getSession().getAttribute("loginUser");
//
//            List<Cart> all = cartService.findAll(loginUser.getUid());
//            request.getSession().setAttribute("list",all);

//            response.setContentType("application/json;charset=utf-8");
//            response.getWriter().write(JSON.toJSONString(all));


            response.sendRedirect("cart.do?method=list");
        }else {
//            System.out.println("修改失败");
            request.getRequestDispatcher("cart.jsp").forward(request,response);
        }
    }
    // 删除购物车全部商品
    public void clear(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        int uid = loginUser.getUid();

        // 先查询购物车内容
        List<Cart> cartList = cartService.findAll(uid);
        request.getSession().setAttribute("cartList",cartList);

        // 购物车为空无法清空
        if(cartList.size()==0) {
            request.setAttribute("msg", "购物车为空无法清空！");
            request.getRequestDispatcher("cart.do?method=list").forward(request, response);
        }else {
            boolean b = cartService.clear(uid);

            if(b){

                response.sendRedirect("cart.do?method=list");
            }else {
                System.out.println("删除失败");
            }
        }

    }
}