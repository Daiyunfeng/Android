package com.example.lenovo.hello.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.hello.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;

import static android.os.Environment.getExternalStoragePublicDirectory;
import static android.os.Environment.getRootDirectory;

public class LoginActivity extends Activity
{
    private static final String TAG = "LoginActivity";
    private Button btnLogin, btnRegister,btnFile;
    private TextView tvInput;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i(this.getLocalClassName(), "---creat");
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                btnLogin.setText("Quit"); //将按钮文本设为Quit；
                tvInput = (TextView) findViewById(R.id.tv_input);
                tvInput.setTextColor(Color.RED); //将文本变为红色;
                Log.i(TAG, tvInput.getText().toString());
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnFile = (Button)findViewById(R.id.btn_login_file);
        btnFile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                //getRootDirectory()  /system
//                //getExternalStoragePublicDirectory()  /storage/emulated/0 没有权限
//                Log.i(TAG,getRootDirectory().getAbsolutePath());
//                StringBuilder result = new StringBuilder();
//                File file = getExternalStoragePublicDirectory("/tencent/Midas.xml");
//                //File file = new File("/data/tencent/Midas.xml");
//                try{
//                    BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
//                    String s = null;
//                    while((s = br.readLine())!=null){//使用readLine方法，一次读一行
//                        result.append(s);
//                    }
//                    br.close();
//                }catch(Exception e){
//                    Log.i(TAG,e.getMessage());
//                }
//                Log.i(TAG,result.toString());
            }
        });
    }
}
