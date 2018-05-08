package com.sunny.univstar.view.valuable.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.recker.flybanner.FlyBanner;
import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;
import com.sunny.univstar.contract.HomeValuableContract;
import com.sunny.univstar.presenter.HomeValuablePresenter;
import com.sunny.univstar.view.valuable.fragment.adapter.ValuableAdapter;

import java.lang.reflect.Field;
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
        reflex(valuableTabLayout);

        Log.d("ValuableFragment", "title_list.size():" + title_list.size());
    }

    @Override
    public void showValueFlyBannerData(ValueFlaybannerBean valueFlaybannerBean) {
//        Log.d("ValuableFragment", "valueFlaybannerBean.getData().getList().size():" + valueFlaybannerBean.getData().getList().size());
        List<ValueFlaybannerBean.DataBean.ListBean> list = valueFlaybannerBean.getData().getList();
        for (int i = 0; i < list.size(); i++) {
            banner_list.add(list.get(i).getMobileImgUrl());

        }
        valuableBanner.setImagesUrl(banner_list);


    }

    public void reflex(final TabLayout tabLayout){
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = dip2px(tabLayout.getContext(), 10);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        tabView.measure(0,0);
                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        mTextView.setTextColor(Color.parseColor("#9e9e9e"));
                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
