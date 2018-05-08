package com.sunny.univstar.view.valuable.fragment.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sunny.univstar.R;
import com.sunny.univstar.view.valuable.fragment.HomeValuableBean;

import java.util.List;

/**
 * Created by 张玗 on 2018/5/7.
 */

public class ValuableSonAdapter extends RecyclerView.Adapter<ValuableSonAdapter.Holder> {
    List<HomeValuableBean.DataBean.ArtcircleListBean.ListBean> list;
    Context context;

    public ValuableSonAdapter(List<HomeValuableBean.DataBean.ArtcircleListBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_work_content_item, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        if (list.size()>0) {
            Glide.with(context)
                    .load(list.get(position).getPhoto())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                    .into(new BitmapImageViewTarget(holder.user_img){
                        @Override
                        protected void setResource(Bitmap resource) {
                            super.setResource(resource);
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true); //设置圆角弧度
                            holder.user_img.setImageDrawable(circularBitmapDrawable);
                        }
                    });
            if (list.get(position).getPicProperty() != null && !"".equals(list.get(position).getPicProperty()))
            {
                String picProperty = list.get(position).getPicProperty();
                String[] split = picProperty.split("_");
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                layoutParams.leftMargin = 155;
                layoutParams.topMargin = 40;
                holder.user_content_image.setLayoutParams(layoutParams);
            }
            if (list.get(position).getFavoriteNum() > 0){
                holder.gift_img.setImageResource(R.mipmap.reward_active);
                holder.gift.setTextColor(Color.parseColor("#ff0000"));
            }else {
                holder.gift_img.setImageResource(R.mipmap.reward_normal);
            }
            holder.gift.setText(list.get(position).getFavoriteNum()+"");
            holder.user_name.setText(list.get(position).getNickname());
            holder.value_btn.setText(list.get(position).getContentType());
            holder.user_title.setText(list.get(position).getTitle());
            holder.user_content.setText(list.get(position).getContent());
            Glide.with(context).load(list.get(position).getCoverImg()).asBitmap().into(holder.user_content_image);
            holder.comment.setText(list.get(position).getCommentNum() + "");
            holder.praise.setText(list.get(position).getPraiseNum() + "");
//        holder.gift.setText(list.get(position).getGiftNum()+"");
        }
    }

    @Override
    public int getItemCount() {
        return list.isEmpty() ? 0 : list.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        public ImageView user_img;
        public TextView user_name;
        public Button value_btn;
        public TextView user_time;
        public TextView user_from;
        public TextView user_content;
        public ImageView user_content_image;
        public ImageView one_other;
        public TextView user_content_type;
        public ImageView user_son_image;
        public TextView user_son_name;
        public TextView user_son_detail;
        public Button user_son_btn;
        public RelativeLayout user_son;
        public TextView value_collectText;
        public LinearLayout value_collection;
        public TextView comment;
        public TextView praise;
        public ImageView gift_img;
        public TextView gift;
        public TextView shape;
        public TextView user_title;

        public Holder(View itemView) {
            super(itemView);
            user_img = itemView.findViewById(R.id.user_img);
            user_name = (TextView) itemView.findViewById(R.id.user_name);
            value_btn = (Button) itemView.findViewById(R.id.value_btn);
            user_time = (TextView) itemView.findViewById(R.id.user_time);
            user_from = (TextView) itemView.findViewById(R.id.user_from);
            user_content = (TextView) itemView.findViewById(R.id.user_content);
            user_content_image = (ImageView) itemView.findViewById(R.id.user_content_image);
            one_other = (ImageView) itemView.findViewById(R.id.one_other);
            user_content_type = (TextView) itemView.findViewById(R.id.user_content_type);
            user_son_image = (ImageView) itemView.findViewById(R.id.user_son_image);
            user_son_name = (TextView) itemView.findViewById(R.id.user_son_name);
            user_son_detail = (TextView) itemView.findViewById(R.id.user_son_detail);
            user_son_btn = (Button) itemView.findViewById(R.id.user_son_btn);
            user_son = (RelativeLayout) itemView.findViewById(R.id.user_son);
            value_collectText = (TextView) itemView.findViewById(R.id.value_collectText);
            value_collection = (LinearLayout) itemView.findViewById(R.id.value_collection);
            comment = (TextView) itemView.findViewById(R.id.comment);
            praise = (TextView) itemView.findViewById(R.id.praise);
            gift_img = (ImageView) itemView.findViewById(R.id.gift_img);
            gift = (TextView) itemView.findViewById(R.id.gift);
            shape = (TextView) itemView.findViewById(R.id.shape);
            user_title = (TextView) itemView.findViewById(R.id.user_title);

        }

    }


}
