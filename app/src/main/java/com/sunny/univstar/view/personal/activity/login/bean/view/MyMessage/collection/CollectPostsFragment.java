package com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;
import com.sunny.univstar.contract.mycollect.CollectPostsContract;
import com.sunny.univstar.presenter.mycoollect.CollectPostsPresenter;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.adapter.CollectPostsAdapter;
import com.sunny.univstar.view.work.activity.WorkDetailedActivity;

import java.util.List;

import butterknife.Bind;

public class CollectPostsFragment extends BaseFragment implements CollectPostsContract.CollectPostsInView{
    public CollectPostsContract.CollectPostsInPresenter collectPostsInPresenter;
    
    @Bind(R.id.myCollectionPosts_recyView)
    RecyclerView myCollectionPostsRecyView;
    @Bind(R.id.myCollectionPosts_empty)
    RelativeLayout myCollectionPostsEmpty;
    public SharedPreferences sharedPreferences;
    private List<CollectPostsBean.DataBean.ArtcircleListBean.ListBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect_posts;
    }

    @Override
    protected void init() {
        collectPostsInPresenter=new CollectPostsPresenter(this);
        sharedPreferences = getActivity().getSharedPreferences("userState", 0);
        int loginUserId = sharedPreferences.getInt("loginUserId", 0);
        collectPostsInPresenter.sendCollectPostsData(loginUserId,4);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void showCollectPostsData(CollectPostsBean collectPostsBean) {
        CollectPostsBean.DataBean data = collectPostsBean.getData();
        CollectPostsBean.DataBean.ArtcircleListBean artcircleList = data.getArtcircleList();
        list = artcircleList.getList();
        if (list.size()==0){
            myCollectionPostsEmpty.setVisibility(View.VISIBLE);
            myCollectionPostsRecyView.setVisibility(View.GONE);
        }
        else {
            myCollectionPostsEmpty.setVisibility(View.GONE);
            myCollectionPostsRecyView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            myCollectionPostsRecyView.setLayoutManager(linearLayoutManager);
            CollectPostsAdapter collectPostsAdapter = new CollectPostsAdapter(list, getActivity());
            myCollectionPostsRecyView.setAdapter(collectPostsAdapter);
            collectPostsAdapter.OnShortListener(new CollectPostsAdapter.OnShortListener() {
                @Override
                public void setOnShortListener(View view, int position) {
                    if (list != null && list.size() > 0) {
                        Intent intent = new Intent(getContext(), WorkDetailedActivity.class);
                        intent.putExtra("homewokId", list.get(position).getId() + "");
                        intent.putExtra("type","宝典");
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
