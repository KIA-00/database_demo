package com.itheima.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 替换HttpServlet,根据请求的方法,调用对应的方法
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求路径
        String uri = req.getRequestURI();//brand-case/brand/selectAll
        //2.获取最后一个/的位置，方法名
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);

        //3.获取当前类的字节码对象
        Class<? extends BaseServlet> cls = this.getClass();
        try {
            //4.获取方法对象
            java.lang.reflect.Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //5.执行方法
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
