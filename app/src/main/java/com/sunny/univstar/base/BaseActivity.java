package com.sunny.univstar.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.sunny.univstar.app.App;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.ButterKnife;
import qiu.niorgai.StatusBarCompat;

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
    protected BaseFragment fragmentRepeat(int contaired, Class<? extends BaseFragment> baseFragment,Bundle bundle) {
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
            if (bundle!=null){
                fragmentByTag.setArguments(bundle);
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

    protected void setTitleTheme(Activity activity,boolean darmode){
        setStatusBarCompat(activity);

        setMiuiStatusBarDarkMode(activity, darmode);

        setMeizuStatusBarDarkIcon(activity, darmode);

        setMStatusBarDarkIcon(activity);
    }
    /**
     *
     * 设置状态栏透明
     * @param activity
     */
    protected  void setStatusBarCompat(Activity activity){
        StatusBarCompat.translucentStatusBar(activity,true);
    }

    /**
     * 设置小米状态栏字体图标颜色
     * @param activity
     * @param darkmode
     * @return
     */
    protected  boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 设置魅族手机状态栏字体图标颜色
     * @param activity
     * @param dark
     * @return
     */
    protected boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }

    /**
     * 6.0以上设置状态栏字体图标颜色
     * @param activity
     */
    protected void setMStatusBarDarkIcon(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }
}
