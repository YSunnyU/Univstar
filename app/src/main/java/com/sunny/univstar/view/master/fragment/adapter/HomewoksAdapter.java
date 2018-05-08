package com.sunny.univstar.view.master.fragment.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sunny.univstar.R;
import com.sunny.univstar.view.master.fragment.HomeMasterBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 张玗 on 2018/5/4.
 */

public class HomewoksAdapter extends RecyclerView.Adapter<HomewoksAdapter.Holder> {
    private List<HomeMasterBean.DataBean.HomewoksBean> homewoksBeanList;
    public Context context;

    public HomewoksAdapter(List<HomeMasterBean.DataBean.HomewoksBean> homewoksBeanList, Context context) {
        this.homewoksBeanList = homewoksBeanList;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.master_homeworks_item, parent, false);
        Holder holder = new Holder(inflate);

        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        Glide.with(context)
                .load(homewoksBeanList.get(position).getPhoto())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                .into(new BitmapImageViewTarget(holder.master_user_img){
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true); //设置圆角弧度
                        holder.master_user_img.setImageDrawable(circularBitmapDrawable);
                    }
                });
        holder.master_user_name.setText(homewoksBeanList.get(position).getNickname());
//            时间转换
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        Date date = new Date(homewoksBeanList.get(position).getCreateDate());
        holder.master_user_time.setText(sdf.format(date));
        holder.master_user_from.setText(homewoksBeanList.get(position).getSource());
        holder.master_user_content.setText(homewoksBeanList.get(position).getContent());
        Glide.with(context).load(homewoksBeanList.get(position).getCoverImg()).asBitmap().into(holder.master_user_content_image);
        if (homewoksBeanList.get(position).getPraiseNum() > 0){
            holder.master_praise_img.setImageResource(R.mipmap.reward_active);
            holder.master_user_reward.setTextColor(Color.parseColor("#ff0000"));
        }else {
            holder.master_praise_img.setImageResource(R.mipmap.reward_normal);
        }
        holder.master_user_praise.setText(homewoksBeanList.get(position).getPraiseNum() + "");
        holder.master_user_reward.setText(homewoksBeanList.get(position).getGiftNum() + "");
    }

    @Override
    public int getItemCount() {
        return homewoksBeanList.isEmpty() ? 0 : homewoksBeanList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView master_user_img;
        public TextView master_user_name;
        public TextView master_user_time;
        public TextView master_user_from;
        public TextView master_user_content;
        public ImageView master_user_content_image;
        public ImageView one_other;
        public TextView master_user_reply;
        public TextView master_user_praise;
        public TextView master_user_reward;
        public TextView master_user_share;
        public ImageView master_praise_img;
        public Holder(View itemView) {
            super(itemView);
            this.master_user_img = (ImageView) itemView.findViewById(R.id.master_user_img);
            this.master_user_name = (TextView) itemView.findViewById(R.id.master_user_name);
            this.master_user_time = (TextView) itemView.findViewById(R.id.master_user_time);
            this.master_user_from = (TextView) itemView.findViewById(R.id.master_user_from);
            this.master_user_content = (TextView) itemView.findViewById(R.id.master_user_content);
            this.master_user_content_image = (ImageView) itemView.findViewById(R.id.master_user_content_image);
            this.one_other = (ImageView) itemView.findViewById(R.id.one_other);
            this.master_user_reply = (TextView) itemView.findViewById(R.id.master_user_reply);
            this.master_user_praise = (TextView) itemView.findViewById(R.id.master_user_praise);
            this.master_user_reward = (TextView) itemView.findViewById(R.id.master_user_reward);
            this.master_user_share = (TextView) itemView.findViewById(R.id.master_user_share);
            this.master_praise_img = (ImageView) itemView.findViewById(R.id.master_praise_img);
        }
    }

}
