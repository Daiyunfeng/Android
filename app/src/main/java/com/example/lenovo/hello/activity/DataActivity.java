package com.example.lenovo.hello.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.fragment.MyPreferenceFragment;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * sharedPreferences 存储第一块登陆信息
 * 文件保存第二块登陆用户名
 * preferenceFragment 模拟一些按钮 加载页为 MyPreferenceFragment
 * Created by lenovo on 2017/12/11.
 */

public class DataActivity extends BaseActivity
{
    private static final String TAG = "DataActivity", SP_NAME = "sp", SP_ITEM_NAME = "username", SP_ITEM_PWD = "password", SP_ITEM_ISREM = "isRemember", FILE_NAME = "username.txt";
    private Button loginButton, preferenceButton, loginButton1;
    private AutoCompleteTextView usernameAutoEditText, passwordAutoEditText;
    private EditText usernameEditText, passwordEditText;
    private CheckBox isRememberCheckBox;
    private SharedPreferences sp;
    private Boolean flag;
    private Set<String> usernames;

    @Override
    protected int getLayoutID()
    {
        return R.layout.activity_data;
    }

    @Override
    protected void init()
    {
        usernames = new HashSet<>();
        flag = false;
        usernameEditText = (EditText) findViewById(R.id.et_data_username);
        passwordEditText = (EditText) findViewById(R.id.et_data_password);
        isRememberCheckBox = (CheckBox) findViewById(R.id.cb_data_remember);
        loginButton = (Button) findViewById(R.id.btn_data_login);
        preferenceButton = (Button) findViewById(R.id.btn_data_preference);
        loginButton1 = (Button) findViewById(R.id.btn_data_login2);
        usernameAutoEditText = (AutoCompleteTextView) findViewById(R.id.actv_data_username);
        passwordAutoEditText = (AutoCompleteTextView) findViewById(R.id.actv_data_password);
        sp = this.getSharedPreferences(SP_NAME, MODE_PRIVATE);

        loadBySharedPreferences();
        loadUsernameByFile();

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                saveBySharedPreferences();
            }
        });
        preferenceButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!flag)
                {
                    flag = true;
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.add(R.id.fl_data_preference, new MyPreferenceFragment());
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });
        loginButton1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username = usernameAutoEditText.getText().toString();
                Log.i(TAG, username);
                if (username.trim().equals(""))
                {
                    return;
                }
//                int count = usernames.size();
//                usernames.add(username);
                if (!usernames.contains(username))
                {
                    Log.i(TAG, "save");
                    usernames.add(username);
                    saveUsernameByFile();
                    loadAutoCompleteData();
                }
            }
        });
    }

    /**
     * 返回键 将flag 改为false 代表 Fragment页未载入
     */
    @Override
    public void onBackPressed()
    {
        if (flag)
        {
            flag = false;
        }
        super.onBackPressed();
    }

    /**
     * 打开此Activity后加载数据
     */
    private void loadBySharedPreferences()
    {
        Boolean isRemember = sp.getBoolean(SP_ITEM_ISREM, false);
        Log.i(TAG, isRemember.toString());
        if (isRemember == true)
        {
            isRememberCheckBox.setChecked(true);
            usernameEditText.setText(sp.getString(SP_ITEM_NAME, ""));
            passwordEditText.setText(sp.getString(SP_ITEM_PWD, ""));
        }
    }

    /**
     * 记住密码将数据存到sharedPreferences
     */
    private void saveBySharedPreferences()
    {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_ITEM_NAME, usernameEditText.getText().toString());
        editor.putString(SP_ITEM_PWD, passwordEditText.getText().toString());
        editor.putBoolean(SP_ITEM_ISREM, isRememberCheckBox.isChecked());
        editor.commit();
    }

    /**
     * 从文件中读取自动补全的usernmae
     */
    private void loadUsernameByFile()
    {
        try
        {
            FileInputStream inputStream = openFileInput(FILE_NAME);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String tmp = null;
            while ((tmp = dataInputStream.readLine()) != null)
            {
                usernames.add(tmp);
            }
            loadAutoCompleteData();
            dataInputStream.close();
            inputStream.close();

        }
        catch (java.io.IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新完usernames后
     * 保存到文件中
     */
    private void saveUsernameByFile()
    {
        try
        {
            FileOutputStream outputStream = openFileOutput(FILE_NAME, Activity.MODE_PRIVATE);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            StringBuffer sb = new StringBuffer("");
            for (String str : usernames)
            {
                sb.append(str + "\n");
            }
            dataOutputStream.write(sb.toString().getBytes());
            dataOutputStream.close();
            outputStream.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新完usernames后
     * 加载自动补全的数据
     */
    private void loadAutoCompleteData()
    {
        int count = 0;
        String[] tmp = new String[usernames.size()];
        for (String str : usernames)
        {
            tmp[count++] = str;
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tmp);
        usernameAutoEditText.setAdapter(arrayAdapter);
    }
}

