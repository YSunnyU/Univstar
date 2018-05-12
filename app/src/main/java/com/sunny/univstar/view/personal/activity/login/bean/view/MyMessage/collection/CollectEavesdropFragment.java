package com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection;


import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;
import com.sunny.univstar.contract.mycollect.CollectEavesdropContract;
import com.sunny.univstar.presenter.mycoollect.CollectEavesdropPresenter;

import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectEavesdropFragment extends BaseFragment implements CollectEavesdropContract.CollectEavesdropInView {
    @Bind(R.id.myCollectionEavesdrop_recyView)
    RecyclerView myCollectionEavesdropRecyView;
    @Bind(R.id.myCollectionEavesdrop_empty)
    RelativeLayout myCollectionEavesdropEmpty;
    public CollectEavesdropContract.CollectEavesdropInPresenter collectEavesdropInPresenter;
    public SharedPreferences sharedPreferences;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect_eavesdrop;
    }

    @Override
    protected void init() {
        collectEavesdropInPresenter=new CollectEavesdropPresenter(this);
        sharedPreferences = getActivity().getSharedPreferences("userState", 0);
        int loginUserId = sharedPreferences.getInt("loginUserId", 0);
        collectEavesdropInPresenter.sendCollectEavesdropData(loginUserId,3);

    }

    @Override
    protected void initData() {

    }


    @Override
    public void showCollectEavesdropData(CollectEavesdropBean collectEavesdropBean) {
        List<?> list = collectEavesdropBean.getData().getList();
        if (list.size()==0){
            myCollectionEavesdropEmpty.setVisibility(View.VISIBLE);
            myCollectionEavesdropRecyView.setVisibility(View.GONE);
        }
    }
}
