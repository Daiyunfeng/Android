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

public class LoginActivity extends Activity
{
    private static final String TAG = "LoginActivity";
    private Button btnLogin, btnRegister;
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
    }
}
