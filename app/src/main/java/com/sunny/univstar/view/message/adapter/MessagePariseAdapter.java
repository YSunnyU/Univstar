package com.sunny.univstar.view.message.adapter;

import android.content.Context;
import android.graphics.Bitmap;
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
import com.bumptech.glide.request.target.ImageViewTarget;
import com.sunny.univstar.R;
import com.sunny.univstar.view.message.bean.MessageCommentBean;
import com.sunny.univstar.view.message.bean.MessagePraiseBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 张玗 on 2018/5/13.
 */

public class MessagePariseAdapter extends RecyclerView.Adapter<MessagePariseAdapter.Holder> implements View.OnClickListener {
    List<Object> list;
    //    MessagePraiseBean.DataBean.ListBean
    public Context context;

    public MessagePariseAdapter(List<Object> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.above_message_item, parent, false);
        Holder holder = new Holder(inflate);
        inflate.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        Object o = list.get(position);
        if (o instanceof MessagePraiseBean.DataBean.ListBean) {
            MessagePraiseBean.DataBean.ListBean list = (MessagePraiseBean.DataBean.ListBean) o;
            //            时间转换
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm");
            Date date = new Date(list.getCreateDate());
            holder.messagePraise_time.setVisibility(View.VISIBLE);
            holder.messagePraise_time.setText(sdf.format(date));
            holder.aboveMessageItem_content.setText(list.getContent());
            holder.aboveMessageItem_time.setText(list.getTitle());
            holder.itemView.setTag(position);
            holder.aboveMessageItem_one.setText(list.getTitle());
            Glide.with(context)
                    .load(list.getOtherUserPhoto())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                    .into(new ImageViewTarget<Bitmap>(holder.aboveMessageItem_image) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true); //设置圆角弧度
                            holder.aboveMessageItem_image.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        }
        if (o instanceof MessageCommentBean.DataBean.ListBean) {
            MessageCommentBean.DataBean.ListBean list = (MessageCommentBean.DataBean.ListBean) o;
            holder.aboveMessageItem_one.setText(list.getTitle());

            //            时间转换
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm");
            Date date = new Date(list.getCreateDate());
            holder.messagePraise_time.setVisibility(View.VISIBLE);
            holder.messagePraise_time.setText(sdf.format(date));
            holder.aboveMessageItem_content.setText(list.getContent());
            holder.aboveMessageItem_time.setText(list.getTitle());
            holder.itemView.setTag(position);
            Glide.with(context)
                    .load(list.getOtherUserPhoto())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                    .into(new ImageViewTarget<Bitmap>(holder.aboveMessageItem_image) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true); //设置圆角弧度
                            holder.aboveMessageItem_image.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        }

    }

    @Override
    public int getItemCount() {
        return list.isEmpty() ? 0 : list.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView aboveMessageItem_image;
        public TextView aboveMessageItem_content;
        public TextView aboveMessageItem_time;
        public TextView messagePraise_time;
        public TextView aboveMessageItem_one;

        public Holder(View itemView) {
            super(itemView);
            this.aboveMessageItem_image = (ImageView) itemView.findViewById(R.id.aboveMessageItem_image);
            this.aboveMessageItem_content = (TextView) itemView.findViewById(R.id.aboveMessageItem_content);
            this.aboveMessageItem_time = (TextView) itemView.findViewById(R.id.aboveMessageItem_time);
            this.messagePraise_time = (TextView) itemView.findViewById(R.id.messagePraise_time);
            this.aboveMessageItem_one = (TextView) itemView.findViewById(R.id.aboveMessageItem_one);

        }
    }



}
