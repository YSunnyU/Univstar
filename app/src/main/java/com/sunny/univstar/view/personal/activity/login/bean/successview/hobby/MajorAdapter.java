package com.sunny.univstar.view.personal.activity.login.bean.successview.hobby;

import android.content.Context;
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

public class MajorAdapter extends RecyclerView.Adapter<MajorAdapter.Holder> {

    public interface ItemSelectCallBack {
        void onItemSelect(List<String> selectList);
    }

    private ItemSelectCallBack selectCallBack;


    List<PreferenceAboveBean.DataBean.MajorsBean> majors;
    private Context context;

    /**
     * 默认上限为3
     */
    private int maxSelect = 3;

    private LayoutInflater layoutInflater;
    private List<String> selectList;

    public MajorAdapter(ItemSelectCallBack selectCallBack, List<PreferenceAboveBean.DataBean.MajorsBean> majors, Context context) {
        this.selectCallBack = selectCallBack;
        this.majors = majors;
        this.context = context;

        initSelectList();

        layoutInflater = LayoutInflater.from(context);
    }

    private void initSelectList() {

        selectList = new ArrayList<>();

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.hobby_major_item, parent, false);
        Holder holder = new Holder(inflate);

        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.major_multiChoice_item_tv.setText(majors.get(position).getName());
        holder.major_multiChoice_item_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.major_multiChoice_item_tv.isChecked()) {

                    selectList.remove(majors.get(position).getId() + "");
                    holder.major_multiChoice_item_tv.setChecked(false);
                    if (selectCallBack != null) {
                        selectCallBack.onItemSelect(selectList);
                    }

                } else {
                    holder.major_multiChoice_item_tv.setChecked(true);
                    selectList.add(majors.get(position).getId() + "");
                    if (selectCallBack != null) {
                        selectCallBack.onItemSelect(selectList);
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return majors.isEmpty() ? 0 : majors.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public CheckedTextView major_multiChoice_item_tv;

        public Holder(View itemView) {
            super(itemView);
            this.major_multiChoice_item_tv = (CheckedTextView) itemView.findViewById(R.id.major_multiChoice_item_tv);

        }
    }

    public void setSelectCallBack(ItemSelectCallBack selectCallBack) {
        this.selectCallBack = selectCallBack;
    }

    public void setMaxSelect(int maxSelect) {
        this.maxSelect = maxSelect;
    }


}
