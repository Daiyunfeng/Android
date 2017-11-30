package com.example.lenovo.hello.activity;

import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.utils.HttpURLConnectionUtil;
import com.example.lenovo.hello.utils.MyToast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by lenovo on 2017/11/16.
 */

public class MultiThreadActivity extends AppCompatActivity
{
    private final static String TAG = "MultiThreadActivity";
    private final static int TV_MULTI_THREAD = 1, DOWNLOAD_RATE = 2, INCREACE_PROGRESS_BAR = 3;   //TV_MULTI_THREAD 代表计算求和
    private int n;
    private boolean stopFlag, downloadFlag;
    private Button simpleThread, multiThread, endThread, oneButton, fiveButton, tenButton, downloadButton;
    private TextView tvSimpleThread, tvMultiThread, tvDownload;
    private EditText editText;
    private ProgressBar progressBar;
    private Runnable runnable;
    private Handler handler;
    private Thread thread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_multithread);
        init();
    }

    private void init()
    {
        n = 3000;
        handler = new MyHandle();
        simpleThread = (Button) findViewById(R.id.btn_simple_thread);
        multiThread = (Button) findViewById(R.id.btn_multi_thread);
        endThread = (Button) findViewById(R.id.btn_end_thread);
        oneButton = (Button) findViewById(R.id.btn_thread_1);
        fiveButton = (Button) findViewById(R.id.btn_thread_5);
        tenButton = (Button) findViewById(R.id.btn_thread_10);
        downloadButton = (Button) findViewById(R.id.btn_thread_download);
        editText = (EditText) findViewById(R.id.et_thread_input);
        tvDownload = (TextView) findViewById(R.id.tv_thread_download);
        progressBar = (ProgressBar) findViewById(R.id.pb_thread_horizontal);
        tvSimpleThread = (TextView) findViewById(R.id.tv_simple_thread);
        tvMultiThread = (TextView) findViewById(R.id.tv_multi_thread);
        tvSimpleThread.setText("0");
        tvMultiThread.setText("0");

        stopFlag = true;
        downloadFlag = true;
        simpleThread.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int t, sum = Integer.parseInt(tvMultiThread.getText().toString());
                for (int i = 0; i <= n; i++)
                {
                    t = (int) 1e5;
                    while (t-- != 0)
                        ;
                    sum += i;
                    tvSimpleThread.setText("" + sum);
                }
            }
        });

        multiThread.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (stopFlag)
                {
                    stopFlag = false;
                    runnable = new MyRunnable();
                    thread = new Thread(runnable);
                    thread.start();
                }
            }
        });

        endThread.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                thread.interrupt();
                stopFlag = true;
            }
        });

        oneButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new Thread(new MyProgressBarRunnable(1)).start();
                String str = editText.getText().toString();
                if (str.trim().equals(""))
                {
                    return;
                } else
                {
                    Message message = Message.obtain();
                    message.what = INCREACE_PROGRESS_BAR;
                    message.obj = str;
                    handler.sendMessage(message);
                }
            }
        });

        fiveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new Thread(new MyProgressBarRunnable(5)).start();
                String str = editText.getText().toString();
                if (str.trim().equals(""))
                {
                    return;
                } else
                {
                    Message message = Message.obtain();
                    message.what = INCREACE_PROGRESS_BAR;
                    message.obj = str;
                    handler.sendMessage(message);
                }
            }
        });

        tenButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new Thread(new MyProgressBarRunnable(10)).start();
                String str = editText.getText().toString();
                if (str.trim().equals(""))
                {
                    return;
                } else
                {
                    Message message = Message.obtain();
                    message.what = INCREACE_PROGRESS_BAR;
                    message.obj = str;
                    handler.sendMessage(message);
                }
            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (downloadFlag)
                {
                    downloadFlag = false;
                    MyAsyncTask mTask = new MyAsyncTask();
                    mTask.execute("http://acm.hznu.edu.cn/OJ/");
                }
            }
        });
    }

    private class MyRunnable implements Runnable
    {

        @Override
        public void run()
        {
            int t, sum = Integer.parseInt(tvMultiThread.getText().toString());
            for (int i = 0; i <= n; i++)
            {
                if (stopFlag == true)
                {
                    return;
                }
                t = (int) 1e5;
                while (t-- != 0)
                    ;
                sum += i;
//                tvSimpleThread.setText(""+sum);   //子线程
                Message message = Message.obtain();
                message.what = TV_MULTI_THREAD;
                message.obj = "" + sum;
                handler.sendMessage(message);
            }
            stopFlag = true;
        }
    }

    /**
     * 进度条操作的runnable实现类
     */
    private class MyProgressBarRunnable implements Runnable
    {
        private int number;

        public MyProgressBarRunnable(int number)
        {
            this.number = number;
        }

        @Override
        public void run()
        {
            try
            {
                Thread.sleep(3000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            progressBar.incrementProgressBy(number);
        }
    }

    /**
     * 下载界面 并输出进度
     */
    private class MyAsyncTask extends AsyncTask<String,Integer,String>
    {
        private static final String TAG = "MyAsyncTask";
        @Override
        protected String doInBackground(String... params)
        {
            try
            {
                Boolean gzipFlag = false;
//                URL url = new URL("http://www.bilibili.com/");        //0或者-1 不知怎么解决
//                URL url = new URL("https://www.apache.org");
//                URL url = new URL("http://acm.hznu.edu.cn/OJ/");
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(6000);
                conn.setConnectTimeout(3000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
                conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
                conn.setRequestProperty("Accept-Encoding", "gzip"); //Transfer-Encoding 始终为chunked identity;q=1.0,gzip;q=0.5
                conn.connect();

                Map<String ,List<String>> header = conn.getHeaderFields();  //http://nxlhero.blog.51cto.com/962631/1868483
                for(String key : header.keySet())
                {
                    Log.i(TAG,key+"---");
                    for(String value : header.get(key))
                    {
                        Log.i(TAG,value);
                    }
                    Log.i(TAG,"-------------");
                }
                int total = conn.getContentLength();
                InputStream is = conn.getInputStream();
//                GZIPInputStream gzin = null;
                if(header.containsKey("Content-Encoding"))
                {
                    for(String value : header.get("Content-Encoding"))
                    {
                        if(value.equals("gzip"))
                        {
                            gzipFlag = true;
                            break;
                        }
                    }
                }
//                if(gzipFlag)
//                    gzin = new GZIPInputStream(is);
                Log.i(TAG,total+"");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int count = 0;
                int length = -1;
                while ((length = is.read(buf)) != -1)//gzipFlag==true?((length = is.read(buf)) != -1):((length = gzin.read(buf)) != -1)
                {
                    baos.write(buf, 0, length);
                    count += length;
                    if (total > 0)
                    {
                        publishProgress((int) ((count / (float) total) * 100));
                    }
                    Log.i(TAG,new String(buf));
                    Thread.sleep(1000);
                }
                conn.disconnect();
                downloadFlag = true;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            tvDownload.setText(values[0]+"%");
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
        }
    }

    /**
     * message 类TV_MULTI_THREAD 代表计算求和
     */
    private class MyHandle extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case TV_MULTI_THREAD:
                    tvMultiThread.setText(msg.obj.toString());
                    break;
                case DOWNLOAD_RATE:
                    tvDownload.setText(msg.obj.toString());
                    break;
                case INCREACE_PROGRESS_BAR:
                    MyToast.showText(MultiThreadActivity.this, msg.obj.toString());
                    break;
                default:
                    //do nothing
            }
        }
    }
}
