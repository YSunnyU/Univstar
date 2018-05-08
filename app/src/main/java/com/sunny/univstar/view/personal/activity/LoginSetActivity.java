package com.sunny.univstar.view.personal.activity;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginSetActivity extends BaseActivity {


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
    public static SharedPreferences sharedPreferences;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_set;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.setting_aty_cancle, R.id.setting_aty_changephone_group, R.id.setting_aty_bind_group, R.id.setting_aty_pass_group, R.id.setting_glide_cahce_tv, R.id.setting_aty_about_group, R.id.setting_aty_tuichu_group})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            返回键
            case R.id.setting_aty_cancle:
                break;
//            换绑手机
            case R.id.setting_aty_changephone_group:
                break;
//            社交账号绑定
            case R.id.setting_aty_bind_group:
                break;
//            修改登录密码
            case R.id.setting_aty_pass_group:
                break;
//            清除缓存
            case R.id.setting_glide_cahce_tv:
                break;
//            关于UnivStar
            case R.id.setting_aty_about_group:
                break;
//            退出登录
            case R.id.setting_aty_tuichu_group:
                sharedPreferences=getSharedPreferences("userState",0);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("isLogin",false);
                edit.commit();
                finish();
                break;
        }
    }
}
