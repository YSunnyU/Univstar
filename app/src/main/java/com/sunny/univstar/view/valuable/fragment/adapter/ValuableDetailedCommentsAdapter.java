package com.sunny.univstar.view.valuable.fragment.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
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
import com.sunny.univstar.model.entity.ValuableDetailedCommentsEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.sunny.univstar.R.id.comment_listitem_praise_cb;

public class ValuableDetailedCommentsAdapter extends BaseAdapter implements View.OnClickListener {
    private List<ValuableDetailedCommentsEntity.DataBean.CommentsBean> commentsList;
    private Context context;

    public ValuableDetailedCommentsAdapter(List<ValuableDetailedCommentsEntity.DataBean.CommentsBean> commentsList, Context context) {
        this.commentsList = commentsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return commentsList.size() > 0 ? commentsList.get(0).getList().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private boolean isPraise;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("aaaaa",commentsList.get(0).getList().size()+"");
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.work_detailed_comments_item, null);
            viewHolder.comment_listitem_head_img =  convertView.findViewById(R.id.comment_listitem_head_img);
            viewHolder.comment_listitem_name =  convertView.findViewById(R.id.comment_listitem_name);
            viewHolder.ccomment_listitem_time = convertView.findViewById(R.id.comment_listitem_time);
            viewHolder.comment_listitem_content =  convertView.findViewById(R.id.comment_listitem_content);
            viewHolder.comment_listitem_praise_cb =  convertView.findViewById(comment_listitem_praise_cb);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ViewHolder finalViewHolder = viewHolder;
//        设置头像
        if (commentsList.get(0).getList().size() > 0) {
            Glide.with(context)
                    .load(commentsList.get(0).getList().get(position).getPhoto())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(new BitmapImageViewTarget(finalViewHolder.comment_listitem_head_img) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            bitmapDrawable.setCircular(true);
                            finalViewHolder.comment_listitem_head_img.setImageDrawable(bitmapDrawable);
                        }
                    });
//        设置昵称
            viewHolder.comment_listitem_name.setText(commentsList.get(0).getList().get(position).getNickname());
//        设置内容
            viewHolder.comment_listitem_content.setText(commentsList.get(0).getList().get(position).getContent());
//        设置回复时间
            SimpleDateFormat sdf = new SimpleDateFormat("HH");
            long createDate = commentsList.get(0).getList().get(position).getCreateDate();
            long systemDate = System.currentTimeMillis();
            Date date = new Date((systemDate - createDate));
            viewHolder.ccomment_listitem_time.setText(sdf.format(date));
//        设置赞了多少
            if(commentsList.get(0).getList().get(position).getIsPraise()==0){
                isPraise = false;
                viewHolder.comment_listitem_praise_cb.setChecked(false);
            }else{
                isPraise = true;
                viewHolder.comment_listitem_praise_cb.setChecked(true);
            }
            viewHolder.comment_listitem_praise_cb.setText(commentsList.get(0).getList().get(position).getPraiseNum()+"");
            viewHolder.comment_listitem_praise_cb.setOnClickListener(this);
            viewHolder.comment_listitem_praise_cb.setTag(R.id.work_detailed_check,position);
        }
        return convertView;
    }

    class ViewHolder {
        private ImageView comment_listitem_head_img;
        private TextView comment_listitem_name;
        private TextView ccomment_listitem_time;
        private TextView comment_listitem_content;
        private CheckBox comment_listitem_praise_cb;
    }
    public interface OnClickItem{
        void onClickItem(View view,boolean isPraise,int position);
    }
    private OnClickItem onClickItem;
    public void setOnClickItem(OnClickItem onClickItem){
        this.onClickItem = onClickItem;
    }
    @Override
    public void onClick(View v) {
        if (onClickItem != null)
            onClickItem.onClickItem(v, isPraise, (int) v.getTag(R.id.work_detailed_check));
    }
}