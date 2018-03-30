package com.example.myapplication.fragment;

import android.view.View;

import com.example.myapplication.App.MyApp;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;

/**
 * Created by Administrator on 2018/3/14.
 */

public class CCTVFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.cctv;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void loadDate() {

    }



    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    public void upDateTitle() {
        ((MainActivity) MyApp.content).getTitleTV().setText("CCTV");
        ((MainActivity)MyApp.content).getPandaIcon().setVisibility(View.INVISIBLE);
    }
}
