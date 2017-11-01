package com.example.lenovo.hello.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.utils.MyToast;
import com.example.lenovo.hello.utils.RandomColor;

/**
 * Created by lenovo on 2017/10/31.
 */

public class MenuActivity extends Activity
{
    private final static int ITEM_RED = 1, ITEM_BULE = 2, ITEM_GREEN = 3, ITEM_CHIESE = 4, ITEM_MATH = 5, ITEM_ENGLISH = 6;
    private TextView textView;
    private Button buttonMenu1, buttonMenu2, buttonMenu3;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_menu);
        //随机背景颜色
        this.getWindow().getDecorView().setBackgroundColor(RandomColor.randomColorInt());
        init();
        buttonMenu1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PopupMenu pm = new PopupMenu(MenuActivity.this, buttonMenu1);
                getMenuInflater().inflate(R.menu.menu_color, pm.getMenu());
                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        switch (item.getItemId())
                        {
                            case R.id.menu_item_red:
                                textView.setTextColor(Color.RED);
                                break;
                            case R.id.menu_item_blue:
                                textView.setTextColor(Color.BLUE);
                                break;
                            case R.id.menu_item_green:
                                textView.setTextColor(Color.GREEN);
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
                pm.show();
            }
        });

        buttonMenu2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PopupMenu pm = new PopupMenu(MenuActivity.this, buttonMenu2);
                Menu menu = pm.getMenu();
                menu.add(Menu.NONE, ITEM_RED, 1, "RED");
                menu.add(Menu.NONE, ITEM_BULE, 2, "BLUE");
                menu.add(Menu.NONE, ITEM_GREEN, 3, "GREEN");
                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        switch (item.getItemId())
                        {
                            case ITEM_RED:
                                textView.setTextColor(Color.RED);
                                break;
                            case ITEM_BULE:
                                textView.setTextColor(Color.BLUE);
                                break;
                            case ITEM_GREEN:
                                textView.setTextColor(Color.GREEN);
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
                pm.show();
            }
        });

        buttonMenu3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PopupMenu pm = new PopupMenu(MenuActivity.this, buttonMenu3);
                Menu menu = pm.getMenu();
                getMenuInflater().inflate(R.menu.menu_check, pm.getMenu());
                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        if(item.isChecked()==true)
                        {
                            item.setChecked(false);
                        }
                        else
                        {
                            item.setChecked(true);
                        }
                        return true;
                    }
                });
                pm.show();
            }
        });

        //ListView
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        for (int i = 0; i < 10; i++)
        {
            menu.add(Menu.NONE, i, i, "第" + i);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        MyToast.showText(MenuActivity.this, "选中" + item.getTitle());
        return true;
    }

    private void init()
    {
        textView = (TextView) findViewById(R.id.tv_menu);
        buttonMenu1 = (Button) findViewById(R.id.btn_menu_popupmenu1);
        buttonMenu3 = (Button) findViewById(R.id.btn_menu_menu);
        buttonMenu2 = (Button) findViewById(R.id.btn_menu_popupmenu2);
        listView = (ListView) findViewById(R.id.lv_menu);
    }
}
