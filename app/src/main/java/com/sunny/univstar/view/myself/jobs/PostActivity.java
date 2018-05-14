package com.sunny.univstar.view.myself.jobs;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


public class PostActivity extends BaseActivity implements IPostContract.IPostView {


    @Bind(R.id.post_return)
    ImageView postReturn;
    @Bind(R.id.post_list)
    ListView postList;
    @Bind(R.id.post_nothing)
    LinearLayout postNothing;
    private SharedPreferences user;
    private SharedPreferences.Editor edit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_post;
    }

    @Override
    protected void init() {
//        post_return = findViewById(R.id.post_return);
//        post_list = findViewById(R.id.post_list);
//        post_nothing = findViewById(R.id.post_nothing);
//        post_return.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        user = getSharedPreferences("userState", MODE_PRIVATE);
        edit = user.edit();
        PostPresenterImp postPresenterImp = new PostPresenterImp(this);
        postPresenterImp.loadPostBean(user.getInt("loginUserId", 0));
    }


    @Override
    public void showPostBean(List<PostBean.DataBean.ArtcircleListBean.ListBean> artcircleListBeans) {
        if (artcircleListBeans.size() == 0) {
            postNothing.setVisibility(View.VISIBLE);
            postList.setVisibility(View.GONE);
        } else {
            postNothing.setVisibility(View.GONE);
            postList.setVisibility(View.VISIBLE);
            PostListAdapter postListAdapter = new PostListAdapter(artcircleListBeans);
            postList.setAdapter(postListAdapter);
        }
    }


    @OnClick(R.id.post_return)
    public void onViewClicked() {
        finish();
    }


}
