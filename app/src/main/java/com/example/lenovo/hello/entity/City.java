package com.example.lenovo.hello.entity;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by lenovo on 2017/9/30.
 */

public class City extends DataSupport
{
    private int id;

    private String city;

    @SerializedName("province_id")
    private int province_id;

    public int getId()
    {
        return id;
    }

    public String getCity()
    {
        return city;
    }

    public int getProvince_id()
    {
        return province_id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setProvince_id(int province_id)
    {
        this.province_id = province_id;
    }

    /**
     * 重写了tostring 为了spinner 如果是一个对象 显示时会调用toString 方法
     * 实现了下拉框 但我不觉得是个好的解决方法 toString 方法直接无法使用了 还好转json并不会用到toString
     *
     * @return 城市名
     */
    @Override
    public String toString()
    {
        return city;
    }
}
