package com.sunny.univstar.view.personal.activity.login.bean.successview.hobby;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.sunny.univstar.R;
import com.sunny.univstar.view.preference.PreferenceAboveBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张玗 on 2018/5/9.
 */

public class CollegeAdapter extends RecyclerView.Adapter<CollegeAdapter.Holder> {
    List<PreferenceAboveBean.DataBean.CollegesBean> colleges;
    private List<String> selectList;

    public interface ItemSelectCallBack {
        void onItemSelect(List<String> selectList);
    }

    private ItemSelectCallBack selectCallBack;
    /**
     * 默认上限为3
     */
    private int maxSelect = 3;

    public CollegeAdapter(List<PreferenceAboveBean.DataBean.CollegesBean> colleges, ItemSelectCallBack selectCallBack) {
        this.colleges = colleges;
        this.selectCallBack = selectCallBack;
        selectList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.hobby_college_item, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.college_multiChoice_item_tv.setText(colleges.get(position).getName());

        holder.college_multiChoice_item_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.college_multiChoice_item_tv.isChecked()) {

                    selectList.remove(colleges.get(position).getId() + "");
                    holder.college_multiChoice_item_tv.setChecked(false);
                    if (selectCallBack != null) {
                        selectCallBack.onItemSelect(selectList);
                    }

                } else {
                    holder.college_multiChoice_item_tv.setChecked(true);
                    selectList.add(colleges.get(position).getId() + "");
                    if (selectCallBack != null) {
                        selectCallBack.onItemSelect(selectList);
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return colleges.isEmpty() ? 0 : colleges.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public CheckedTextView college_multiChoice_item_tv;

        public Holder(View itemView) {
            super(itemView);
            this.college_multiChoice_item_tv = (CheckedTextView) itemView.findViewById(R.id.college_multiChoice_item_tv);

        }
    }

    public void setSelectCallBack(ItemSelectCallBack selectCallBack) {
        this.selectCallBack = selectCallBack;
    }

    public void setMaxSelect(int maxSelect) {
        this.maxSelect = maxSelect;
    }
}
