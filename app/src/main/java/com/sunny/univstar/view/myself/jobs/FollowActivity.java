package com.sunny.univstar.view.myself.jobs;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


public class FollowActivity extends BaseActivity implements IFollowContract.IFollowView {


    @Bind(R.id.follow_return)
    ImageView followReturn;
    @Bind(R.id.follow_list)
    ListView followList;
    @Bind(R.id.follow_nothing)
    LinearLayout followNothing;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_follow;
    }

    @Override
    protected void init() {


    }

    @Override
    protected void initData() {
        SharedPreferences user = getSharedPreferences("userState", MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
        FollowPresenterImp followPresenterImp = new FollowPresenterImp(this);
        followPresenterImp.loadFollowBean(user.getInt("loginUserId", 0));
    }


    @Override
    public void showFollowBean(List<FollowBean.DataBean.ListBean> listBeans) {
        if (listBeans.size() == 0) {
            followNothing.setVisibility(View.VISIBLE);
            followList.setVisibility(View.GONE);
        } else {
            followNothing.setVisibility(View.GONE);
            followList.setVisibility(View.VISIBLE);
            FollowListAdapter followListAdapter = new FollowListAdapter(listBeans);
            followList.setAdapter(followListAdapter);
        }
    }


    @OnClick(R.id.follow_return)
    public void onViewClicked() {
        finish();
    }



}
