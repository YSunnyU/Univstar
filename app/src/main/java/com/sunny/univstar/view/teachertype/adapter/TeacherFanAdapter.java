package com.sunny.univstar.view.teachertype.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sunny.univstar.R;
import com.sunny.univstar.model.entity.TeacherFanEntity;

import java.util.List;

/**
 * Created by DELL on 2018/5/10.
 */

public class TeacherFanAdapter extends BaseAdapter implements View.OnClickListener {
    private List<TeacherFanEntity.DataBean.ListBean> mList;
    private Context context;

    public TeacherFanAdapter(List<TeacherFanEntity.DataBean.ListBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.teacher_fan_item_view, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ViewHolder finalViewHolder = viewHolder;
        Glide.with(context)
                .load(mList.get(position).getPhoto())
                .asBitmap()
                .placeholder(R.mipmap.user_head_portrait)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(new BitmapImageViewTarget(finalViewHolder.teacher_fan_item_head_img){
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(),resource);
                        bitmapDrawable.setCircular(true);
                        finalViewHolder.teacher_fan_item_head_img.setImageDrawable(bitmapDrawable);
                    }
                });
        viewHolder.teacher_fan_item_name.setText(mList.get(position).getNickname());
        viewHolder.teacher_fan_item_check.setOnClickListener(this);
        viewHolder.teacher_fan_item_check.setTag(R.id.work_detailed_check,position);
        if (mList.get(position).getAttention() == 0){
            viewHolder.teacher_fan_item_check.setChecked(true);
            viewHolder.teacher_fan_item_check.setText("关注");
            viewHolder.teacher_fan_item_check.setTextColor(Color.parseColor("#9E9E9E"));

        }else {
            viewHolder.teacher_fan_item_check.setChecked(false);
            viewHolder.teacher_fan_item_check.setText("已关注");
            viewHolder.teacher_fan_item_check.setTextColor(Color.parseColor("#FFFFFF"));
        }
            if (mList.get(position).getUserType() == 4 || mList.get(position).getUserType() == 6){
                viewHolder.teacher_fan_item_vip_img.setImageResource(R.mipmap.home_level_vip_red);
                viewHolder.teacher_fan_item_vip_img.setVisibility(View.VISIBLE);
        }else if (mList.get(position).getUserType() == 3) {
            viewHolder.teacher_fan_item_vip_img.setImageResource(R.mipmap.home_level_vip_yellow);
                viewHolder.teacher_fan_item_vip_img.setVisibility(View.VISIBLE);
        }else if (mList.get(position).getUserType() == 2) {
            viewHolder.teacher_fan_item_vip_img.setImageResource(R.mipmap.home_level_vip_blue);
                viewHolder.teacher_fan_item_vip_img.setVisibility(View.VISIBLE);
        }else {
            viewHolder.teacher_fan_item_vip_img.setVisibility(View.GONE);
        }
        return convertView;
    }

    public interface OnClickItem{
        void onClickItem(View view ,int position);
    }
    private OnClickItem onClickItem;
    public void setOnClickItem(OnClickItem onClickItem){
        this.onClickItem = onClickItem;
    }
    @Override
    public void onClick(View v) {
        if (onClickItem != null)
            onClickItem.onClickItem(v, (int) v.getTag(R.id.work_detailed_check));
    }


    class ViewHolder {
        public View rootView;
        public ImageView teacher_fan_item_head_img;
        public TextView teacher_fan_item_name;
        public ImageView teacher_fan_item_vip_img;
        public CheckBox teacher_fan_item_check;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.teacher_fan_item_head_img = rootView.findViewById(R.id.teacher_fan_item_head_img);
            this.teacher_fan_item_name = (TextView) rootView.findViewById(R.id.teacher_fan_item_name);
            this.teacher_fan_item_vip_img = (ImageView) rootView.findViewById(R.id.teacher_fan_item_vip_img);
            this.teacher_fan_item_check = (CheckBox) rootView.findViewById(R.id.teacher_fan_item_check);
        }

    }
}
