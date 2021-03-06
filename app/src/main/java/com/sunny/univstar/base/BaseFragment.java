package com.sunny.univstar.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by 张玗 on 2018/5/3.
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    /*public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(inflate);
        return inflate;
    }*/
//    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View inflate = inflater.inflate(getLayoutId(), container, false);
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initData();
    }


    //    统一加载布局
    protected abstract int getLayoutId();

    //    统一初始化
    protected abstract void init();

    //    统一加载数据
    protected abstract void initData();
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
