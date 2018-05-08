package com.sunny.univstar.presenter;

import com.sunny.univstar.contract.LiveCourseDetailedContract;
import com.sunny.univstar.model.HomeModel;
import com.sunny.univstar.model.IHomeModel;
import com.sunny.univstar.model.entity.LiveCourseDetailedEntity;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DELL on 2018/5/8.
 */

public class LiveCourseDetailedPresenter implements LiveCourseDetailedContract.LiveCourseDetailedPresenter{
    private IHomeModel iHomeModel;

    private LiveCourseDetailedContract.LiveCourseDetailedView liveCourseDetailedView;

    public LiveCourseDetailedPresenter(LiveCourseDetailedContract.LiveCourseDetailedView liveCourseDetailedView) {
        this.liveCourseDetailedView = liveCourseDetailedView;
        iHomeModel = new HomeModel();
    }

    @Override
    public void sendLiveCourseDetailed(Map<String, String> map) {
        iHomeModel.getLiveCourseDetailed().getLiveCourseDetailed(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<LiveCourseDetailedEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LiveCourseDetailedEntity liveCourseDetailedEntity) {
                        liveCourseDetailedView.getLiveCourseDetailedData(liveCourseDetailedEntity);
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
