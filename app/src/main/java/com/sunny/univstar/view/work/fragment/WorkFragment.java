package com.sunny.univstar.view.work.fragment;


import android.support.v4.app.Fragment;
import android.util.Log;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;
import com.sunny.univstar.contract.HomeWorkContract;
import com.sunny.univstar.model.entity.HomeWokListModel;
import com.sunny.univstar.presenter.HomeWorkPresenter;
import com.sunny.univstar.view.work.adapter.HomeWorkAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkFragment extends BaseFragment implements HomeWorkContract.HomeWorkView{
    private HomeWorkContract.HomeWorkPreference preferencel;
    private HomeWorkAdapter mAdapter;
    private List<Object> mList;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_work;
    }

    @Override
    protected void init() {
        preferencel = new HomeWorkPresenter(this);
        preferencel.sendWorkData(getContext(),1,0);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void getWorkData(HomeWokListModel homeWokListModel) {
        List<HomeWokListModel.DataBean.ListBean> list = homeWokListModel.getData().getList();
        mList.clear();
        mList.add(list);
        for (HomeWokListModel.DataBean.ListBean listBean : list) {
            Log.e("listBean--List",listBean.getContent());
        }
    }
}
