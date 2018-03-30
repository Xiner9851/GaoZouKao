package com.example.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entity.HomeLai;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 */

public class HomeOne extends RecyclerView.Adapter<HomeOne.Holder> {
    private List<HomeLai.DataBean.ChinaliveBean.ListBeanXX> list;

    private Context context;

    public HomeOne(List<HomeLai.DataBean.ChinaliveBean.ListBeanXX> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public HomeOne.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_one, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeOne.Holder holder, int position) {

        Picasso.with(context).load(list.get(position).getImage()).into(holder.img);
        holder.name.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView img;
        public Holder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.home_item_one_name);
            img= (ImageView) itemView.findViewById(R.id.home_item_one_img);
        }
    }
}
