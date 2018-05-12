package com.sunny.univstar.view.master.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.recker.flybanner.FlyBanner;
import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;
import com.sunny.univstar.contract.FollowPraiseContract;
import com.sunny.univstar.contract.HomeMasterContract;
import com.sunny.univstar.model.utils.ShapeUtils;
import com.sunny.univstar.presenter.FollowPraiisePresenter;
import com.sunny.univstar.presenter.HomeMasterPresenter;
import com.sunny.univstar.view.MainActivity;
import com.sunny.univstar.view.livecourse.activity.LiveCourseActivity;
import com.sunny.univstar.view.livecourse.activity.LiveCourseDetailedActivity;
import com.sunny.univstar.view.master.fragment.adapter.HomewoksAdapter;
import com.sunny.univstar.view.master.fragment.adapter.LiveCoursesAdapter;
import com.sunny.univstar.view.master.fragment.adapter.UserBeanAdapter;
import com.sunny.univstar.view.master.fragment.autoui.MyScrollView;
import com.sunny.univstar.view.notice.activity.NoticeDetailedActivity;
import com.sunny.univstar.view.teachertype.activity.FindTeacherActivity;
import com.sunny.univstar.view.teachertype.activity.FindTeacherDetailsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.sunny.univstar.R.id.home_master_work_listView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends BaseFragment implements HomeMasterContract.homeMasterInView ,FollowPraiseContract.FollowPraiseView{

    public HomeMasterContract.homeMasterInPresenter homeMasterInPresenter;
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
     //@Bind(R.id.home_master_fragment_swipe)
    PullRefreshLayout homeMasterFragmentSwipe;
    private FollowPraiseContract.FollowPraisePresenter followPraisePresenter;
    private List<HomeMasterBean.DataBean.SystemAdsBean> systemAdsBeanList;
    private List<HomeMasterBean.DataBean.UsersBean> usersBeanList;
    private List<HomeMasterBean.DataBean.HomewoksBean> homewoksBeanList;
    private List<HomeMasterBean.DataBean.LiveCoursesBean> liveCoursesBeanList;
    private MainActivity activity;
    private int loginUserId;
    private List<Object> list;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_master;
    }

    @Override
    protected void init() {
        activity = (MainActivity) getActivity();
        homeMasterFragmentSwipe = getView().findViewById(R.id.home_master_fragment_swipe);
//        实例化P 层
        homeMasterInPresenter = new HomeMasterPresenter(this);
        followPraisePresenter = new FollowPraiisePresenter(this);
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
                startActivity(new Intent(getContext(), FindTeacherActivity.class));
                break;
            case R.id.home_master_look_group:
                startActivity(new Intent(getContext(), LiveCourseActivity.class));
                break;
            case R.id.home_master_work_group:
                activity.homeWorkBtn.setChecked(true);
                activity.homeWork();
                break;
            case R.id.home_master_chat_group:
                activity.homeValuableBtn.setChecked(true);
                activity.valuable();
                break;
            case R.id.home_master_learn_group:
                activity.homeNoticeBtn.setChecked(true);
                activity.notice();
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
        if (homeMasterBean.getCode() == 0) {
            if (homeMasterBean.getData().getSystemAds() != null && homeMasterBean.getData().getSystemAds().size() > 0)
                systemAdsBeanList = homeMasterBean.getData().getSystemAds();
            else
                return;
//        得到名师推荐集合
            if (homeMasterBean.getData().getUsers() != null && homeMasterBean.getData().getUsers().size() > 0)
                usersBeanList = homeMasterBean.getData().getUsers();
            else
                return;
//        得到推荐作业集合
            if (homeMasterBean.getData().getHomewoks() != null && homeMasterBean.getData().getHomewoks().size() > 0)
                homewoksBeanList = homeMasterBean.getData().getHomewoks();
            else
                return;
//        得到课程推荐集合
            if (homeMasterBean.getData().getLiveCourses() != null && homeMasterBean.getData().getLiveCourses().size() > 0)
                liveCoursesBeanList = homeMasterBean.getData().getLiveCourses();
            else
                return;
            //    制作轮播图UI界面
            flayBannerData();
//        制作名师推荐UI界面
            usersBeanData();
//        制作课程推荐UI界面
            liveCoursesData();
//        制作推荐作业UI界面
            homewoksData();
//        Log.d("MasterFragment", "systemAdsBeanList.size():" + systemAdsBeanList.size());
        }else {
            homeMasterInPresenter.sendHomeMaster(getActivity());
        }
    }

    //    制作课程推荐UI界面
    private void liveCoursesData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        homeMasterLiveGridView.setLayoutManager(gridLayoutManager);
        list = new ArrayList<>();
        for (HomeMasterBean.DataBean.LiveCoursesBean liveCoursesBean : liveCoursesBeanList) {
            list.add(liveCoursesBean);
        }
        LiveCoursesAdapter liveCoursesAdapter = new LiveCoursesAdapter(list, getActivity());
        liveCoursesAdapter.setOnClickItem(new LiveCoursesAdapter.OnClickItem() {
            @Override
            public void onClickItem(View view, int position) {
                Intent intent = new Intent(getContext(), LiveCourseDetailedActivity.class);
                intent.putExtra("id",liveCoursesBeanList.get(position).getId()+"");
                startActivity(intent);
            }
        });
        homeMasterLiveGridView.setAdapter(liveCoursesAdapter);
    }

    //        制作推荐作业UI界面
    private void homewoksData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        homeMasterWorkListView.setLayoutManager(linearLayoutManager);
        HomewoksAdapter homewoksAdapter = new HomewoksAdapter(homewoksBeanList, getActivity());
        SharedPreferences userState = getActivity().getSharedPreferences("userState", 0);
        loginUserId = userState.getInt("loginUserId", 0);
        homewoksAdapter.setOnClickItem(new HomewoksAdapter.OnClickItem() {
            @Override
            public void onClickItem(View view, int position) {
                switch (view.getId()){
                    case R.id.master_user_praise:
                        CheckBox checkBox = view.findViewById(R.id.master_user_praise);
                        if (checkBox != null) {
                            if (checkBox.isChecked()) {
                                checkBox.setTextColor(Color.parseColor("#FFB300"));

                                prasre("https://www.univstar.com/v1/m/user/praise", homewoksBeanList.get(position).getStudentId(), homewoksBeanList.get(position).getId(), "作业评论");
                                homewoksBeanList.get(position).setPraiseNum(homewoksBeanList.get(position).getPraiseNum() + 1);
                                checkBox.setText(homewoksBeanList.get(position).getPraiseNum() + "");
                            } else {
                                checkBox.setTextColor(Color.parseColor("#9E9E9E"));
                                prasre("https://www.univstar.com/v1/m/user/praise/cancel", homewoksBeanList.get(position).getStudentId(), homewoksBeanList.get(position).getId(), "作业评论");
                                if (homewoksBeanList.get(position).getPraiseNum() == 0) {
                                    checkBox.setText(homewoksBeanList.get(position).getPraiseNum() + "");
                                } else {
                                    homewoksBeanList.get(position).setPraiseNum(homewoksBeanList.get(position).getPraiseNum() - 1);
                                    checkBox.setText(homewoksBeanList.get(position).getPraiseNum() + "");
                                }

                            }
                        }
                        break;
                    case R.id.master_user_share_linear:
                        ShapeUtils shapeUtils = new ShapeUtils(getContext());
                        shapeUtils.setWeb("http://share.univstar.com/share/work-detail.html?homewokId="+homewoksBeanList.get(position).getId(),
                                homewoksBeanList.get(position).getContent(),"风里雨里,心愿艺考等你",R.mipmap.ic_launcher);
                        break;
                }

            }
        });
        homeMasterWorkListView.setAdapter(homewoksAdapter);
    }
    //        制作名师推荐UI界面
    private void usersBeanData() {
        //        设置名师推荐布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homeMasterRecommendRecyclerView.setLayoutManager(linearLayoutManager);
        UserBeanAdapter userBeanAdapter = new UserBeanAdapter(usersBeanList, getActivity());
        userBeanAdapter.setOnClickItem(new UserBeanAdapter.OnClickItem() {
            @Override
            public void onClickItem(View view, int position) {

                HomeMasterBean.DataBean.UsersBean usersBean = usersBeanList.get(position);
                Intent intent = new Intent(getContext(), FindTeacherDetailsActivity.class);
                intent.putExtra("teacherId",usersBean.getId()+"");
                startActivity(intent);
            }
        });
        homeMasterRecommendRecyclerView.setAdapter(userBeanAdapter);

    }

    //    制作轮播图UI界面
    private void flayBannerData() {
        flayBanner_list = new ArrayList<>();
        for (int i = 0; i < systemAdsBeanList.size(); i++) {
            flayBanner_list.add(systemAdsBeanList.get(i).getMobileImgUrl());
        }
        masterFlyBanner.setImagesUrl(flayBanner_list);
        masterFlyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if ("3".equals(systemAdsBeanList.get(position).getUrlType())){
                    Intent intent = new Intent(getContext(), NoticeDetailedActivity.class);
                    intent.putExtra("id",systemAdsBeanList.get(position).getMobileUrl()+"");
                    startActivity(intent);
                }else if ("4".equals(systemAdsBeanList.get(position).getUrlType())){
                    Intent intent = new Intent(getContext(), LiveCourseDetailedActivity.class);
                    intent.putExtra("id",systemAdsBeanList.get(position).getMobileUrl()+"");
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void getFollowPraise(String msg) {

    }
    private void prasre(String url,int studentId,int attentionId,String type){
        SharedPreferences userState = getActivity().getSharedPreferences("userState", 0);
        int userId = userState.getInt("loginUserId", 0);
        Log.e("userId",userId+"");
        Log.e("studentId",studentId+"");
        Log.e("attentionId",attentionId+"");
        Map<String,String> map = new HashMap<>();
        map.put("id",attentionId+"");
        map.put("loginUserId",userId+"");
        map.put("userId",studentId+"");
        map.put("type",type);
        followPraisePresenter.sendFollowPraise(url,map);
    }
}
