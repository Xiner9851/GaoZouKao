package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/3/16.
 */

public interface JieKou  {
    @GET("toutiao/index")
    Call<BeanLai> loadDate(@Query("type") String type,@Query("key") String key);
}
