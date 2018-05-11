package com.sunny.univstar.view.teachertype.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.FollowPraiseContract;
import com.sunny.univstar.contract.TeacherFanContract;
import com.sunny.univstar.model.entity.TeacherFanEntity;
import com.sunny.univstar.presenter.FollowPraiisePresenter;
import com.sunny.univstar.presenter.TeacherFanPresenter;
import com.sunny.univstar.view.personal.activity.LoginActivity;
import com.sunny.univstar.view.teachertype.adapter.TeacherFanAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class TeacherFanActivity extends BaseActivity implements TeacherFanContract.TeacherFanView,FollowPraiseContract.FollowPraiseView{

    @Bind(R.id.teacher_follower_return)
    ImageView teacherFollowerReturn;
    @Bind(R.id.teacher_follower_list)
    ListView teacherFollowerList;
    @Bind(R.id.teacher_fan_title)
    TextView teacherFanTitle;
    private FollowPraiseContract.FollowPraisePresenter followPraisePresenter;
    private TeacherFanContract.TeacherFanPersenter teacherFanPersenter;
    private List<TeacherFanEntity.DataBean.ListBean> mList;
    private TeacherFanAdapter adapter;
    private int loginUserId;
    private boolean isLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_follower;
    }

    @Override
    protected void init() {
        teacherFanPersenter = new TeacherFanPresenter(this);
        followPraisePresenter = new FollowPraiisePresenter(this);
        mList = new ArrayList<>();
        adapter = new TeacherFanAdapter(mList,this);
        adapter.setOnClickItem(new TeacherFanAdapter.OnClickItem() {
            @Override
            public void onClickItem(View view, int position) {
                CheckBox checkBox = view.findViewById(R.id.teacher_fan_item_check);
                if (!isLogin) {
                    checkBox.setChecked(!checkBox.isChecked());
                    startActivity(new Intent(TeacherFanActivity.this, LoginActivity.class));
                    return;
                }
                Map<String,String> map = new HashMap<>();
                map.put("attentionId",mList.get(position).getFansId()+"");
                map.put("loginUserId",loginUserId+"");
                if (checkBox.isChecked()){
                    followPraisePresenter.sendFollowPraise("https://www.univstar.com/v1/m/user/attention/cancel",map);

                    checkBox.setText("关注");
                    checkBox.setTextColor(Color.parseColor("#9E9E9E"));
                }else {
                    followPraisePresenter.sendFollowPraise("https://www.univstar.com/v1/m/user/attention",map);

                    checkBox.setText("已关注");
                    checkBox.setTextColor(Color.parseColor("#FFFFFF"));

                }
            }
        });
        teacherFollowerList.setAdapter(adapter);
        SharedPreferences userState = getSharedPreferences("userState", 0);
        loginUserId = userState.getInt("loginUserId", 0);
        isLogin = userState.getBoolean("isLogin", false);
        Intent intent = getIntent();
        String teacherId = intent.getStringExtra("teacherId");
        String title = intent.getStringExtra("title");
        teacherFanTitle.setText(title+"的粉丝");
        Map<String,String> map = new HashMap<>();
        map.put("page","1");
        map.put("rows","100");
        map.put("loginUserId", loginUserId +"");
        map.put("teacherId",teacherId);
        teacherFanPersenter.sendTeacherFan(map);

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.teacher_follower_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.teacher_follower_return:
                finish();
                break;
        }
    }

    @Override
    public void getTeacherFanData(TeacherFanEntity teacherFanEntity) {
        mList.clear();
        mList.addAll(teacherFanEntity.getData().getList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getFollowPraise(String msg) {

    }
}
