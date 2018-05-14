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


public class FansActivity extends BaseActivity implements View.OnClickListener, IFansContract.IFansView {


    @Bind(R.id.fans_return)
    ImageView fansReturn;
    @Bind(R.id.fans_list)
    ListView fansList;
    @Bind(R.id.fans_nothing)
    LinearLayout fansNothing;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fans;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {
        SharedPreferences user = getSharedPreferences("userState", MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
        FansPresenterImp fansPresenterImp = new FansPresenterImp(this);
        fansPresenterImp.loadFansBean(user.getInt("loginUserId", 0));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fans_return:
                finish();
                break;
        }
    }

    @Override
    public void showFansBean(List<FansBean.DataBean.ListBean> listBeans) {
        if (listBeans.size() == 0) {
            fansNothing.setVisibility(View.VISIBLE);
            fansList.setVisibility(View.GONE);
        } else {
            fansNothing.setVisibility(View.GONE);
            fansList.setVisibility(View.VISIBLE);
            FansListAdapter fansListAdapter = new FansListAdapter(listBeans);
            fansList.setAdapter(fansListAdapter);
        }
    }


    @OnClick(R.id.fans_return)
    public void onViewClicked() {
        finish();
    }


}
