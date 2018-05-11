package com.sunny.univstar.view.teachertype.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.TeacherLiveContract;
import com.sunny.univstar.model.entity.LiveCourseEntity;
import com.sunny.univstar.presenter.TeacherLivePresenter;
import com.sunny.univstar.view.livecourse.activity.LiveCourseDetailedActivity;
import com.sunny.univstar.view.livecourse.adapter.LiveCourseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class TeacherLiveActivity extends BaseActivity implements TeacherLiveContract.TeacherLiveView{


    @Bind(R.id.course_return)
    ImageView courseReturn;
    @Bind(R.id.live_course_list)
    RecyclerView liveCourseList;
    @Bind(R.id.course_no_bg)
    LinearLayout courseNoBg;
    private TeacherLiveContract.TeacherLivePresenter teacherLivePresenter;
    private List<LiveCourseEntity.DataBean.ListBean> mList;
    private LiveCourseAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_live;
    }

    @Override
    protected void init() {
        teacherLivePresenter = new TeacherLivePresenter(this);
        mList = new ArrayList<>();
        Intent intent = getIntent();
        String teacherId = intent.getStringExtra("teacherId");
        SharedPreferences userState = getSharedPreferences("userState", 0);
        int loginUserId = userState.getInt("loginUserId", 0);
        adapter = new LiveCourseAdapter(mList);
        adapter.setOnClickItem(new LiveCourseAdapter.OnClickItem() {
            @Override
            public void onClickItem(View view, int position) {
                Intent intent = new Intent(TeacherLiveActivity.this, LiveCourseDetailedActivity.class);
//                课程ID
                intent.putExtra("id",mList.get(position).getId()+"");
                startActivity(intent);
            }
        });
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        liveCourseList.setLayoutManager(linearLayout);
        liveCourseList.setAdapter(adapter);
        Map<String,String> map = new HashMap<>();
        map.put("page","1");
        map.put("rows","20");
        map.put("teacherId",teacherId+"");
        map.put("loginUserId",loginUserId+"");
        teacherLivePresenter.sendTeacherLive(map);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.course_return, R.id.course_no_bg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.course_return:
                finish();
                break;
            case R.id.course_no_bg:
                break;
        }
    }

    @Override
    public void getTeacherLiveData(LiveCourseEntity liveCourseEntity) {
        if (liveCourseEntity.getCode() == 0) {
            courseNoBg.setVisibility(View.GONE);
            liveCourseList.setVisibility(View.VISIBLE);
            mList.clear();
            mList.addAll(liveCourseEntity.getData().getList());
            adapter.notifyDataSetChanged();
        }else {
            courseNoBg.setVisibility(View.VISIBLE);
            liveCourseList.setVisibility(View.GONE);
        }
    }
}
