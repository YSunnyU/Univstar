package com.sunny.univstar.view.valuable.fragment;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;

import com.recker.flybanner.FlyBanner;
import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;
import com.sunny.univstar.contract.HomeValuableContract;
import com.sunny.univstar.presenter.HomeValuablePresenter;
import com.sunny.univstar.view.valuable.fragment.adapter.ValuableAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class ValuableFragment extends BaseFragment implements HomeValuableContract.HomeValuableInView {

    public HomeValuableContract.HomeValuableInPresenter homeValuableInPresenter;
    @Bind(R.id.valuable_banner)
    FlyBanner valuableBanner;
    @Bind(R.id.valuable_tabLayout)
    TabLayout valuableTabLayout;

    @Bind(R.id.myMainScrollView)
    NestedScrollView myMainScrollView;
    @Bind(R.id.scrollview)
    CoordinatorLayout scrollview;
    @Bind(R.id.valuable_viewPager)
    ViewPager valuableViewPager;
    private List<String> title_list;
    private List<HomeValuableBean.DataBean.ArtcircleCategoriesBean> dataArtcircleCategories;
    private List<String> banner_list = new ArrayList<>();
    private List<ValuableSonFragment> son_frag = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_valuable;
    }

    @Override
    protected void init() {
        homeValuableInPresenter = new HomeValuablePresenter(this);
        homeValuableInPresenter.sendFlyBannerData();
        homeValuableInPresenter.sendHomeValuableData("0", "20", "0", "0");
        title_list = new ArrayList<>();
        title_list.add("智能筛选");
        title_list.add("赞数最多");
        title_list.add("最新评论");

    }

    @Override
    protected void initData() {

    }


    @Override
    public void showHomeValuableData(HomeValuableBean homeValuableBean) {
        HomeValuableBean.DataBean data = homeValuableBean.getData();
        dataArtcircleCategories = data.getArtcircleCategories();
        ValuableSonFragment valuableSonFragment = null;
        for (int i = 0; i < dataArtcircleCategories.size(); i++) {
            title_list.add(dataArtcircleCategories.get(i).getName());

        }
        for (int i = 0; i < title_list.size(); i++) {
            valuableSonFragment = new ValuableSonFragment();
            Bundle bundle = new Bundle();
            bundle.putString("page", i + "");
            bundle.putString("rows", 20 + "");
            bundle.putString("sortord", i + "");
            bundle.putString("loginUserId", "0");
            bundle.putInt("size", i);
            valuableSonFragment.setArguments(bundle);
            son_frag.add(valuableSonFragment);
        }

        ValuableAdapter vva;
        vva = new ValuableAdapter(getChildFragmentManager(), title_list, son_frag);
        valuableViewPager.setAdapter(vva);
        valuableTabLayout.setupWithViewPager(valuableViewPager);
        valuableTabLayout.setTabMode(View.HAPTIC_FEEDBACK_ENABLED);
        Log.d("ValuableFragment", "title_list.size():" + title_list.size());
    }

    @Override
    public void showValueFlyBannerData(ValueFlaybannerBean valueFlaybannerBean) {
        Log.d("ValuableFragment", "valueFlaybannerBean.getData().getList().size():" + valueFlaybannerBean.getData().getList().size());
        List<ValueFlaybannerBean.DataBean.ListBean> list = valueFlaybannerBean.getData().getList();
        for (int i = 0; i < list.size(); i++) {
            banner_list.add(list.get(i).getMobileImgUrl());

        }
        valuableBanner.setImagesUrl(banner_list);


    }


}
