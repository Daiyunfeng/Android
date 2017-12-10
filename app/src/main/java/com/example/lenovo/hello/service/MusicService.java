package com.example.lenovo.hello.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import com.example.lenovo.hello.utils.MyToast;

public class MusicService extends Service
{
    public final Binder musicBinder = new MusicBinder();

    public MusicService()
    {
    }

    public class MusicBinder extends Binder
    {
        public MusicService getService()
        {
            return MusicService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return musicBinder;
    }

    public void playMusic()
    {
        MyToast.showText(this, "嗨呀 放歌了呢");
    }
}
