package controller;

import com.alibaba.fastjson.JSON;
import entity.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.Base64Utils;
import utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/user.do")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    // 检查用户名是否存在
   public void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
       String username = request.getParameter("username");
       boolean b = userService.check(username);
       response.setContentType("application/json;charset=utf-8");
       if(b){
           response.getWriter().write(JSON.toJSONString(1));
       }else {
           response.getWriter().write(JSON.toJSONString(0));
       }
   }
   // 用户注册
    public void register(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String upassword = request.getParameter("upassword");
        String email = request.getParameter("email");
        String usex = request.getParameter("usex");

        User user = new User();
        user.setUemail(email);
        user.setUname(username);
        user.setUpassword(upassword);
        user.setUsex(usex);

        int i = userService.addUser(user);

        if(i<0){
            request.setAttribute("registerMsg","注册失败");
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }else {

            response.sendRedirect("registerSuccess.jsp");
        }

    }
    // 用户激活
    public void active(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String c = request.getParameter("c");
        System.out.println(c);
        String decode = Base64Utils.decode(c);

        // 开始激活
        int i = userService.activeUser(decode);

        if(i== Constants.ACTIVE_SUCCESS){
            request.setAttribute("msg","激活成功");
        }else if(i==Constants.ACTIVE_FAIL){
            request.setAttribute("msg","激活失败");
        }
        request.getRequestDispatcher("message.jsp").forward(request,response);

    }
    // 用户登录
    public void login(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");

        String vcode = (String) request.getSession().getAttribute("code");
        User user = null;
        if (vcode.equalsIgnoreCase(code)){
            // 验证码验证成功
            user = userService.login(username,password);
            if (user!=null){
                // 先判断该用户是否激活
                if(user.getUstatus()==Constants.ACTIVE_SUCCESS){
                    // 激活了
                    String auto = request.getParameter("auto");
                    if(auto!=null){
                        // 需要自动登录
                        //编码是为了在cookie中存储汉字
                        String usernameCode = URLEncoder.encode(username, "utf-8");

                        Cookie usernameCookie = new Cookie("username",usernameCode);
                        Cookie passwordCookie = new Cookie("password",password);
                        //设置持久化时间
                        usernameCookie.setMaxAge(60*60);
                        passwordCookie.setMaxAge(60*60);
                        //设置cookie携带路径
                        usernameCookie.setPath(request.getContextPath());
                        passwordCookie.setPath(request.getContextPath());
                        //发送cookie
                        response.addCookie(usernameCookie);
                        response.addCookie(passwordCookie);

                    }
                    request.getSession().setAttribute("username", username);
                    request.getSession().setAttribute("password", password);
                    request.getSession().setAttribute("loginUser",user);

                    response.sendRedirect("index.jsp");
                }else if(user.getUstatus()==Constants.ACTIVE_FAIL){
                    // 没有激活
                    request.setAttribute("msg","没有激活");
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                }

            }else {
                request.setAttribute("msg","登录失败");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        }else {
            request.setAttribute("msg","验证码错误");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    }

    // 用户退出登录
    public void logOut(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        // 清除cookie
        Cookie cookie1 = new Cookie("username",null);
        Cookie cookie2 = new Cookie("password",null);
        cookie1.setMaxAge(0);//设置存活时间，“0”即马上消失
//        cookie.setPath("/");
        response.addCookie(cookie1);
        cookie2.setMaxAge(0);
        response.addCookie(cookie2);
        response.sendRedirect("index.jsp");
    }

}