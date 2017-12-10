package com.example.lenovo.hello.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.activity.ServiceActivity;

public class MyService extends Service
{
    public static final String TAG = "MyService";
    private NotificationManager nm;
    private Intent messageIntent;
    private int messageNotificationID;
    private Boolean stopFlag;
    private PendingIntent pendingIntent;

    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        nm = (NotificationManager) getApplication().getSystemService(NOTIFICATION_SERVICE);
        messageIntent = new Intent(this, ServiceActivity.class);
        pendingIntent = PendingIntent.getActivity(this, 0, messageIntent, 0);
        messageNotificationID = 1;
        stopFlag = false;
        // 开启线程
        new Thread(new MyRunnable()).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
        stopFlag = true;
        super.onDestroy();
    }

    private class MyRunnable implements Runnable
    {

        @Override
        public void run()
        {
            while (!stopFlag)
            {
                try
                {
                    Log.i(TAG, "--");

                    Notification n = new Notification.Builder(getApplication()).setContentTitle("实验六").setContentText("有更新了")
                            .setSmallIcon(R.mipmap.ic_launcher).setContentIntent(pendingIntent).build();
                    long v[] = {0, 100, 200, 300}; //震动频率
                    n.vibrate = v;
                    n.flags |= Notification.FLAG_AUTO_CANCEL;// 点击消息后,该消息自动退出
                    n.flags |= Notification.FLAG_ONGOING_EVENT;// 在上方运行消息栏中出现
                    nm.notify(messageNotificationID, n);
                    messageNotificationID++;

                    Log.i(TAG, "end");
                    Thread.sleep(10000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            stopSelf();
        }
    }

}
