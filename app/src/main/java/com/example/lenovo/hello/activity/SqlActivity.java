package com.example.lenovo.hello.activity;

import android.support.v4.app.Fragment;
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
import java.util.List;

/**
 * Created by lenovo on 2017/12/13.
 */

public class SqlActivity extends BaseActivity
{
    private String TAG = "SqlActivity";
    private EditText nameEditText, accountEditText, bankEditText;
    private Button addButton, findAllButton, clearButton, deleteALLButton;
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

        addButton = (Button) findViewById(R.id.btn_sql_add);
        findAllButton = (Button) findViewById(R.id.btn_sql_find_all);
        clearButton = (Button) findViewById(R.id.btn_sql_clear);
        deleteALLButton = (Button) findViewById(R.id.btn_sql_delete_all);

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
    }


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