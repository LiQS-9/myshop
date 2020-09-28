package controller.adminTODO;

import com.alibaba.fastjson.JSON;
import controller.BaseServlet;
import entity.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/usertodo.do")
public class UserToServlet extends BaseServlet {
   private UserService userService = new UserServiceImpl();

   // 查询全部会员信息
   public void getUserList(HttpServletRequest request, HttpServletResponse response) throws IOException {
       List<User> list = userService.findAll();
       response.setContentType("application/json;charset=utf-8");
       response.getWriter().write(JSON.toJSONString(list));
   }
   // 查询全部没有激活的会员
    public void getInvalidUserList(HttpServletRequest request,HttpServletResponse response) throws IOException {
       List<User> list = userService.findAllInvalid();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(list));

    }
   // 删除会员
   public void delUser(HttpServletRequest request,HttpServletResponse response){

       String id = request.getParameter("id");
       int nid = Integer.parseInt(id);
       int i = userService.delUser(nid);
       if (i>0){
           System.out.println("删除成功");
       }
   }
   // 查询会员
   public void searchUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
       String username = request.getParameter("username");
       String gender = request.getParameter("gender");

       List<User> list = userService.findByNameSex(username,gender);
       response.setContentType("application/json;charset=utf-8");
       response.getWriter().write(JSON.toJSONString(list));

   }
}