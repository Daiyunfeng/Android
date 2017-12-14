package com.example.lenovo.hello.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.utils.RandomColor;

/**
 * PhoneActivity的子页面
 * Created by lenovo on 2017/11/1.
 */

public class SubActivity2 extends Activity
{
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_subpage2);
        //随机背景颜色
        this.getWindow().getDecorView().setBackgroundColor(RandomColor.randomColorInt());
        listView = (ListView) findViewById(R.id.lv_phone_subpage2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SubActivity2.this, android.R.layout.simple_list_item_1);
        for (int i = 0; i < 10; i++)
        {
            adapter.add("Item" + i);
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent();
                intent.putExtra("index", position);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


}
