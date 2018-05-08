package com.sunny.univstar.view.teachertype.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.MasterDetailContract;
import com.sunny.univstar.model.entity.MasterDetailEntity;
import com.sunny.univstar.presenter.MasterDeatilPresenter;
import com.sunny.univstar.view.teachertype.adapter.MyTeacherDetailsAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class FindTeacherDetailsActivity extends BaseActivity implements MasterDetailContract.MasterDetailView {


    @Bind(R.id.masterdetail_coachbtn)
    LinearLayout masterdetailCoachbtn;
    @Bind(R.id.teacher_details_msg_img)
    ImageView teacherDetailsMsgImg;
    @Bind(R.id.teacher_details_classify)
    TextView teacherDetailsClassify;
    @Bind(R.id.teacher_details_praise)
    CheckBox teacherDetailsPraise;
    @Bind(R.id.teacher_details_header_img)
    ImageView teacherDetailsHeaderImg;
    @Bind(R.id.teacher_details_nick_name)
    TextView teacherDetailsNickName;
    @Bind(R.id.teacher_details_synopsis)
    TextView teacherDetailsSynopsis;
    @Bind(R.id.teacher_details_follow_check)
    CheckBox teacherDetailsFollowCheck;
    @Bind(R.id.teacher_details_coures)
    TextView teacherDetailsCoures;
    @Bind(R.id.teacher_details_work)
    TextView teacherDetailsWork;
    @Bind(R.id.teacher_details_coach)
    TextView teacherDetailsCoach;
    @Bind(R.id.teacher_details_note)
    TextView teacherDetailsNote;
    @Bind(R.id.teacher_details_follow)
    TextView teacherDetailsFollow;
    @Bind(R.id.teacher_details_etc)
    TextView teacherDetailsEtc;
    @Bind(R.id.teacher_details_list)
    RecyclerView teacherDetailsList;
    @Bind(R.id.teacher_details_vip)
    ImageView teacherDetailsVip;
    @Bind(R.id.teacher_return)
    ImageView teacherReturn;
    @Bind(R.id.teacher_share)
    ImageView teacherShare;
    private MyTeacherDetailsAdapter mAdapter;
    private List<String> mList;
    private MasterDetailContract.MasterDetailPresenter masterDetailPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_teacher_details;
    }

    @Override
    protected void init() {
        mList = new ArrayList<>();
        masterDetailPresenter = new MasterDeatilPresenter(this);
        Intent intent = getIntent();
        SharedPreferences userState = getSharedPreferences("userState", 0);
        String userId = userState.getString("userId", "0");
        String teacherId = intent.getStringExtra("teacherId");
        mAdapter = new MyTeacherDetailsAdapter(mList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        teacherDetailsList.setLayoutManager(linearLayoutManager);
        teacherDetailsList.setAdapter(mAdapter);
        Map<String, String> map = new HashMap<>();
        map.put("loginUserId", userId);
        map.put("teacherId", teacherId);
        masterDetailPresenter.sendMasterDetail(map);
//        if (listBean != null){
////            设置主图
//            Glide.with(this).load(listBean.getImages())
//                    .asBitmap()
//                    .into(teacherDetailsMsgImg);
////            设置skilled
//            String skilled = listBean.getSkilled();
//            String[] split = skilled.split(",");
//            for (String s : split) {
//                teacherDetailsClassify.setText(teacherDetailsClassify.getText().toString()+s);
//            }
//
////            设置赞de数量
//
//        }
        teacherDetailsFollowCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    teacherDetailsFollowCheck.setText("已关注");
                    teacherDetailsFollowCheck.setTextColor(Color.parseColor("#9c9c9c"));
                } else {
                    teacherDetailsFollowCheck.setText("关注");
                }
            }
        });

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.teacher_return, R.id.teacher_share,R.id.teacher_details_coures, R.id.teacher_details_work, R.id.teacher_details_coach, R.id.teacher_details_note, R.id.teacher_details_follow, R.id.teacher_details_etc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.teacher_details_coures:
                Toast.makeText(this, "课程", Toast.LENGTH_SHORT).show();
                break;
            case R.id.teacher_details_work:
                Toast.makeText(this, "作业", Toast.LENGTH_SHORT).show();

                break;
            case R.id.teacher_details_coach:
                Toast.makeText(this, "辅导", Toast.LENGTH_SHORT).show();

                break;
            case R.id.teacher_details_note:
                Toast.makeText(this, "帖子", Toast.LENGTH_SHORT).show();

                break;
            case R.id.teacher_details_follow:
                Toast.makeText(this, "关注", Toast.LENGTH_SHORT).show();

                break;
            case R.id.teacher_details_etc:
                Toast.makeText(this, "粉丝", Toast.LENGTH_SHORT).show();

                break;
            case R.id.teacher_return:
                finish();
                break;
            case R.id.teacher_share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void getMasterDetailData(MasterDetailEntity masterDetailEntity) {
        MasterDetailEntity.DataBean.UserBean user = masterDetailEntity.getData().getUser();
//            设置主图
        Glide.with(this).load(user.getImages())
                .asBitmap()
                .into(teacherDetailsMsgImg);
//            设置skilled
        String skilled = user.getSkilled();
        String[] split = skilled.split(",");
        for (String s : split) {
            teacherDetailsClassify.setText(teacherDetailsClassify.getText().toString() + s);
        }

//            设置赞de数量
        teacherDetailsPraise.setText(masterDetailEntity.getData().getPraise().getPraiseCount() + "");
//        设置头像
        Glide.with(this)
                .load(user.getPhoto())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(new BitmapImageViewTarget(teacherDetailsHeaderImg){
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),resource);
                        bitmapDrawable.setCircular(true);
                        teacherDetailsHeaderImg.setImageDrawable(bitmapDrawable);
                    }
                });
//        设置名字
        teacherDetailsNickName.setText(user.getNickname());
//        设置Vip颜色
        if (user.getUserType() == 4)
            teacherDetailsVip.setImageResource(R.mipmap.home_level_vip_red);
        else if (user.getUserType() == 3)
            teacherDetailsVip.setImageResource(R.mipmap.home_level_vip_yellow);
        else if (user.getUserType() == 2)
            teacherDetailsVip.setImageResource(R.mipmap.home_level_vip_blue);

//        设置简介
        teacherDetailsSynopsis.setText(user.getIntro());
//        设置课程数量
        teacherDetailsCoures.setText(masterDetailEntity.getData().getLiveCount() + "");
//        设置作业数量
        teacherDetailsWork.setText(masterDetailEntity.getData().getHomewokPublishCount() + "");
//        设置辅导数量
        teacherDetailsCoach.setText(masterDetailEntity.getData().getCoachingCount() + "");
//        设置帖子数量
        teacherDetailsNote.setText(masterDetailEntity.getData().getPostsCount() + "");
//        设置关注数量
        teacherDetailsFollow.setText(masterDetailEntity.getData().getAttentionCount() + "");
//        设置粉丝数量
        teacherDetailsEtc.setText(masterDetailEntity.getData().getFansCount() + "");

//        设置内容
        mList.clear();
        mList.add(user.getDetails());
        mAdapter.notifyDataSetChanged();
    }
}
