package com.example.lenovo.hello.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.adapter.FragmentPagersAdapter;
import com.example.lenovo.hello.fragment.AmusementPagerFragment;
import com.example.lenovo.hello.fragment.ListPagerFragment;
import com.example.lenovo.hello.fragment.PhysicalPagerFragment;
import com.example.lenovo.hello.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/11/14.
 */

public class TabActivity extends AppCompatActivity
{
    private TabLayout tabLayout;
    private ViewPager viewPagerTitle;
    private String[] items;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tab);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);//变成菜色
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem item)
            {
                MyToast.showText(TabActivity.this, item.getTitle().toString());
                return true;
            }
        });
//        ((DrawerLayout)findViewById(R.id.drawerlayout)).setScrimColor(Color.TRANSPARENT);//取消遮罩
        tabLayout = (TabLayout) findViewById(R.id.tl_main);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText("头条"));
//        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_launcher);
        tabLayout.addTab(tabLayout.newTab().setText("娱乐"));
        tabLayout.addTab(tabLayout.newTab().setText("体育"));

//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.add(R.id.fl_tag_title,new ListPagerFragment());
//        ft.addToBackStack(null);
//        ft.commit();

        List<Fragment> viewList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();

        viewPagerTitle = (ViewPager) findViewById(R.id.vp_tag_main);
//        View vPageHeader = LayoutInflater.from(this).inflate(R.layout.page_header,null);
//        ListView listView = (ListView) vPageHeader.findViewById(R.id.lv_page_header);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TabActivity.this, android.R.layout.simple_list_item_1);
//        items = new String[10];
//        for (int i = 0; i < 10; i++)
//        {
//            items[i] = "items"+i;
//            adapter.add("items"+i);
//        }
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//            {
//                MyToast.showText(TabActivity.this,items[position]);
//            }
//        });
//        viewList.add(vPageHeader);viewList.add(vPageHeader);viewList.add(vPageHeader);
        viewList.add(new ListPagerFragment());
        viewList.add(new AmusementPagerFragment());
        viewList.add(new PhysicalPagerFragment());
        titleList.add("头条");
        titleList.add("娱乐");
        titleList.add("体育");
        //viewPagerTitle.setAdapter(new TabPagerAdapter(viewList,titleList));
        viewPagerTitle.setAdapter(new FragmentPagersAdapter(getSupportFragmentManager(), viewList, titleList));
        tabLayout.setupWithViewPager(viewPagerTitle);
    }
}
