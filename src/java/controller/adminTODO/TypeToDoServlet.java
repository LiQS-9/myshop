package controller.adminTODO;

import com.alibaba.fastjson.JSON;
import controller.BaseServlet;
import entity.Type;
import service.TypeService;
import service.impl.TypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/typetodo.do")
public class TypeToDoServlet extends BaseServlet {
   private TypeService typeService = new TypeServiceImpl();
   // 类型展示
   public void getGoodsType(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       List<Type> all = typeService.findAll();
       request.setAttribute("goodsTypeList",all);
       request.getRequestDispatcher("admin/showGoodsType.jsp").forward(request,response);
   }

   // 添加类型
   public void addGoodsType(HttpServletRequest request,HttpServletResponse response) throws IOException {
       Type type = new Type();
       type.setTname(request.getParameter("typename"));
       type.setTinfo(request.getParameter("typeinfo"));
       int i = typeService.addType(type);
       response.sendRedirect("typetodo.do?method=getGoodsType");
   }
   // 预修改类型
    public void preupdateGoodType(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String tid = request.getParameter("tid");
        int ntid = Integer.parseInt(tid);
        Type type = typeService.findById(ntid);
        request.setAttribute("type",type);
        request.getRequestDispatcher("admin/updateGoodsType.jsp").forward(request,response);
    }
    // 修改类型
    public void updateGoodsType(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String tid = request.getParameter("tid");

        int ntid = Integer.parseInt(tid);
        Type type = new Type(ntid,request.getParameter("typename"),request.getParameter("typeinfo"));

        int i = typeService.update(type);
        if(i>0){
            response.sendRedirect("typetodo.do?method=getGoodsType");
        }
    }
    // 删除类型
    public void delete(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String tid = request.getParameter("tid");
        int ntid = Integer.parseInt(tid);
        boolean b = typeService.delete(ntid);
        response.setContentType("application/json;charset=utf-8");
        if (b){
            response.getWriter().write(JSON.toJSONString(1));
        }else {
            response.getWriter().write(JSON.toJSONString(0));
        }
    }
    // 查找类型
    public void search(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException {
        String tname = request.getParameter("tname");
        List<Type> list = typeService.findByName(tname);
        request.setAttribute("goodsTypeList",list);
        request.getRequestDispatcher("admin/showGoodsType.jsp").forward(request,response);

    }
}