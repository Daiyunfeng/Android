package com.example.lenovo.hello.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.utils.RandomColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/12/12.
 */

public class ListActivity extends BaseActivity
{
    private static final String TAG = "ListActivity";
    private ListView listView;
    private String[] items = new String[]{"中文", "英文", "法语", "葡萄牙语", "日语", "孟加拉语", "阿拉伯语", "俄语", "西班牙语", "印度斯坦语"};
    private String[] strElement = new String[]{"title"};
    private List<Map<String, Object>> listItems = new ArrayList<>();

    @Override
    protected int getLayoutID()
    {
        return R.layout.activity_phone_subpage2;
    }

    @Override
    protected void init()
    {
        listView = (ListView) findViewById(R.id.lv_phone_subpage2);
        for (int i = 0; i < items.length; i++)
        {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put(strElement[0], items[i]);
            listItems.add(tmp);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.string_list_item, strElement, new int[]{R.id.tv_list});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent();
                intent.putExtra("language", items[position]);
                Log.i(TAG, items[position]);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
