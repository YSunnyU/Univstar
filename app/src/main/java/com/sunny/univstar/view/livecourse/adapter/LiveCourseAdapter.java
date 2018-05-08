package com.sunny.univstar.view.livecourse.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunny.univstar.R;
import com.sunny.univstar.model.entity.LiveCourseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DELL on 2018/5/7.
 */

public class LiveCourseAdapter extends RecyclerView.Adapter<LiveCourseAdapter.Holder> implements View.OnClickListener {
    private List<LiveCourseEntity.DataBean.ListBean> mList;
    private Context context;

    public LiveCourseAdapter(List<LiveCourseEntity.DataBean.ListBean> mList) {
        this.mList = mList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_course_item, parent, false);
        view.setOnClickListener(this);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (mList.size() > 0 && mList != null) {
            Glide.with(context).load(mList.get(position).getCoverImg())
                    .asBitmap()
                    .into(holder.course_item_img);
//            开课时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date(mList.get(position).getStartDate());
            holder.course_item_time.setText("开课时间："+sdf.format(date)+"");

//            老师名字
            holder.course_item_address.setText(mList.get(position).getNickname());

//            判断老师是什么类型加载什么VIP图片
            if (mList.get(position).getUserType() == 4){
                holder.course_item_vip_img.setImageResource(R.mipmap.home_level_vip_red);
            }else if (mList.get(position).getUserType() == 3){
                holder.course_item_vip_img.setImageResource(R.mipmap.home_level_vip_yellow);
            }else if (mList.get(position).getUserType() == 2){
                holder.course_item_vip_img.setImageResource(R.mipmap.home_level_vip_blue);
            }

//            类别
            holder.course_type_text.setText(mList.get(position).getType());

//            预约数
            holder.course_item_reservation.setText(mList.get(position).getSubscribe()+"");
            holder.course_item_subscribe.setText(mList.get(position).getSubscribeNum()+"");

//            价格
            holder.course_item_price.setText(mList.get(position).getPrice()+"");
            holder.itemView.setTag(position);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public interface OnClickItem{
        void onClickItem(View view,int position);
    }
    private OnClickItem onClickItem;
    public void setOnClickItem(OnClickItem onClickItem){
        this.onClickItem = onClickItem;
    }
    @Override
    public void onClick(View v) {
        if (onClickItem != null){
            onClickItem.onClickItem(v,(int)v.getTag());
        }
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView course_item_img;
        private TextView course_item_time;
        private TextView course_item_address;
        private ImageView course_item_vip_img;
        private TextView course_item_reservation;
        private TextView course_item_subscribe;
        private TextView course_item_price;
        private TextView course_type_text;
        public Holder(View itemView) {
            super(itemView);
            course_item_img = itemView.findViewById(R.id.course_item_img);
            course_item_time = itemView.findViewById(R.id.course_item_time);
            course_item_address = itemView.findViewById(R.id.course_item_address);
            course_item_vip_img = itemView.findViewById(R.id.course_item_vip_img);
            course_item_reservation = itemView.findViewById(R.id.course_item_reservation);
            course_item_subscribe = itemView.findViewById(R.id.course_item_subscribe);
            course_item_price = itemView.findViewById(R.id.course_item_price);
            course_type_text = itemView.findViewById(R.id.course_type_text);
        }
    }
}
