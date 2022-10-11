package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    //1.创建sqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {
        //2.创建sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper接口的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        List<Brand> brands = mapper.selectAll();
        //5.释放资源
        sqlSession.close();
        return brands;
    }

    @Override
    public void add(Brand brand) {
        //2.创建sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper接口的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.add(brand);
        //5.提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        //2.创建sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper接口的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.deleteById(id);
        //5.提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
    }

    @Override
    public void updateById(Brand brand) {
        //2.创建sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper接口的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.updateById(brand);
        //5.提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        //2.创建sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper接口的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.deleteByIds(ids);
        //5.提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //2.创建sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper接口的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算当前条数
        int size = pageSize;
        //5.查询当前页的数据
        List<Brand> rows = mapper.selectByPage(begin, size);
        //6.查询总条数
        int totalCount = mapper.selectTotalCount();

        //7.创建PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setTotalCount(totalCount);
        pageBean.setRows(rows);
        //8.释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
//3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
//4. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
// 计算查询条目数
        int size = pageSize;
// 处理brand条件，模糊表达式
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");
        }
        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }
//5. 查询当前页数据
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size, brand);
//6. 查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(brand);
//7. 封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
//8. 释放资源
        sqlSession.close();
        return pageBean;
    }
}

