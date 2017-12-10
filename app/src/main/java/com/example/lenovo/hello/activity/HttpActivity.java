package com.example.lenovo.hello.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.adapter.WeaterPagersAdapter;
import com.example.lenovo.hello.entity.City;
import com.example.lenovo.hello.entity.Province;
import com.example.lenovo.hello.model.Weater;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/12/6.
 */

public class HttpActivity extends AppCompatActivity
{
    private static final int REQUEST_STATUS_OK = 0, REQUEST_STATUS_ERROR=1, WEATER_RESULT = 2,POST_ERROR = 3,POST_OK = 4;
    private static final String TAG = "HttpActivity";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Spinner spnProvinces, spnCities;
    private TextView cityTextView,resultTextView;
    private Button queryButton,loginButton;
    private EditText usernameEditText,passwordEditText;
    private String cityName;
    private String URL = "http://wthrcdn.etouch.cn/weather_mini?city=";
    private MyHandle handle = new MyHandle();
    private List<Weater> weaters;
    private List<String> titles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_http);
        init();
    }

    private void init()
    {
        spnProvinces = (Spinner) findViewById(R.id.spn_http_provinces);
        spnCities = (Spinner) findViewById(R.id.spn_http_cities);
        queryButton = (Button) findViewById(R.id.btn_http_query);
        loginButton = (Button) findViewById(R.id.btn_http_login);
        cityTextView = (TextView) findViewById(R.id.tv_http_city);
        tabLayout = (TabLayout) findViewById(R.id.tab_http_main);
        viewPager = (ViewPager) findViewById(R.id.vp_http_list);
        usernameEditText = (EditText) findViewById(R.id.et_http_username);
        passwordEditText = (EditText) findViewById(R.id.et_http_password);
        resultTextView=(TextView) findViewById(R.id.tv_http_result);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        List<Province> provinces = DataSupport.findAll(Province.class);
        ArrayAdapter<Province> provinceArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, provinces);
        spnProvinces.setAdapter(provinceArrayAdapter);
        spnProvinces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Province province = (Province) parent.getAdapter().getItem(position);
                int provinceId = province.getId();
                List<City> cities = DataSupport.where("province_id = ?", String.valueOf(provinceId)).find(City.class);
//                Log.i(TAG, cities.size() + "");
//                Log.i(TAG,cities.get(1).getCity() +cities.get(0).getCity());
                if (cities.size() < 2 || (cities.get(0).getCity().equals("市辖区") && cities.get(1).getCity().equals("县")))
                {
                    cityName = province.getProvince();
                    cityName = cityName.replaceAll("特别行政区","");
//                    Log.i(TAG,cityName);
                    spnCities.setVisibility(View.INVISIBLE);
                    return;
                }
                spnCities.setVisibility(View.VISIBLE);
                ArrayAdapter<City> cityArrayAdapter = new ArrayAdapter<>(HttpActivity.this, android.R.layout.simple_spinner_dropdown_item, cities);
                spnCities.setAdapter(cityArrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        spnCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                City city = (City) parent.getAdapter().getItem(position);
                cityName = city.getCity();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        queryButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cityTextView.setText("正在查询" + cityName + "的天气");
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .build();
                final Request request = new Request.Builder()
                        .url(URL + cityName).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback()
                {
                    @Override
                    public void onFailure(Call call, IOException e)
                    {
                        cityTextView.setText("请求失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException
                    {
                        String result = response.body().string();
                        Log.i(TAG, result);
                        JSONObject jsonObject = null;
                        try
                        {
                            jsonObject = new JSONObject(result);
                            if (jsonObject.getInt("status") == 1000 && jsonObject.getString("desc").equals("OK"))
                            {
                                Gson gson = new Gson();
                                Message message = Message.obtain();
                                message.what = HttpActivity.REQUEST_STATUS_OK;
                                message.obj = cityName + "的天气";
                                handle.sendMessage(message);

                                weaters = new ArrayList<Weater>();
                                titles = new ArrayList<String>();
                                weaters.add(gson.fromJson(jsonObject.getJSONObject("data").get("yesterday").toString(), Weater.class));
                                weaters.addAll(Arrays.asList(gson.fromJson(jsonObject.getJSONObject("data").getJSONArray("forecast").toString(), Weater[].class)));
                                for (int i = 0; i < weaters.size(); i++)
                                {
                                    titles.add(weaters.get(i).getDate());
                                }
                                Message weaterResult = Message.obtain();
                                weaterResult.what = HttpActivity.WEATER_RESULT;
                                handle.sendMessage(weaterResult);
                            } else
                            {
                                Message message = Message.obtain();
                                message.what = HttpActivity.REQUEST_STATUS_ERROR;
                                message.obj = "错误:" + jsonObject.getString("desc");
                                handle.sendMessage(message);
                            }
                        }
                        catch (JSONException e)
                        {
                            Log.e(TAG, e.getMessage());
                            Message message = Message.obtain();
                            message.what = HttpActivity.REQUEST_STATUS_ERROR;
                            message.obj = "请求错误";
                            handle.sendMessage(message);
                        }
                    }
                });
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String urlStr = "https://d-star.xyz/android/login.php";
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .build();
                RequestBody requestBodyPost = new FormBody.Builder()
                        .add("user_id", username)
                        .add("password", password)
                        .build();
                Request requestPost = new Request.Builder()
                        .url(urlStr)
                        .post(requestBodyPost)
                        .build();
                okHttpClient.newCall(requestPost).enqueue(new Callback()
                {
                    @Override
                    public void onFailure(Call call, IOException e)
                    {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException
                    {
                        String result = response.body().string();
                        JSONObject jsonObject;
                        try
                        {
                            jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("return_code");
                            String res = jsonObject.getString("message");
                            if(code == 1)
                            {
                                Message message = Message.obtain();
                                message.what = HttpActivity.POST_ERROR;
                                message.obj = res;
                                handle.sendMessage(message);
                            }
                            else
                            {
                                Message message = Message.obtain();
                                message.what = HttpActivity.POST_OK;
                                message.obj = res;
                                handle.sendMessage(message);
                            }
                        }
                        catch (JSONException e)
                        {
                            Message message = Message.obtain();
                            message.what = HttpActivity.POST_ERROR;
                            message.obj = "请求错误";
                            handle.sendMessage(message);
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private class MyHandle extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case HttpActivity.REQUEST_STATUS_OK:
                    cityTextView.setText(msg.obj.toString());
                    break;
                case HttpActivity.REQUEST_STATUS_ERROR:
                    cityTextView.setText(msg.obj.toString());
                    tabLayout.removeAllTabs();
                    viewPager.removeAllViews();
                    break;
                case HttpActivity.WEATER_RESULT:
                    tabLayout.removeAllTabs();
                    for (int i = 0; i < weaters.size(); i++)
                    {
                        tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
                    }
                    viewPager.setAdapter(new WeaterPagersAdapter(getSupportFragmentManager(), weaters, titles));
                    tabLayout.setupWithViewPager(viewPager);
                    break;
                case HttpActivity.POST_ERROR:
                    resultTextView.setText("错误:" + msg.obj.toString());
                    resultTextView.setTextColor(Color.RED);
                    break;
                case HttpActivity.POST_OK:
                    resultTextView.setText(msg.obj.toString());
                    resultTextView.setTextColor(Color.BLACK);
                    break;
            }
        }
    }

}
