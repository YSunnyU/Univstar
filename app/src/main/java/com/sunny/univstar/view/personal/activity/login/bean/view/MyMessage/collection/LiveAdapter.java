package com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 张玗 on 2018/5/11.
 */

public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.Holder>{
    public LiveAdapter(int one,int two,int three) {
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }
}
