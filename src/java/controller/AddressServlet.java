package controller;

import entity.Address;
import entity.User;
import service.AddressService;
import service.impl.AddressServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/address.do")
public class AddressServlet extends BaseServlet {
    private AddressService addressSevice = new AddressServiceImpl();
    // 用户查看自己的地址
    public void showAddressList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer uid = loginUser.getUid();

        List<Address> addressList = addressSevice.findAllByUId(uid);
        request.getSession().setAttribute("addressList",addressList);
        response.sendRedirect("self_info.jsp");
    }
    // 删除地址
    public void delete(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String aid = request.getParameter("aid");
        int naid = Integer.parseInt(aid);

        boolean del = addressSevice.delete(naid);
        response.sendRedirect("address.do?method=showAddressList");
    }
    // 添加地址
    public void add(HttpServletRequest request,HttpServletResponse response) throws IOException {

        String aname = request.getParameter("aname");
        String aphone = request.getParameter("aphone");
        String adetail = request.getParameter("adetail");
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer uid = loginUser.getUid();

        boolean add =   addressSevice.add(aname,aphone,adetail,uid);
        response.sendRedirect("address.do?method=showAddressList");
    }
    // 修改地址
    public void update(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String aid = request.getParameter("aid");
//        String astate = request.getParameter("astate");
        String aname = request.getParameter("aname");
        String aphone = request.getParameter("aphone");
        String adetail = request.getParameter("adetail");
        Address address = new Address();
        address.setAid(Integer.parseInt(aid));
        address.setAname(aname);
        address.setAphone(aphone);
//        address.setAstate(Integer.parseInt(astate));
        address.setAdetail(adetail);
        boolean update = addressSevice.update(address);
        response.sendRedirect("address.do?method=showAddressList");
    }
    // 设为默认
    public void setDefault(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String aid = request.getParameter("aid");
        int naid = Integer.parseInt(aid);

       boolean set =  addressSevice.setDefault(naid);
        response.sendRedirect("address.do?method=showAddressList");
    }
}