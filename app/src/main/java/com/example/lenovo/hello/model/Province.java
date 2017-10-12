package com.example.lenovo.hello.model;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by lenovo on 2017/9/30.
 */

public class Province extends DataSupport
{
    private int id;

    @SerializedName("province_name")
    private String province;

    private List<City> citys;

    public int getId()
    {
        return id;
    }

    public String getProvince()
    {
        return province;
    }

    public List<City> getCitys()
    {
        return citys;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public void setCitys(List<City> citys)
    {
        this.citys = citys;
    }

    /**
     * 重写了tostring 为了spinner 如果是一个对象 显示时会调用toString 方法
     * 实现了下拉框 但我不觉得是个好的解决方法 toString 方法直接无法使用了 还好转json并不会用到toString
     * @return 省份名
     */
    @Override
    public String toString()
    {
        return province;
    }
}
