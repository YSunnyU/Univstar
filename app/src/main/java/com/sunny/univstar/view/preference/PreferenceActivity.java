package com.sunny.univstar.view.preference;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by 张玗 on 2018/5/3.
 */

public class PreferenceActivity extends BaseActivity {


    @Bind(R.id.presenter_back)
    ImageView presenterBack;
    @Bind(R.id.presenter_skeep)
    TextView presenterSkeep;
    @Bind(R.id.one_presenter)
    TextView onePresenter;
    @Bind(R.id.preference_img)
    ImageView preferenceImg;
    @Bind(R.id.majorList)
    ListView majorList;
    @Bind(R.id.preference_img2)
    ImageView preferenceImg2;
    @Bind(R.id.schoolList)
    ListView schoolList;
    @Bind(R.id.presenter_check)
    TextView presenterCheck;
    @Bind(R.id.presenter_enter)
    TextView presenterEnter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_preference;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.presenter_back, R.id.presenter_skeep, R.id.presenter_enter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            点击返回键，结束进程
            case R.id.presenter_back:
                finish();
                break;
//            点击跳过
            case R.id.presenter_skeep:

                break;
//            选好了？进入UnivStar
            case R.id.presenter_enter:

                break;
        }
    }



}
