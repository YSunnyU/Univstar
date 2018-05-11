package com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.order;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunny.univstar.R;
import com.sunny.univstar.view.notice.activity.NoticeDetailedActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 张玗 on 2018/5/10.
 */

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.Holder> {
    List<MyOrderBean.DataBean.ListBean> list;
    private Context context;

    public MyOrderAdapter(List<MyOrderBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item, parent, false);
        Holder holder = new Holder(inflate);

        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        if (list.size() != 0) {
            holder.myOrderItem_title.setText(list.get(position).getTitle());
            int orderStatus = list.get(position).getOrderStatus();
            if (orderStatus == 0) {
                holder.myOrderItem_payStatus.setText("代付款");
                holder.myOrderItem_pay.setVisibility(View.VISIBLE);
            }
            if (orderStatus == 2) {
                holder.myOrderItem_payStatus.setText("已取消");
                holder.myOrderItem_pay.setVisibility(View.GONE);


            }
            //            时间转换
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            Date date = new Date(list.get(position).getStartDate());
            holder.myOrderItem_time.setText(sdf.format(date));
            holder.myOrderItem_money.setText("￥" + list.get(position).getPrice() + ".0");
            Glide.with(context).load(list.get(position).getCoverImg()).asBitmap()
                    .into(holder.myOrderItem_Image);
            holder.myOrderItem_skipNotice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, NoticeDetailedActivity.class);
                    intent.putExtra("id",list.get(position).getRefId()+"");
                    context.startActivity(intent);
                }
            });
            holder.myOrderItem_Image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, OrderDetailActivity.class);
                    int oid = list.get(position).getOid();
                    intent.putExtra("oid",oid+"");
                    intent.putExtra("type",list.get(position).getType());
//                    intent.putExtra("myOrderItem_title",list.get(position).getTitle());
//                    intent.putExtra("myOrderItem_Image",list.get(position).getCoverImg());
//                    intent.putExtra("myOrderItem_time",list.get(position).getStartDate());
//                    intent.putExtra("myOrderItem_money",list.get(position).getPrice()+"");
//                    intent.putExtra("orderNum",list.get(position).getOrderNo());

                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.isEmpty() ? 0 : list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView myOrderItem_title;
        public TextView myOrderItem_payStatus;
        public ImageView myOrderItem_Image;
        public TextView myOrderItem_time;
        public TextView myOrderItem_money;
        public Button myOrderItem_pay;
        public RelativeLayout myOrderItem_skipNotice;

        public Holder(View itemView) {
            super(itemView);
            this.myOrderItem_title = (TextView) itemView.findViewById(R.id.myOrderItem_title);
            this.myOrderItem_payStatus = (TextView) itemView.findViewById(R.id.myOrderItem_payStatus);
            this.myOrderItem_Image = (ImageView) itemView.findViewById(R.id.myOrderItem_Image);
            this.myOrderItem_time = (TextView) itemView.findViewById(R.id.myOrderItem_time);
            this.myOrderItem_pay = (Button) itemView.findViewById(R.id.myOrderItem_pay);
            this.myOrderItem_skipNotice = (RelativeLayout) itemView.findViewById(R.id.myOrderItem_skipNotice);

            this.myOrderItem_money = (TextView) itemView.findViewById(R.id.myOrderItem_money);
        }
    }


}
