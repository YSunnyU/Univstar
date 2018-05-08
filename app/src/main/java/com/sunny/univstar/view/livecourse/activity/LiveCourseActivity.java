package com.sunny.univstar.view.livecourse.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.LiveCourseContract;
import com.sunny.univstar.model.entity.LiveCourseEntity;
import com.sunny.univstar.presenter.LiveCoursePresneter;
import com.sunny.univstar.view.livecourse.adapter.LiveCourseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class LiveCourseActivity extends BaseActivity implements LiveCourseContract.LiveCourseView {


    @Bind(R.id.course_btn_text)
    TextView courseBtnText;
    @Bind(R.id.course_btn_line)
    TextView courseBtnLine;
    @Bind(R.id.course_btn)
    RelativeLayout courseBtn;
    @Bind(R.id.live_course_text)
    TextView liveCourseText;
    @Bind(R.id.live_course_line)
    TextView liveCourseLine;
    @Bind(R.id.live_course)
    RelativeLayout liveCourse;
    @Bind(R.id.live_course_list)
    RecyclerView liveCourseList;
    @Bind(R.id.course_no_bg)
    LinearLayout courseNoBg;
    @Bind(R.id.course_return)
    ImageView courseReturn;

    private LiveCourseContract.LiveCoursePresenter liveCoursePresenter;
    private List<LiveCourseEntity.DataBean.ListBean> mList;
    private LiveCourseAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_live_course;
    }

    @Override
    protected void init() {
        mList = new ArrayList<>();
        liveCoursePresenter = new LiveCoursePresneter(this);
        SharedPreferences userState = getSharedPreferences("userState", 0);
        Map<String, String> map = new HashMap<>();
        map.put("loginUserId", userState.getString("userId", "0") + "");
        map.put("type", "讲堂");
        map.put("page", "1");
        liveCoursePresenter.sendLiveCourse(map);
        mAdapter = new LiveCourseAdapter(mList);
        mAdapter.setOnClickItem(new LiveCourseAdapter.OnClickItem() {
            @Override
            public void onClickItem(View view, int position) {
                Intent intent = new Intent(LiveCourseActivity.this, LiveCourseDetailedActivity.class);
//                课程ID
                intent.putExtra("id",mList.get(position).getId()+"");
                startActivity(intent);
            }
        });
        liveCourseList.setLayoutManager(new LinearLayoutManager(this));
        liveCourseList.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.course_btn, R.id.live_course,R.id.course_return})
    public void onViewClicked(View view) {
        SharedPreferences userState = getSharedPreferences("userState", 0);
        Map<String, String> map = new HashMap<>();
        switch (view.getId()) {
            case R.id.course_btn:
                map.clear();
                map.put("loginUserId", userState.getString("userId", "0") + "");
                map.put("type", "讲堂");
                map.put("page", "1");
                liveCoursePresenter.sendLiveCourse(map);
                courseBtnLine.setVisibility(View.VISIBLE);
                liveCourseLine.setVisibility(View.GONE);
                break;
            case R.id.live_course:
                map.clear();
                map.put("loginUserId", userState.getString("userId", "0") + "");
                map.put("type", "私塾");
                map.put("page", "1");
                liveCoursePresenter.sendLiveCourse(map);
                courseBtnLine.setVisibility(View.GONE);
                liveCourseLine.setVisibility(View.VISIBLE);
                break;
            case R.id.course_return:
                finish();
                break;
        }
    }

    @Override
    public void getLiveCourseData(LiveCourseEntity liveCourseEntity) {
        if (liveCourseEntity.getData().getList().size() > 0 && liveCourseEntity.getData().getList() != null) {
            mList.clear();
            mList.addAll(liveCourseEntity.getData().getList());
            mAdapter.notifyDataSetChanged();
            courseNoBg.setVisibility(View.GONE);
            liveCourseList.setVisibility(View.VISIBLE);
        } else {
            courseNoBg.setVisibility(View.VISIBLE);
            liveCourseList.setVisibility(View.GONE);
        }
    }

}
