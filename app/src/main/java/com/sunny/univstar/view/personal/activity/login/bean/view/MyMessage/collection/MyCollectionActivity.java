package com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class MyCollectionActivity extends BaseActivity {
    @Bind(R.id.myCollection_back)
    ImageView myCollectionBack;
    @Bind(R.id.myCollection_Living_tv)
    TextView myCollectionLivingTv;
    @Bind(R.id.myCollection_LivingLine)
    TextView myCollectionLivingLine;
    @Bind(R.id.myCollection_Living)
    RelativeLayout myCollectionLiving;
    @Bind(R.id.myCollection_experience_tv)
    TextView myCollectionExperienceTv;
    @Bind(R.id.myCollection_experienceLine)
    TextView myCollectionExperienceLine;
    @Bind(R.id.myCollection_experience)
    RelativeLayout myCollectionExperience;
    @Bind(R.id.myCollection_eavesdrop_tv)
    TextView myCollectionEavesdropTv;
    @Bind(R.id.myCollection_eavesdropLine)
    TextView myCollectionEavesdropLine;
    @Bind(R.id.myCollection_eavesdrop)
    RelativeLayout myCollectionEavesdrop;
    @Bind(R.id.myCollection_posts_tv)
    TextView myCollectionPostsTv;
    @Bind(R.id.myCollection_postsLine)
    TextView myCollectionPostsLine;
    @Bind(R.id.myCollection_posts)
    RelativeLayout myCollectionPosts;
    @Bind(R.id.myCollection_frag)
    FrameLayout myCollectionFrag;
//    @Bind(R.id.myCollection_back)
//    ImageView myCollectionBack;
//    @Bind(R.id.myCollection_Living_tv)
//    TextView myCollectionLivingTv;
//    @Bind(R.id.myCollection_LivingLine)
//    TextView myCollectionLivingLine;
//    @Bind(R.id.myCollection_Living)
//    RelativeLayout myCollectionLiving;
//    @Bind(R.id.myCollection_experience_tv)
//    TextView myCollectionExperienceTv;
//    @Bind(R.id.myCollection_experienceLine)
//    TextView myCollectionExperienceLine;
//    @Bind(R.id.myCollection_experience)
//    RelativeLayout myCollectionExperience;
//    @Bind(R.id.myCollection_eavesdrop_tv)
//    TextView myCollectionEavesdropTv;
//    @Bind(R.id.myCollection_eavesdropLine)
//    TextView myCollectionEavesdropLine;
//    @Bind(R.id.myCollection_eavesdrop)
//    RelativeLayout myCollectionEavesdrop;
//    @Bind(R.id.myCollection_posts_tv)
//    TextView myCollectionPostsTv;
//    @Bind(R.id.myCollection_postsLine)
//    TextView myCollectionPostsLine;
//    @Bind(R.id.myCollection_posts)
//    RelativeLayout myCollectionPosts;
//
//
//
//    @Bind(R.id.myCollection_frag)
//    FrameLayout myCollectionFrag;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collection;
    }

    @Override
    protected void init() {

        //            帖子
        myCollectionPostsTv.setTextSize(14);
        myCollectionPostsLine.setVisibility(View.GONE);

//          直播课程
        myCollectionLivingTv.setTextSize(16);
        myCollectionLivingLine.setVisibility(View.VISIBLE);
//            偷听更多
        myCollectionEavesdropTv.setTextSize(14);
        myCollectionEavesdropLine.setVisibility(View.GONE);
//           体验课程
        myCollectionExperienceTv.setTextSize(14);
        myCollectionExperienceLine.setVisibility(View.GONE);
        replce(new CollectLivingFragment());
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.myCollection_back, R.id.myCollection_Living, R.id.myCollection_experience, R.id.myCollection_eavesdrop, R.id.myCollection_posts})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myCollection_back:
                finish();
                break;
            case R.id.myCollection_Living:
                //            帖子
                myCollectionPostsTv.setTextSize(14);
                myCollectionPostsLine.setVisibility(View.GONE);

//          直播课程
                myCollectionLivingTv.setTextSize(16);
                myCollectionLivingLine.setVisibility(View.VISIBLE);
//            偷听更多
                myCollectionEavesdropTv.setTextSize(14);
                myCollectionEavesdropLine.setVisibility(View.GONE);
//           体验课程
                myCollectionExperienceTv.setTextSize(14);
                myCollectionExperienceLine.setVisibility(View.GONE);
                replce(new CollectLivingFragment());
                break;
            case R.id.myCollection_experience:
                //            帖子
                myCollectionPostsTv.setTextSize(14);
                myCollectionPostsLine.setVisibility(View.GONE);
//          直播课程
                myCollectionLivingTv.setTextSize(14);
                myCollectionLivingLine.setVisibility(View.GONE);
//            偷听更多
                myCollectionEavesdropTv.setTextSize(14);
                myCollectionEavesdropLine.setVisibility(View.GONE);
//           体验课程
                myCollectionExperienceTv.setTextSize(16);
                myCollectionExperienceLine.setVisibility(View.VISIBLE);

                replce(new CollectExperienceFragment());
                break;
            case R.id.myCollection_eavesdrop:
                //            帖子
                myCollectionPostsTv.setTextSize(14);
                myCollectionPostsLine.setVisibility(View.GONE);
//          直播课程
                myCollectionLivingTv.setTextSize(14);
                myCollectionLivingLine.setVisibility(View.GONE);
//            偷听更多
                myCollectionEavesdropTv.setTextSize(16);
                myCollectionEavesdropLine.setVisibility(View.VISIBLE);
//           体验课程
                myCollectionExperienceTv.setTextSize(14);
                myCollectionExperienceLine.setVisibility(View.GONE);

                replce(new CollectEavesdropFragment());
                break;
            case R.id.myCollection_posts:
                //            帖子
                myCollectionPostsTv.setTextSize(16);
                myCollectionPostsLine.setVisibility(View.VISIBLE);
//          直播课程
                myCollectionLivingTv.setTextSize(14);
                myCollectionLivingLine.setVisibility(View.GONE);
//            偷听更多
                myCollectionEavesdropTv.setTextSize(14);
                myCollectionEavesdropLine.setVisibility(View.GONE);
//           体验课程
                myCollectionExperienceTv.setTextSize(14);
                myCollectionExperienceLine.setVisibility(View.GONE);

                replce(new CollectPostsFragment());
                break;

        }
    }

    private void replce(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.myCollection_frag, fragment);
        transaction.commit();
    }



}
