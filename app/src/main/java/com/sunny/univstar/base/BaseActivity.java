package com.sunny.univstar.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sunny.univstar.app.App;

import butterknife.ButterKnife;

/**
 * Created by 张玗 on 2018/5/3.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public BaseFragment lastFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init();
        initData();
        App.context = this;

    }


    //    统一加载布局
    protected abstract int getLayoutId();

    //    统一初始化
    protected abstract void init();

    //    统一加载数据
    protected abstract void initData();


    //    fragment复用
    protected BaseFragment fragmentRepeat(int contaired, Class<? extends BaseFragment> baseFragment) {
//        得到一个Fragment管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
//        得到转换器
        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        得到标记名字
        String simpleName = baseFragment.getSimpleName();

        BaseFragment fragmentByTag = (BaseFragment) fragmentManager.findFragmentByTag(simpleName);

        try {
            if (fragmentByTag == null) {
                fragmentByTag = baseFragment.newInstance();
                transaction.add(contaired, fragmentByTag);
            }
            if (lastFragment != null) {
                transaction.hide(lastFragment);
            }
            transaction.show(fragmentByTag);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        lastFragment = fragmentByTag;
        transaction.commit();
        return fragmentByTag;


    }

    @Override
    protected void onResume() {
        super.onResume();
        App.context = this;
    }

    @Override
    protected void onStop() {
        super.onStop();
//        App.context = null;
    }
}
