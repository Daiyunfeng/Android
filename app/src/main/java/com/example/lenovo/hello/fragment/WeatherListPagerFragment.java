package com.example.lenovo.hello.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.model.Weather;

/**
 * Created by lenovo on 2017/11/15.
 */

public class WeatherListPagerFragment extends Fragment
{
    private Weather weather;

    public WeatherListPagerFragment()
    {

    }

    public WeatherListPagerFragment(Weather weather)
    {
        this.weather = weather;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.page_header, container, false);
        ListView listView = (ListView) view.findViewById(R.id.lv_page_header);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);

        adapter.add(weather.getDate());
        adapter.add("最高温 : " + weather.getHigh());
        adapter.add("最低温 : " + weather.getLow());
        adapter.add("风向 : " + weather.getFx());
        adapter.add("风力 : " + weather.getFl());
        adapter.add("天气 : " + weather.getType());

        listView.setAdapter(adapter);
        return view;
    }
}
