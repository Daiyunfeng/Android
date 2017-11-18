package com.example.lenovo.hello.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.model.Content;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lenovo on 2017/10/13.
 */

public class WeChatAdapter extends BaseAdapter
{
    private Context context;
    private List<Content> mData;

    public WeChatAdapter(Context c, List<Content> data)
    {
        context = c;
        mData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++)
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
        Content content = mData.get(position);
        if (content.getPosition() == Content.CHAT_LEFT)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.wechat_left, null);
            ImageView imgHead = (ImageView) convertView.findViewById(R.id.iv_wechat_left_head);
            imgHead.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), content.getUser().getHead()));
            TextView tvContent = (TextView) convertView.findViewById(R.id.tv_wechat_left);
            tvContent.setText(content.getText());
        } else if (content.getPosition() == Content.CHAT_RIGHT)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.wechat_right, null);
            ImageView imgHead = (ImageView) convertView.findViewById(R.id.iv_wechat_right_head);
            imgHead.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), content.getUser().getHead()));
            TextView tvContent = (TextView) convertView.findViewById(R.id.tv_wechat_right);
            tvContent.setText(content.getText());
        }
        return convertView;
    }

}
