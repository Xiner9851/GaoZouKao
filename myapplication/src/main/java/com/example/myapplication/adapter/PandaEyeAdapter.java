package com.example.myapplication.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entity.HomeLai;
import com.example.myapplication.entity.PandaEyeBean;
import com.example.myapplication.entity.PandaEyeErBean;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/18.
 */

public class PandaEyeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Object> mArraylist;
    private Context context;
    private int one=1;
    private int two=2;


    public PandaEyeAdapter(ArrayList<Object> mArraylist, Context context) {
        this.mArraylist = mArraylist;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder=null;
        if (viewType==one){
            View inflate = inflater.inflate(R.layout.eye_item_one, parent, false);
            holder=new HolderOne(inflate);

        }else if (viewType==two){
            View inflate = inflater.inflate(R.layout.eye_item_two, parent, false);
            RecyclerView recy_two= (RecyclerView) inflate.findViewById(R.id.eye_two_recy);
            PandaEyeErBean pandaEyeErBean= (PandaEyeErBean) mArraylist.get(1);
            List<PandaEyeErBean.ListBean> list = pandaEyeErBean.getList();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(parent.getContext());
            recy_two.setLayoutManager(linearLayoutManager);
            PandaEyeItemAdapter pandaEyeItemAdapter = new PandaEyeItemAdapter(list, parent.getContext());
            recy_two.setAdapter(pandaEyeItemAdapter);


            holder=new HolderTwo(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HolderOne){
            PandaEyeBean.DataBean data= (PandaEyeBean.DataBean) mArraylist.get(0);
            ((HolderOne) holder).name.setText(data.getBigImg().get(position).getTitle());
            Picasso.with(context).load(data.getBigImg().get(position).getImage()).into(((HolderOne) holder).img);
        }else {


        }

    }

    @Override
    public int getItemCount() {
        return mArraylist.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position==0){
            return one;
        }else {
            return two;
        }
    }
    public class HolderOne extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView name;
        public HolderOne(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.eye_one_img);
            name= (TextView) itemView.findViewById(R.id.eye_one_name);
        }
    }

    public class HolderTwo extends RecyclerView.ViewHolder{


        public HolderTwo(View itemView) {
            super(itemView);

        }
    }
}
