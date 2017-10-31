package com.example.lenovo.hello.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.utils.MyToast;
import com.example.lenovo.hello.utils.RandomColor;

import java.util.Calendar;

/**
 * Created by lenovo on 2017/10/31.
 */

public class DialogActivity extends Activity
{
    private static final String TAG = "DialogActivity";
    //private static int color = -1; //如果需要把旋转改色去掉 需要保存这个color
    private Button btnSimple, btnComplex, btnTPDialog, btnSee, btnAdd, btnReduce;
    private ProgressBar pbHorizontal, pbSmall;
    private SeekBar seekBar;
    private boolean[] select, tmp;
    private DatePicker datePicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.activity_dialog);
        //随机背景颜色
        this.getWindow().getDecorView().setBackgroundColor(RandomColor.randomColorInt());
        //获取width height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        String text = "width="+displayMetrics.widthPixels+" height"+displayMetrics.heightPixels;
        MyToast.showText(DialogActivity.this,text);
        //初始化变量
        init();
        //事件注册
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
                        MyToast.showText(DialogActivity.this,"我需要帮助");
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
                            MyToast.showText(DialogActivity.this,items[which]);
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
                String text = year + "年" + monthOfYear + "月" + dayOfMonth + "天";
                MyToast.showText(DialogActivity.this,text);
            }
        });

        btnTPDialog.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                    {
                        String text = hourOfDay + "点" + minute + "分";
                        MyToast.showText(DialogActivity.this,text);
                    }
                };
                Calendar c = Calendar.getInstance();
                new TimePickerDialog(DialogActivity.this, timeSetListener, c.get(Calendar.HOUR), c.get(Calendar.MINUTE), false).show();
            }
        });

        //bar
        btnSee.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (pbSmall.getVisibility() == View.VISIBLE)
                {
                    pbSmall.setVisibility(View.GONE);
                } else
                {
                    pbSmall.setVisibility(View.VISIBLE);
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pbHorizontal.incrementProgressBy(5);
            }
        });
        btnReduce.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pbHorizontal.incrementProgressBy(-5);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                String text = "当前位置"+progress;
                MyToast.showText(DialogActivity.this,text);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }

    private void init()
    {
        select = new boolean[]{false, false, false};
        tmp = new boolean[]{false, false, false};
        btnSimple = (Button) findViewById(R.id.btn_dialog_simple);
        btnComplex = (Button) findViewById(R.id.btn_dialog_complex);
        btnTPDialog = (Button) findViewById(R.id.btn_dialog_tp);
        btnAdd = (Button) findViewById(R.id.btn_dialog_add);
        btnReduce = (Button) findViewById(R.id.btn_dialog_reduce);
        btnSee = (Button) findViewById(R.id.btn_dialog_see);
        pbSmall = (ProgressBar) findViewById(R.id.pb_dialog_small);
        pbHorizontal = (ProgressBar) findViewById(R.id.pb_dialog_horizontal);
        seekBar = (SeekBar) findViewById(R.id.sb_dialog_simple);
    }
}
