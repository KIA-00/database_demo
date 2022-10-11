package com.itheima.pojo;

import java.util.List;

public class PageBean<T> {
    private int totalCount;//总记录数

    private List<T> rows;//当前页的数据

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
