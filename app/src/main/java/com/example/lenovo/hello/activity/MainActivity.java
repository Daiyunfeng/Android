package com.example.lenovo.hello.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.database.DBManager;

import org.litepal.tablemanager.Connector;

/**
 * GridLayout 点击进入
 * Created by lenovo on 2017/9/218
 */

public class MainActivity extends Activity
{
    private static final String TAG = "MainActivity";
    private Button btnLab1, btnLab2, btnLab22, btnSimple, btnWeChat, btnDelete,
            btnLab3, btnLab4, btnLab5, btnLab6, btnLab7, btnLab8, btnLab9, btnLab10, btnLab11, btnLab12;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btnLab1 = (Button) findViewById(R.id.btn_list_lab1);
        btnLab2 = (Button) findViewById(R.id.btn_list_lab2_1);
        btnLab22 = (Button) findViewById(R.id.btn_list_lab2_2);
        btnSimple = (Button) findViewById(R.id.btn_list_view_simple);
        btnDelete = (Button) findViewById(R.id.btn_list_delete);
        btnWeChat = (Button) findViewById(R.id.btn_list_view_chat);
        btnLab3 = (Button) findViewById(R.id.btn_list_lab3);
        btnLab4 = (Button) findViewById(R.id.btn_list_lab4);
        btnLab5 = (Button) findViewById(R.id.btn_list_lab5);
        btnLab6 = (Button) findViewById(R.id.btn_list_lab6);
        btnLab7 = (Button) findViewById(R.id.btn_list_lab7);
        btnLab8 = (Button) findViewById(R.id.btn_list_lab8);
        btnLab9 = (Button) findViewById(R.id.btn_list_lab9);
        btnLab10 = (Button) findViewById(R.id.btn_list_lab10);
        btnLab11 = (Button) findViewById(R.id.btn_list_lab11);
        btnLab12 = (Button) findViewById(R.id.btn_list_lab12);
        btnLab1.setOnClickListener(new MyButtonOnClickListener(LoginActivity.class));
        btnLab2.setOnClickListener(new MyButtonOnClickListener(CalcActivity.class));
        btnLab22.setOnClickListener(new MyButtonOnClickListener(ButtonActivity.class));
        btnSimple.setOnClickListener(new MyButtonOnClickListener(SimpleLVActivity.class));
        btnWeChat.setOnClickListener((new MyButtonOnClickListener(WeChatLVActivity.class)));
        btnLab3.setOnClickListener(new MyButtonOnClickListener(DialogActivity.class));
        btnLab4.setOnClickListener(new MyButtonOnClickListener(PhoneActivity.class));
        btnLab5.setOnClickListener(new MyButtonOnClickListener(MenuActivity.class));
        btnLab6.setOnClickListener(new MyButtonOnClickListener(ServiceActivity.class));
        btnLab7.setOnClickListener(new MyButtonOnClickListener(TabActivity.class));
        btnLab8.setOnClickListener(new MyButtonOnClickListener(MultiThreadActivity.class));
        btnLab9.setOnClickListener(new MyButtonOnClickListener(HttpActivity.class));
        btnLab10.setOnClickListener(new MyButtonOnClickListener(BroadcastActivity.class));
        btnLab11.setOnClickListener(new MyButtonOnClickListener(DataActivity.class));
        btnLab12.setOnClickListener(new MyButtonOnClickListener(SqlActivity.class));
        btnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                new DBManager(MainActivityActivity.this).deleteDatabase();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        //super.onBackPressed();
        new AlertDialog.Builder(this).setTitle("确认退出吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener()
                {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Log.i(TAG, "确认");
                        MainActivity.this.finish();
                        System.exit(0); //杀死自己 因为我再次点开会进去splash界面
                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener()
                {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // do nothing
                    }
                }).show();
    }

    private class MyButtonOnClickListener implements View.OnClickListener
    {
        private Class nextPage;

        public MyButtonOnClickListener(Class nextPage)
        {
            this.nextPage = nextPage;
        }

        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, nextPage);
            startActivity(intent);
        }
    }
}
