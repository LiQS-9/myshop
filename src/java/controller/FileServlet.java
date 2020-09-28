package controller;

import utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/show.do")
public class FileServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取photo文件名字
        String name = request.getParameter("name");
        // 创建文件输入流 读取上传的文件 文件的位置是本来的文件夹路径+照片名字（从数据库读取的名字）
        FileInputStream inputStream = new FileInputStream(new File(Constants.FILE_BASE_PATH + name));
        // 上传时候做了拼接所以读取的时候要先切割
        String[] strings = name.split("_");
        // 先判断是否拼接
        if (strings.length > 1){
            response.setHeader("content-disposition", "attachment;filename=" + URLDecoder.decode(strings[1], "utf-8"));
        }else {
            // 没有拼接的就不管了
            response.setHeader("content-disposition", "attachment;filename=123");
        }
        // 文件预览 先创建输出流 边读边写
        ServletOutputStream outputStream = response.getOutputStream();
        byte [] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1){
            outputStream.write(buffer, 0, len);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
