package com.sunny.univstar.view.work.fragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;
import com.sunny.univstar.contract.HomeWorkContract;
import com.sunny.univstar.model.entity.HomeWokListModel;
import com.sunny.univstar.presenter.HomeWorkPresenter;
import com.sunny.univstar.view.work.adapter.HomeWorkAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkFragment extends BaseFragment implements HomeWorkContract.HomeWorkView, View.OnClickListener {
    private HomeWorkContract.HomeWorkPreference preferencel;
    private HomeWorkAdapter mAdapter;
    private List<Object> mList;
    private RecyclerView work_list;
    private View inflate;
    private View inflate2;
    private LinearLayout listHeader;
    private View header_view;
//    头部选择蓝
    private TextView home_work_fragment_capacity_tv;
    private TextView home_work_fragment_capacity_line;
    private RelativeLayout home_work_fragment_capacity_group;
    private TextView home_work_fragment_listen_tv;
    private TextView home_work_fragment_listen_line;
    private RelativeLayout home_work_fragment_listen_group;
    private TextView home_work_fragment_comment_tv;
    private TextView home_work_fragment_comment_line;
    private RelativeLayout home_work_fragment_comment_group;
    private SweetAlertDialog pDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_work;
    }

    @Override
    protected void init() {
//        初始化导航栏
//        home_work_fragment_capacity_tv = getView().findViewById(R.id.home_work_fragment_capacity_tv);
//            home_work_fragment_capacity_line = getView().findViewById(R.id.home_work_fragment_capacity_line);
//            home_work_fragment_capacity_group = getView().findViewById(R.id.home_work_fragment_capacity_group);
//        home_work_fragment_capacity_group.setOnClickListener(this);
//            home_work_fragment_listen_tv = getView().findViewById(R.id.home_work_fragment_listen_tv);
//            home_work_fragment_listen_line = getView().findViewById(R.id.home_work_fragment_listen_line);
//            home_work_fragment_listen_group = getView().findViewById(R.id.home_work_fragment_listen_group);
//        home_work_fragment_listen_group.setOnClickListener(this);
//            home_work_fragment_comment_tv = getView().findViewById(R.id.home_work_fragment_comment_tv);
//            home_work_fragment_comment_line = getView().findViewById(R.id.home_work_fragment_comment_line);
//            home_work_fragment_comment_group = getView().findViewById(R.id.home_work_fragment_comment_group);
//        home_work_fragment_comment_group.setOnClickListener(this);
//        加载时的弹出DiaLog
        pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);


//        初始化数据
        mList = new ArrayList();
        mAdapter = new HomeWorkAdapter(mList, getContext());
        work_list = getView().findViewById(R.id.work_list);
        inflate = LayoutInflater.from(getContext()).inflate(R.layout.work_list_head_item, null);
        inflate2 = LayoutInflater.from(getContext()).inflate(R.layout.work_list_content_item, null);
//        header_view = LayoutInflater.from(getContext()).inflate(R.layout.work_list_head_item,null);
        mAdapter.addHeaderView(inflate);
//        listHeader = getView().findViewById(R.layout.work_list_head_item);


//        设置RecyclerView样式
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        work_list.setLayoutManager(linearLayoutManager);
        work_list.setAdapter(mAdapter);

        preferencel = new HomeWorkPresenter(this);
        preferencel.sendWorkData(getContext(), 1,2, 20);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void getWorkData(HomeWokListModel homeWokListModel) {
        if (homeWokListModel.getCode() == 0) {
            List<HomeWokListModel.DataBean.ListBean> list = homeWokListModel.getData().getList();
//        清空上一次请求的数据-
            mList.clear();
            for (HomeWokListModel.DataBean.ListBean listBean : list) {
                mList.add(listBean);
            }
//        关闭DiaLog
            pDialog.dismiss();
            mAdapter.notifyDataSetChanged();
            for (HomeWokListModel.DataBean.ListBean listBean : list) {
                Log.e("listBean--List", listBean.getContent());
            }
        }else {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            智能筛选
            case R.id.home_work_fragment_capacity_group:
//                点击智能筛选弹出DiaLog并且下次不可点击以及重新加载
                pDialog.show();
//                请求数据
                preferencel = new HomeWorkPresenter(this);
                preferencel.sendWorkData(getContext(), 1, 0, 20);
                home_work_fragment_capacity_line.setVisibility(View.VISIBLE);
                    home_work_fragment_comment_line.setVisibility(View.INVISIBLE);
                   home_work_fragment_listen_line.setVisibility(View.INVISIBLE);
//                    ((MessageHolder) holder).home_work_fragment_capacity_tv.setTextSize(20);
//                    ((MessageHolder) holder).home_work_fragment_capacity_tv.setTextColor(Color.parseColor("#333333"));
                    home_work_fragment_capacity_group.setEnabled(false);
                    home_work_fragment_comment_group.setEnabled(true);
                    home_work_fragment_listen_group.setEnabled(true);
                break;
//            最新点评

            case R.id.home_work_fragment_comment_group:
//                点击最新点评弹出DiaLog并且下次不可点击以及重新加载
                pDialog.show();
//                请求数据
                preferencel = new HomeWorkPresenter(this);
                preferencel.sendWorkData(getContext(), 1, 2, 20);
                home_work_fragment_capacity_line.setVisibility(View.INVISIBLE);
                home_work_fragment_comment_line.setVisibility(View.VISIBLE);
                home_work_fragment_listen_line.setVisibility(View.INVISIBLE);
//                    ((MessageHolder) holder).home_work_fragment_capacity_tv.setTextSize(20);
//                    ((MessageHolder) holder).home_work_fragment_capacity_tv.setTextColor(Color.parseColor("#333333"));
                home_work_fragment_capacity_group.setEnabled(true);
                home_work_fragment_comment_group.setEnabled(false);
                home_work_fragment_listen_group.setEnabled(true);
                break;
//            偷听最多
            case R.id.home_work_fragment_listen_group:
//                点击偷听最多弹出DiaLog并且下次不可点击以及重新加载
                pDialog.show();
//                请求数据
                preferencel = new HomeWorkPresenter(this);
                preferencel.sendWorkData(getContext(), 1, 1, 20);
                home_work_fragment_capacity_line.setVisibility(View.INVISIBLE);
                home_work_fragment_comment_line.setVisibility(View.INVISIBLE);
                home_work_fragment_listen_line.setVisibility(View.VISIBLE);
//                    ((MessageHolder) holder).home_work_fragment_capacity_tv.setTextSize(20);
//                    ((MessageHolder) holder).home_work_fragment_capacity_tv.setTextColor(Color.parseColor("#333333"));
                home_work_fragment_capacity_group.setEnabled(true);
                home_work_fragment_comment_group.setEnabled(true);
                home_work_fragment_listen_group.setEnabled(false);
                break;
        }
    }
}
