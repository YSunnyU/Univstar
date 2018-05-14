package com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection;


import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;
import com.sunny.univstar.contract.mycollect.CollectLivingContract;
import com.sunny.univstar.presenter.mycoollect.CollectLivingPresenter;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.adapter.CollectLivingAdapter;

import java.util.List;

import butterknife.Bind;

public class CollectLivingFragment extends BaseFragment implements CollectLivingContract.MyCollectionInView {

    public CollectLivingContract.MyCollectionInPresenter myCollectionInPresenter;
    @Bind(R.id.myCollectLiving_RecyView)
    RecyclerView myCollectLivingRecyView;
    @Bind(R.id.myCollectionLiving_emptyImage)
    ImageView myCollectionLivingEmptyImage;
    @Bind(R.id.myCollectionLiving_empty)
    RelativeLayout myCollectionLivingEmpty;
    public SharedPreferences sharedPreferences;
    /*@Bind(R.id.myCollectLiving_RecyView)
    RecyclerView myCollectLivingRecyView;

    @Bind(R.id.myCollectionLiving_empty)
    RelativeLayout myCollectionLivingEmpty;*/

    //    public static SharedPreferences sharedPreferences;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect_living;
    }

    @Override
    protected void init() {
        sharedPreferences = getActivity().getSharedPreferences("userState", 0);
        int loginUserId = sharedPreferences.getInt("loginUserId", 0);
        myCollectionInPresenter = new CollectLivingPresenter(this);
        myCollectionInPresenter.sendMyCollectionData(loginUserId, 1);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void showMyCollectionData(CollectionLivingBean collectionLivingBean) {
        List<CollectionLivingBean.DataBean.ListBean> list = collectionLivingBean.getData().getList();
        if (list.size() == 0) {
            myCollectionLivingEmpty.setVisibility(View.VISIBLE);
            myCollectLivingRecyView.setVisibility(View.GONE);
        } else {
            myCollectionLivingEmpty.setVisibility(View.GONE);
            myCollectLivingRecyView.setVisibility(View.VISIBLE);
            CollectLivingAdapter collectLivingAdapter = new CollectLivingAdapter(list, getActivity());
            myCollectLivingRecyView.setLayoutManager(new LinearLayoutManager(getContext()));
            myCollectLivingRecyView.setAdapter(collectLivingAdapter);
        }
    }


}
