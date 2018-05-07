package com.sunny.univstar.view;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.view.master.fragment.MasterFragment;
import com.sunny.univstar.view.myself.fragment.MySelfFragment;
import com.sunny.univstar.view.notice.fragment.NoticeFragment;
import com.sunny.univstar.view.valuable.fragment.ValuableFragment;
import com.sunny.univstar.view.work.fragment.WorkFragment;

import butterknife.Bind;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {


    @Bind(R.id.head_toolbar)
    Toolbar headToolbar;
    @Bind(R.id.container)
    FrameLayout container;
    @Bind(R.id.home_master_btn)
    public RadioButton homeMasterBtn;
    @Bind(R.id.home_work_btn)
    public RadioButton homeWorkBtn;
    @Bind(R.id.home_valuable_btn)
    public RadioButton homeValuableBtn;
    @Bind(R.id.home_notice_btn)
    public RadioButton homeNoticeBtn;
    @Bind(R.id.home_myself_btn)
    public RadioButton homeMyselfBtn;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        setTitleTheme(this,true);
        fragmentRepeat(R.id.container, MasterFragment.class);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.home_master_btn, R.id.home_work_btn, R.id.home_valuable_btn, R.id.home_notice_btn, R.id.home_myself_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_master_btn:
                headToolbar.setVisibility(View.VISIBLE);
                fragmentRepeat(R.id.container, MasterFragment.class);
                break;
            case R.id.home_work_btn:
                homeWork();
                break;
            case R.id.home_valuable_btn:
                valuable();
                break;
            case R.id.home_notice_btn:
                notice();

                break;
            case R.id.home_myself_btn:
                headToolbar.setVisibility(View.GONE);
                fragmentRepeat(R.id.container, MySelfFragment.class);
                break;
        }
    }

    public void notice() {
        headToolbar.setVisibility(View.VISIBLE);
        fragmentRepeat(R.id.container, NoticeFragment.class);
    }

    public void homeWork(){
        headToolbar.setVisibility(View.VISIBLE);
        fragmentRepeat(R.id.container, WorkFragment.class);
    }
    public void valuable(){
        headToolbar.setVisibility(View.VISIBLE);
        fragmentRepeat(R.id.container, ValuableFragment.class);
    }
}
