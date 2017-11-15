package com.example.lenovo.hello.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.utils.MyToast;
import com.example.lenovo.hello.utils.RandomColor;

import java.lang.reflect.Method;

/**
 * Created by lenovo on 2017/10/31.
 */

public class PhoneActivity extends AppCompatActivity
{
    public final static int REQUSET_ITEMS_INDEX = 100;
    private final static String TAG = "PhoneActivity";
    private EditText phone, username, password;
    private Button call, login1, login2;
    private TextView tvSelect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_phone);
        //随机背景颜色
        this.getWindow().getDecorView().setBackgroundColor(RandomColor.randomColorInt());
        init();
        call.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String number = phone.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });
        login1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putString("username", username.getText().toString());
                bundle.putString("password", password.getText().toString());
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(PhoneActivity.this, SubActivity1.class);
                startActivity(intent);
            }
        });
        login2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(PhoneActivity.this, SubActivity2.class);
                startActivityForResult(intent, REQUSET_ITEMS_INDEX);
            }
        });
    }

    private void init()
    {
        phone = (EditText) findViewById(R.id.et_phone_number);
        username = (EditText) findViewById(R.id.et_phone_username);
        password = (EditText) findViewById(R.id.et_phone_password);
        call = (Button) findViewById(R.id.btn_phone_call);
        login1 = (Button) findViewById(R.id.btn_phone_log1);
        login2 = (Button) findViewById(R.id.btn_phone_log2);
        tvSelect = (TextView) findViewById(R.id.tv_phone_select);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
            return;
        String selectIndex = bundle.getString("index");
        if (selectIndex != null && selectIndex != "")
        {
            tvSelect.setText(selectIndex);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK != resultCode)
        {
            tvSelect.setText("没有正确的点击");
            return;
        }
        //data
        String index = data.getExtras().get("index").toString();
        switch (requestCode)
        {
            case REQUSET_ITEMS_INDEX:
                tvSelect.setText("选中了" + index);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        setIconEnable(menu, true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        MyToast.showText(PhoneActivity.this, "选中" + item.getTitle());
        return true;
    }

    private void setIconEnable(Menu menu, boolean enable)
    {
        try
        {
            Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
            m.setAccessible(true);
            m.invoke(menu, enable);
        } catch (Exception e)
        {
            Log.e("aaa", e.getMessage());
        }
    }
}
