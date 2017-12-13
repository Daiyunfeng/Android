package com.example.lenovo.hello.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.activity.ListActivity;
import com.example.lenovo.hello.utils.MyToast;

/**
 * Created by lenovo on 2017/12/11.
 */

public class MyPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private static final String TAG = "MyPreferenceFragment", SP_ITEM_LANGUAGE = "language";
    private PreferenceScreen languagePreferenceScreen;
    private ListPreference vendorListPreference;
    private SharedPreferences sp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);

        init();
    }

    @Override
    public void onResume()
    {
        loadByPreference();
        super.onResume();
    }

    private void loadByPreference()
    {
        String value = sp.getString("list_vendor", "NULL");
        if (!value.equals("NULL"))
        {
            vendorListPreference.setSummary(value);
        }
        value = sp.getString(SP_ITEM_LANGUAGE, "NULL");
        if (!value.equals("NULL"))
        {
            languagePreferenceScreen.setSummary(value);
        }
    }

    private void init()
    {
        languagePreferenceScreen = (PreferenceScreen) findPreference("ps_lan");
        vendorListPreference = (ListPreference) findPreference("list_vendor");
        sp = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        sp.registerOnSharedPreferenceChangeListener(this);

        loadByPreference();

        languagePreferenceScreen.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
        {
            @Override
            public boolean onPreferenceClick(Preference preference)
            {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ListActivity.class);
                startActivityForResult(intent, 111); //临时的随便写的
                return false;
            }
        });
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        if (key.equals("list_vendor"))
        {
            Log.i(TAG, key + "---" + sharedPreferences.getString(key, "NULL"));
            String value = sp.getString(key, "NULL");
            if (!value.equals("NULL"))
            {
                vendorListPreference.setSummary(value);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "result:" + resultCode);
        if (Activity.RESULT_OK != resultCode)
        {
            return;
        }
        String language = data.getStringExtra("language");
        languagePreferenceScreen.setSummary(language.toString());
        sp.edit().putString(SP_ITEM_LANGUAGE, language).commit();
    }
}
