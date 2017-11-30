package com.example.lenovo.hello.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.utils.MyToast;
import com.example.lenovo.hello.utils.RandomColor;

import java.lang.reflect.Method;

/**
 * Created by lenovo on 2017/10/31.
 */

public class MenuActivity extends AppCompatActivity
{
    private final static String TAG = "MenuActivity";
    private final static int ITEM_RED = 1, ITEM_BULE = 2, ITEM_GREEN = 3, ITEM_COLOR = 4;
    private TextView textView;
    private Button buttonMenu1, buttonMenu2, buttonMenu3;
    private ListView listView;
    private Toolbar toolbar;
    private int selected, checked;
    private String[] items;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_menu);
        //随机背景颜色
        //this.getWindow().getDecorView().setBackgroundColor(RandomColor.randomColorInt());
        //toolbar
        toolbar = (Toolbar) findViewById(R.id.tb_menu);
        toolbar.setTitle("大标题");
        toolbar.setSubtitle("小标题");
        setSupportActionBar(toolbar);
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
                SubMenu subMenu = menu.addSubMenu(Menu.NONE, ITEM_COLOR, 1, "COLOR");
                subMenu.add(Menu.NONE, ITEM_RED, 1, "RED");
                subMenu.add(Menu.NONE, ITEM_BULE, 2, "BLUE");
                subMenu.add(Menu.NONE, ITEM_GREEN, 3, "GREEN");
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
                getMenuInflater().inflate(R.menu.menu_check, pm.getMenu());
                pm.getMenu().findItem(checked).setChecked(true);
                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        if (item.isChecked() == true)
                        {
                            //单选
                            return true;
                        } else
                        {
                            checked = item.getItemId();
                            item.setChecked(true);
                        }
                        return true;
                    }
                });
                pm.show();
            }
        });

        //ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MenuActivity.this, android.R.layout.simple_list_item_1);
        for (int i = 0; i < items.length; i++)
        {
            adapter.add(items[i]);
        }
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                selected = position;
                return false;
            }
        });
        registerForContextMenu(listView);

//        linearLayout.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                //do nothing
//                //让主界面的点击事件覆盖
//            }
//        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        menu.add(Menu.NONE, ITEM_RED, 1, "RED");
        menu.add(Menu.NONE, ITEM_BULE, 2, "BLUE");
        menu.add(Menu.NONE, ITEM_GREEN, 3, "GREEN");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int indexOfContextMenu = info.position;
        String str = items[selected];
        str += item.toString();
        textView.setText(str);
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
//        for (int i = 0; i < 10; i++)
//        {
//            menu.add(Menu.NONE, i, i, "第" + i);
//        }
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.action_search)
        {
            Intent intent = new Intent();
            intent.setClass(MenuActivity.this, SubMenuActivity.class);
            startActivity(intent);
        } else
        {
            MyToast.showText(MenuActivity.this, "选中" + item.getTitle());
        }
        return true;
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu)
    {
        if (menu != null)
        {
            if (menu.getClass().getSimpleName().equals("MenuBuilder"))
            {
                try
                {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                }
                catch (Exception e)
                {
                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    private void init()
    {
        textView = (TextView) findViewById(R.id.tv_menu);
        buttonMenu1 = (Button) findViewById(R.id.btn_menu_popupmenu1);
        buttonMenu3 = (Button) findViewById(R.id.btn_menu_menu);
        buttonMenu2 = (Button) findViewById(R.id.btn_menu_popupmenu2);
        listView = (ListView) findViewById(R.id.lv_menu);
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        items = new String[]{"我喜欢", "我讨厌", "我不在乎"};
        selected = 0;
        checked = R.id.menu_item_check1;
    }
}
