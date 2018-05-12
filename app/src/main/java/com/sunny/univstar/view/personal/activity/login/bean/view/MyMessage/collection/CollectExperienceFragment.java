package com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;
import com.sunny.univstar.contract.mycollect.CollectExperienceContract;
import com.sunny.univstar.presenter.mycoollect.CollectExperiencePresenter;
import com.sunny.univstar.view.notice.activity.NoticeDetailedActivity;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.adapter.CollectExperienceAdapter;

import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectExperienceFragment extends BaseFragment implements CollectExperienceContract.ExperienceInView {
    public CollectExperienceContract.ExperienceInPresenter experienceInPresenter;
    @Bind(R.id.collectExperience_recyView)
    RecyclerView collectExperienceRecyView;
    public SharedPreferences sharedPreferences;
    @Bind(R.id.myCollectionExperience_empty)
    RelativeLayout myCollectionExperienceEmpty;
    private CollectExperienceAdapter collectExperienceAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect_experience;
    }

    @Override
    protected void init() {
        experienceInPresenter = new CollectExperiencePresenter(this);
        sharedPreferences = getActivity().getSharedPreferences("userState", 0);
        int loginUserId = sharedPreferences.getInt("loginUserId", 0);
        experienceInPresenter.sendExperience(loginUserId, 2);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showExperienceData(CollectExperienceBean collectExperienceBean) {
        final List<CollectExperienceBean.DataBean.ListBean> list = collectExperienceBean.getData().getList();
        if (list.size() == 0) {
            myCollectionExperienceEmpty.setVisibility(View.VISIBLE);
            collectExperienceRecyView.setVisibility(View.GONE);
        }else {
            myCollectionExperienceEmpty.setVisibility(View.GONE);
            collectExperienceRecyView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            collectExperienceRecyView.setLayoutManager(linearLayoutManager);
            collectExperienceAdapter = new CollectExperienceAdapter(list, getActivity());
            collectExperienceRecyView.setAdapter(collectExperienceAdapter);
        }
        collectExperienceAdapter.OnShortListener(new CollectExperienceAdapter.OnShortListener() {
            @Override
            public void setOnShortListener(View view, int position) {
                Intent intent = new Intent(getContext(), NoticeDetailedActivity.class);
                intent.putExtra("id",list.get(position).getId()+"");
                startActivity(intent);
            }
        });
    }


}
