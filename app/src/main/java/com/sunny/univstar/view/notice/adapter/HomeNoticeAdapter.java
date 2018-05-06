package com.sunny.univstar.view.notice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunny.univstar.R;
import com.sunny.univstar.model.entity.HomeNoticeEntity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DELL on 2018/5/5.
 */

public class HomeNoticeAdapter extends RecyclerView.Adapter<HomeNoticeAdapter.ViewHolder> {
    private List<HomeNoticeEntity.DataBean.ListBean> listData;
    private Context context;


    public HomeNoticeAdapter(List<HomeNoticeEntity.DataBean.ListBean> listData) {
        this.listData = listData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_notice_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (listData.size() > 0 && listData != null){
            HomeNoticeEntity.DataBean.ListBean listBean = listData.get(position);
            holder.notice_item_address.setText(listBean.getAddress());

            holder.notice_reservation.setText(listBean.getReservationNum()+"");
            holder.notice_subscribe.setText(listBean.getSubscribeNum()+"");
            holder.notice_item_price.setText(getPiceDecimal(listBean.getPrice()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date(listBean.getStartDate());
            holder.notice_item_time.setText("开课时间："+sdf.format(date));
            Glide.with(context).load(listBean.getCoverImg())
                    .asBitmap()
                    .into(holder.notice_item_img);
        }
    }
    //int类型保留一位小数
    private String getPiceDecimal(int reservationNum) {
        DecimalFormat df  =new DecimalFormat("#.0");
        return df.format(reservationNum);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView notice_item_img;
        private TextView notice_item_address;
        private TextView notice_reservation;
        private TextView notice_subscribe;
        private TextView notice_item_price;
        private TextView notice_item_time;

        public ViewHolder(View itemView) {
            super(itemView);
            notice_item_img = itemView.findViewById(R.id.notice_item_img);
            notice_item_address = itemView.findViewById(R.id.notice_item_address);
            notice_reservation = itemView.findViewById(R.id.notice_reservation);
            notice_subscribe = itemView.findViewById(R.id.notice_subscribe);
            notice_item_price = itemView.findViewById(R.id.notice_item_price);
            notice_item_time = itemView.findViewById(R.id.notice_item_time);
        }
    }
}
