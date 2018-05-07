package com.sunny.univstar.presenter;

import android.util.Log;

import com.sunny.univstar.contract.HomeNoticeContract;
import com.sunny.univstar.model.HomeModel;
import com.sunny.univstar.model.IHomeModel;
import com.sunny.univstar.model.entity.HomeNoticeEntity;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DELL on 2018/5/4.
 */

public class HomeNoticePresenter implements HomeNoticeContract.HomeNoticePresenter{
    private IHomeModel iHomeModel;
    private HomeNoticeContract.HomeNoticeView homeNoticeView;

    public HomeNoticePresenter(HomeNoticeContract.HomeNoticeView homeNoticeView) {
        this.homeNoticeView = homeNoticeView;
        iHomeModel = new HomeModel();
    }

    @Override
    public void sendHomeNotice(Map<String, String> map) {
        iHomeModel.getHomeNotice().getHomeNoticeService(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeNoticeEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HomeNoticeEntity homeNoticeEntity) {
                        homeNoticeView.getHomeNoticeData(homeNoticeEntity);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("HomeNoticeService",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
