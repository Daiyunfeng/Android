package com.example.lenovo.hello.database.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by lenovo on 2017/9/29.
 */

public class BaseDao<T>
{
    public static <T> List<T> queryList(String sql, String[] params, T t)
    {
        //SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase();
        return null;
    }
}
