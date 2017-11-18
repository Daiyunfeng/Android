package com.example.lenovo.hello.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/11/15.
 */

public class TabPagerAdapter extends PagerAdapter
{
    List<View> viewList;
    List<String> titleList;

    public TabPagerAdapter()
    {
    }

    public TabPagerAdapter(List<View> viewList, List<String> titleList)
    {
        this.viewList = viewList;
        this.titleList = titleList;
    }

    @Override
    public int getCount()
    {
        return titleList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titleList.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView(viewList.get(position));
    }
}
