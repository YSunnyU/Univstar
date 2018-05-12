package com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunny.univstar.R;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.CollectionLivingBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 张玗 on 2018/5/11.
 */

public class CollectLivingAdapter extends RecyclerView.Adapter<CollectLivingAdapter.Holder> implements View.OnClickListener {
    List<CollectionLivingBean.DataBean.ListBean> list;
    public Context context;

    public CollectLivingAdapter(List<CollectionLivingBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.collect_live_item, parent, false);
        Holder holder = new Holder(inflate);
        inflate.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Glide.with(context).load(list.get(position).getCoverImg()).asBitmap().into(holder.collectLiveItem_image);
        //            时间转换
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm");
        Date date = new Date(list.get(position).getStartDate());
        holder.collectLiveItem_startTime.setText("开课时间 ：" + sdf.format(date));
        holder.collectLiveItem_name.setText(list.get(position).getNickname());
        if (list.get(position).getUserType() == 4) {
            holder.collectLiveItem_vip_img.setImageResource(R.mipmap.home_level_vip_red);
        } else if (list.get(position).getUserType() == 3) {
            holder.collectLiveItem_vip_img.setImageResource(R.mipmap.home_level_vip_yellow);
        } else if (list.get(position).getUserType() == 2) {
            holder.collectLiveItem_vip_img.setImageResource(R.mipmap.home_level_vip_blue);
        }
        int subscribe = list.get(position).getSubscribe();
        int subscribeNum = list.get(position).getSubscribeNum();

        holder.collectLiveItem_content.setText("已预约： " + subscribe + " / " + subscribeNum);
        holder.collectLiveItem_money.setText(list.get(position).getPrice() + "");
    }

    @Override
    public int getItemCount() {
        return list.isEmpty() ? 0 : list.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView collectLiveItem_image;
        public TextView collectLiveItem_startTime;
        public TextView collectLiveItem_name;
        public ImageView collectLiveItem_vip_img;
        public TextView collectLiveItem_content;
        public TextView collectLiveItem_money;

        public Holder(View itemView) {
            super(itemView);
            this.collectLiveItem_image = (ImageView) itemView.findViewById(R.id.collectLiveItem_image);
            this.collectLiveItem_startTime = (TextView) itemView.findViewById(R.id.collectLiveItem_startTime);
            this.collectLiveItem_name = (TextView) itemView.findViewById(R.id.collectLiveItem_name);
            this.collectLiveItem_vip_img = (ImageView) itemView.findViewById(R.id.collectLiveItem_vip_img);
            this.collectLiveItem_content = (TextView) itemView.findViewById(R.id.collectLiveItem_content);
            this.collectLiveItem_money = (TextView) itemView.findViewById(R.id.collectLiveItem_money);
        }
    }


}
