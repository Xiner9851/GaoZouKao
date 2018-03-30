package com.example.myapplication;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/3/16.
 */

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.Holder> {
    private Context context;
    private List<BeanLai.ResultBean.DataBean> data;

    public RecyAdapter(Context context, List<BeanLai.ResultBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyAdapter.Holder holder, int position) {
        Picasso.with(context).load(data.get(position).getThumbnail_pic_s()).into(holder.img);
        holder.name.setText(data.get(position).getTitle());
       /* ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        Random random=new Random();
        int height = random.nextInt(400) + 200;
        layoutParams.height=height;*/
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;

        public Holder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.img);
            name= (TextView) itemView.findViewById(R.id.name);
        }
    }
}
