package com.sunny.univstar.view.teachertype.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunny.univstar.R;

import java.util.List;

/**
 * Created by DELL on 2018/5/7.
 */

public class MyTeacherDetailsAdapter extends RecyclerView.Adapter<MyTeacherDetailsAdapter.Holder>{
    private List<String> mList;

    public MyTeacherDetailsAdapter(List<String> mList) {
        this.mList = mList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_details_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.textView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView textView;
        public Holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.teacher_details_text);
        }
    }
}
