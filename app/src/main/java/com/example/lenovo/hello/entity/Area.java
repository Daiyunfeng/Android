package com.example.lenovo.hello.entity;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by lenovo on 2017/10/2.
 */

public class Area extends DataSupport
{
    private int id;

    private String area;

    public int getId()
    {
        return id;
    }

    public String getArea()
    {
        return area;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    /**
     * 重写了tostring 为了spinner 如果是一个对象 显示时会调用toString 方法
     * 实现了下拉框 但我不觉得是个好的解决方法 toString 方法直接无法使用了 还好转json并不会用到toString
     *
     * @return 区名
     */
    @Override
    public String toString()
    {
        return area;
    }
}
