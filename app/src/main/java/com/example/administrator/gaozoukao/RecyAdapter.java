package com.example.administrator.gaozoukao;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 */

public class RecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
     private List<lai.ResultBean.DataBean> data;
private Context context;
    int one=1;
    int two=2;
    int three=3;

    public RecyAdapter(List<lai.ResultBean.DataBean> data,Context context) {
        this.data = data;
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder han=null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType==one){
            View view = inflater.inflate(R.layout.item, parent, false);
            han=new HolderOne(view);

            han.itemView.setOnClickListener(this);
        }else if (viewType==two){
            View view = inflater.inflate(R.layout.item2, parent, false);
            han=new HolderTwo(view);
            han.itemView.setOnClickListener(this);
        }else {
            View view = inflater.inflate(R.layout.item3, parent, false);
            han=new HolderThree(view);
            han.itemView.setOnClickListener(this);
        }
        return han;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HolderOne){
            ((HolderOne) holder).name.setText(data.get(position).getTitle());
            Picasso.with(context).load(data.get(position).getThumbnail_pic_s()).into(((HolderOne) holder).item_name);
            ((HolderOne) holder).itemView.setTag(position);
        }else if (holder instanceof  HolderTwo){
            ((HolderTwo) holder).name.setText(data.get(position).getTitle());
            Picasso.with(context).load(data.get(position).getThumbnail_pic_s()).into(((HolderTwo) holder).item_img2);
            Picasso.with(context).load(data.get(position).getThumbnail_pic_s02()).into(((HolderTwo) holder).item_img22);
            ((HolderTwo) holder).itemView.setTag(position);
        }else {
            ((HolderThree) holder).name.setText(data.get(position).getTitle());
            Picasso.with(context).load(data.get(position).getThumbnail_pic_s()).into(((HolderThree) holder).item_img3);
            Picasso.with(context).load(data.get(position).getThumbnail_pic_s02()).into(((HolderThree) holder).item_img33);
            Picasso.with(context).load(data.get(position).getThumbnail_pic_s03()).into(((HolderThree) holder).item_name32);
            ((HolderThree) holder).itemView.setTag(position);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return one;
        }else if (position==1){
            return two;
        }else {
            return three;
        }

    }

    public class HolderOne extends RecyclerView.ViewHolder{

        private TextView name;
        private ImageView item_name;
        public HolderOne(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.item_name);
            item_name= (ImageView) itemView.findViewById(R.id.item_img);

        }
    }
    public class HolderTwo extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView item_img2,item_img22;
        public HolderTwo(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.item_name2);
            item_img2= (ImageView) itemView.findViewById(R.id.item_img2);
            item_img22= (ImageView) itemView.findViewById(R.id.item_img22);
        }
    }
    public class HolderThree extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView item_name32,item_img33,item_img3;
        public HolderThree(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.item_name3);
            item_name32= (ImageView) itemView.findViewById(R.id.item_img32);
            item_img33= (ImageView) itemView.findViewById(R.id.item_img33);
            item_img3= (ImageView) itemView.findViewById(R.id.item_img3);

        }
    }
    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface OnClick {
        void setOnClick(View v, int potion);
    }

    private OnClick on;

    @Override
    public void onClick(View v) {
        if (on != null) {
            on.setOnClick(v, (int)v.getTag());
        }
    }

    public void setOnClickListio(OnClick on) {
        this.on = on;
    }
}
