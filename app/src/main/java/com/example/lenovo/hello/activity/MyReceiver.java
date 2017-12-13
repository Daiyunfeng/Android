package com.example.lenovo.hello.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbManager;
import android.util.Log;

import com.example.lenovo.hello.utils.MyToast;

public class MyReceiver extends BroadcastReceiver
{
    public static final String MY_ACTION = "hznu.hjc.action", USB_STATE = "android.hardware.usb.action.USB_STATE";
    public static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action = intent.getAction();
        Log.i(TAG, action);
        /**
         * Case String 的效率很低
         * 编译器会先修改为 switch(action.hashCode()) 然后case MY_ACTION.hashCode()
         * 再在里面action.equal(MY_ACTION)再执行
         */
        switch (action)
        {
            case MY_ACTION:
                String msg = intent.getStringExtra("msg");
                MyToast.showText(context, msg);
                break;
            case Intent.ACTION_TIME_CHANGED:
                MyToast.showText(context, "时间改变");
                break;
            case Intent.ACTION_DATE_CHANGED:
                MyToast.showText(context, "日期改变");
                break;
            case USB_STATE:
                MyToast.showText(context, "USB状态改变");
                break;
            case Intent.ACTION_POWER_CONNECTED:
                MyToast.showText(context, "开始充电");
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                MyToast.showText(context, "停止充电");
                break;
            // 下面四个都没触发过
            case UsbManager.ACTION_USB_ACCESSORY_ATTACHED:
                MyToast.showText(context, "USB链接-ACTION_USB_ACCESSORY_ATTACHED");
                break;
            case UsbManager.ACTION_USB_ACCESSORY_DETACHED:
                MyToast.showText(context, "USB断开-ACTION_USB_ACCESSORY_DETACHED");
                break;
            case UsbManager.ACTION_USB_DEVICE_ATTACHED:
                MyToast.showText(context, "USB连接-ACTION_USB_DEVICE_ATTACHED");
                break;
            case UsbManager.ACTION_USB_DEVICE_DETACHED:
                MyToast.showText(context, "USB断开-ACTION_USB_DEVICE_DETACHED");
                break;
            default:
                Log.e(TAG, action);
                break;
        }
    }
}
