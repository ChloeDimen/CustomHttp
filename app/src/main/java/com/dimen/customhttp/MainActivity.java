package com.dimen.customhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dimen.customhttp.http.Volley;
import com.dimen.customhttp.http.download.DownFileManager;
import com.dimen.customhttp.http.interfaces.IDataListener;

public class MainActivity extends AppCompatActivity {
    public static final String url = "http://192.168.20.183:8080/app/";
    public static final String LOGIN="Login.json";
    private static final String TAG = "dimen";
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.content);
    }

    /**
     * 1
     * 2
     *
     * @param view
     */
    public void login(View view) {
        User user = new User();
        user.setName("13343491234");
        user.setPassword("123456");
        for (int i = 0; i < 50; i++) {
            Volley.sendRequest(user, url, LoginRespense.class, new IDataListener<LoginRespense>() {
                @Override
                public void onSuccess(LoginRespense loginRespense) {
                    if (loginRespense!=null) {
                        Log.i(TAG, loginRespense.toString());
                    } else {
                        Log.i(TAG, "数据为null");
                    }
                }

                @Override
                public void onFail() {
                    Log.i(TAG, "获取失败");
                }
            });
        }
    }

    public void down(View view) {

        DownFileManager downFileService=new DownFileManager();
        downFileService.down(url+"com.jetway.custom_version_1_1.1.apk");

    }

    public void stop(View view) {
    }
}