package com.example.myapplication.http;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/3/14.
 */

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;
    private Retrofit retrofit;
    private Panda panda;
    private RetrofitUtils(){
        retrofit = new Retrofit.Builder()
                .baseUrl(url.SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        panda = retrofit.create(Panda.class);
    }
    public static RetrofitUtils getInstance(){
        if (retrofitUtils == null)
            retrofitUtils = new RetrofitUtils();
        return retrofitUtils;
    }

    public Panda getPandaService() {
        return panda;
    }
}
