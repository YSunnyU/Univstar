package com.sunny.univstar.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 张玗 on 2018/5/3.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public BaseFragment lastFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayoutId());
    }

    //    统一加载布局
    protected abstract int getLayoutId();

    //    统一初始化
    protected abstract void init();

    //    统一加载数据
    protected abstract void initData();

    public BaseFragment fragmentRepeat(int container, Class<? extends BaseFragment> baseFragmemt) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        String simpleName = baseFragmemt.getSimpleName();
        BaseFragment fragmentByTag = (BaseFragment) supportFragmentManager.findFragmentByTag(simpleName);

        try {
            if (fragmentByTag == null) {
                fragmentByTag = baseFragmemt.newInstance();
                transaction.add(container, fragmentByTag, simpleName);
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
        lastFragment=fragmentByTag;
        transaction.commit();
        return fragmentByTag;
    }


}
