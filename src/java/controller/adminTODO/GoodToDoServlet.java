package controller.adminTODO;

import com.alibaba.fastjson.JSON;
import controller.BaseServlet;
import entity.Goods;
import entity.Product;
import entity.ResultData;
import entity.Type;
import service.ProductSevice;
import service.TypeService;
import service.impl.ProductServiceImpl;
import service.impl.TypeServiceImpl;
import utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@MultipartConfig
@WebServlet("/goodtodo.do")
public class GoodToDoServlet extends BaseServlet {
   private ProductSevice productSevice = new ProductServiceImpl();
   private TypeService typeService = new TypeServiceImpl();

   // 展示商品列表
   public void getGoodsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      List<Goods> list = productSevice.findAllGoods();
      request.setAttribute("goodsList",list);
       ResultData resultData = null;
       if(list != null){
           resultData = ResultData.successMsg("0","管理员查询商品列表成功");
       }else {
           resultData = ResultData.failure("1","管理员查询商品列表失败");
       }
       request.getSession().setAttribute("resultMsg",resultData);
      request.getRequestDispatcher("admin/showGoods.jsp").forward(request,response);
   }

   // 预添加
    public void preAdd(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        // 查找所有类型
        List<Type> all = typeService.findAll();
        request.setAttribute("typeList",all);
        request.getRequestDispatcher("admin/addGoods.jsp").forward(request,response);
    }
    /**
     * 因为不需要连表所以添加商品用的product类
     * @param request
     * @param response
     * @throws ParseException
     */
   public void addGood(HttpServletRequest request,HttpServletResponse response) throws ParseException, IOException, ServletException {
       Product product = new Product();
       product.setPname(request.getParameter("name"));
       product.setPinfo(request.getParameter("intro"));

       String price = request.getParameter("price");
       if( price == null|| price.equals("")){
           price = "0";
       }
       product.setPprice(Double.parseDouble(price));
       String star = request.getParameter("star");
       if(star == null || star.equals("")){
           star = "0";
       }
       product.setPstate(Integer.parseInt(star));//state
       String pubdate = request.getParameter("pubdate");
       if(pubdate == null || pubdate.equals("")){
           // 不传就默认现在的时间
           product.setPtime(new Date());
       }else {
           product.setPtime(new SimpleDateFormat("yyyy-MM-dd").parse(pubdate));
       }
       String typeid = request.getParameter("typeid");
       if(typeid == null || typeid.equals("")){
           // 默认手机
           typeid = "1";
       }
       product.setTid(Integer.parseInt(typeid));
       Part picture = request.getPart("picture");
       String oldFileName = picture.getSubmittedFileName();
       product.setPimage("image/"+oldFileName);
       // 写到指定位置 工程目录下
       picture.write(Constants.FILE_BASE_PATH  +"image/"+ oldFileName);
       // 调用service层添加
      int i =  productSevice.addProduct(product);

       ResultData resultData = null;
       if(i>0){
         resultData = ResultData.successMsg("0","管理员添加商品成功");
      }else {
          resultData = ResultData.failure("1","管理员添加商品失败");
      }
      request.getSession().setAttribute("resultMsg",resultData);
       response.sendRedirect("goodtodo.do?method=getGoodsList");

   }
   // 预修改商品
    public void preupdateGood(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        int npid = Integer.parseInt(pid);

        // 查找所有类型
        List<Type> all = typeService.findAll();
        request.setAttribute("typeList",all);

        Product b = productSevice.findByPId(npid);

        if(b!=null){
            request.setAttribute("g",b);
            request.getRequestDispatcher("admin/updateGoods.jsp").forward(request,response);
        }else {
            System.out.println("修改失败");
        }
    }

    // 修改商品
    public void updateGood(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException, ServletException {
        String pid = request.getParameter("pid");

        int npid = Integer.parseInt(pid);

        Product product = new Product();
        product.setPname(request.getParameter("name"));
        System.out.println(request.getParameter("name"));
        product.setPinfo(request.getParameter("intro"));//info
        product.setPid(npid);

        String price = request.getParameter("price");
        if( price == null|| price.equals("")){
            price = "0";
        }
        product.setPprice(Double.parseDouble(price));

        String star = request.getParameter("star");
        if(star == null || star.equals("")){
            star = "0";
        }
        product.setPstate(Integer.parseInt(star));//state
        String pubdate = request.getParameter("pubdate");
        if(pubdate == null || pubdate.equals("")){
            // 不传就默认现在的时间
            product.setPtime(new Date());
        }else {
            product.setPtime(new SimpleDateFormat("yyyy-MM-dd").parse(pubdate));
        }

        String typeid = request.getParameter("typeid");
        if(typeid == null || typeid.equals("")){
            // 默认手机
            typeid = "1";
        }
        product.setTid(Integer.parseInt(typeid));

        String oldFileName = null;
        Part picture = request.getPart("picture");
        if(picture.getSize() <= 0){
            Part oldPic = request.getPart("oldPic");
            oldFileName = oldPic.getSubmittedFileName();
        }else {
             oldFileName = picture.getSubmittedFileName();
        }
        product.setPimage("image/"+oldFileName);
        // 写到指定位置
        picture.write(Constants.FILE_BASE_PATH  +"image/"+ oldFileName);

        boolean b = productSevice.update(product);
        if(b){
            response.sendRedirect("goodtodo.do?method=getGoodsList");
        }else {
            System.out.println("修改失败");
        }
    }
    // 删除商品
    public void delete(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String pid = request.getParameter("pid");
        int npid = Integer.parseInt(pid);

        boolean b = productSevice.delete(npid);

        response.setContentType("application/json;charset=utf-8");
        if (b){
            response.getWriter().write(JSON.toJSONString(1));
        }else {
            response.getWriter().write(JSON.toJSONString(0));
        }

    }
    // 查询指定商品
    public void search(HttpServletRequest request,HttpServletResponse response) throws ParseException, ServletException, IOException {

        String pname = request.getParameter("pname");
        String ptime = request.getParameter("ptime");

        List<Goods> list = productSevice.findByNameTime(pname,ptime);

        request.setAttribute("goodsList",list);
        request.getRequestDispatcher("admin/showGoods.jsp").forward(request,response);

    }
}