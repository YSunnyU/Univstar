package com.sunny.univstar.presenter;

import com.sunny.univstar.contract.FindTeacherContract;
import com.sunny.univstar.model.HomeModel;
import com.sunny.univstar.model.IHomeModel;
import com.sunny.univstar.model.entity.FindTeacherEntity;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DELL on 2018/5/7.
 */

public class FindTeacherPresenter implements FindTeacherContract.FindTeacherPresneter{

    private IHomeModel iHomeModel;
    private FindTeacherContract.FindTeacherView findTeacherView;

    public FindTeacherPresenter(FindTeacherContract.FindTeacherView findTeacherView) {
        this.findTeacherView = findTeacherView;
        iHomeModel = new HomeModel();
    }

    @Override
    public void sendFindTeacher(Map<String, String> map) {
        iHomeModel.getFindTeacher().getFindTeacherData(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindTeacherEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FindTeacherEntity findTeacherEntity) {
                        findTeacherView.getFindTeacherData(findTeacherEntity);
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
