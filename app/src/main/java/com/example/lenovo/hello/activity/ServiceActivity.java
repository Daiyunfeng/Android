package com.example.lenovo.hello.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;

import com.example.lenovo.hello.R;

/**
 * Created by lenovo on 2017/11/21.
 */

public class ServiceActivity extends AppCompatActivity
{
    private Button btnOpenUpdate,btnCloseUpdate,btnOpenMusic,btnCloseMusic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_service);
        init();
    }

    private void init()
    {
        btnOpenUpdate = (Button) findViewById(R.id.btn_service_open_update);
        btnCloseUpdate = (Button) findViewById(R.id.btn_service_close_update);
        btnOpenMusic = (Button) findViewById(R.id.btn_service_open_music);
        btnCloseMusic = (Button) findViewById(R.id.btn_service_close_music);
    }
}
