package com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.adapter;

import android.content.Context;
import android.graphics.Bitmap;
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
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.CollectPostsBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 张玗 on 2018/5/12.
 */

public class CollectPostsAdapter extends RecyclerView.Adapter<CollectPostsAdapter.Holder> implements View.OnClickListener {
    List<CollectPostsBean.DataBean.ArtcircleListBean.ListBean> list;
    public Context context;

    public CollectPostsAdapter(List<CollectPostsBean.DataBean.ArtcircleListBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_work_content_item, parent, false);
        Holder holder = new Holder(inflate);
        inflate.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        if (list.size() > 0) {
            Glide.with(context)
                    .load(list.get(position).getPhoto())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                    .into(new BitmapImageViewTarget(holder.user_img) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            super.setResource(resource);
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true); //设置圆角弧度
                            holder.user_img.setImageDrawable(circularBitmapDrawable);
                        }
                    });
            if (list.get(position).getPicProperty() != null && !"".equals(list.get(position).getPicProperty())) {
                String picProperty = list.get(position).getPicProperty();
                String[] split = picProperty.split("_");
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                layoutParams.leftMargin = 155;
                layoutParams.topMargin = 40;
                holder.user_content_image.setLayoutParams(layoutParams);
            }
//            if (list.get(position).getFavoriteNum() > 0) {
//                holder.gift_img.setImageResource(R.mipmap.reward_active);
//                holder.gift.setTextColor(Color.parseColor("#ff0000"));
//            } else {
//                holder.gift_img.setImageResource(R.mipmap.reward_normal);
//            }
            holder.value_gift.setVisibility(View.GONE);
            holder.value_collection.setVisibility(View.VISIBLE);

            //            时间转换
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
            Date date = new Date(list.get(position).getCreateDate());
            holder.user_time.setText(sdf.format(date));
            holder.gift.setText(list.get(position).getFavoriteNum() + "");
            holder.user_name.setText(list.get(position).getNickname());
            holder.value_btn.setText(list.get(position).getContentType());
            holder.user_title.setText(list.get(position).getTitle());
            holder.user_content.setText(list.get(position).getContent());
            Glide.with(context).load(list.get(position).getCoverImg()).asBitmap().into(holder.user_content_image);
            holder.comment.setText(list.get(position).getCommentNum() + "");
            holder.praise.setText(list.get(position).getPraiseNum() + "");
            holder.itemView.setTag(position);
//        holder.gift.setText(list.get(position).getGiftNum()+"");
        }
    }

    @Override
    public int getItemCount() {
        return list.isEmpty() ? 0 : list.size();
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
        public ImageView user_img;
        public TextView user_name;
        public Button value_btn;
        public TextView user_time;
        public TextView user_from;
        public TextView user_title;
        public TextView user_content;
        public ImageView user_content_image;
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
        public LinearLayout shape;
        public LinearLayout value_gift;

        public Holder(View itemView) {
            super(itemView);
            this.user_img = (ImageView) itemView.findViewById(R.id.user_img);
            this.user_name = (TextView) itemView.findViewById(R.id.user_name);
            this.value_btn = (Button) itemView.findViewById(R.id.value_btn);
            this.user_time = (TextView) itemView.findViewById(R.id.user_time);
            this.user_from = (TextView) itemView.findViewById(R.id.user_from);
            this.user_title = (TextView) itemView.findViewById(R.id.user_title);
            this.user_content = (TextView) itemView.findViewById(R.id.user_content);
            this.user_content_image = (ImageView) itemView.findViewById(R.id.user_content_image);
            this.user_content_type = (TextView) itemView.findViewById(R.id.user_content_type);
            this.user_son_image = (ImageView) itemView.findViewById(R.id.user_son_image);
            this.user_son_name = (TextView) itemView.findViewById(R.id.user_son_name);
            this.user_son_detail = (TextView) itemView.findViewById(R.id.user_son_detail);
            this.user_son_btn = (Button) itemView.findViewById(R.id.user_son_btn);
            this.user_son = (RelativeLayout) itemView.findViewById(R.id.user_son);
            this.value_collectText = (TextView) itemView.findViewById(R.id.value_collectText);
            this.value_collection = (LinearLayout) itemView.findViewById(R.id.value_collection);
            this.comment = (TextView) itemView.findViewById(R.id.comment);
            this.praise = (TextView) itemView.findViewById(R.id.praise);
            this.gift_img = (ImageView) itemView.findViewById(R.id.gift_img);
            this.gift = (TextView) itemView.findViewById(R.id.gift);
            this.shape = (LinearLayout) itemView.findViewById(R.id.shape);
            this.value_gift = (LinearLayout) itemView.findViewById(R.id.value_gift);

        }
    }


}
