package com.sunny.univstar.view.master.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunny.univstar.R;
import com.sunny.univstar.view.master.fragment.HomeMasterBean;

import java.util.List;

/**
 * Created by 张玗 on 2018/5/4.
 */

public class UserBeanAdapter extends RecyclerView.Adapter<UserBeanAdapter.Holder> {
    private List<HomeMasterBean.DataBean.UsersBean> usersBeanList;
    public Context context;

    public UserBeanAdapter(List<HomeMasterBean.DataBean.UsersBean> usersBeanList, Context context) {
        this.usersBeanList = usersBeanList;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.master_user_item, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Glide.with(context).load(usersBeanList.get(position).getImages()).asBitmap().into(holder.master_user_image);
        holder.master_user_name.setText(usersBeanList.get(position).getNickname());
        holder.master_user_detail.setText(usersBeanList.get(position).getIntro());
    }

    @Override
    public int getItemCount() {
        return usersBeanList.isEmpty() ? 0 : usersBeanList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView master_user_image;
        public TextView master_user_name;
        public TextView master_user_detail;
        public ImageView master_user_medal;

        public Holder(View itemView) {
            super(itemView);
            this.master_user_image = (ImageView) itemView.findViewById(R.id.master_user_image);
            this.master_user_name = (TextView) itemView.findViewById(R.id.master_user_name);
            this.master_user_detail = (TextView) itemView.findViewById(R.id.master_user_detail);
            this.master_user_medal = (ImageView) itemView.findViewById(R.id.master_user_medal);
        }
    }


}
