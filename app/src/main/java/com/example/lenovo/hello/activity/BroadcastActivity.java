package com.example.lenovo.hello.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.lenovo.hello.R;

/**
 * Created by lenovo on 2017/12/10.
 */

public class BroadcastActivity extends AppCompatActivity
{
    public static final String TAG = "BroadcastActivity";
    private Button sendButton;
    private EditText inputEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_broadcast);
        init();
    }

    private void init()
    {
        sendButton = (Button) findViewById(R.id.btn_broadcast_send);
        inputEditText = (EditText) findViewById(R.id.et_broadcast_input);

        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String input = inputEditText.getText().toString();
                Intent intent = new Intent();
                intent.setAction(MyReceiver.MY_ACTION);
                intent.putExtra("msg", input);
//                Log.i(TAG,input);
                sendBroadcast(intent);
            }
        });
    }

}
