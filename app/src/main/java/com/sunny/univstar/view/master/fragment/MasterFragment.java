package com.sunny.univstar.view.master.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.recker.flybanner.FlyBanner;
import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends BaseFragment {


    @Bind(R.id.masterFlyBanner)
    FlyBanner masterFlyBanner;
    @Bind(R.id.home_master_find_group)
    LinearLayout homeMasterFindGroup;
    @Bind(R.id.home_master_look_group)
    LinearLayout homeMasterLookGroup;
    @Bind(R.id.home_master_work_group)
    LinearLayout homeMasterWorkGroup;
    @Bind(R.id.home_master_chat_group)
    LinearLayout homeMasterChatGroup;
    @Bind(R.id.home_master_learn_group)
    LinearLayout homeMasterLearnGroup;
    @Bind(R.id.home_master_recommend_more)
    TextView homeMasterRecommendMore;
    @Bind(R.id.home_master_recommend_recyclerView)
    RecyclerView homeMasterRecommendRecyclerView;
    @Bind(R.id.home_master_live_more)
    TextView homeMasterLiveMore;
    @Bind(R.id.home_master_live_gridView)
    GridView homeMasterLiveGridView;
    @Bind(R.id.home_master_fragment_workMore)
    TextView homeMasterFragmentWorkMore;
    @Bind(R.id.home_master_work_listView)
    ListView homeMasterWorkListView;
    @Bind(R.id.home_master_fragment_chatValuable)
    RelativeLayout homeMasterFragmentChatValuable;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_master;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.masterFlyBanner, R.id.home_master_find_group, R.id.home_master_look_group, R.id.home_master_work_group, R.id.home_master_chat_group, R.id.home_master_learn_group, R.id.home_master_recommend_more, R.id.home_master_recommend_recyclerView, R.id.home_master_live_more, R.id.home_master_live_gridView, R.id.home_master_fragment_workMore, R.id.home_master_work_listView, R.id.home_master_fragment_chatValuable})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.masterFlyBanner:
                break;
            case R.id.home_master_find_group:
                break;
            case R.id.home_master_look_group:
                break;
            case R.id.home_master_work_group:
                break;
            case R.id.home_master_chat_group:
                break;
            case R.id.home_master_learn_group:
                break;
            case R.id.home_master_recommend_more:
                break;
            case R.id.home_master_recommend_recyclerView:
                break;
            case R.id.home_master_live_more:
                break;
            case R.id.home_master_live_gridView:
                break;
            case R.id.home_master_fragment_workMore:
                break;
            case R.id.home_master_work_listView:
                break;
            case R.id.home_master_fragment_chatValuable:
                break;
        }
    }
}
