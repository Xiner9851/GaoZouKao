package com.example.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entity.HomeLai;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int one=1;
    private int two=2;
    private int three=3;
    private int four=4;
    private int five=5;

    private Context context;
    private  ArrayList<Object> datalist;

    public HomeAdapter(Context context, ArrayList<Object> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);


        if (one==viewType){

            HomeLai.DataBean.ChinaliveBean chinalive = (HomeLai.DataBean.ChinaliveBean) datalist.get(0);
            List<HomeLai.DataBean.ChinaliveBean.ListBeanXX> list = chinalive.getList();


            return new HolderOne(getAdapterData(parent,R.layout.home_one,R.id.home_one_recy,new HomeOne(list,parent.getContext())));

        }else if (two==viewType){
            HomeLai.DataBean.PandaliveBean pandalive= (HomeLai.DataBean.PandaliveBean) datalist.get(1);
            List<HomeLai.DataBean.PandaliveBean.ListBean> list = pandalive.getList();

            return new HolderTwo(getAdapterData(parent,R.layout.home_two,R.id.home_two_recy,new HomeTwo(list,parent.getContext())));

        }else {
            HomeLai.DataBean.WallliveBean walllive = (HomeLai.DataBean.WallliveBean) datalist.get(2);
            List<HomeLai.DataBean.WallliveBean.ListBeanX> list = walllive.getList();
            return new HolderThree(getAdapterData(parent,R.layout.home_three,R.id.home_three_recy,new HomeThree(list,parent.getContext())));

        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HolderOne){
            HomeLai.DataBean.ChinaliveBean chinalive = (HomeLai.DataBean.ChinaliveBean) datalist.get(0);
            ((HolderOne) holder).titleOne.setText(chinalive.getTitle());

        }else if (holder instanceof HolderTwo){
            HomeLai.DataBean.PandaliveBean pandalive= (HomeLai.DataBean.PandaliveBean) datalist.get(1);
            ((HolderTwo) holder).titleTwo.setText(pandalive.getTitle());
        }else {
            HomeLai.DataBean.WallliveBean walllive = (HomeLai.DataBean.WallliveBean) datalist.get(2);
            ((HolderThree) holder).titleThree.setText(walllive.getTitle());
        }

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return one;
        }else if (position==1){
            return two;

        }else if (position==2){
            return three;
        }else if (position==3){
            return four;
        }else {
            return five;
        }

    }
    private View getAdapterData(ViewGroup parent,int mRid,int mRid2,RecyclerView.Adapter adpp){
        View view = LayoutInflater.from(parent.getContext()).inflate(mRid,parent,false);
        RecyclerView rv = (RecyclerView) view.findViewById(mRid2);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(parent.getContext(), 3);
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(adpp);
        return view;
    }
    public class HolderOne extends RecyclerView.ViewHolder{

        private TextView titleOne;
        public HolderOne(View itemView) {
            super(itemView);
            titleOne= (TextView) itemView.findViewById(R.id.titleOne);
        }
    }
    public class HolderTwo extends RecyclerView.ViewHolder{
        private TextView titleTwo;
        public HolderTwo(View itemView) {
            super(itemView);
            titleTwo= (TextView) itemView.findViewById(R.id.titleTwo);
        }
    }
    public class HolderThree extends RecyclerView.ViewHolder{
        private TextView titleThree;
        public HolderThree(View itemView) {
            super(itemView);
            titleThree= (TextView) itemView.findViewById(R.id.titleThree);
        }
    }
    public class HolderFour extends RecyclerView.ViewHolder{

        public HolderFour(View itemView) {
            super(itemView);
        }
    }
    public class HolderFive extends RecyclerView.ViewHolder{

        public HolderFive(View itemView) {
            super(itemView);
        }
    }


}
