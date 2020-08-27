package com.doubleball.entity;

import java.util.List;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/8/14 下午4:30
 */
public class ListDoubleballStatistics {

    //  总的记录数量
    private int count;

    //  根据传进来的每页数量，计算页码数
    private int pageSize;

    private List<DoubleballStatistics> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public List<DoubleballStatistics> getList() {
        return list;
    }

    public void setList(List<DoubleballStatistics> list) {
        this.list = list;
    }

}
