package com.sunny.univstar.presenter.mycoollect;

import com.sunny.univstar.contract.mycollect.CollectPostsContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.CollectPostsBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/5/12.
 */

public class CollectPostsPresenter implements CollectPostsContract.CollectPostsInPresenter {
    public CollectPostsContract.CollectPostsInView collectPostsInView;
    public CollectPostsPresenter(CollectPostsContract.CollectPostsInView collectPostsInView){
        this.collectPostsInView=collectPostsInView;
    }
    @Override
    public void sendCollectPostsData(int userId, int type) {
        RetrofitUtils.getInstance()
                .getCollectPostsService()
                .getPosts(userId,type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<CollectPostsBean>() {
                    @Override
                    public void accept(CollectPostsBean collectPostsBean) throws Exception {
                        collectPostsInView.showCollectPostsData(collectPostsBean);
                    }
                });
    }
}
