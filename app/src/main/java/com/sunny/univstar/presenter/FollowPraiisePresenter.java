package com.sunny.univstar.presenter;

import com.sunny.univstar.contract.FollowPraiseContract;
import com.sunny.univstar.model.HomeModel;
import com.sunny.univstar.model.IHomeModel;

import java.io.IOException;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by DELL on 2018/5/8.
 */

public class FollowPraiisePresenter implements FollowPraiseContract.FollowPraisePresenter{
    private IHomeModel iHomeModel;
    private FollowPraiseContract.FollowPraiseView followPraiseView;

    public FollowPraiisePresenter(FollowPraiseContract.FollowPraiseView followPraiseView) {
        this.followPraiseView = followPraiseView;
        iHomeModel = new HomeModel();
    }

    @Override
    public void sendFollowPraise(String url, Map<String, String> map) {
        iHomeModel.getMyFollowPraiseService().getFollowPraiseService(url,map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            followPraiseView.getFollowPraise(string);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
