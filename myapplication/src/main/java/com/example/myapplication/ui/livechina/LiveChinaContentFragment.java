package com.example.myapplication.ui.livechina;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.LiveChinaContentAdapter;
import com.example.myapplication.adapter.LiveChinaPageAdapter;
import com.example.myapplication.entity.LiveChinaContentBean;
import com.example.myapplication.http.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveChinaContentFragment extends Fragment {


    private ListView LIveChinaListView;
    private List<LiveChinaContentBean.LiveBean> lives;
    private LiveChinaContentAdapter contentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_china_content, container, false);


        initView(view);
        return view;
    }
    private void init(){
        lives=new ArrayList<>();
        contentAdapter=new LiveChinaContentAdapter(getActivity(),lives);
        LIveChinaListView.setAdapter(contentAdapter);
    }
    private void loadDate(String url){
        RetrofitUtils.getInstance().getPandaService().chinliveContent(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LiveChinaContentBean>() {
                    @Override
                    public void accept(LiveChinaContentBean liveChinaContentBean) throws Exception {
                       lives.addAll( liveChinaContentBean.getLive());
                        contentAdapter.notifyDataSetChanged();
                    }
                });
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        Bundle bundle = getArguments();
        String url = bundle.getString("url");
        loadDate(url);

    }

    private void initView(View view) {
        LIveChinaListView = (ListView) view.findViewById(R.id.LIveChinaListView);
    }
}
