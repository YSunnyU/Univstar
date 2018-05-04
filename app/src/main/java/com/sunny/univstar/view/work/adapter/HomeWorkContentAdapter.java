package com.sunny.univstar.view.work.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sunny.univstar.R;
import com.sunny.univstar.model.entity.HomeWokListModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DELL on 2018/5/4.
 */

class HomeWorkContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Object> list;
    Context mContext;



    public HomeWorkContentAdapter(List<Object> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 0;
        }else {
            return 1;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.work_list_content_item,parent,false);
            return new HeadHolder(inflate);
        }else {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_work_content_item, parent, false);
            return new ContentHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentHolder){
            if (list != null && list.size()>0) {
                HomeWokListModel.DataBean.ListBean listBean = (HomeWokListModel.DataBean.ListBean) list.get(position);

                Glide.with(mContext)
                        .load(listBean.getPhoto())
                        .asBitmap()
                        .placeholder(R.mipmap.user_head_portrait)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(new BitmapImageViewTarget(((ContentHolder) holder).user_img){
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(),resource);
                                bitmapDrawable.setCircular(true);
                                ((ContentHolder) holder).user_img.setImageDrawable(bitmapDrawable);
                            }
                        });
                ((ContentHolder) holder).user_name.setText(listBean.getNickname());
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
                Date date = new Date(listBean.getCreateDate());
                ((ContentHolder) holder).user_time.setText(sdf.format(date));
                ((ContentHolder) holder).user_from.setText(listBean.getSource());
                ((ContentHolder) holder).user_content.setText(listBean.getContent());
                if (listBean.getPicProperty() != null) {
                    String picProperty = listBean.getPicProperty();
                    Log.e("aaaaa"+position,picProperty);
                    String[] split = picProperty.split("_");
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                    layoutParams.leftMargin = 160;
                    layoutParams.topMargin = 40;
                    ((ContentHolder) holder).user_content_image.setLayoutParams(layoutParams);
                }
//            如果服务器返回的Type不是图片就加载音乐图片
                if ("图片".equals(listBean.getWorksType())) {
                    Glide.with(mContext)
                            .load(listBean.getCoverImg())
                            .into(((ContentHolder) holder).user_content_image);
                }else {
                    ((ContentHolder) holder).user_content_image.setImageResource(R.mipmap.play_music_bg);
                }
                ((ContentHolder) holder).comment.setText(listBean.getCommentNum()+"");
                ((ContentHolder) holder).praise.setText(listBean.getPraiseNum()+"");
                if (listBean.getGiftNum() > 0){
                    ((ContentHolder) holder).gift_img.setImageResource(R.mipmap.reward_active);
                    ((ContentHolder) holder).gift.setTextColor(Color.parseColor("#FF0000"));
                }
                ((ContentHolder) holder).gift.setText(listBean.getGiftNum()+"");
            }
        }else if (holder instanceof HeadHolder){

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ContentHolder extends RecyclerView.ViewHolder {
        private ImageView user_img;//用户头像
        private TextView user_name;//用户昵称
        private TextView user_time;//发表时间
        private TextView user_from;//来自
        private TextView user_content;//内容
        private ImageView user_content_image;//内容图片
        private ImageView one_other;//云标签
        private ImageView gift_img;
        private TextView comment;
        private TextView praise;
        private TextView gift;
        private TextView shape;
        public ContentHolder(View itemView) {
            super(itemView);
            user_img = itemView.findViewById(R.id.user_img);
            user_name = itemView.findViewById(R.id.user_name);
            user_time = itemView.findViewById(R.id.user_time);
            user_from = itemView.findViewById(R.id.user_from);
            gift_img = itemView.findViewById(R.id.gift_img);
            user_content = itemView.findViewById(R.id.user_content);
            user_content_image = itemView.findViewById(R.id.user_content_image);
            comment = itemView.findViewById(R.id.comment);
            praise = itemView.findViewById(R.id.praise);
            gift = itemView.findViewById(R.id.gift);
            shape = itemView.findViewById(R.id.shape);
        }
    }

    public class HeadHolder extends RecyclerView.ViewHolder{

        public HeadHolder(View itemView) {
            super(itemView);
        }
    }
}
