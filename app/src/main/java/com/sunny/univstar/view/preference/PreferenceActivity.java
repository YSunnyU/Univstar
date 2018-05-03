package com.sunny.univstar.view.preference;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.PreferenceContract;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by 张玗 on 2018/5/3.
 */

public class PreferenceActivity extends BaseActivity implements PreferenceContract.PreferenceInView {


    @Bind(R.id.presenter_back)
    ImageView presenterBack;
    @Bind(R.id.presenter_skeep)
    TextView presenterSkeep;
    @Bind(R.id.majorList)
    ListView majorList;
    @Bind(R.id.schoolList)
    ListView schoolList;
    @Bind(R.id.presenter_check)
    TextView presenterChack;
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


    @Override
    public void showPreferenceData(PreferenceAboveBean preferenceAboveBean) {
        List<PreferenceAboveBean.DataBean.CollegesBean> colleges = preferenceAboveBean.getData().getColleges();
        List<PreferenceAboveBean.DataBean.MajorsBean> majors = preferenceAboveBean.getData().getMajors();

    }
}
