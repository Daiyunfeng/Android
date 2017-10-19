package com.example.lenovo.hello.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.entity.Area;
import com.example.lenovo.hello.entity.City;
import com.example.lenovo.hello.entity.Province;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Lab2-2
 * Created by lenovo on 2017/9/28.
 */

public class ButtonActivity extends Activity
{
    private static final String TAG = "ButtonActivity";
    private Spinner spnCities, spnProvinces, spnAreas;
    private RadioButton rbBoy, rbGirl;
    private RadioGroup groupSex;
    private CheckBox cbMath, cbChinese, cbEnglish;
    private ImageView image;
    private ToggleButton tb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_button);
        initSpinner();
        initRadioButton();
        initCheckBox();
    }

    private void initCheckBox()
    {
        cbMath = (CheckBox) findViewById(R.id.cb_button_math);
        cbChinese = (CheckBox) findViewById(R.id.cb_button_chinese);
        cbEnglish = (CheckBox) findViewById(R.id.cb_button_english);
        cbMath.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        cbChinese.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        cbEnglish.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        image = (ImageView) findViewById(R.id.iv_button_img);
        tb = (ToggleButton) findViewById(R.id.tb_button_open);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    image.setImageResource(R.mipmap.btn_on);
                } else
                {
                    image.setImageResource(R.mipmap.btn_press);
                }
            }
        });
    }

    public void initSpinner()
    {
        spnCities = (Spinner) findViewById(R.id.spn_button_cities);
        spnProvinces = (Spinner) findViewById(R.id.spn_button_provinces);
        spnAreas = (Spinner) findViewById(R.id.spn_button_areas);

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
                ArrayAdapter<City> cityArrayAdapter = new ArrayAdapter<>(ButtonActivity.this, android.R.layout.simple_spinner_dropdown_item, cities);
                spnCities.setAdapter(cityArrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                spnProvinces.setSelection(0);
            }
        });

        spnCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                City city = (City) parent.getAdapter().getItem(position);
                int cityId = city.getId();
                List<Area> areas = DataSupport.where("city_id = ?", String.valueOf(cityId)).find(Area.class);
                ArrayAdapter<Area> areaArrayAdapter = new ArrayAdapter<>(ButtonActivity.this, android.R.layout.simple_spinner_dropdown_item, areas);
                spnAreas.setAdapter(areaArrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                spnCities.setSelection(0);
            }
        });
    }

    public void initRadioButton()
    {
        rbBoy = (RadioButton) findViewById(R.id.rb_button_boy);
        rbGirl = (RadioButton) findViewById(R.id.rb_button_girl);
        groupSex = (RadioGroup) findViewById(R.id.group_button_sex);
        groupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId)
            {
                if (checkedId == rbBoy.getId())
                {
                    Toast.makeText(ButtonActivity.this, "你点了" + rbBoy.getText(), Toast.LENGTH_SHORT).show();
                } else
                {
                    Toast.makeText(ButtonActivity.this, "你点了" + rbGirl.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class MyOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            if (isChecked == true)
            {
                Toast.makeText(ButtonActivity.this, "选中" + buttonView.getText(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}

