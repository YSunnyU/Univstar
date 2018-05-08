package com.sunny.univstar.view.teachertype.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunny.univstar.R;
import com.sunny.univstar.model.entity.FindTeacherEntity;

import java.util.List;

/**
 * Created by DELL on 2018/5/7.
 */

public class FindTeacherAdapter extends RecyclerView.Adapter<FindTeacherAdapter.Holder> implements View.OnClickListener {
    private List<FindTeacherEntity> mList;
    private Context context;


    public FindTeacherAdapter(List<FindTeacherEntity> mList) {
        this.mList = mList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_teacher_item, parent, false);
        view.setOnClickListener(this);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (mList.get(0).getData().getList() != null && mList.get(0).getData().getList().size() > 0)
        {
            Glide.with(context).load(mList.get(0).getData().getList().get(position).getImages()).into(holder.find_teacher_img);
            holder.find_teache_type.setText(mList.get(0).getData().getList().get(position).getIntro());
            holder.find_teacher_name.setText(mList.get(0).getData().getList().get(position).getNickname());
            if (mList.get(0).getData().getList().get(position).getUserType() == 4)
                holder.find_teacher_vip_img.setImageResource(R.mipmap.home_level_vip_red);
            else if (mList.get(0).getData().getList().get(position).getUserType() == 3)
                holder.find_teacher_vip_img.setImageResource(R.mipmap.home_level_vip_yellow);
            else if (mList.get(0).getData().getList().get(position).getUserType() == 2)
                holder.find_teacher_vip_img.setImageResource(R.mipmap.home_level_vip_blue);
            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null && mList.size() > 0) {
            return mList.get(0).getData().getList().size();
        }else {
            return 0;
        }
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
            onClickItem.onClickItem(v,(int)v.getTag());
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView find_teacher_img;
        private TextView find_teacher_name;
        private ImageView find_teacher_vip_img;
        private TextView find_teache_type;
        public Holder(View itemView) {
            super(itemView);
            find_teacher_img = itemView.findViewById(R.id.find_teacher_img);
            find_teacher_name = itemView.findViewById(R.id.find_teacher_name);
            find_teacher_vip_img = itemView.findViewById(R.id.find_teacher_vip_img);
            find_teache_type = itemView.findViewById(R.id.find_teache_type);
        }
    }
}
