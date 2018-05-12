package com.sunny.univstar.presenter.mycoollect;

import com.sunny.univstar.contract.mycollect.CollectEavesdropContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.CollectEavesdropBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/5/12.
 */

public class CollectEavesdropPresenter implements CollectEavesdropContract.CollectEavesdropInPresenter {
    public CollectEavesdropContract.CollectEavesdropInView collectEavesdropInView;

    public CollectEavesdropPresenter(CollectEavesdropContract.CollectEavesdropInView collectEavesdropInView) {
        this.collectEavesdropInView = collectEavesdropInView;
    }

    @Override
    public void sendCollectEavesdropData(int userId, int type) {
        RetrofitUtils.getInstance()
                .getCollectEavesdropService()
                .getEavesdrop(userId, type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CollectEavesdropBean>() {
                    @Override
                    public void accept(CollectEavesdropBean collectEavesdropBean) throws Exception {
                        collectEavesdropInView.showCollectEavesdropData(collectEavesdropBean);
                    }
                });

    }
}
