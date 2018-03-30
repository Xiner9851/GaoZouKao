package com.example.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entity.PandaEyeErBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2018/3/18.
 */

public class PandaEyeItemAdapter extends RecyclerView.Adapter<PandaEyeItemAdapter.Holder> {
    List<PandaEyeErBean.ListBean> list;
    Context context;

    public PandaEyeItemAdapter(List<PandaEyeErBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public PandaEyeItemAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.eye_item_two_zi, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(PandaEyeItemAdapter.Holder holder, int position) {
        holder.name.setText(list.get(position).getTitle());
        Picasso.with(context).load(list.get(position).getPicurl()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        public Holder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.eye_two_img);
            name= (TextView) itemView.findViewById(R.id.eye_two_name);
        }
    }
}
