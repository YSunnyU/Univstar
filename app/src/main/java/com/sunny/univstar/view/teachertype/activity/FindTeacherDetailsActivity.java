package com.sunny.univstar.view.teachertype.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.FollowPraiseContract;
import com.sunny.univstar.contract.MasterDetailContract;
import com.sunny.univstar.model.entity.MasterDetailEntity;
import com.sunny.univstar.model.utils.ShapeUtils;
import com.sunny.univstar.presenter.FollowPraiisePresenter;
import com.sunny.univstar.presenter.MasterDeatilPresenter;
import com.sunny.univstar.view.personal.activity.LoginActivity;
import com.sunny.univstar.view.teachertype.adapter.MyTeacherDetailsAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class FindTeacherDetailsActivity extends BaseActivity implements MasterDetailContract.MasterDetailView, FollowPraiseContract.FollowPraiseView {


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
    @Bind(R.id.teacher_details_coures_linear)
    LinearLayout teacherDetailsCouresLinear;
    @Bind(R.id.teacher_details_work_linear)
    LinearLayout teacherDetailsWorkLinear;
    @Bind(R.id.teacher_details_coach_linear)
    LinearLayout teacherDetailsCoachLinear;
    @Bind(R.id.teacher_details_note_linear)
    LinearLayout teacherDetailsNoteLinear;
    @Bind(R.id.teacher_details_follow_linear)
    LinearLayout teacherDetailsFollowLinear;
    @Bind(R.id.teacher_details_linear)
    LinearLayout teacherDetailsLinear;
    private MyTeacherDetailsAdapter mAdapter;
    private List<String> mList;
    private MasterDetailContract.MasterDetailPresenter masterDetailPresenter;
    private FollowPraiseContract.FollowPraisePresenter followPraisePresenter;
    private String teacherId;
    private List<MasterDetailEntity> mList2;
    private boolean isLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_teacher_details;
    }

    @Override
    protected void init() {
        mList2 = new ArrayList<>();
        mList = new ArrayList<>();
        masterDetailPresenter = new MasterDeatilPresenter(this);
        followPraisePresenter = new FollowPraiisePresenter(this);
        Intent intent = getIntent();
        SharedPreferences userState = getSharedPreferences("userState", 0);
        isLogin = userState.getBoolean("isLogin", false);
        int userId = userState.getInt("loginUserId", 0);
        teacherId = intent.getStringExtra("teacherId");
        mAdapter = new MyTeacherDetailsAdapter(mList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        teacherDetailsList.setLayoutManager(linearLayoutManager);
        teacherDetailsList.setAdapter(mAdapter);
        Map<String, String> map = new HashMap<>();
        map.put("loginUserId", userId + "");
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
        teacherDetailsFollowCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLogin) {
                    teacherDetailsFollowCheck.setChecked(!teacherDetailsFollowCheck.isChecked());
                    startActivity(new Intent(FindTeacherDetailsActivity.this, LoginActivity.class));
                    return;
                }

                if (isAttention) {
                    isAttention = false;
                    prasre("https://www.univstar.com/v1/m/user/attention/cancel", teacherId);
                    teacherDetailsFollowCheck.setText("关注");
                    teacherDetailsFollowCheck.setTextColor(Color.parseColor("#000000"));
                } else {
                    isAttention = true;
                    prasre("https://www.univstar.com/v1/m/user/attention", teacherId);
                    teacherDetailsFollowCheck.setText("已关注");
                    teacherDetailsFollowCheck.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });
        teacherDetailsPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLogin) {
                    teacherDetailsPraise.setChecked(false);
                    startActivity(new Intent(FindTeacherDetailsActivity.this, LoginActivity.class));
                    return;
                }
//                if (isPraise){
//                    isPraise = false;
//                    prasre2("https://www.univstar.com/v1/m/user/praise",teacherId,"老师");
//                    teacherDetailsPraise.setText((mList2.get(0).getData().getPraise().getPraiseCount()+1)+"");
//                    teacherDetailsPraise.setTextColor(Color.parseColor("#ff9100"));
//                }else {
//                    isPraise = true;
//                    prasre2("https://www.univstar.com/v1/m/user/praise/cancel",teacherId,"老师");
//                    teacherDetailsPraise.setTextColor(Color.parseColor("#FFFFFF"));
//                    teacherDetailsPraise.setText((mList2.get(0).getData().getPraise().getPraiseCount())+"");
//                }

                if (mList2.get(0).getData().getPraise().getIsPraise() == 0) {
                    prasre2("https://www.univstar.com/v1/m/user/praise", teacherId, "老师");
                    mList2.get(0).getData().getPraise().setIsPraise(1);
                    teacherDetailsPraise.setChecked(true);
                    teacherDetailsPraise.setTextColor(Color.parseColor("#ff9100"));
                    mList2.get(0).getData().getPraise().setPraiseCount(mList2.get(0).getData().getPraise().getPraiseCount() + 1);
                    teacherDetailsPraise.setText(mList2.get(0).getData().getPraise().getPraiseCount() + "");
                } else {
                    prasre2("https://www.univstar.com/v1/m/user/praise/cancel", teacherId, "老师");
                    mList2.get(0).getData().getPraise().setIsPraise(0);
                    teacherDetailsPraise.setTextColor(Color.parseColor("#FFFFFF"));
                    teacherDetailsPraise.setChecked(false);
                    if (mList2.get(0).getData().getPraise().getPraiseCount() == 0) {
                        teacherDetailsPraise.setText(mList2.get(0).getData().getPraise().getPraiseCount() + "");
                    } else {
                        mList2.get(0).getData().getPraise().setPraiseCount(mList2.get(0).getData().getPraise().getPraiseCount() - 1);
                        teacherDetailsPraise.setText(mList2.get(0).getData().getPraise().getPraiseCount() + "");
                    }
                }

            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.masterdetail_coachbtn, R.id.teacher_details_coures_linear, R.id.teacher_details_work_linear, R.id.teacher_details_coach_linear, R.id.teacher_details_note_linear, R.id.teacher_details_follow_linear, R.id.teacher_details_linear,R.id.teacher_return, R.id.teacher_share, R.id.teacher_details_coures, R.id.teacher_details_work, R.id.teacher_details_coach, R.id.teacher_details_note, R.id.teacher_details_follow, R.id.teacher_details_etc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.teacher_return:
                finish();
                break;
            case R.id.teacher_share:
                ShapeUtils shapeUtils = new ShapeUtils(this);
                shapeUtils.setWeb("http://share.univstar.com/share/teacher-home.html?teacherId="+teacherId,
                        null,"风里雨里,心愿艺考等你",R.mipmap.ic_launcher);
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.masterdetail_coachbtn:
                if (!isLogin)
                    startActivity(new Intent(FindTeacherDetailsActivity.this, LoginActivity.class));
//                跳转到亲求辅导页面
                Intent intent2 = new Intent(this, CoachActivity.class);
                intent2.putExtra("name",mList2.get(0).getData().getUser().getNickname());
                startActivity(intent2);
                break;
            case R.id.teacher_details_coures_linear:
                Toast.makeText(this, "课程", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, TeacherLiveActivity.class);
                intent1.putExtra("teacherId",mList2.get(0).getData().getUser().get_$Id121()+"");
                startActivity(intent1);
                break;
            case R.id.teacher_details_work_linear:
                Toast.makeText(this, "作业", Toast.LENGTH_SHORT).show();

                break;
            case R.id.teacher_details_coach_linear:
                Toast.makeText(this, "辅导", Toast.LENGTH_SHORT).show();

                break;
            case R.id.teacher_details_note_linear:
                Toast.makeText(this, "帖子", Toast.LENGTH_SHORT).show();

                break;
            case R.id.teacher_details_follow_linear:
                Toast.makeText(this, "关注", Toast.LENGTH_SHORT).show();

                break;
            case R.id.teacher_details_linear:
                Toast.makeText(this, "粉丝", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, TeacherFanActivity.class);
                intent.putExtra("title",mList2.get(0).getData().getUser().getNickname());
                intent.putExtra("teacherId", teacherId + "");
                startActivity(intent);
                break;
        }
    }

    private boolean isAttention;
    private boolean isPraise;

    @Override
    public void getMasterDetailData(MasterDetailEntity masterDetailEntity) {
        MasterDetailEntity.DataBean.UserBean user = masterDetailEntity.getData().getUser();
        mList2.clear();
        mList2.add(masterDetailEntity);
//            设置主图
        Glide.with(this).load(user.getImages())
                .asBitmap()
                .into(teacherDetailsMsgImg);
//            设置skilled
        String skilled = user.getSkilled();
        String[] split = skilled.split(",");
        String classify = "";
        for (String s : split) {
            classify += s;
        }
        teacherDetailsClassify.setText(classify);

//            设置赞de数量
        teacherDetailsPraise.setText(masterDetailEntity.getData().getPraise().getPraiseCount() + "");
        if (masterDetailEntity.getData().getPraise().getIsPraise() == 1) {
            teacherDetailsPraise.setChecked(true);
            teacherDetailsPraise.setTextColor(Color.parseColor("#ff9100"));
            isPraise = true;
        } else {
            teacherDetailsPraise.setChecked(false);
            teacherDetailsPraise.setTextColor(Color.parseColor("#ffffff"));
            isPraise = false;
        }
//        设置头像
        Glide.with(this)
                .load(user.getPhoto())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(new BitmapImageViewTarget(teacherDetailsHeaderImg) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
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

        int isAttention257 = masterDetailEntity.getData().get_$IsAttention257();
        if (isAttention257 == 0) {
            teacherDetailsFollow.setText("关注");
            teacherDetailsFollowCheck.setTextColor(Color.parseColor("#000000"));
            teacherDetailsFollowCheck.setChecked(true);
            isAttention = false;
        } else if (isAttention257 == 1) {
            teacherDetailsFollowCheck.setText("已关注");
            teacherDetailsFollowCheck.setTextColor(Color.parseColor("#ffffff"));
            teacherDetailsFollowCheck.setChecked(false);
            isAttention = true;
        } else if (isAttention257 == 2) {
            teacherDetailsFollowCheck.setText("相互关注");
            teacherDetailsFollowCheck.setTextColor(Color.parseColor("#ffffff"));
            teacherDetailsFollowCheck.setChecked(false);
            isAttention = true;
        }

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

    @Override
    public void getFollowPraise(String msg) {
        if (!msg.contains("成功")) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    private void prasre(String url, String attentionId) {
        SharedPreferences userState = getSharedPreferences("userState", 0);
        int userId = userState.getInt("loginUserId", 0);
        Map<String, String> map = new HashMap<>();
        map.put("attentionId", attentionId + "");
        map.put("loginUserId", userId + "");
        followPraisePresenter.sendFollowPraise(url, map);
    }

    private void prasre2(String url, String attentionId, String type) {
        SharedPreferences userState = getSharedPreferences("userState", 0);
        int userId = userState.getInt("loginUserId", 0);
        Log.e("userIdaaaaaaaa", userId + "");
        Map<String, String> map = new HashMap<>();
        map.put("userId", attentionId + "");
        map.put("loginUserId", userId + "");
        map.put("id", attentionId + "");
        map.put("type", type);
        followPraisePresenter.sendFollowPraise(url, map);
    }

}
