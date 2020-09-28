package controller.adminTODO;

import controller.BaseServlet;
import entity.Order;
import entity.User;
import service.AdminService;
import service.OrderService;
import service.impl.AdminServiceImpl;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin.do")
public class AdminServlet extends BaseServlet {
   private AdminService adminService = new AdminServiceImpl();
   private OrderService orderService = new OrderServiceImpl();
    /**
     * 管理员登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
   public void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String username = request.getParameter("username");
       String password = request.getParameter("password");

       User admin = adminService.findAdmin(username,password);
       if(admin != null){
           request.getSession().setAttribute("admin",admin);
           request.getRequestDispatcher("admin/admin.jsp").forward(request,response);
       }else {
           response.sendRedirect("admin/login.jsp");
       }
   }
   // 管理员退出
   public void logOut(HttpServletRequest request,HttpServletResponse response) throws IOException {
       request.getSession().invalidate();
       response.sendRedirect("admin/login.jsp");
   }
   // 查询全部订单
    public void showAOrder(HttpServletRequest request,HttpServletResponse response) throws IOException {
      List<Order> list =  orderService.showAllOrder();
      request.getSession().setAttribute("orderList",list);
      response.sendRedirect("admin/showAllOrder.jsp");
    }
    // 管理员查询指定的订单信息
    public void search(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String uname = request.getParameter("uname");
        String ostate = request.getParameter("ostate");
        List<Order> list =  orderService.findByNameState(uname,ostate);
        request.getSession().setAttribute("orderList",list);
        response.sendRedirect("admin/showAllOrder.jsp");

    }
}