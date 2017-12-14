package com.example.lenovo.hello.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 2017/12/6.
 */

public class Weather
{
    private String date;

    private String high;

    @SerializedName(value = "fx", alternate = {"fengxiang"})
    private String fx;

    private String low;

    @SerializedName(value = "fl", alternate = {"fengli"})
    private String fl;

    private String type;

    @Override
    public String toString()
    {
        return date + "天气 : " + "最" + high + ", 最" + low + ", " + "风向" + fx + ", " + "风力" + fl + ", 天气" + type;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setHigh(String high)
    {
        this.high = high;
    }

    public void setFx(String fx)
    {
        this.fx = fx;
    }

    public void setLow(String low)
    {
        this.low = low;
    }

    public void setFl(String fl)
    {
        this.fl = fl;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getDate()
    {
        return date;
    }

    public String getHigh()
    {
        return high;
    }

    public String getFx()
    {
        return fx;
    }

    public String getLow()
    {
        return low;
    }

    public String getFl()
    {
        return fl;
    }

    public String getType()
    {
        return type;
    }
}
