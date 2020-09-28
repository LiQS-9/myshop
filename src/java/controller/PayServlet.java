package controller;

import com.alibaba.fastjson.JSON;
import entity.Weixin;
import service.OrderService;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pay.do")
public class PayServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();
    public void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String result = request.getParameter("result");
        System.out.println(result);
        // 回调
        Weixin weixin = JSON.parseObject(result,Weixin.class);

        // 修改订单状态
        boolean b = orderService.upState(request.getParameter("oid"));
        // 跳转paySuccess.jsp
        request.getRequestDispatcher("paySuccess.jsp").forward(request,response);
    }
}