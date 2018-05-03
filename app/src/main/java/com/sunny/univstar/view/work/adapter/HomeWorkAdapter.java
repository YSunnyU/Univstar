package com.sunny.univstar.view.work.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.sunny.univstar.model.entity.HomeWokListModel;

import java.util.List;

/**
 * Created by DELL on 2018/5/3.
 */

public class HomeWorkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Object> list;

    public HomeWorkAdapter(List<Object> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        Object o = list.get(position);
        if (o instanceof HomeWokListModel.DataBean.ListBean){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
    private class HeadHolder extends RecyclerView.ViewHolder{

        public HeadHolder(View itemView) {
            super(itemView);
        }
    }
    private class MessageHolder extends RecyclerView.ViewHolder{

        public MessageHolder(View itemView) {
            super(itemView);
        }
    }
    @Override
    public int getItemCount() {
        return list.isEmpty()?0:list.size()+1;
    }
}
