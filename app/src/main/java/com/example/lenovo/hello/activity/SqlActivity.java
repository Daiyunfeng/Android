package com.example.lenovo.hello.activity;

import android.content.ContentValues;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.database.DBManager;
import com.example.lenovo.hello.entity.Bank;
import com.example.lenovo.hello.utils.MyToast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 2017/12/13.
 */

public class SqlActivity extends BaseActivity
{
    private String TAG = "SqlActivity";
    private EditText nameEditText, accountEditText, bankEditText,idEditText;
    private Button addButton, findAllButton, clearButton, deleteALLButton,updateIdButton,findIdButton,deleteIdButton;
    private ListView resultListView;

    @Override
    protected int getLayoutID()
    {
        return R.layout.activity_sql;
    }

    @Override
    protected void init()
    {
        //银行的数据库
        LitePal.use(DBManager.useDataBase(DBManager.DATABASE_2));
        nameEditText = (EditText) findViewById(R.id.et_sql_name);
        accountEditText = (EditText) findViewById(R.id.et_sql_account);
        bankEditText = (EditText) findViewById(R.id.et_sql_bank);
        resultListView = (ListView) findViewById(R.id.lv_sql_result);
        idEditText = (EditText) findViewById(R.id.et_sql_id);

        addButton = (Button) findViewById(R.id.btn_sql_add);
        findAllButton = (Button) findViewById(R.id.btn_sql_find_all);
        clearButton = (Button) findViewById(R.id.btn_sql_clear);
        deleteALLButton = (Button) findViewById(R.id.btn_sql_delete_all);
        findIdButton = (Button) findViewById(R.id.btn_sql_find_id);
        updateIdButton = (Button) findViewById(R.id.btn_sql_update_id);
        deleteIdButton = (Button) findViewById(R.id.btn_sql_delete_id);

        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bank bank = new Bank();
                String name = nameEditText.getText().toString();
                String bankName = bankEditText.getText().toString();
                String account = accountEditText.getText().toString();
                if (name.trim().equals("") || bankName.trim().equals("") || account.trim().equals(""))
                {
                    MyToast.showText(SqlActivity.this, "输入完整信息");
                    return;
                }
                bank.setName(name);
                bank.setBank(bankName);
                bank.setAccount(account);
                bank.save();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showResult(new ArrayList<Bank>());
            }
        });

        findAllButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                List<Bank> banks = DataSupport.findAll(Bank.class);
                showResult(banks);
            }
        });

        deleteALLButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DataSupport.deleteAll(Bank.class);
                MyToast.showText(SqlActivity.this, "删除成功");
            }
        });

        updateIdButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String str = idEditText.getText().toString();
                if(str.trim().equals(""))
                {
                    MyToast.showText(SqlActivity.this,"输入信息不全");
                    return;
                }
                int id = Integer.parseInt(str);

                String name = nameEditText.getText().toString();
                String bankName = bankEditText.getText().toString();
                String account = accountEditText.getText().toString();
                ContentValues contentValues = new ContentValues();
                if (name.trim().equals("") || bankName.trim().equals("") || account.trim().equals(""))
                {
                    MyToast.showText(SqlActivity.this, "输入完整信息");
                    return;
                }
                contentValues.put("name",name);
                contentValues.put("bank",bankName);
                contentValues.put("account",account);

                int count = DataSupport.update(Bank.class,contentValues,id);
                if(count == 0)
                {
                    MyToast.showText(SqlActivity.this,"不存在此ID的数据");
                    return;
                }
                MyToast.showText(SqlActivity.this,"修改成功");
            }
        });

        deleteIdButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String str = idEditText.getText().toString();
                if(str.trim().equals(""))
                {
                    MyToast.showText(SqlActivity.this,"输入信息不全");
                    return;
                }
                int id = Integer.parseInt(str);
                int count = DataSupport.delete(Bank.class,id);
                if(count == 0)
                {
                    MyToast.showText(SqlActivity.this,"不存在此ID的数据");
                    return;
                }
                MyToast.showText(SqlActivity.this,"删除成功");
            }
        });

        findIdButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    String str = idEditText.getText().toString();
                    if(str.trim().equals(""))
                    {
                        MyToast.showText(SqlActivity.this,"输入信息不全");
                        return;
                    }
                    int id = Integer.parseInt(str);
                    Bank bank = DataSupport.find(Bank.class, id);
                    showResult(Arrays.asList(bank));
                }
                catch (NullPointerException e)
                {
                    Log.e(TAG,e.toString());
                    MyToast.showText(SqlActivity.this,"不存在此ID的数据");
                }
            }
        });
    }

    /**
     * 将结果更新在listview上
     * @param banks
     */
    private void showResult(List<Bank> banks)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for (int i = 0; i < banks.size(); i++)
        {
            Bank bank = banks.get(i);
            String result = "ID:" + bank.getId() + ",姓名:" + bank.getName() + ",账户:" + bank.getAccount() + ",银行:" + bank.getBank();
            adapter.add(result);
        }
        resultListView.setAdapter(adapter);
    }
}