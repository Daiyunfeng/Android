package com.example.lenovo.hello.activity;

import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.fragment.MyPreferenceFragment;

/**
 * Created by lenovo on 2017/12/11.
 */

public class DataActivity extends BaseActivity
{
    private static final String TAG = "DataActivity", SP_NAME = "sp", SP_ITEM_NAME = "username", SP_ITEM_PWD = "password", SP_ITEM_ISREM = "isRemember";
    private Button loginButton, preferenceButton;
    private EditText usernameEditText, passwordEditText;
    private CheckBox isRememberCheckBox;
    private SharedPreferences sp;
    private Boolean flag;

    @Override
    protected int getLayoutID()
    {
        return R.layout.activity_data;
    }

    @Override
    protected void init()
    {
        flag = false;
        usernameEditText = (EditText) findViewById(R.id.et_data_username);
        passwordEditText = (EditText) findViewById(R.id.et_data_password);
        isRememberCheckBox = (CheckBox) findViewById(R.id.cb_data_remember);
        loginButton = (Button) findViewById(R.id.btn_data_login);
        preferenceButton = (Button) findViewById(R.id.btn_data_preference);
        sp = this.getSharedPreferences(SP_NAME, MODE_PRIVATE);

        loadBySharedPreferences();

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
    }

    @Override
    public void onBackPressed()
    {
        if (flag)
        {
            flag = false;
        }
        super.onBackPressed();
    }

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

    private void saveBySharedPreferences()
    {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_ITEM_NAME, usernameEditText.getText().toString());
        editor.putString(SP_ITEM_PWD, passwordEditText.getText().toString());
        editor.putBoolean(SP_ITEM_ISREM, isRememberCheckBox.isChecked());
        editor.commit();
    }
}
