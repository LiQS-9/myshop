package filter;

import entity.User;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

//@WebFilter("/user.do?method=login")
public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 获得cookie中用户名和密码 进行登录的操作
        // 定义cookie_username
        String cookie_username = null;
        // 定义cookie_password
        String cookie_password = null;
        // 获得cookie 要对request转型
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // 获得名字是cookie_username和cookie_password
                if ("username".equals(cookie.getName())) {
                    cookie_username = cookie.getValue();
                    // 对cookie中的值解码
                    cookie_username = URLDecoder.decode(cookie_username,
                            "UTF-8");
                }
                if ("password".equals(cookie.getName())) {
                    cookie_password = cookie.getValue();
                }
            }
        }

        if (cookie_username != null && cookie_password != null) {
            UserServiceImpl userService = new UserServiceImpl();
            User login = userService.login(cookie_username, cookie_password);

            if (login!=null) {
//                System.out.println("自动登录实现");
                session.setAttribute("username", cookie_username);
                session.setAttribute("password", cookie_password);
                request.getSession().setAttribute("loginUser",login);
            }


        }
        chain.doFilter(request, resp);
    }


    public void init(FilterConfig config) throws ServletException {

    }

}
