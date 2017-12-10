package com.example.lenovo.hello.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.service.MusicService;
import com.example.lenovo.hello.service.MyIntentService;
import com.example.lenovo.hello.service.MyService;
import com.example.lenovo.hello.utils.MyToast;

/**
 * Created by lenovo on 2017/11/21.
 */

public class ServiceActivity extends AppCompatActivity
{
    private static final String TAG = "ServiceActivity";
    private Button btnOpenUpdate, btnCloseUpdate, btnOpenMusic, btnCloseMusic;
    private boolean isBound = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_service);
        Intent myIntent = new Intent();
        myIntent.setClass(this, MyIntentService.class);
        Log.i(TAG, TAG + " thread id is " + Thread.currentThread().getId());
        startService(myIntent);
        stopService(myIntent);
        init();
    }

    private void init()
    {
        btnOpenUpdate = (Button) findViewById(R.id.btn_service_open_update);
        btnCloseUpdate = (Button) findViewById(R.id.btn_service_close_update);
        btnOpenMusic = (Button) findViewById(R.id.btn_service_open_music);
        btnCloseMusic = (Button) findViewById(R.id.btn_service_close_music);
        final Intent myIntent = new Intent();
        myIntent.setClass(this, MyService.class);

        btnOpenUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startService(myIntent);
            }
        });

        btnCloseUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                stopService(myIntent);
            }
        });

        final Intent musicIntent = new Intent();
        musicIntent.setClass(this, MusicService.class);
        final ServiceConnection sc = new ServiceConnection()
        {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service)
            {
                MusicService musicService = ((MusicService.MusicBinder) service).getService();
                isBound = true;
                musicService.playMusic();
            }

            @Override
            public void onServiceDisconnected(ComponentName name)
            {
                MyToast.showText(ServiceActivity.this, "stop");
            }
        };
        btnOpenMusic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bindService(musicIntent, sc, BIND_AUTO_CREATE);
            }
        });

        btnCloseMusic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (isBound)
                {
                    isBound = false;
                    unbindService(sc);
                }
            }
        });
    }
}
