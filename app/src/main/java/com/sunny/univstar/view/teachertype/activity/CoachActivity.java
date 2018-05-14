package com.sunny.univstar.view.teachertype.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

public class CoachActivity extends BaseActivity {


    @Bind(R.id.coact_return)
    TextView coactReturn;
    @Bind(R.id.coact_major)
    RecyclerView coactMajor;
    @Bind(R.id.ms_text)
    TextView msText;
    @Bind(R.id.coact_teacher_name)
    TextView coactTeacherName;
    @Bind(R.id.coact_add)
    LinearLayout coactAdd;
    @Bind(R.id.coact_content)
    EditText coactContent;
    @Bind(R.id.coact_permission)
    TextView coactPermission;
    @Bind(R.id.coact_money)
    TextView coactMoney;
    private String videoPath;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coach;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        videoPath = intent.getExtras().getString("videoPath");
        File sourceVideoFile = new File(videoPath);
        coactTeacherName.setText(name);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.coact_return, R.id.coact_add, R.id.coact_permission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.coact_return:
                finish();
                break;
            case R.id.coact_add:
                startActivity(new Intent(this, ShootVideoActivity.class));
                break;
            case R.id.coact_permission:
                break;
        }
    }

}
