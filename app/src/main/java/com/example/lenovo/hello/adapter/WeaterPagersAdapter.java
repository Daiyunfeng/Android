package com.example.lenovo.hello.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lenovo.hello.fragment.WeatherListPagerFragment;
import com.example.lenovo.hello.model.Weather;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/12/7.
 */

public class WeaterPagersAdapter extends FragmentStatePagerAdapter
{
    private List<WeatherListPagerFragment> weatherListPagerFragments;
    private List<String> titleList;

    public WeaterPagersAdapter(FragmentManager fm, List<Weather> weathers, List<String> titleList)
    {
        super(fm);
        this.titleList = titleList;
        weatherListPagerFragments = new ArrayList<>();
        for (int i = 0; i < weathers.size(); i++)
        {
            weatherListPagerFragments.add(new WeatherListPagerFragment(weathers.get(i)));
        }
    }

    @Override
    public Fragment getItem(int position)
    {
        return weatherListPagerFragments.get(position);
    }

    @Override
    public int getCount()
    {
        return weatherListPagerFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titleList.get(position);
    }
}
