package com.example.administrator.gaozoukao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
//我是物联网专业H1708C班赵慧鑫正，在考B场
public class MainActivity extends AppCompatActivity {

    private RecyclerView recy;
    private Handler han=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                String s= (String) msg.obj;
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                lai lai = gson.fromJson(s, lai.class);
                com.example.administrator.gaozoukao.lai.ResultBean result = lai.getResult();
                final List<com.example.administrator.gaozoukao.lai.ResultBean.DataBean> data = result.getData();
                RecyAdapter recyAdapter = new RecyAdapter(data, MainActivity.this);
                recy.setAdapter(recyAdapter);
                recyAdapter.setOnClickListio(new RecyAdapter.OnClick() {
                    @Override
                    public void setOnClick(View v, int potion) {
                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        intent.putExtra("xin",data.get(potion).getUrl());
                        startActivity(intent);
                    }
                });



            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initDate();


    }

    private void initDate() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response proceed = chain.proceed(request);
                if (TextUtils.isEmpty(proceed.cacheControl().toString())){
                    Response build = proceed.newBuilder().addHeader("Cache-Control", "max-age=" + 60 * 60 * 120).build();
                    return build;
                }
                return proceed;
            }
        };
        OkHttpClient build = new OkHttpClient.Builder().cache(new Cache(this.getCacheDir(), 1024 * 1024 * 10)).addNetworkInterceptor(interceptor).build();

        final Request request = new Request.Builder().url("http://v.juhe.cn/toutiao/index?type=top&key=097060266650f67b2cebd2a06aded587").build();
        build.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                han.sendMessage(han.obtainMessage(1,response.body().string()));

            }
        });
    }

    private void initView() {
        recy = (RecyclerView) findViewById(R.id.recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recy.setLayoutManager(linearLayoutManager);
    }
}
