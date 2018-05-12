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
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.CollectExperienceBean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 张玗 on 2018/5/11.
 */

public class CollectExperienceAdapter extends RecyclerView.Adapter<CollectExperienceAdapter.Holder> implements View.OnClickListener {
    List<CollectExperienceBean.DataBean.ListBean> list;
    public Context context;

    public CollectExperienceAdapter(List<CollectExperienceBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_notice_item, parent, false);
        Holder holder = new Holder(inflate);
        inflate.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (list.size() > 0 && list != null) {

            holder.notice_item_address.setText(list.get(position).getAddress());

            holder.notice_reservation.setText(list.get(position).getReservationNum() + "");
            holder.notice_subscribe.setText(list.get(position).getSubscribeNum() + "");
            holder.notice_item_price.setText(getPiceDecimal(list.get(position).getPrice()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date(list.get(position).getStartDate());
            holder.notice_item_time.setText("开课时间：" + sdf.format(date));
            Glide.with(context).load(list.get(position).getCoverImg())
                    .asBitmap()
                    .into(holder.notice_item_img);
        }
        holder.itemView.setTag(position);
    }

    //int类型保留一位小数
    private String getPiceDecimal(int reservationNum) {
        DecimalFormat df = new DecimalFormat("#.0");
        return df.format(reservationNum);

    }

    @Override
    public int getItemCount() {
        return list.isEmpty() ? 0 : list.size();
    }
public interface OnShortListener{
        void setOnShortListener(View view,int position);
    }
    private OnShortListener shortListener;
    public void OnShortListener(OnShortListener shortListener){
        this.shortListener=shortListener;
    };
    @Override
    public void onClick(View view) {
        if (shortListener!=null){
            shortListener.setOnShortListener(view,(int)view.getTag());
        }
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView notice_item_img;
        public TextView notice_item_time;
        public TextView notice_item_address;
        public TextView notice_reservation;
        public TextView notice_subscribe;
        public TextView notice_item_price;

        public Holder(View itemView) {
            super(itemView);
            this.notice_item_img = (ImageView) itemView.findViewById(R.id.notice_item_img);
            this.notice_item_time = (TextView) itemView.findViewById(R.id.notice_item_time);
            this.notice_item_address = (TextView) itemView.findViewById(R.id.notice_item_address);
            this.notice_reservation = (TextView) itemView.findViewById(R.id.notice_reservation);
            this.notice_subscribe = (TextView) itemView.findViewById(R.id.notice_subscribe);
            this.notice_item_price = (TextView) itemView.findViewById(R.id.notice_item_price);
        }
    }


}
