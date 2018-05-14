package com.sunny.univstar.view.personal.activity.login.bean.set;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

//社交账号绑定
public class SocialNumBindActivity extends BaseActivity {
    @Bind(R.id.socialNumBind_back)
    TextView socialNumBindBack;
    @Bind(R.id.socialNumBind_QQ)
    RelativeLayout socialNumBindQQ;
    @Bind(R.id.socialNumBind_Sina)
    RelativeLayout socialNumBindSina;
    @Bind(R.id.socialNumBind_Weixin)
    RelativeLayout socialNumBindWeixin;
    /*@Bind(R.id.socialNumBind_back)
    TextView socialNumBindBack;
    @Bind(R.id.socialNumBind_QQ)
    RelativeLayout socialNumBindQQ;
    @Bind(R.id.socialNumBind_Sina)
    RelativeLayout socialNumBindSina;
    @Bind(R.id.socialNumBind_Weixin)
    RelativeLayout socialNumBindWeixin;*/

    @Override
    protected int getLayoutId() {
        return R.layout.activity_social_num_bind;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.socialNumBind_back, R.id.socialNumBind_QQ, R.id.socialNumBind_Sina, R.id.socialNumBind_Weixin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.socialNumBind_back:
                finish();
                break;
            case R.id.socialNumBind_QQ:
                break;
            case R.id.socialNumBind_Sina:
                break;
            case R.id.socialNumBind_Weixin:
                break;
        }
    }




}
