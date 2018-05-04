package com.sunny.univstar.view.work.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunny.univstar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2018/5/3.
 */

public class HomeWorkAdapter extends RecyclerView.Adapter<HomeWorkAdapter.WorkHolder> {
    private RecyclerView mRecyclerView;

    private List<Object> data = new ArrayList<>();
    private Context mContext;

    private View VIEW_FOOTER;
    private View VIEW_HEADER;

    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;

    public HomeWorkAdapter(List<Object> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
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
    }
//    private List<Object> list;
//
//
//    public HomeWorkAdapter(List<Object> list) {
//        this.list = list;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        Object o = list.get(position);
//        if (o instanceof HomeWokListModel.DataBean.ListBean) {
//            return 0;
//        }
//        return 0;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == 0) {
//            /**
//             * 加载作业内容
//             */
//            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.work_list_content_item, parent, false);
//            return new MessageHolder(inflate);
//        }
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
//        if (holder instanceof MessageHolder) {
////            智能筛选被点击时
//            ((MessageHolder) holder).home_work_fragment_capacity_group.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    显示隐藏字体大小是否可以被点击
//                    ((MessageHolder) holder).home_work_fragment_capacity_line.setVisibility(View.VISIBLE);
//                    ((MessageHolder) holder).home_work_fragment_comment_line.setVisibility(View.INVISIBLE);
//                    ((MessageHolder) holder).home_work_fragment_listen_line.setVisibility(View.INVISIBLE);
////                    ((MessageHolder) holder).home_work_fragment_capacity_tv.setTextSize(20);
////                    ((MessageHolder) holder).home_work_fragment_capacity_tv.setTextColor(Color.parseColor("#333333"));
//                    ((MessageHolder) holder).home_work_fragment_capacity_group.setEnabled(false);
//                    ((MessageHolder) holder).home_work_fragment_comment_group.setEnabled(true);
//                    ((MessageHolder) holder).home_work_fragment_listen_group.setEnabled(true);
//                }
//            });
//            ((MessageHolder) holder).home_work_fragment_comment_group.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ((MessageHolder) holder).home_work_fragment_capacity_line.setVisibility(View.INVISIBLE);
//                    ((MessageHolder) holder).home_work_fragment_comment_line.setVisibility(View.VISIBLE);
//                    ((MessageHolder) holder).home_work_fragment_listen_line.setVisibility(View.INVISIBLE);
//
//                    ((MessageHolder) holder).home_work_fragment_comment_group.setEnabled(false);
//                    ((MessageHolder) holder).home_work_fragment_capacity_group.setEnabled(true);
//                    ((MessageHolder) holder).home_work_fragment_listen_group.setEnabled(true);
//                }
//            });
//            ((MessageHolder) holder).home_work_fragment_listen_group.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ((MessageHolder) holder).home_work_fragment_capacity_line.setVisibility(View.INVISIBLE);
//                    ((MessageHolder) holder).home_work_fragment_comment_line.setVisibility(View.INVISIBLE);
//                    ((MessageHolder) holder).home_work_fragment_listen_line.setVisibility(View.VISIBLE);
//
//                    ((MessageHolder) holder).home_work_fragment_comment_group.setEnabled(true);
//                    ((MessageHolder) holder).home_work_fragment_capacity_group.setEnabled(true);
//                    ((MessageHolder) holder).home_work_fragment_listen_group.setEnabled(false);
//                }
//            });
//        }
//    }
//    /**
//     * 作业内容
//     */
//    private class MessageHolder extends RecyclerView.ViewHolder {
//        //智能筛选
//        private TextView home_work_fragment_capacity_tv;
//        private TextView home_work_fragment_capacity_line;
//        private RelativeLayout home_work_fragment_capacity_group;
//        //偷听最多
//        private TextView home_work_fragment_listen_tv;
//        private TextView home_work_fragment_listen_line;
//        private RelativeLayout home_work_fragment_listen_group;
//        //最新点拼
//        private TextView home_work_fragment_comment_tv;
//        private TextView home_work_fragment_comment_line;
//        private RelativeLayout home_work_fragment_comment_group;
//        public MessageHolder(View itemView) {
//            super(itemView);
//            home_work_fragment_capacity_tv = itemView.findViewById(R.id.home_work_fragment_capacity_tv);
//            home_work_fragment_capacity_line = itemView.findViewById(R.id.home_work_fragment_capacity_line);
//            home_work_fragment_capacity_group = itemView.findViewById(R.id.home_work_fragment_capacity_group);
//            home_work_fragment_listen_tv = itemView.findViewById(R.id.home_work_fragment_listen_tv);
//            home_work_fragment_listen_line = itemView.findViewById(R.id.home_work_fragment_listen_line);
//            home_work_fragment_listen_group = itemView.findViewById(R.id.home_work_fragment_listen_group);
//            home_work_fragment_comment_tv = itemView.findViewById(R.id.home_work_fragment_comment_tv);
//            home_work_fragment_comment_line = itemView.findViewById(R.id.home_work_fragment_comment_line);
//            home_work_fragment_comment_group = itemView.findViewById(R.id.home_work_fragment_comment_group);
//        }
//    }
//
//    public void addHeaderView(View headerView) {
//        if (haveHeaderView()) {
//            throw new IllegalStateException("hearview has already exists!");
//        } else {
//            //避免出现宽度自适应
//            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            headerView.setLayoutParams(params);
//            VIEW_HEADER = headerView;
//            ifGridLayoutManager();
//            notifyItemInserted(0);
//        }
//
//    }
//    private boolean haveHeaderView() {
//        return VIEW_HEADER != null;
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.isEmpty() ? 0 : list.size();
//    }
}
