package com.sunny.univstar.view.message;

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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 张玗 on 2018/5/12.
 */

public class AboveMessageAdapter extends RecyclerView.Adapter<AboveMessageAdapter.Holder> implements View.OnClickListener {
    List<AboveMessageBean.DataBean> data;
    public Context context;

    public AboveMessageAdapter(List<AboveMessageBean.DataBean> data, Context context) {
        this.data = data;
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
        //            时间转换
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm");
        Date date = new Date(data.get(position).getCreateDate());

        holder.aboveMessageItem_time.setText(sdf.format(date));
        holder.aboveMessageItem_content.setText(data.get(position).getContent());
        holder.itemView.setTag(position);
        String type = data.get(position).getType();
        if ("MSG_ORDER".equals(type)) {
            Glide.with(context)
                    .load(R.mipmap.message_order)
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
            holder.aboveMessageItem_one.setText("订单提醒");
        }
        if ("MSG_PRAISE".equals(type)) {
            Glide.with(context)
                    .load(R.mipmap.message_praise)
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
            holder.aboveMessageItem_one.setText("赞我的");

        }
        if ("MSG_COMMENTS".equals(type)) {
            Glide.with(context)
                    .load(R.mipmap.message_comment)
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
            holder.aboveMessageItem_one.setText("评论我的");

        }
        if ("MSG_HOMEWOK".equals(type)) {
            Glide.with(context)
                    .load(R.mipmap.message_job)
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
            holder.aboveMessageItem_one.setText("作业提醒");

        }
        if ("MSG_UNIVSTAR".equals(type)) {
            Glide.with(context)
                    .load(R.mipmap.message_official)
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
            holder.aboveMessageItem_one.setText("UnivStar团队");

        }
        if ("MSG_ATTENTION".equals(type)) {
            Glide.with(context)
                    .load(R.mipmap.message_attention)
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
            holder.aboveMessageItem_one.setText("关注提醒");

        }
    }

    @Override
    public int getItemCount() {
        return data.isEmpty() ? 0 : data.size();
    }

    public interface OnShortListener {
        void setOnShortListener(View view, int position);
    }

    private OnShortListener shortListener;

    public void OnShortListener(OnShortListener shortListener) {
        this.shortListener = shortListener;
    }

    ;

    @Override
    public void onClick(View view) {
        if (shortListener != null) {
            shortListener.setOnShortListener(view, (int) view.getTag());
        }
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView aboveMessageItem_image;
        public TextView aboveMessageItem_time;
        public TextView aboveMessageItem_content;
        public TextView aboveMessageItem_one;


        public Holder(View itemView) {
            super(itemView);
            this.aboveMessageItem_image = (ImageView) itemView.findViewById(R.id.aboveMessageItem_image);
            this.aboveMessageItem_time = (TextView) itemView.findViewById(R.id.aboveMessageItem_time);
            this.aboveMessageItem_content = (TextView) itemView.findViewById(R.id.aboveMessageItem_content);
            this.aboveMessageItem_one = (TextView) itemView.findViewById(R.id.aboveMessageItem_one);

        }
    }


}
