package com.example.myapplication.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entity.LiveChinaContentBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 */

public class LiveChinaContentAdapter extends BaseAdapter {
    private Context context;
    private  List<LiveChinaContentBean.LiveBean> live;
    private LayoutInflater inflater;

    public LiveChinaContentAdapter(Context context, List<LiveChinaContentBean.LiveBean> live){
        this.context=context;
        this.live=live;
        inflater=LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return live.size();
    }

    @Override
    public Object getItem(int position) {
        return live.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=null;
        if (convertView==null) {
            convertView = inflater.inflate(R.layout.live_china_content_item, null);
            holder=new Holder();
            holder.ima= (ImageView) convertView.findViewById(R.id.contentimg);
            holder.name= (TextView) convertView.findViewById(R.id.contenttitle);
            convertView.setTag(holder);
        }else {
            holder= (Holder) convertView.getTag();
        }
        LiveChinaContentBean.LiveBean liveBean = live.get(position);

        holder.name.setText(liveBean.getTitle());
        Picasso.with(context).load(liveBean.getImage()).into(holder.ima);
        return convertView;
    }
    static  class Holder{
        TextView name;
        ImageView ima;

    }
}
