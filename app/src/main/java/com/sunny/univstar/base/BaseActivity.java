package com.sunny.univstar.base;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
        verifyStoragePermissions(this);
        ButterKnife.bind(this);
        App.context = this;
        init();
        initData();
    }


    //    统一加载布局
    protected abstract int getLayoutId();

    //    统一初始化
    protected abstract void init();

    //    统一加载数据
    protected abstract void initData();
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };


    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
    protected void onPause() {
        super.onPause();
        App.context = null;
    }

    protected void setTitleTheme(Activity activity, boolean darmode){
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
