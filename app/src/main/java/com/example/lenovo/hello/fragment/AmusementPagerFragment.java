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
import com.example.lenovo.hello.utils.MyToast;

/**
 * 娱乐界面
 * Created by lenovo on 2017/11/15.
 */

public class AmusementPagerFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.page_amusement, container, false);
    }
}
