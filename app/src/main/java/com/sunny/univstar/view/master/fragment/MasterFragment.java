package com.sunny.univstar.view.master.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.recker.flybanner.FlyBanner;
import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;
import com.sunny.univstar.contract.HomeMasterContract;
import com.sunny.univstar.presenter.HomeMasterPresenter;
import com.sunny.univstar.view.master.fragment.adapter.HomewoksAdapter;
import com.sunny.univstar.view.master.fragment.adapter.LiveCoursesAdapter;
import com.sunny.univstar.view.master.fragment.adapter.UserBeanAdapter;
import com.sunny.univstar.view.master.fragment.autoui.MyScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sunny.univstar.R.id.home_master_work_listView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends BaseFragment implements HomeMasterContract.homeMasterInView {

    public HomeMasterContract.homeMasterInPresenter homeMasterInPresenter;
    //    FlyBanner masterFlyBanner;
//    @Bind(R.id.home_master_find_group)
//    LinearLayout homeMasterFindGroup;
//    @Bind(R.id.home_master_look_group)
//    LinearLayout homeMasterLookGroup;
//    @Bind(R.id.home_master_work_group)
//    LinearLayout homeMasterWorkGroup;
//    @Bind(R.id.home_master_chat_group)
//    LinearLayout homeMasterChatGroup;
//    @Bind(R.id.home_master_learn_group)
//    LinearLayout homeMasterLearnGroup;
//    @Bind(R.id.home_master_recommend_more)
//    TextView homeMasterRecommendMore;
//    @Bind(R.id.home_master_recommend_recyclerView)
//    RecyclerView homeMasterRecommendRecyclerView;
//    @Bind(R.id.home_master_live_more)
//    TextView homeMasterLiveMore;
//    @Bind(R.id.home_master_live_gridView)
//    RecyclerView homeMasterLiveGridView;
//    @Bind(R.id.home_master_fragment_workMore)
//    TextView homeMasterFragmentWorkMore;
//    @Bind(home_master_work_listView)
//    RecyclerView homeMasterWorkListView;
//    @Bind(R.id.home_master_fragment_chatValuable)
//    RelativeLayout homeMasterFragmentChatValuable;
    public List<String> flayBanner_list = new ArrayList<>();

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
    RecyclerView homeMasterLiveGridView;
    @Bind(R.id.home_master_fragment_workMore)
    TextView homeMasterFragmentWorkMore;
    @Bind(R.id.home_master_work_listView)
    RecyclerView homeMasterWorkListView;
    @Bind(R.id.home_master_fragment_chatValuable)
    RelativeLayout homeMasterFragmentChatValuable;
    @Bind(R.id.home_master_fragment_scrollview)
    MyScrollView homeMasterFragmentScrollview;
    //    @Bind(R.id.home_master_fragment_swipe)
    PullRefreshLayout homeMasterFragmentSwipe;
    private List<HomeMasterBean.DataBean.SystemAdsBean> systemAdsBeanList;
    private List<HomeMasterBean.DataBean.UsersBean> usersBeanList;
    private List<HomeMasterBean.DataBean.HomewoksBean> homewoksBeanList;
    private List<HomeMasterBean.DataBean.LiveCoursesBean> liveCoursesBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_master;
    }

    @Override
    protected void init() {
        homeMasterFragmentSwipe = getView().findViewById(R.id.home_master_fragment_swipe);
//        实例化P 层
        homeMasterInPresenter = new HomeMasterPresenter(this);
        homeMasterInPresenter.sendHomeMaster(getActivity());

    }

    @Override
    protected void initData() {
        homeMasterFragmentSwipe.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homeMasterFragmentSwipe.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        homeMasterFragmentSwipe.setRefreshing(false);
                    }
                }, 2000);
            }
        });
//        homeMasterFragmentSwipe.setRefreshing(false);
        homeMasterFragmentSwipe.setRefreshStyle(PullRefreshLayout.STYLE_CIRCLES);
    }


    @OnClick({R.id.masterFlyBanner, R.id.home_master_find_group, R.id.home_master_look_group, R.id.home_master_work_group, R.id.home_master_chat_group, R.id.home_master_learn_group, R.id.home_master_recommend_more, R.id.home_master_recommend_recyclerView, R.id.home_master_live_more, R.id.home_master_live_gridView, R.id.home_master_fragment_workMore, home_master_work_listView, R.id.home_master_fragment_chatValuable})
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
            case home_master_work_listView:
                break;
            case R.id.home_master_fragment_chatValuable:
                break;
        }
    }

    @Override
    public void showHomeMasterData(HomeMasterBean homeMasterBean) {
//        Log.d("MasterFragment", "homeMasterBean.getData().getHomewoks().size():" + homeMasterBean.getData().getHomewoks().size());
//        得到首页轮播图的集合
        systemAdsBeanList = homeMasterBean.getData().getSystemAds();
//        得到名师推荐集合
        usersBeanList = homeMasterBean.getData().getUsers();
//        得到推荐作业集合
        homewoksBeanList = homeMasterBean.getData().getHomewoks();
//        得到课程推荐集合
        liveCoursesBeanList = homeMasterBean.getData().getLiveCourses();
        //    制作轮播图UI界面
        flayBannerData();
//        制作名师推荐UI界面
        usersBeanData();
//        制作课程推荐UI界面
        liveCoursesData();
//        制作推荐作业UI界面
        homewoksData();
//        Log.d("MasterFragment", "systemAdsBeanList.size():" + systemAdsBeanList.size());
    }

    //    制作课程推荐UI界面
    private void liveCoursesData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        homeMasterLiveGridView.setLayoutManager(gridLayoutManager);
        LiveCoursesAdapter liveCoursesAdapter = new LiveCoursesAdapter(liveCoursesBeanList, getActivity());
        homeMasterLiveGridView.setAdapter(liveCoursesAdapter);
    }

    //        制作推荐作业UI界面
    private void homewoksData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        homeMasterWorkListView.setLayoutManager(linearLayoutManager);
        HomewoksAdapter homewoksAdapter = new HomewoksAdapter(homewoksBeanList, getActivity());
        homeMasterWorkListView.setAdapter(homewoksAdapter);
    }

    //        制作名师推荐UI界面
    private void usersBeanData() {
        //        设置名师推荐布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homeMasterRecommendRecyclerView.setLayoutManager(linearLayoutManager);
        UserBeanAdapter userBeanAdapter = new UserBeanAdapter(usersBeanList, getActivity());
        homeMasterRecommendRecyclerView.setAdapter(userBeanAdapter);

    }

    //    制作轮播图UI界面
    private void flayBannerData() {
        flayBanner_list = new ArrayList<>();
        for (int i = 0; i < systemAdsBeanList.size(); i++) {
            flayBanner_list.add(systemAdsBeanList.get(i).getMobileImgUrl());
        }
        masterFlyBanner.setImagesUrl(flayBanner_list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
