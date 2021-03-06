package com.sunny.univstar.view.master.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sunny.univstar.R;
import com.sunny.univstar.view.master.fragment.HomeMasterBean;
import com.sunny.univstar.view.work.activity.WorkDetailedActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class HomewoksAdapter extends RecyclerView.Adapter<HomewoksAdapter.Holder> implements View.OnClickListener {
    private List<HomeMasterBean.DataBean.HomewoksBean> homewoksBeanList;
    public Context context;


    public HomewoksAdapter(List<HomeMasterBean.DataBean.HomewoksBean> homewoksBeanList, Context context) {
        this.homewoksBeanList = homewoksBeanList;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.master_homeworks_item, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        Glide.with(context)
                .load(homewoksBeanList.get(position).getPhoto())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                .into(new BitmapImageViewTarget(holder.master_user_img) {
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
        if (homewoksBeanList.get(position).getPicProperty() != null && !"".equals(homewoksBeanList.get(position).getPicProperty())) {
            String picProperty = homewoksBeanList.get(position).getPicProperty();
            String[] split = picProperty.split("_");
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            layoutParams.leftMargin = 155;
            layoutParams.topMargin = 40;
            holder.master_user_content_image.setLayoutParams(layoutParams);
        }
        Glide.with(context).load(homewoksBeanList.get(position).getCoverImg()).asBitmap().into(holder.master_user_content_image);
        if (homewoksBeanList.get(position).getPraiseNum() > 0) {
            holder.master_praise_img.setImageResource(R.mipmap.reward_active);
            holder.master_user_reward.setTextColor(Color.parseColor("#ff0000"));
        } else {
            holder.master_praise_img.setImageResource(R.mipmap.reward_normal);
        }
        if (homewoksBeanList.get(position).getIsAnswer() == 0){
            holder.master_user_praise.setChecked(false);
            holder.master_user_praise.setTextColor(Color.parseColor("#9E9E9E"));

        }else {
            holder.master_user_praise.setChecked(true);
            holder.master_user_praise.setTextColor(Color.parseColor("#FFB300"));
        }
        holder.master_user_praise.setOnClickListener(this);
        holder.master_user_praise.setTag(position);
        holder.master_user_share_linear.setOnClickListener(this);
        holder.master_user_share_linear.setTag(position);
        holder.master_user_praise.setText(homewoksBeanList.get(position).getPraiseNum() + "");
        holder.master_user_reward.setText(homewoksBeanList.get(position).getGiftNum() + "");
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WorkDetailedActivity.class);
                intent.putExtra("homewokId",homewoksBeanList.get(position).getId()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return homewoksBeanList.isEmpty() ? 0 : homewoksBeanList.size();
    }

    public interface OnClickItem {
        void onClickItem(View view, int position);
    }

    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @Override
    public void onClick(View v) {
        if (onClickItem != null)
            onClickItem.onClickItem(v, (int) v.getTag());
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
        public CheckBox master_user_praise;
        public TextView master_user_reward;
        public TextView master_user_share;
        public ImageView master_praise_img;
        private LinearLayout master_user_reply_linear;
        private LinearLayout master_user_praise_linear;
        private LinearLayout master_praise_img_linear;
        private LinearLayout master_user_share_linear;
        private ImageView master_user_praise_img;
        public Holder(View itemView) {
            super(itemView);
            master_user_reply_linear = itemView.findViewById(R.id.master_user_reply_linear);
            master_user_praise_linear = itemView.findViewById(R.id.master_user_praise_linear);
            master_praise_img_linear = itemView.findViewById(R.id.master_praise_img_linear);
            master_user_share_linear = itemView.findViewById(R.id.master_user_share_linear);
            this.master_user_img = (ImageView) itemView.findViewById(R.id.master_user_img);
            this.master_user_name = (TextView) itemView.findViewById(R.id.master_user_name);
            this.master_user_time = (TextView) itemView.findViewById(R.id.master_user_time);
            this.master_user_from = (TextView) itemView.findViewById(R.id.master_user_from);
            this.master_user_content = (TextView) itemView.findViewById(R.id.master_user_content);
            this.master_user_content_image = (ImageView) itemView.findViewById(R.id.master_user_content_image);
            this.one_other = (ImageView) itemView.findViewById(R.id.one_other);
            this.master_user_reply = (TextView) itemView.findViewById(R.id.master_user_reply);
            this.master_user_praise = (CheckBox) itemView.findViewById(R.id.master_user_praise);
            this.master_user_reward = (TextView) itemView.findViewById(R.id.master_user_reward);
            this.master_user_share = (TextView) itemView.findViewById(R.id.master_user_share);
            this.master_praise_img = (ImageView) itemView.findViewById(R.id.master_praise_img);
        }
    }

}
