package com.example.myapplication.http;


import com.example.myapplication.entity.ChinaLiveBean;
import com.example.myapplication.entity.LiveChinaContentBean;
import com.example.myapplication.entity.PandaEyeBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


/**
 * Created by Administrator on 2018/3/14.
 */

public interface Panda {
    @GET(url.CHINALIVE)
    Observable<ChinaLiveBean> chianlive();
    @GET
    Observable<LiveChinaContentBean> chinliveContent(@Url String url);
    @GET(url.PANDAEYE)
    Observable<PandaEyeBean> pandaEye();
}
