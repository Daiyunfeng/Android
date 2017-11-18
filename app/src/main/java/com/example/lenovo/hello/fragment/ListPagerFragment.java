package com.example.lenovo.hello.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.activity.TabActivity;
import com.example.lenovo.hello.utils.MyToast;

/**
 * Created by lenovo on 2017/11/15.
 */

public class ListPagerFragment extends Fragment
{
    private String[] items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.page_header, container, false);
        ListView listView = (ListView) view.findViewById(R.id.lv_page_header);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        items = new String[10];
        for (int i = 0; i < 10; i++)
        {
            items[i] = "items" + i;
            adapter.add("items" + i);
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                MyToast.showText(getActivity(), items[position]);
            }
        });
        return view;
    }
}
