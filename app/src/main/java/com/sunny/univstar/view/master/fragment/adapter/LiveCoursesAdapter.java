package com.sunny.univstar.view.master.fragment.adapter;

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
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sunny.univstar.R;
import com.sunny.univstar.view.master.fragment.HomeMasterBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 张玗 on 2018/5/4.
 */

public class LiveCoursesAdapter extends RecyclerView.Adapter<LiveCoursesAdapter.Holder> implements View.OnClickListener {
    private List<HomeMasterBean.DataBean.LiveCoursesBean> liveCoursesBeanList;
    public Context context;

    public LiveCoursesAdapter(List<HomeMasterBean.DataBean.LiveCoursesBean> liveCoursesBeanList, Context context) {
        this.liveCoursesBeanList = liveCoursesBeanList;
        this.context = context;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.master_livecourses_item, parent, false);
        inflate.setOnClickListener(this);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        Glide.with(context)
                .load(liveCoursesBeanList.get(position).getCoverImg())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                .into(new BitmapImageViewTarget(holder.master_live_image){
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(10); //设置圆角弧度
                        holder.master_live_image.setImageDrawable(circularBitmapDrawable);
                    }
                });
        if (liveCoursesBeanList.get(position).getUserType() == 4){
            holder.master_live_vip_img.setImageResource(R.mipmap.home_level_vip_red);
        }else if (liveCoursesBeanList.get(position).getUserType() == 3){
            holder.master_live_vip_img.setImageResource(R.mipmap.home_level_vip_yellow);
        }else if (liveCoursesBeanList.get(position).getUserType() == 2){
            holder.master_live_vip_img.setImageResource(R.mipmap.home_level_vip_blue);
        }
        holder.master_live_name.setText(liveCoursesBeanList.get(position).getNickname());
        holder.master_live_type.setText(liveCoursesBeanList.get(position).getType());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(liveCoursesBeanList.get(position).getStartDate());
        holder.master_live_content.setText("讲堂开播"+sdf.format(date));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return liveCoursesBeanList.isEmpty()?0:liveCoursesBeanList.size();
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

     class Holder extends RecyclerView.ViewHolder {
        private ImageView master_live_image;
        private TextView master_live_type;
        private TextView master_live_name;
        private TextView master_live_content;
        private ImageView master_live_vip_img;
        private Holder(View itemView) {
            super(itemView);
            this.master_live_image =  itemView.findViewById(R.id.master_live_image);
            this.master_live_type =  itemView.findViewById(R.id.master_live_type);
            this.master_live_name =  itemView.findViewById(R.id.master_live_name);
            this.master_live_content = itemView.findViewById(R.id.master_live_content);
            master_live_vip_img = itemView.findViewById(R.id.master_live_vip_img);
        }
    }




    /*@Override
    public int getCount() {
        return liveCoursesBeanList.isEmpty() ? 0 : liveCoursesBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh = null;
        if (view == null) {
            vh = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.master_livecourses_item, null);

            vh.master_live_image = (ImageView) view.findViewById(R.id.master_live_image);
            vh.master_live_name = (TextView) view.findViewById(R.id.master_live_name);
            vh.master_live_content = (TextView) view.findViewById(R.id.master_live_content);
            vh.master_live_type = (TextView) view.findViewById(R.id.master_live_type);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        Glide.with(context).load(liveCoursesBeanList.get(i).getCoverImg()).asBitmap().into(vh.master_live_image);
        vh.master_live_name.setText(liveCoursesBeanList.get(i).getNickname());
        vh.master_live_type.setText(liveCoursesBeanList.get(i).getType());
        vh.master_live_content.setText(liveCoursesBeanList.get(i).getEndDate()+"");
        return view;
    }


    public static class ViewHolder {
        public View view;
        public ImageView master_live_image;
        public TextView master_live_name;
        public TextView master_live_content;
        public TextView master_live_type;


    }*/


}
