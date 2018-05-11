package com.sunny.univstar.presenter;

import com.sunny.univstar.contract.TeacherFanContract;
import com.sunny.univstar.model.HomeModel;
import com.sunny.univstar.model.IHomeModel;
import com.sunny.univstar.model.entity.TeacherFanEntity;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DELL on 2018/5/10.
 */

public class TeacherFanPresenter implements TeacherFanContract.TeacherFanPersenter{

    private IHomeModel iHomeModel;
    private TeacherFanContract.TeacherFanView teacherFanView;

    public TeacherFanPresenter(TeacherFanContract.TeacherFanView teacherFanView) {
        this.teacherFanView = teacherFanView;
        iHomeModel = new HomeModel();
    }

    @Override
    public void sendTeacherFan(Map<String,String> map) {
        iHomeModel.getTeacherFanService().getTeacherFanData(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeacherFanEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull TeacherFanEntity teacherFanEntity) {
                        teacherFanView.getTeacherFanData(teacherFanEntity);
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
