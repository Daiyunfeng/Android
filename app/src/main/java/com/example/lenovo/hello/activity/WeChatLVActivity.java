package com.example.lenovo.hello.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.adapter.WeChatAdapter;
import com.example.lenovo.hello.model.Content;
import com.example.lenovo.hello.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/10/12.
 */

public class WeChatLVActivity extends Activity
{
    private static final String TAG = "WeChatLVActivity";

    private ListView listView;
    private WeChatAdapter weChatAdapter;
    private List<Content> contents;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_simple);
        listView = (ListView) findViewById(R.id.lv_simple);
        contents = new ArrayList<>();
        User user1 = new User("12", "12", R.mipmap.lv_head);
        contents.add(new Content(user1, "123131241241", Content.CHAT_LEFT));
        contents.add(new Content(user1, "1231312123123211321231341241", Content.CHAT_LEFT));
        contents.add(new Content(user1, "给力", Content.CHAT_RIGHT));

        weChatAdapter = new WeChatAdapter(this, contents);
        listView.setAdapter(weChatAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String str = "你选中第" + position + "个 内容: " + ((Content) parent.getAdapter().getItem(position)).getText();
                Toast.makeText(WeChatLVActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
