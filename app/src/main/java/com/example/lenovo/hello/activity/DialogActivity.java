package com.example.lenovo.hello.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.utils.RandomColor;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by lenovo on 2017/10/31.
 */

public class DialogActivity extends Activity
{
    private static final String TAG = "DialogActivity";
    //private static int color = -1; //如果需要把旋转改色去掉 需要保存这个color
    private Button btnSimple, btnComplex,btnTPDialog;
    private boolean[] select, tmp;
    private DatePicker datePicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_dialog);
        this.getWindow().getDecorView().setBackgroundColor(RandomColor.randomColorInt());
        select = new boolean[]{false, false, false};
        tmp = new boolean[]{false, false, false};
        btnSimple = (Button) findViewById(R.id.btn_dialog_simple);
        btnComplex = (Button) findViewById(R.id.btn_dialog_complex);
        btnSimple.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
                builder.setTitle("简单对话框");
                builder.setMessage("xxxxxxxxxx");

                builder.setPositiveButton("确认", null);
                builder.setNeutralButton("帮助", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(DialogActivity.this, "我需要帮助", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        btnComplex.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
                builder.setTitle("复杂对话框");
                final String[] items = new String[]{"数学", "英语", "物理"};
                builder.setMultiChoiceItems(items, select, new DialogInterface.OnMultiChoiceClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked)
                    {
                        if (isChecked == true)
                        {
                            Toast.makeText(DialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setPositiveButton("确认", null);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        for (int i = 0; i < tmp.length; i++)
                        {
                            select[i] = tmp[i];
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        ///datePicker
        datePicker = (DatePicker) findViewById(R.id.datepicker_dialog);
        Calendar c = Calendar.getInstance();
        datePicker.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener()
        {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                String str = year + "年" + monthOfYear + "月" + dayOfMonth + "天";
                Toast.makeText(DialogActivity.this,str,Toast.LENGTH_LONG);
            }
        });
    }
}
