package com.sunny.univstar.view.work.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sunny.univstar.R;
import com.sunny.univstar.model.entity.WorkDetailedEntity;

import java.util.List;

/**
 * Created by DELL on 2018/5/9.
 */

public class WorkDetailedRewardAdapter extends RecyclerView.Adapter<WorkDetailedRewardAdapter.Holder>{
    private List<WorkDetailedEntity.DataBean.RewardUserListBean> rewardUserList;
    private Context context;

    public WorkDetailedRewardAdapter(List<WorkDetailedEntity.DataBean.RewardUserListBean> rewardUserList) {
        this.rewardUserList = rewardUserList;
    }

    @Override
    public WorkDetailedRewardAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.work_detailed_reward_img,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final WorkDetailedRewardAdapter.Holder holder, int position) {
        if (rewardUserList.size() > 0){
            Glide.with(context)
                    .load(rewardUserList.get(position).getUserPhoto())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(new BitmapImageViewTarget(holder.imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            bitmapDrawable.setCircular(true);
                            holder.imageView.setImageDrawable(bitmapDrawable);
                        }
                    });
        }
    }

    @Override
    public int getItemCount() {
        return rewardUserList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.work_detailed_reward_head_img);
        }
    }
}
