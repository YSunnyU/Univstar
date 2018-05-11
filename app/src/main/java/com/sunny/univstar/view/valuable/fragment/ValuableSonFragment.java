package com.sunny.univstar.view.valuable.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;
import com.sunny.univstar.contract.HomeValuableContract;
import com.sunny.univstar.presenter.HomeValuablePresenter;
import com.sunny.univstar.view.valuable.fragment.adapter.ValuableSonAdapter;
import com.sunny.univstar.view.work.activity.WorkDetailedActivity;

import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class ValuableSonFragment extends BaseFragment implements HomeValuableContract.HomeValuableInView {

    public HomeValuableContract.HomeValuableInPresenter homeValuableInPresenter;

    @Bind(R.id.valuable_son_recyclerView)
    RecyclerView valuableSonRecyclerView;
    @Bind(R.id.content_empty)
    RelativeLayout contentEmpty;
    private int size;
    private List<HomeValuableBean.DataBean.ArtcircleListBean.ListBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_valuable_son;
    }

    @Override
    protected void init() {
        homeValuableInPresenter = new HomeValuablePresenter(this);
//        homeValuableInPresenter.sendFlyBannerData();
        Bundle arguments = getArguments();
        Log.d("ValuableSonFragment", "arguments:" + arguments);
        if (arguments != null) {
            String page = arguments.getString("page");
            Log.e("ValuableSonFragment", page);
            String rows = arguments.getString("rows");
            String sortord = arguments.getString("sortord");
            String loginUserId = arguments.getString("loginUserId");
            size = arguments.getInt("size");
                homeValuableInPresenter.sendHomeValuableData(page, rows, loginUserId, sortord);
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showHomeValuableData(HomeValuableBean homeValuableBean) {
        if (size >= 3) {
            contentEmpty.setVisibility(View.VISIBLE);
            valuableSonRecyclerView.setVisibility(View.GONE);
        }else {
            HomeValuableBean.DataBean.ArtcircleListBean artcircleList = homeValuableBean.getData().getArtcircleList();
            list = artcircleList.getList();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            valuableSonRecyclerView.setLayoutManager(linearLayoutManager);
            ValuableSonAdapter valuableSonAdapter = new ValuableSonAdapter(list, getActivity());
            valuableSonAdapter.setOnClickItem(new ValuableSonAdapter.OnClickItem() {
                @Override
                public void onClickItem(View view, int position) {
                    if (list != null && list.size() > 0) {
                        Intent intent = new Intent(getContext(), WorkDetailedActivity.class);
                        intent.putExtra("homewokId", list.get(position).getId() + "");
                        intent.putExtra("type","宝典");
                        startActivity(intent);
                    }
                }
            });
            valuableSonRecyclerView.setAdapter(valuableSonAdapter);
        }

    }

    @Override
    public void showValueFlyBannerData(ValueFlaybannerBean valueFlaybannerBean) {
        Log.d("ValuableSonFragment", "aaaaa");
    }

}
