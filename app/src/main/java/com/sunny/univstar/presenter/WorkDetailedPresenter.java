package com.sunny.univstar.presenter;

import android.util.Log;

import com.sunny.univstar.contract.WorkDetailedContract;
import com.sunny.univstar.model.HomeModel;
import com.sunny.univstar.model.IHomeModel;
import com.sunny.univstar.model.entity.SendWorkDetailedCommentsEntity;
import com.sunny.univstar.model.entity.WorkDetailedCommentsEntity;
import com.sunny.univstar.model.entity.WorkDetailedEntity;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DELL on 2018/5/9.
 */

public class WorkDetailedPresenter implements WorkDetailedContract.WorkDetailedPresenter{
    private IHomeModel iHomeModel;
    private WorkDetailedContract.WorkDetailedView workDetailedView;

    public WorkDetailedPresenter(WorkDetailedContract.WorkDetailedView workDetailedView) {
        this.workDetailedView = workDetailedView;
        iHomeModel = new HomeModel();
    }

    @Override
    public void sendWorkDetailed(String url,Map<String,String> map) {
        iHomeModel.getWorkDetailedService().getWorkDetailed(url,map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<WorkDetailedEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull WorkDetailedEntity workDetailedEntity) {
                        workDetailedView.getWorkDetailedData(workDetailedEntity);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("getWorkDetailed",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void sendWorkDetailedComments(String url,Map<String, String> map) {
        iHomeModel.getWorkDetailedService().getWorkDetailedComments(url,map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<WorkDetailedCommentsEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull WorkDetailedCommentsEntity workDetailedCommentsEntity) {
                        workDetailedView.getWorkDetailedComments(workDetailedCommentsEntity);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void sendWorkDetailedCommentsList(String url,Map<String, String> map) {
        iHomeModel.getWorkDetailedService().getSendWorkDetailedComments(url,map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<SendWorkDetailedCommentsEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SendWorkDetailedCommentsEntity sendWorkDetailedCommentsEntity) {
                        workDetailedView.getSendWorkDetailedComments(sendWorkDetailedCommentsEntity);
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
