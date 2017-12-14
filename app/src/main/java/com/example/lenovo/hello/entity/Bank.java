package com.example.lenovo.hello.entity;

import org.litepal.crud.DataSupport;

/**
 * 银行 存储在 LitePal DBManager.DATABASE_2 上
 * Created by lenovo on 2017/12/13.
 */

public class Bank extends DataSupport
{
    private int id;

    private String name;

    private String account;

    private String bank;

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getAccount()
    {
        return account;
    }

    public String getBank()
    {
        return bank;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public void setBank(String bank)
    {
        this.bank = bank;
    }
}
