package com.sunny.univstar.view.work.fragment;


import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;
import com.sunny.univstar.contract.HomeWorkContract;
import com.sunny.univstar.model.entity.HomeWokListModel;
import com.sunny.univstar.model.utils.ShapeUtils;
import com.sunny.univstar.presenter.HomeWorkPresenter;
import com.sunny.univstar.view.work.adapter.HomeWorkAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkFragment extends BaseFragment implements HomeWorkContract.HomeWorkView{
    @Bind(R.id.home_work_fragment_publishwok_group)
    LinearLayout homeWorkFragmentPublishwokGroup;
    @Bind(R.id.home_work_fragment_publishaskwok_group)
    LinearLayout homeWorkFragmentPublishaskwokGroup;
    @Bind(R.id.collapsing_tool_bar_test_ctl)
    CollapsingToolbarLayout collapsingToolBarTestCtl;
    @Bind(R.id.home_work_fragment_capacity_tv)
    TextView homeWorkFragmentCapacityTv;
    @Bind(R.id.home_work_fragment_capacity_line)
    TextView homeWorkFragmentCapacityLine;
    @Bind(R.id.home_work_fragment_capacity_group)
    RelativeLayout homeWorkFragmentCapacityGroup;
    @Bind(R.id.home_work_fragment_listen_tv)
    TextView homeWorkFragmentListenTv;
    @Bind(R.id.home_work_fragment_listen_line)
    TextView homeWorkFragmentListenLine;
    @Bind(R.id.home_work_fragment_listen_group)
    RelativeLayout homeWorkFragmentListenGroup;
    @Bind(R.id.home_work_fragment_comment_tv)
    TextView homeWorkFragmentCommentTv;
    @Bind(R.id.home_work_fragment_comment_line)
    TextView homeWorkFragmentCommentLine;
    @Bind(R.id.home_work_fragment_comment_group)
    RelativeLayout homeWorkFragmentCommentGroup;
    @Bind(R.id.id_appbarlayout)
    AppBarLayout idAppbarlayout;
    @Bind(R.id.work_list)
    RecyclerView workList;
    @Bind(R.id.myMainScrollView)
    NestedScrollView myMainScrollView;
    @Bind(R.id.scrollview)
    CoordinatorLayout scrollview;
    private HomeWorkContract.HomeWorkPreference preferencel;
    private HomeWorkAdapter mAdapter;
    private List<HomeWokListModel.DataBean.ListBean> mList;

    private SweetAlertDialog pDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_work;
    }

    @Override
    protected void init() {
//        加载时的弹出DiaLog
        preferencel = new HomeWorkPresenter(this);
        pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);


//        初始化数据
        mList = new ArrayList();
        mAdapter = new HomeWorkAdapter(mList, getContext());
        mAdapter.setOnClickItem(new HomeWorkAdapter.OnClickItem() {
            @Override
            public void onClickItem(View view, int position) {
                ShapeUtils shapeUtils = new ShapeUtils(getContext());
                shapeUtils.setWeb("http://share.univstar.com/share/work-detail.html?homewokId="+mList.get(position).getId(),
                        null,"风里雨里,心愿艺考等你",R.mipmap.ic_launcher);
            }
        });
//        设置RecyclerView样式
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        workList.setLayoutManager(linearLayoutManager);
        workList.setAdapter(mAdapter);

        preferencel = new HomeWorkPresenter(this);
        preferencel.sendWorkData(getContext(), 1, 1, 20);
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
        } else {

        }
    }

    @OnClick({R.id.home_work_fragment_capacity_group, R.id.home_work_fragment_listen_group, R.id.home_work_fragment_comment_group})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_work_fragment_capacity_group:
                pDialog.show();
//                请求数据
                homeWorkFragmentCapacityLine.setVisibility(View.VISIBLE);
                homeWorkFragmentCommentLine.setVisibility(View.GONE);
                homeWorkFragmentListenLine.setVisibility(View.GONE);
//                    ((MessageHolder) holder).home_work_fragment_capacity_tv.setTextSize(20);
//                    ((MessageHolder) holder).home_work_fragment_capacity_tv.setTextColor(Color.parseColor("#333333"));
//                homeWorkFragmentCapacityGroup.setEnabled(false);
//                homeWorkFragmentCommentGroup.setEnabled(true);
//                homeWorkFragmentListenGroup.setEnabled(true);
                preferencel.sendWorkData(getContext(), 1, 0, 20);
                break;
            case R.id.home_work_fragment_listen_group:
                pDialog.show();
//                请求数据
                homeWorkFragmentCapacityLine.setVisibility(View.GONE);
                homeWorkFragmentCommentLine.setVisibility(View.GONE);
                homeWorkFragmentListenLine.setVisibility(View.VISIBLE);
//                    ((MessageHolder) holder).home_work_fragment_capacity_tv.setTextSize(20);
//                    ((MessageHolder) holder).home_work_fragment_capacity_tv.setTextColor(Color.parseColor("#333333"));
//                homeWorkFragmentCapacityGroup.setEnabled(true);
//                homeWorkFragmentCommentGroup.setEnabled(false);
//                homeWorkFragmentListenGroup.setEnabled(true);
                preferencel.sendWorkData(getContext(), 1, 1, 20);

                break;
            case R.id.home_work_fragment_comment_group:
                //                点击偷听最多弹出DiaLog并且下次不可点击以及重新加载
                pDialog.show();
//                请求数据
                homeWorkFragmentCapacityLine.setVisibility(View.GONE);
                homeWorkFragmentCommentLine.setVisibility(View.VISIBLE);
                homeWorkFragmentListenLine.setVisibility(View.GONE);
//                    ((MessageHolder) holder).home_work_fragment_capacity_tv.setTextSize(20);
//                    ((MessageHolder) holder).home_work_fragment_capacity_tv.setTextColor(Color.parseColor("#333333"));
//                homeWorkFragmentCapacityGroup.setEnabled(true);
//                homeWorkFragmentCommentGroup.setEnabled(true);
//                homeWorkFragmentListenGroup.setEnabled(false);
                preferencel.sendWorkData(getContext(), 1, 2, 20);
                break;
        }
    }
}
