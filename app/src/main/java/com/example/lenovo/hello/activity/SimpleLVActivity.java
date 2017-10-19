package com.example.lenovo.hello.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import com.example.lenovo.hello.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lenovo on 2017/10/12.
 */

public class SimpleLVActivity extends Activity
{
    private static final String TAG = "SimpleLVActivity";
    private String[] strElement ;
    private String[] titles;
    private String[] contents;
    private int[] imageids;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_simple);
        listView = (ListView) findViewById(R.id.lv_simple);
        strElement = new String[]{"image","title","content"};
        titles = new String[10];
        contents = new String[10];
        imageids = new int[10];
        List<Map<String,Object>> listems = new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            Map<String,Object> tmp = new HashMap<>();
            titles[i] = "胡迪恒";
            contents[i] = "";
            for(int j=0;j<=i;j++)
            {
                contents[i]+="给胡迪恒上香";
            }
            imageids[i]=R.mipmap.lv_head;
            tmp.put(strElement[0],imageids[i]);
            tmp.put(strElement[1],titles[i]);
            tmp.put(strElement[2],contents[i]);
            listems.add(tmp);
        }
        Log.i(TAG,""+listems.size());
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listems,R.layout.simple_list_view,strElement,new int[]{R.id.lv_iv_icon,R.id.lv_tv_title,R.id.lv_tv_content});
        listView.setAdapter(simpleAdapter);
    }
}
