package com.sunny.univstar.view.personal.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.personal.activity.login.bean.set.AboutUnivStarActivity;
import com.sunny.univstar.view.personal.activity.login.bean.set.AlertPasswordActivity;
import com.sunny.univstar.view.personal.activity.login.bean.set.SocialNumBindActivity;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginSetActivity extends BaseActivity {


    public static SharedPreferences sharedPreferences;
    @Bind(R.id.setting_aty_cancle)
    TextView settingAtyCancle;
    @Bind(R.id.setting_aty_phone_tv)
    TextView settingAtyPhoneTv;
    @Bind(R.id.setting_aty_changephone_group)
    RelativeLayout settingAtyChangephoneGroup;
    @Bind(R.id.setting_aty_bind_group)
    RelativeLayout settingAtyBindGroup;
    @Bind(R.id.setting_aty_pass_group)
    RelativeLayout settingAtyPassGroup;
    @Bind(R.id.setting_glide_cahce_tv)
    TextView settingGlideCahceTv;
    @Bind(R.id.setting_aty_about_group)
    RelativeLayout settingAtyAboutGroup;
    @Bind(R.id.setting_aty_tuichu_group)
    RelativeLayout settingAtyTuichuGroup;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_set;
    }

    @Override
    protected void init() {

        try {
            long cache = RetrofitUtils.getFolderSize(new File(Environment.getExternalStorageDirectory().getPath() + "/cache"));
            settingGlideCahceTv.setText(RetrofitUtils.getFormatSize(cache));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.setting_aty_cancle, R.id.setting_aty_changephone_group, R.id.setting_aty_bind_group, R.id.setting_aty_pass_group, R.id.setting_glide_cahce_tv, R.id.setting_aty_about_group, R.id.setting_aty_tuichu_group})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            返回键
            case R.id.setting_aty_cancle:
                finish();
                break;
//            换绑手机
            case R.id.setting_aty_changephone_group:
                startActivity(new Intent(this, AlertPasswordActivity.class));

                break;
//            社交账号绑定
            case R.id.setting_aty_bind_group:
                startActivity(new Intent(this, SocialNumBindActivity.class));
                break;
//            修改登录密码
            case R.id.setting_aty_pass_group:
                startActivity(new Intent(this, AlertPasswordActivity.class));
                break;
//            清除缓存
            case R.id.setting_glide_cahce_tv:
                RetrofitUtils.deleteFile(Environment.getExternalStorageDirectory().getPath() + "/cache");
                init();
                break;
//            关于UnivStar
            case R.id.setting_aty_about_group:
                startActivity(new Intent(LoginSetActivity.this, AboutUnivStarActivity.class));
                break;
//            退出登录
            case R.id.setting_aty_tuichu_group:
                sharedPreferences = getSharedPreferences("userState", 0);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("isLogin", false);
                edit.commit();
                finish();
                break;
        }
    }


}
