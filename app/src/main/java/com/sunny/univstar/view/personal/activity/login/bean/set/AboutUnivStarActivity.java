package com.sunny.univstar.view.personal.activity.login.bean.set;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class AboutUnivStarActivity extends BaseActivity {
    @Bind(R.id.aboutUStar_back)
    ImageView aboutUStarBack;
    @Bind(R.id.aboutUStar_intro)
    RelativeLayout aboutUStarIntro;
    @Bind(R.id.aboutUStar_version)
    RelativeLayout aboutUStarVersion;
//    @Bind(R.id.aboutUStar_back)
//    ImageView aboutUStarBack;
//    @Bind(R.id.aboutUStar_intro)
//    RelativeLayout aboutUStarIntro;
//    @Bind(R.id.aboutUStar_version)
//    RelativeLayout aboutUStarVersion;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_univ_star;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.aboutUStar_back, R.id.aboutUStar_intro})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aboutUStar_back:
                finish();
                break;
            case R.id.aboutUStar_intro:
                startActivity(new Intent(AboutUnivStarActivity.this, AboutIntroActivity.class));
                break;
        }
    }

}
