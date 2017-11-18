package com.example.lenovo.hello.model;

/**
 * Created by lenovo on 2017/10/19.
 */

public class Content
{
    public static int CHAT_LEFT = 0;
    public static int CHAT_RIGHT = 1;
    private User user;
    private String text;
    private int position;

    public Content()
    {
    }

    public Content(User user, String text, int position)
    {
        this.user = user;
        this.text = text;
        this.position = position;
    }

    public User getUser()
    {
        return user;
    }

    public String getText()
    {
        return text;
    }

    public int getPosition()
    {
        return position;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }
}
