package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //2018.3.30.xiner
    List<BeanLai.ResultBean.DataBean> data=new ArrayList<>();
    private PullLoadMoreRecyclerView pullload;
    private Handler han=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                BeanLai.ResultBean result = (BeanLai.ResultBean) msg.obj;
                List<BeanLai.ResultBean.DataBean> date1 = result.getData();
                MainActivity.this.data.addAll(date1);

                Toast.makeText(MainActivity.this,date1.get(0).getTitle(), Toast.LENGTH_SHORT).show();
                RecyAdapter recyAdapter = new RecyAdapter(MainActivity.this, MainActivity.this.data);
                pullload.setAdapter(recyAdapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initLoad();
        pullload.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                han.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        pullload.setPullLoadMoreCompleted();
                    }
                },2000);

            }

            @Override
            public void onLoadMore() {
                    han.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            initLoad();
                            pullload.setPullLoadMoreCompleted();
                        }
                    },2000);
            }
        });

    }

    private void initLoad() {
        Retrofit retrofit = new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://v.juhe.cn/").build();
        JieKou top = retrofit.create(JieKou.class);
        Call<BeanLai> top1 = top.loadDate("top", "097060266650f67b2cebd2a06aded587");

        top1.enqueue(new Callback<BeanLai>() {
            @Override
            public void onResponse(Call<BeanLai> call, Response<BeanLai> response) {


                han.sendMessage(han.obtainMessage(1,response.body().getResult()));



            }

            @Override
            public void onFailure(Call<BeanLai> call, Throwable t) {

            }
        });
    }

    private void initView() {
        pullload = (PullLoadMoreRecyclerView) findViewById(R.id.pullload);
        pullload.setFooterViewText("加载更多.....");
        pullload.setFooterViewTextColor(R.color.colorPrimary);
        pullload.setStaggeredGridLayout(2);


    }

}
