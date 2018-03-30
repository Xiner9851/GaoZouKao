package com.example.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.App.MyApp;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.HomeAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.entity.HomeLai;
import com.google.gson.Gson;
import com.recker.flybanner.FlyBanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/14.
 */

public class HomeFragment extends BaseFragment {
    private FlyBanner lunbo;
    private RecyclerView recy;
    private ArrayList<String> lunlist;
    private ArrayList<Object> datalist;
    private Handler han=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                String xin= (String) msg.obj;
               // Toast.makeText(getContext(), xin, Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                HomeLai homeLai = gson.fromJson(xin, HomeLai.class);
                HomeLai.DataBean data = homeLai.getData();
                HomeLai.DataBean.ChinaliveBean chinalive = data.getChinalive();
                HomeLai.DataBean.PandaliveBean pandalive = data.getPandalive();
                HomeLai.DataBean.WallliveBean walllive = data.getWalllive();
                HomeLai.DataBean.PandaeyeBean pandaeye = data.getPandaeye();





                datalist.add(chinalive);
                datalist.add(pandalive);
                datalist.add(walllive);
                datalist.add(pandaeye);
                List<HomeLai.DataBean.BigImgBean> bigImg = homeLai.getData().getBigImg();
                String image = bigImg.get(0).getImage();
                String image2 = bigImg.get(1).getImage();
                String image3 = bigImg.get(2).getImage();
                String image4 = bigImg.get(3).getImage();
                lunlist.add(image);
                lunlist.add(image2);
                lunlist.add(image3);
                lunlist.add(image4);
                lunbo.setImagesUrl(lunlist);
                HomeAdapter homeAdapter = new HomeAdapter(getContext(),datalist);
                recy.setAdapter(homeAdapter);


            }


        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void init() {
            lunlist=new ArrayList<>();
        datalist=new ArrayList<>();
    }

    @Override
    protected void loadDate() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        final Request request = new Request.Builder().url("http://www.ipanda.com/kehuduan/PAGE14501749764071042/index.json")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            han.sendMessage(han.obtainMessage(1,response.body().string()));
            }
        });

    }


    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    public void upDateTitle() {
        ((MainActivity) MyApp.content).getTitleTV().setText("");
        ((MainActivity) MyApp.content).getPandaIcon().setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment like bottom ... and run LayoutCreator again
        View view = View.inflate(getActivity(), R.layout.home_fragment, null);
        lunbo= (FlyBanner) view.findViewById(R.id.lunbo);
        recy= (RecyclerView) view.findViewById(R.id.recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recy.setLayoutManager(linearLayoutManager);
        return view;
    }
}
