package com.sunny.univstar.presenter;

import com.sunny.univstar.contract.TeacherLiveContract;
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
 * Created by DELL on 2018/5/10.
 */

public class TeacherLivePresenter implements TeacherLiveContract.TeacherLivePresenter{

    private IHomeModel iHomeModel;
    private TeacherLiveContract.TeacherLiveView teacherLiveView;

    public TeacherLivePresenter(TeacherLiveContract.TeacherLiveView teacherLiveView) {
        this.teacherLiveView = teacherLiveView;
        iHomeModel = new HomeModel();
    }


    @Override
    public void sendTeacherLive(Map<String, String> map) {
        iHomeModel.getTeacherLiveService().getTeacherLive(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LiveCourseEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LiveCourseEntity liveCourseEntity) {
                        teacherLiveView.getTeacherLiveData(liveCourseEntity);
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
