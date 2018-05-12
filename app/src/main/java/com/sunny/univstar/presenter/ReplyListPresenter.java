package com.sunny.univstar.presenter;

import com.sunny.univstar.contract.ReplyListContract;
import com.sunny.univstar.model.HomeModel;
import com.sunny.univstar.model.IHomeModel;
import com.sunny.univstar.model.entity.ReplyListEntity;
import com.sunny.univstar.model.entity.ReplyListSendEntity;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DELL on 2018/5/11.
 */

public class ReplyListPresenter implements ReplyListContract.ReplyListPresenter{
    private IHomeModel iHomeModel;
    private ReplyListContract.ReplyListView replyListView;

    public ReplyListPresenter(ReplyListContract.ReplyListView replyListView) {
        this.replyListView = replyListView;
        iHomeModel = new HomeModel();
    }

    @Override
    public void sendReplyList(Map<String, String> map) {
        iHomeModel.getReplyListService().getReplyListData(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReplyListEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ReplyListEntity replyListEntity) {
                        replyListView.getReplyListData(replyListEntity);
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
    public void sendReplyListSend(Map<String, String> map) {
        iHomeModel.getReplyListService().getReplyListSendData(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReplyListSendEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ReplyListSendEntity replyListSendEntity) {
                        replyListView.getReplyListSendData(replyListSendEntity);
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
