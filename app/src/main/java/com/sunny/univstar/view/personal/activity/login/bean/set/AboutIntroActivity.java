package com.sunny.univstar.view.personal.activity.login.bean.set;

import android.widget.ImageView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class AboutIntroActivity extends BaseActivity {


    @Bind(R.id.aboutIntro_back)
    ImageView aboutIntroBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_intro;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }



    @OnClick(R.id.aboutIntro_back)
    public void onViewClicked() {
        finish();
    }
}
