package com.example.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.App.MyApp;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.PandaEyeAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.entity.PandaEyeBean;
import com.example.myapplication.entity.PandaEyeErBean;
import com.example.myapplication.http.Panda;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/3/14.
 */

public class PandaEyeFragment extends BaseFragment {


    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private ArrayList<Object> mArraylist;
    private Handler han=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                String xin= (String) msg.obj;
                Gson gson = new Gson();
                PandaEyeErBean pandaEyeErBean = gson.fromJson(xin, PandaEyeErBean.class);
                mArraylist.add(pandaEyeErBean);
            }

        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.panda_eye_fragment;
    }

    @Override
    protected void init() {
        mArraylist=new ArrayList<>();
    }

    @Override
    protected void loadDate() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.ipanda.com/kehuduan/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Panda panda = retrofit.create(Panda.class);
        panda.pandaEye().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<PandaEyeBean>() {
                    @Override
                    public void accept(PandaEyeBean pandaEyeBean) throws Exception {
                        //Toast.makeText(getContext(), pandaEyeBean.getData().getListurl(), Toast.LENGTH_SHORT).show();
                        PandaEyeBean.DataBean data = pandaEyeBean.getData();
                        mArraylist.add(data);
                        String listurl = data.getListurl();
                        zhangyuSB(listurl);

                        PandaEyeAdapter eye=new PandaEyeAdapter(mArraylist,getContext());
                        pullLoadMoreRecyclerView.setAdapter(eye);

                    }
                });


    }
    private void  zhangyuSB(String url){
        OkHttpClient build = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).build();
        build.newCall(request).enqueue(new Callback() {



            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                han.sendMessage(han.obtainMessage(1,string));
            }
        });
    }


    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    public void upDateTitle() {
        ((MainActivity) MyApp.content).getTitleTV().setText("熊猫观察");
        ((MainActivity) MyApp.content).getPandaIcon().setVisibility(View.INVISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment like bottom ... and run LayoutCreator again
        View view = View.inflate(getActivity(), R.layout.panda_eye_fragment, null);
        pullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pull_recy);
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                han.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {

                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        });
        return view;
    }
}
