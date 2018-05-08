package com.sunny.univstar.view.work.adapter;

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
import com.sunny.univstar.model.entity.HomeWokListModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sunny.univstar.app.App.context;

/**
 * Created by DELL on 2018/5/3.
 */

public class HomeWorkAdapter extends RecyclerView.Adapter<HomeWorkAdapter.Holder> {
    private RecyclerView mRecyclerView;

    private List<HomeWokListModel.DataBean.ListBean> data = new ArrayList<>();
    private Context mContext;

    private View VIEW_FOOTER;
    private View VIEW_HEADER;

    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;

    public HomeWorkAdapter(List<HomeWokListModel.DataBean.ListBean> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View home_work_content_item = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_work_content_item, parent, false);
        Holder holder = new Holder(home_work_content_item);

        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        Glide.with(mContext)
                .load(data.get(position).getPhoto())
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
        holder.user_name.setText(data.get(position).getNickname());
        //            时间转换
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        Date date = new Date(data.get(position).getCreateDate());
        holder.user_time.setText(sdf.format(date));
        holder.user_from.setText(data.get(position).getSource());
        holder.user_content.setText(data.get(position).getContent());
        if (data.get(position).getPicProperty() != null && !"".equals(data.get(position).getPicProperty()))
        {
            String picProperty = data.get(position).getPicProperty();
            String[] split = picProperty.split("_");
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            layoutParams.leftMargin = 155;
            layoutParams.topMargin = 40;
            holder.user_content_image.setLayoutParams(layoutParams);
        }
        if (!"图片".equals(data.get(position).getWorksType())) {
            Glide.with(mContext).load(R.mipmap.play_music_bg).into(holder.user_content_image);
        } else if ("图片".equals(data.get(position).getWorksType())) {
            Glide.with(mContext).load(data.get(position).getCoverImg()).into(holder.user_content_image);
        }
        holder.comment.setText(data.get(position).getCommentNum()+"");
        holder.praise.setText(data.get(position).getPraiseNum()+"");
        holder.gift.setText(data.get(position).getGiftNum()+"");
        if (data.get(position).getTRealname()!=null){
            holder.user_son.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(data.get(position).getTPhoto())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                    .into(new BitmapImageViewTarget(holder.user_son_image){
                        @Override
                        protected void setResource(Bitmap resource) {
                            super.setResource(resource);
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true); //设置圆角弧度
                            holder.user_son_image.setImageDrawable(circularBitmapDrawable);
                        }
                    });
            holder.user_son_name.setText(data.get(position).getTRealname());
            holder.user_son_detail.setText(data.get(position).getTIntro());

        }
    }

    @Override
    public int getItemCount() {
        return data.isEmpty() ? 0 : data.size();
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

        public Holder(View itemView) {
            super(itemView);
            this.user_img = (ImageView) itemView.findViewById(R.id.user_img);
            this.user_name = (TextView) itemView.findViewById(R.id.user_name);
            this.value_btn = (Button) itemView.findViewById(R.id.value_btn);
            this.user_time = (TextView) itemView.findViewById(R.id.user_time);
            this.user_from = (TextView) itemView.findViewById(R.id.user_from);
            this.user_content = (TextView) itemView.findViewById(R.id.user_content);
            this.user_content_image = (ImageView) itemView.findViewById(R.id.user_content_image);
            this.one_other = (ImageView) itemView.findViewById(R.id.one_other);
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
            this.shape = (TextView) itemView.findViewById(R.id.shape);
        }
    }



    /*@Override
    public HomeWorkAdapter.WorkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new WorkHolder(VIEW_FOOTER);
        } else if (viewType == TYPE_HEADER) {
            return new WorkHolder(VIEW_HEADER);
        } else {
            return new WorkHolder(getLayout(R.layout.home_work_item_list));
        }
    }

    private int currentPosition = 0;

    @Override
    public void onBindViewHolder(WorkHolder holder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) {
                RecyclerView work_list = holder.itemView.findViewById(R.id.work_lists);
                final View viewById = holder.itemView.findViewById(R.id.list_head);
                work_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                        int currentPosition = layoutManager.getPosition(layoutManager.getChildAt(0));
                        if (currentPosition >= 1) {
//                    索引大于等于1时显示悬浮
                            viewById.setVisibility(View.VISIBLE);
                        } else {
                            viewById.setVisibility(View.GONE);
                        }
                    }
                });
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                work_list.setLayoutManager(linearLayoutManager);
                HomeWorkContentAdapter myAdapter = new HomeWorkContentAdapter(data, mContext);
                work_list.setAdapter(myAdapter);
            }
        }
    }

    @Override
    public int getItemCount() {
//        HomeWokListModel.DataBean.ListBean mList = (HomeWokListModel.DataBean.ListBean) data.get(0);
        int count = (data == null ? 0 : data.size());
        if (VIEW_FOOTER != null) {
            count++;
        }

        if (VIEW_HEADER != null) {
            count++;
        }
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View getLayout(int layoutId) {
        return LayoutInflater.from(mContext).inflate(layoutId, null);
    }

    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("hearview has already exists!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            VIEW_HEADER = headerView;
            ifGridLayoutManager();
            notifyItemInserted(0);
        }

    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }

    private void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                    ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() :
                            1;
                }
            });
        }
    }

    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }


    public static class WorkHolder extends RecyclerView.ViewHolder {

        public WorkHolder(View itemView) {
            super(itemView);
        }
    }*/

}
