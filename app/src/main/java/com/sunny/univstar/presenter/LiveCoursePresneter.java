package com.sunny.univstar.presenter;

import android.util.Log;

import com.sunny.univstar.contract.LiveCourseContract;
import com.sunny.univstar.model.HomeModel;
import com.sunny.univstar.model.IHomeModel;
import com.sunny.univstar.model.entity.LiveCourseEntity;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DELL on 2018/5/7.
 */

public class LiveCoursePresneter implements LiveCourseContract.LiveCoursePresenter{
    private LiveCourseContract.LiveCourseView liveCourseView;
    private IHomeModel iHomeModel;

    public LiveCoursePresneter(LiveCourseContract.LiveCourseView liveCourseView) {
        this.liveCourseView = liveCourseView;
        iHomeModel = new HomeModel();
    }

    @Override
    public void sendLiveCourse(Map<String, String> map) {
        iHomeModel.getLiveCourse().getLiveCourseData(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<LiveCourseEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LiveCourseEntity liveCourseEntity) {
                        liveCourseView.getLiveCourseData(liveCourseEntity);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("getLiveCourseData",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
