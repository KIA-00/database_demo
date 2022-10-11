package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService=new BrandServiceImpl();
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service层的方法
        List<Brand> brands = brandService.selectAll();
        //2.转为json
        String jsonString = JSON.toJSONString(brands);
        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取数据
        BufferedReader br=request.getReader();
        String params = br.readLine();
        //2.将json转为对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //3.调用service层的方法
        brandService.add(brand);
        //4.响应成功标识
        response.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取数据
        BufferedReader br=request.getReader();
        String params = br.readLine();
        //2.从json获取int
        String id = JSON.parseObject(params).getString("id");
        //2.调用service层的方法
        brandService.deleteById(Integer.parseInt(id));
        //3.响应成功标识
        response.getWriter().write("success");
    }

    public void updateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取数据
        BufferedReader br=request.getReader();
        String params = br.readLine();
        //2.将json转为int
        Brand brand = JSON.parseObject(params, Brand.class);
        //3.调用service层的方法
        brandService.updateById(brand);
        //4.响应成功标识
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取数据
        BufferedReader br=request.getReader();
        String params = br.readLine();
        //2.将json转为int[]
        int[] ids = JSON.parseObject(params, int[].class);
        //3.调用service层的方法
        brandService.deleteByIds(ids);
        //4.响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收当前页码和每页显示的条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //2.调用service层的方法
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);
        //4.转为json
        String jsonString = JSON.toJSONString(pageBean);
        //5.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 条件分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数 url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
// 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
//转为 Brand
        Brand brand = JSON.parseObject(params, Brand.class);
//2. 调用service查询
        PageBean<Brand> pageBean =
                brandService.selectByPageAndCondition(currentPage,pageSize,brand);
//2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
//3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
