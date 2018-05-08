package com.sunny.univstar.presenter;

import com.sunny.univstar.contract.NoticeDetailedContract;
import com.sunny.univstar.model.HomeModel;
import com.sunny.univstar.model.IHomeModel;
import com.sunny.univstar.model.entity.NoticeDetailedEntity;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DELL on 2018/5/8.
 */

public class NoticeDetailedPresenter implements NoticeDetailedContract.NoticeDetailedPresenter{
    private IHomeModel iHomeModel;
    private NoticeDetailedContract.NoticeDetailedView noticeDetailedView;

    public NoticeDetailedPresenter(NoticeDetailedContract.NoticeDetailedView noticeDetailedView) {
        this.noticeDetailedView = noticeDetailedView;
        iHomeModel = new HomeModel();
    }

    @Override
    public void sendNoticeDetailed(Map<String, String> map) {
        iHomeModel.getNoticeDetailedService().getNoticeDetailed(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NoticeDetailedEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull NoticeDetailedEntity noticeDetailedEntity) {
                        noticeDetailedView.getNoticeDetailedData(noticeDetailedEntity);
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
