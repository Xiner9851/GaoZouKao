package com.example.myapplication.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.myapplication.App.MyApp;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

/**
 * Created by Administrator on 2018/3/14.
 */

public  abstract class BaseActivity extends AppCompatActivity{
    private BaseFragment lastFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        MyApp.content=this;
        init();
        loadDate();
    }
    protected abstract int getLayoutId();
    protected abstract void init();
    protected  abstract void loadDate();
    protected BaseFragment setContentView(Class<? extends BaseFragment> fragmentClass){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        String simpleName = fragmentClass.getSimpleName();

        BaseFragment fragment = (BaseFragment) supportFragmentManager.findFragmentByTag(simpleName);
        try {
             if (fragment==null) {
                 fragment=fragmentClass.newInstance();
                 transaction.add(R.id.container,fragment , simpleName);
             }
             if (lastFragment!=null)

            transaction.hide(lastFragment);
            transaction.show(fragment);



             transaction.show(fragment);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
       lastFragment=fragment;
        transaction.commit();
        return fragment;
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApp.content=null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApp.content=this;
    }
}
