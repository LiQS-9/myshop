package controller;

import com.alibaba.fastjson.JSON;
import entity.ResultData;
import entity.Type;
import service.TypeService;
import service.impl.TypeServiceImpl;
import utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/type.do")
public class TypeServlet extends BaseServlet {
    private TypeService typeService = new TypeServiceImpl();
    public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Type> list =  typeService.findAll();

        response.setContentType("application/json;charset=utf-8");
        ResultData res = null;
        if(list != null){
            res = ResultData.successful(list);
        }else {
            res = ResultData.failure(Constants.TYPE_NOT_FOUND_CODE,"未找到类型数据");
        }
        response.getWriter().write(JSON.toJSONString(res));

    }
}