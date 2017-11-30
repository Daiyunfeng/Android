package com.example.lenovo.hello.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.hello.R;

/**
 * Lab2-1
 * Created by lenovo on 2017/9/28.
 */

public class CalcActivity extends Activity
{
    private static final String TAG = "CalcActivity";
    private Button btnCalc, btnReset, btnTest;
    private EditText etUsername, etPassword, etHeight, etWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        btnCalc = (Button) findViewById(R.id.btn_calculate_calc);   //计算
        btnReset = (Button) findViewById(R.id.btn_calculate_reset); //重置
        btnTest = (Button) findViewById(R.id.btn_calculate_test); //测试
        etUsername = (EditText) findViewById(R.id.et_calculate_username);
        etPassword = (EditText) findViewById(R.id.et_calculate_password);
        etHeight = (EditText) findViewById(R.id.et_calculate_height);
        etWeight = (EditText) findViewById(R.id.et_calculate_weight);
        btnCalc.setOnClickListener(new View.OnClickListener()
                                   {
                                       @Override
                                       public void onClick(View v)
                                       {
                                           String str = "";
                                           double height, weight;
                                           double res;
                                           try
                                           {
                                               height = Double.valueOf(etHeight.getText().toString()).doubleValue();
                                               weight = Double.valueOf(etWeight.getText().toString()).doubleValue();
                                               res = weight / height / height;
                                               Log.i(TAG, String.valueOf(res));
                                               if (res <= 19)
                                               {
                                                   str = "体重偏轻";
                                               } else if (res > 19 && res <= 25)
                                               {
                                                   str = "体重正常";
                                               } else if (res > 25 && res <= 30)
                                               {
                                                   str = "体重超重";
                                               } else if (res > 30 && res <= 39)
                                               {
                                                   str = "严重超重";
                                               } else if (res > 39)
                                               {
                                                   str = "极度超重";
                                               }
                                           }
                                           catch (Exception e)
                                           {
                                               str = e.getMessage();
                                           }
                                           Toast.makeText(CalcActivity.this, str, Toast.LENGTH_LONG).show();
                                       }
                                   }
        );
        btnReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                etUsername.setText("");
                etPassword.setText("");
                etWeight.setText("");
                etHeight.setText("");
            }
        });
    }
}
