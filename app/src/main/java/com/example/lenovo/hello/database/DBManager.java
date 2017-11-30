package com.example.lenovo.hello.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.lenovo.hello.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lenovo on 2017/9/30.
 * http://www.cnblogs.com/xiaowenji/archive/2011/01/03/1925014.html 数据库存储的位置
 * data/data/包名/xx.db
 * 数据库管理
 * 在添加了litepal后不再使用
 */

public class DBManager
{
    private static final String TAG = "DBManager";
    private static final String DB_NAME = "city_province.db"; //保存的数据库文件名
    private static final String PACKAGE_NAME = "com.example.lenovo.hello";
    private static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME + "/databases";  //在手机里存放数据库的位置
    private static final String DB_FILE = DB_PATH + "/" + DB_NAME;
    private static final int BUFFER_SIZE = 1024;
    private SQLiteDatabase db;
    private Context context;

    public DBManager(Context context)
    {
        this.context = context;
    }

    public SQLiteDatabase openDatebase()
    {
        if (db != null)
        {
            return db;
        }
        File dbFile = new File(DB_FILE);
        if (!dbFile.exists())  //判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
        {
            new File(DB_PATH).mkdirs();
            copyDatabase();
        }
        db = SQLiteDatabase.openOrCreateDatabase(DB_FILE, null);
        Log.i(TAG, "Success open database");
        return db;
    }

    public void copyDatabase()
    {
        try
        {
            Log.i(TAG, "Start init");
            InputStream is = this.context.getResources().openRawResource(
                    R.raw.city_province); //欲导入的数据库 读入
            FileOutputStream fos = new FileOutputStream(DB_FILE);   //输出
            byte[] buffer = new byte[BUFFER_SIZE];  //一次读取 BUFFER_SIZE 个字节
            int count = 0;
            Log.i(TAG, "Start copy");
            while ((count = is.read(buffer)) > 0)
            {
                fos.write(buffer, 0, count);
            }
            Log.i(TAG, "End copy");
            fos.close();
            is.close();
            Log.i(TAG, "End init");
        }
        catch (FileNotFoundException e)
        {
            Log.e(TAG, e.getMessage());
        }
        catch (IOException e)
        {
            Log.e(TAG, e.getMessage());
        }
    }


    public void deleteDatabase()
    {
        try
        {
            context.deleteDatabase(DB_FILE);
            Toast.makeText(context, "成功删除数据库", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e(TAG, e.getMessage());
        }
    }

    public void closeDatabase()
    {
        try
        {
            this.db.close();
        }
        catch (Exception e)
        {
            Log.e(TAG, e.getMessage());
        }
    }
}
