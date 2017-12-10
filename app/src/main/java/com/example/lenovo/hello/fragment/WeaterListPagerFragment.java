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
import com.example.lenovo.hello.entity.Province;
import com.example.lenovo.hello.model.Weater;
import com.example.lenovo.hello.utils.MyToast;

/**
 * Created by lenovo on 2017/11/15.
 */

public class WeaterListPagerFragment extends Fragment
{
    private Weater weater;

    public WeaterListPagerFragment()
    {

    }

    public WeaterListPagerFragment(Weater weater)
    {
        this.weater = weater;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.page_header, container, false);
        ListView listView = (ListView) view.findViewById(R.id.lv_page_header);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);

        adapter.add(weater.getDate());
        adapter.add("最高温 : " + weater.getHigh());
        adapter.add("最低温 : " + weater.getLow());
        adapter.add("风向 : " + weater.getFx());
        adapter.add("风力 : " + weater.getFl());
        adapter.add("天气 : " + weater.getType());

        listView.setAdapter(adapter);
        return view;
    }
}
