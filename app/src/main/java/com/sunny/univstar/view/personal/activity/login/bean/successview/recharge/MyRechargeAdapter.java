package com.sunny.univstar.view.personal.activity.login.bean.successview.recharge;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sunny.univstar.R;

import java.util.List;

/**
 * Created by 张玗 on 2018/5/9.
 */

public class MyRechargeAdapter extends RecyclerView.Adapter<MyRechargeAdapter.Holder> {
    List<RechargeBean.DataBean.ListBean> list;

    public MyRechargeAdapter(List<RechargeBean.DataBean.ListBean> list) {
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_recharge_item, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.recharge_num.setText(list.get(position).getAmountAndroid()+"");
        holder.recharge_price.setText(list.get(position).getPriceAndroid()+".0");
    }

    @Override
    public int getItemCount() {
        return list.isEmpty() ? 0 : list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView recharge_num;
        public Button recharge_price;
        public Holder(View itemView) {
            super(itemView);
            this.recharge_num = (TextView) itemView.findViewById(R.id.recharge_num);
            this.recharge_price = (Button) itemView.findViewById(R.id.recharge_price);
        }
    }


}
