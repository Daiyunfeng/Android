package com.example.lenovo.hello.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by lenovo on 2017/11/15.
 */

public class FragmentPagersAdapter extends FragmentPagerAdapter
{
    List<Fragment> fragmentList;
    List<String> titleList;

    public FragmentPagersAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList)
    {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragmentList.get(position);
    }

    @Override
    public int getCount()
    {
        return titleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titleList.get(position);
    }
}
