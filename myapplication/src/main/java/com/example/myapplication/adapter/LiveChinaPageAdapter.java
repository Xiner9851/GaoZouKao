package com.example.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myapplication.ui.livechina.LiveChinaContentFragment;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 */

public class LiveChinaPageAdapter extends FragmentPagerAdapter {
    private List<String> titles;
    private List<LiveChinaContentFragment> fragments;
    public LiveChinaPageAdapter(FragmentManager fm, List<String> titles, List<LiveChinaContentFragment> fragments) {
        super(fm);
        this.titles=titles;
        this.fragments=fragments;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
