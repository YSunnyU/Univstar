package com.sunny.univstar.presenter.mycoollect;

import com.sunny.univstar.contract.mycollect.CollectLivingContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.CollectionLivingBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/5/11.
 */

public class CollectLivingPresenter implements CollectLivingContract.MyCollectionInPresenter {
    public CollectLivingContract.MyCollectionInView myCollectionInView;

    public CollectLivingPresenter(CollectLivingContract.MyCollectionInView myCollectionInView) {
        this.myCollectionInView = myCollectionInView;
    }

    @Override
    public void sendMyCollectionData(int userId, int type) {
        RetrofitUtils.getInstance()
                .getMyCollectLivingService()
                .getMyCollection(userId, type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CollectionLivingBean>() {
                    @Override
                    public void accept(CollectionLivingBean collectionLivingBean) throws Exception {
                        myCollectionInView.showMyCollectionData(collectionLivingBean);
                    }
                });
    }
}
