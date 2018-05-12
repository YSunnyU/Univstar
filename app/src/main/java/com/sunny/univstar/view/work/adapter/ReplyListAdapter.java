package com.sunny.univstar.view.work.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sunny.univstar.R;
import com.sunny.univstar.app.App;
import com.sunny.univstar.model.entity.ReplyListEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DELL on 2018/5/11.
 */

public class ReplyListAdapter extends RecyclerView.Adapter<ReplyListAdapter.Holder> implements View.OnClickListener {
    private List<ReplyListEntity.DataBean> mList;
    private Context context;


    public ReplyListAdapter(List<ReplyListEntity.DataBean> mList) {
        this.mList = mList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.reply_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        if (mList.get(0).getComments().getList().size() > 0 || mList.get(0).getComment() != null) {
            if (position == 0) {
                Glide.with(App.context)
                        .load(mList.get(position).getComment().getPhoto())
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(new BitmapImageViewTarget(holder.reply_list_head_img) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                                bitmapDrawable.setCircular(true);
                                holder.reply_list_head_img.setImageDrawable(bitmapDrawable);
                            }
                        });
                holder.reply_list_content.setText(mList.get(position).getComment().getContent());
                holder.reply_list_nick_name.setText(mList.get(position).getComment().getNickname());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = new Date(mList.get(position).getComment().getCreateDate());
                holder.reply_list_time.setText(sdf.format(date));
                if (mList.get(position).getComment().getIsPraise() == 0) {
                    holder.reply_list_praise.setChecked(false);
                } else {
                    holder.reply_list_praise.setChecked(true);
                }
                holder.reply_list_replyName.setVisibility(View.GONE);
                holder.reply_list_aite.setVisibility(View.GONE);
                holder.reply_list_praise.setText(mList.get(position).getComment().getPraiseNum()+"");
                holder.reply_list_praise.setOnClickListener(this);
                holder.reply_list_praise.setTag(position);
            } else {
                Glide.with(App.context)
                        .load(mList.get(0).getComments().getList().get(position-1).getPhoto())
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(new BitmapImageViewTarget(holder.reply_list_head_img) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                                bitmapDrawable.setCircular(true);
                                holder.reply_list_head_img.setImageDrawable(bitmapDrawable);
                            }
                        });
                holder.reply_list_content.setText(mList.get(0).getComments().getList().get(position-1).getContent());
                holder.reply_list_nick_name.setText(mList.get(0).getComments().getList().get(position-1).getNickname());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = new Date(mList.get(0).getComments().getList().get(position-1).getCreateDate());
                holder.reply_list_time.setText(sdf.format(date));
                if (mList.get(0).getComments().getList().get(position-1).getIsPraise() == 0) {
                    holder.reply_list_praise.setChecked(false);
                } else {
                    holder.reply_list_praise.setChecked(true);
                }
                if (mList.get(0).getComments().getList().get(position-1).getReplyName() != null) {
                    holder.reply_list_replyName.setVisibility(View.VISIBLE);
                    holder.reply_list_replyName.setText("@" + mList.get(0).getComments().getList().get(position-1).getReplyName());
                }else {
                    holder.reply_list_replyName.setVisibility(View.GONE);
                }
                holder.reply_list_praise.setText(mList.get(0).getComments().getList().get(position-1).getPraiseNum()+"");
                holder.reply_list_aite.setVisibility(View.VISIBLE);
                holder.reply_list_aite.setOnClickListener(this);
                holder.reply_list_aite.setTag(position-1);
                holder.reply_list_praise.setOnClickListener(this);
                holder.reply_list_praise.setTag(position-1);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList.size() > 0 && mList.get(0).getComments()!=null)
            return mList.get(0).getComments().getList().size()+1;
        else
            return 0;
    }

    public interface OnClickItem{
        void onClickItem(View view,int position);
    }
    private OnClickItem onClickItem;
    public void setOnClickItem(OnClickItem onClickItem){
        this.onClickItem = onClickItem;
    }
    @Override
    public void onClick(View v) {
        if (onClickItem != null)
            onClickItem.onClickItem(v, (int) v.getTag());
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView reply_list_head_img;
        private TextView reply_list_nick_name;
        private TextView reply_list_time;
        private TextView reply_list_content;
        private CheckBox reply_list_praise;
        private TextView reply_list_aite;
        private TextView reply_list_replyName;
        public Holder(View itemView) {
            super(itemView);
              reply_list_head_img = itemView.findViewById(R.id.reply_list_head_img);
              reply_list_nick_name = itemView.findViewById(R.id.reply_list_nick_name);
              reply_list_time = itemView.findViewById(R.id.reply_list_time);
              reply_list_content = itemView.findViewById(R.id.reply_list_content);
              reply_list_praise = itemView.findViewById(R.id.reply_list_praise);
            reply_list_aite = itemView.findViewById(R.id.reply_list_aite);
            reply_list_replyName = itemView.findViewById(R.id.reply_list_replyName);
        }
    }
}
