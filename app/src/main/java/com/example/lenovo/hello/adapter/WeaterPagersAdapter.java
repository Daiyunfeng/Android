package com.example.lenovo.hello.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lenovo.hello.fragment.WeaterListPagerFragment;
import com.example.lenovo.hello.model.Weater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/12/7.
 */

public class WeaterPagersAdapter extends FragmentStatePagerAdapter
{
    private List<WeaterListPagerFragment> weaterListPagerFragments;
    private List<String> titleList;

    public WeaterPagersAdapter(FragmentManager fm, List<Weater> weaters, List<String> titleList)
    {
        super(fm);
        this.titleList = titleList;
        weaterListPagerFragments = new ArrayList<>();
        for (int i = 0; i < weaters.size(); i++)
        {
            weaterListPagerFragments.add(new WeaterListPagerFragment(weaters.get(i)));
        }
    }

    @Override
    public Fragment getItem(int position)
    {
        return weaterListPagerFragments.get(position);
    }

    @Override
    public int getCount()
    {
        return weaterListPagerFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titleList.get(position);
    }
}
