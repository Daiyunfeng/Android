package com.example.lenovo.hello.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lenovo.hello.R;

/**
 * 娱乐界面
 * Created by lenovo on 2017/11/15.
 */

public class PhysicalPagerFragment extends Fragment
{
    private View view;
    private Button btn;
    private boolean flag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.page_physical, container, false);
        init();
        return view;
    }

    private void init()
    {
        flag = false;
        btn = (Button) view.findViewById(R.id.btn_page_physical);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!flag)
                {
                    flag = true;
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.add(R.id.fl_page_physical, new LoadFragment());
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });
    }
}
