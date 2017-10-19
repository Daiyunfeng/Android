package com.example.lenovo.hello.model;

/**
 * Created by lenovo on 2017/10/19.
 */

public class User
{
    private String username;
    private String nickname;
    private int head;//头像 R.mipmap.

    public User(){}

    public User( String username, String nickname,int head)
    {
        this.head = head;
        this.username = username;
        this.nickname = nickname;
    }

    public void setHead(int head)
    {
        this.head = head;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public int getHead()
    {
        return head;
    }

    public String getUsername()
    {
        return username;
    }

    public String getNickname()
    {
        return nickname;
    }
}
