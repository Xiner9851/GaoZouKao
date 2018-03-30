package com.example.myapplication.fragment;

import android.database.Observable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.App.MyApp;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.LiveChinaPageAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.entity.ChinaLiveBean;
import com.example.myapplication.http.RetrofitUtils;
import com.example.myapplication.ui.livechina.LiveChinaContentFragment;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Administrator on 2018/3/14.
 */

public class ChinaLiveFragment extends BaseFragment {


    private TabLayout LiveChinaTabLayout;
    private ViewPager LiveChinaViewPager;
    private ChinaLiveBean chinaLiveBean;
    private List<LiveChinaContentFragment> contentFragment;
    private LiveChinaPageAdapter pageAdapter;
    private List<String> titles;

    @Override
    protected int getLayoutId() {
        return R.layout.panda_chain;
    }

    @Override
    protected void init() {
        contentFragment = new ArrayList<>();
        titles = new ArrayList<>();
        pageAdapter = new LiveChinaPageAdapter(getChildFragmentManager(), titles, contentFragment);
        LiveChinaViewPager.setAdapter(pageAdapter);
        LiveChinaTabLayout.setupWithViewPager(LiveChinaViewPager);

    }

    @Override
    protected void loadDate() {
        chinaLive();

    }

    private void chinaLive() {

        RetrofitUtils.getInstance().getPandaService().chianlive()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.functions.Consumer<ChinaLiveBean>() {
                    @Override
                    public void accept(ChinaLiveBean chinaLiveBean) throws Exception {
                        ChinaLiveFragment.this.chinaLiveBean = chinaLiveBean;
                        showTabLayout(chinaLiveBean);
                    }
                });
    }

    private void showTabLayout(ChinaLiveBean chinaLiveBean) {
        List<ChinaLiveBean.TablistBean> tablist = chinaLiveBean.getTablist();
        for (ChinaLiveBean.TablistBean tabListBean : tablist) {

            LiveChinaContentFragment fragment = new LiveChinaContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", tabListBean.getUrl());
            fragment.setArguments(bundle);
            contentFragment.add(fragment);
            titles.add(tabListBean.getTitle());
        }
        pageAdapter.notifyDataSetChanged();
    }


    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    public void upDateTitle() {
        ((MainActivity) MyApp.content).getTitleTV().setText("直播中国");
        ((MainActivity) MyApp.content).getPandaIcon().setVisibility(View.INVISIBLE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment like bottom ... and run LayoutCreator again
        View view = View.inflate(getActivity(), R.layout.panda_chain, null);
        LiveChinaTabLayout = (TabLayout) view.findViewById(R.id.LiveChinaTabLayout);
        LiveChinaViewPager = (ViewPager) view.findViewById(R.id.LiveChinaViewPager);
        return view;
    }
}
