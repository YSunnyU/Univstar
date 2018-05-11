package com.sunny.univstar.presenter;

import com.sunny.univstar.contract.ValuableDetailedContract;
import com.sunny.univstar.model.HomeModel;
import com.sunny.univstar.model.IHomeModel;
import com.sunny.univstar.model.entity.SendValuableDetailedCommentsEntity;
import com.sunny.univstar.model.entity.ValuableDetailedCommentsEntity;
import com.sunny.univstar.model.entity.ValuableDetailedEntity;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DELL on 2018/5/9.
 */

public class ValuableDetailedPresenter implements ValuableDetailedContract.ValuableDetailedPresenter{
    private IHomeModel iHomeModel;
    private ValuableDetailedContract.ValuableDetailedView valuableDetailedView;

    public ValuableDetailedPresenter(ValuableDetailedContract.ValuableDetailedView valuableDetailedView) {
        this.valuableDetailedView = valuableDetailedView;
        iHomeModel = new HomeModel();
    }

    @Override
    public void sendValuableDetailed(String url, Map<String, String> map) {
        iHomeModel.getValuableDetailedService().getValuableDetailed(url,map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<ValuableDetailedEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ValuableDetailedEntity valuableDetailedEntity) {
                        valuableDetailedView.getValuableDetailedData(valuableDetailedEntity);
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
    public void sendValuableDetailedComments(String url, Map<String, String> map) {
        iHomeModel.getValuableDetailedService().getValuableDetailedComments(url,map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<ValuableDetailedCommentsEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ValuableDetailedCommentsEntity valuableDetailedCommentsEntity) {
                        valuableDetailedView.getValuableDetailedComments(valuableDetailedCommentsEntity);
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
    public void sendValuableDetailedCommentsList(String url, Map<String, String> map) {
        iHomeModel.getValuableDetailedService().getSendValuableDetailedComments(url,map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<SendValuableDetailedCommentsEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SendValuableDetailedCommentsEntity sendValuableDetailedCommentsEntity) {
                        valuableDetailedView.getSendValuableDetailedComments(sendValuableDetailedCommentsEntity);
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
