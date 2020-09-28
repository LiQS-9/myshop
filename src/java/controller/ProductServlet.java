package controller;

import entity.PageBean;
import entity.Product;
import service.ProductSevice;
import service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product.do")
public class ProductServlet extends BaseServlet {

    private ProductSevice productSevice = new ProductServiceImpl();

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tid = request.getParameter("tid");
        int ntid = Integer.parseInt(tid);
        String page = request.getParameter("currentPage");
        if (page == null || page.equals("")){
            page = "1";
        }
        System.out.println(page);
        int npage = Integer.parseInt(page);

//        if (npage <= 0){
//            npage = 1;
//        }
        PageBean list = productSevice.findAllById(ntid,npage);

        request.setAttribute("pageBean",list);
        request.getRequestDispatcher("goodsList.jsp").forward(request,response);

    }

    public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        int npid = Integer.parseInt(pid);

        Product product = productSevice.findByPId(npid);
        request.setAttribute("product",product);
        request.getRequestDispatcher("goodsDetail.jsp").forward(request,response);

    }

}