package com.sunny.univstar.view.myself.jobs;

import android.widget.ImageView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;


public class WorksActivity extends BaseActivity  {

    @Bind(R.id.works_return)
    ImageView worksReturn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_works;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }




    @OnClick(R.id.works_return)
    public void onViewClicked() {
        finish();
    }


}
