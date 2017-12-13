package com.example.lenovo.hello.activity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.database.DBManager;
import com.example.lenovo.hello.entity.Area;
import com.example.lenovo.hello.entity.Bank;
import com.example.lenovo.hello.entity.City;
import com.example.lenovo.hello.entity.Province;
import com.example.lenovo.hello.view.CircleProgressBar;

import org.litepal.LitePal;
import org.litepal.LitePalDB;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/9/28.
 */

public class SplashActivity extends Activity
{
    private static final String TAG = "SplashActivity", SP_NAME = "init", SP_ITEM_FIRST_START = "firstStart";
    private CircleProgressBar circleProgressBar;
//    private CountDownTimer countDownTimer = new CountDownTimer(3000, 1000)
//    {
//        @Override
//        public void onTick(long l)
//        {
//
//        }
//
//        @Override
//        public void onFinish()
//        {
//            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();   //不能返回回来
//        }
//    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        circleProgressBar = (CircleProgressBar) findViewById(R.id.cpb_timer);
        circleProgressBar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                circleProgressBar.cancelAnim(); //不然会调用end
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();   //不能返回回来
            }
        });
        circleProgressBar.setAnimatorListener(new MyAnimatorListener());
        circleProgressBar.startAnim();
        init();
//        countDownTimer.start();
//        Log.i(TAG, "1234");
    }

    @Override
    public void onBackPressed()
    {
        //do nothing
    }

    //整个app第一次启动需要初始化的操作
    private void init()
    {
        SharedPreferences sp = this.getSharedPreferences(SP_NAME, MODE_PRIVATE);
        Boolean flag = sp.getBoolean(SP_ITEM_FIRST_START, false);
        Log.i(TAG, flag + "");
        if (flag)
        {
            return;
        }
        Connector.getDatabase();
        new DBManager(this).copyDatabase();
//        if(!flag)
//            return;
        List<Province> provinces = DataSupport.findAll(Province.class);
        List<City> cities = DataSupport.findAll(City.class);
        List<Area> areas = DataSupport.findAll(Area.class);

        Log.i(TAG, "provinces" + provinces.size());
        LitePal.useDefault();

        Map<Integer, Integer> provinceToCity = new HashMap<>();
        Map<Integer, Integer> cityToArea = new HashMap<>();
        List<Province> provincesTmp = DataSupport.findAll(Province.class);
        List<City> citiesTmp = DataSupport.findAll(City.class);
        List<Area> areasTmp = DataSupport.findAll(Area.class);

        for (int i = 0; i < provinces.size(); i++)
        {
            Province province = new Province();
            province.setProvince(provinces.get(i).getProvince());
            provinceToCity.put(provinces.get(i).getId(), i + 1);
            provincesTmp.add(province);
        }

        for (int i = 0; i < cities.size(); i++)
        {
            City city = new City();
            city.setCity(cities.get(i).getCity());
            city.setProvince_id(provinceToCity.get(cities.get(i).getProvince_id()));
            cityToArea.put(cities.get(i).getId(), i + 1);
            citiesTmp.add(city);
        }

        for (int i = 0; i < areas.size(); i++)
        {
//            Log.i(TAG,i + "" + areas.get(i).getId() + areas.get(i).getCity_id());
            Area area = new Area();
            area.setArea(areas.get(i).getArea());
            area.setCity_id(cityToArea.get(areas.get(i).getCity_id()));
            areasTmp.add(area);
        }
        DataSupport.saveAll(provincesTmp);
        DataSupport.saveAll(citiesTmp);
        DataSupport.saveAll(areasTmp);
        LitePalDB litePalDB = new LitePalDB(DBManager.DATABASE_2, 1);
        litePalDB.addClassName(Bank.class.getName());
        LitePal.use(litePalDB);
        for (int i = 0; i < 10; i++)
        {
            Bank bank = new Bank();
            bank.setName("Name" + i);
            bank.setBank("Bank" + i);
            bank.setAccount("Account" + i);
            bank.save();
        }
        sp.edit().putBoolean(SP_ITEM_FIRST_START, true).commit();
        LitePal.useDefault();
        provinces = DataSupport.findAll(Province.class);
        Log.i(TAG, provinces.size() + "    " + LitePal.getDatabase().getPath());
    }

    private class MyAnimatorListener implements AnimatorListener
    {

        @Override
        public void onAnimationStart(Animator animation)
        {

        }

        @Override
        public void onAnimationEnd(Animator animation)  //计时结束
        {
            Log.i(TAG, "end");
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();   //不能返回回来
        }

        @Override
        public void onAnimationCancel(Animator animation)
        {

        }

        @Override
        public void onAnimationRepeat(Animator animation)
        {

        }
    }
}
