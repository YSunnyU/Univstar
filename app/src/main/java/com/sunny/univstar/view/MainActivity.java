package com.sunny.univstar.view;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.sunny.univstar.R;
import com.sunny.univstar.app.App;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.view.master.fragment.MasterFragment;
import com.sunny.univstar.view.message.AboveMessageActivity;
import com.sunny.univstar.view.myself.fragment.MySelfFragment;
import com.sunny.univstar.view.notice.fragment.NoticeFragment;
import com.sunny.univstar.view.valuable.fragment.ValuableFragment;
import com.sunny.univstar.view.work.fragment.WorkFragment;

import butterknife.Bind;
import butterknife.OnClick;

import static com.sunny.univstar.R.id.home_myself_btn;


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

    @Bind(R.id.AboveMessage)
    ImageView AboveMessage;
    @Bind(R.id.AboveCreation)
    ImageView AboveCreation;

    @Bind(R.id.home_myself_btn)
    RadioButton homeMyselfBtn;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        setTitleTheme(this, true);
        fragmentRepeat(R.id.container, MasterFragment.class);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.AboveMessage, R.id.AboveCreation, R.id.home_master_btn, R.id.home_work_btn, R.id.home_valuable_btn, R.id.home_notice_btn, R.id.home_myself_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.AboveMessage:
                startActivity(new Intent(MainActivity.this, AboveMessageActivity.class));
                break;
            case R.id.AboveCreation:
                break;
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
            case home_myself_btn:
                myself();
                break;
        }
    }

    /* public void myself(){
         headToolbar.setVisibility(View.GONE);
         fragmentRepeat(R.id.container, MySelfFragment.class);
             case R.id.home_myself_btn:
                 myself();
                 break;
         }
     }*/
    public void myself() {
        headToolbar.setVisibility(View.GONE);
        fragmentRepeat(R.id.container, MySelfFragment.class);
    }

    public void notice() {
        headToolbar.setVisibility(View.VISIBLE);
        fragmentRepeat(R.id.container, NoticeFragment.class);
    }

    public void homeWork() {
        headToolbar.setVisibility(View.VISIBLE);
        fragmentRepeat(R.id.container, WorkFragment.class);
    }

    public void valuable() {
        headToolbar.setVisibility(View.VISIBLE);
        fragmentRepeat(R.id.container, ValuableFragment.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String upDataPsw = intent.getStringExtra("upDataPsw");
        if ("aab".equals(upDataPsw)) {
            homeMyselfBtn.setChecked(true);
            myself();
        }
        App.context = this;
    }
}
