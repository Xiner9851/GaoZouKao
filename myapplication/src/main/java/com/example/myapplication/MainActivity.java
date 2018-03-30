package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.fragment.CCTVFragment;
import com.example.myapplication.fragment.ChinaLiveFragment;
import com.example.myapplication.fragment.HomeFragment;
import com.example.myapplication.fragment.PandaEyeFragment;
import com.example.myapplication.fragment.PandaLiveFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    ImageView pandaIcon;
             TextView titleTV;
             ImageView personImg;
              RadioButton homeBtn;
             RadioButton pandaEyeBtn;
                  RadioButton pandaLiveBtn;
              RadioButton liveChinaBtn;
              RadioButton cctvBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        homeBtn= (RadioButton) findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(this);
        pandaLiveBtn= (RadioButton) findViewById(R.id.pandaLiveBtn);
        pandaLiveBtn.setOnClickListener(this);
        pandaEyeBtn= (RadioButton) findViewById(R.id.pandaEyeBtn);
        pandaEyeBtn.setOnClickListener(this);
        liveChinaBtn= (RadioButton) findViewById(R.id.liveChinaBtn);
        liveChinaBtn.setOnClickListener(this);
        cctvBtn= (RadioButton) findViewById(R.id.cctvBtn);
        cctvBtn.setOnClickListener(this);
        pandaIcon= (ImageView) findViewById(R.id.pandaIcon);
        titleTV= (TextView) findViewById(R.id.titleTV);
    }

    @Override
    protected void loadDate() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cctvBtn:
                setContentView(CCTVFragment.class).upDateTitle();
               break;
            case R.id.homeBtn:
                setContentView(HomeFragment.class).upDateTitle();
                break;
            case R.id.liveChinaBtn:
                setContentView(ChinaLiveFragment.class).upDateTitle();
                break;
            case R.id.pandaEyeBtn:
                setContentView(PandaEyeFragment.class).upDateTitle();
                break;
            case R.id.pandaLiveBtn:
                setContentView(PandaLiveFragment.class).upDateTitle();
                break;


        }
    }

    public TextView getTitleTV() {
        return titleTV;
    }

    public ImageView getPandaIcon() {
        return pandaIcon;
    }
}
