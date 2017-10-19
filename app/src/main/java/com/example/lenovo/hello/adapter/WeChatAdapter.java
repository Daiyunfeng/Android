package com.example.lenovo.hello.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lenovo on 2017/10/13.
 */

public class WeChatAdapter extends BaseAdapter
{
    private LayoutInflater m_inflater;
    private List<Map<String,Objects>> mData;

    public WeChatAdapter(Context c,List<Map<String,Objects>> data)
    {
        m_inflater = LayoutInflater.from(c);
        mData = new ArrayList<>();
        for(int i=0;i<data.size();i++)
        {
            mData.add(data.get(i));
        }
    }

    @Override
    public int getCount()
    {
        return mData.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return null;
    }

}
