package com.sunny.univstar.presenter;

import android.util.Log;

import com.sunny.univstar.contract.MasterDetailContract;
import com.sunny.univstar.model.HomeModel;
import com.sunny.univstar.model.IHomeModel;
import com.sunny.univstar.model.entity.MasterDetailEntity;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DELL on 2018/5/7.
 */

public class MasterDeatilPresenter implements MasterDetailContract.MasterDetailPresenter{
    private IHomeModel iHomeModel;
    private MasterDetailContract.MasterDetailView masterDetailView;

    public MasterDeatilPresenter(MasterDetailContract.MasterDetailView masterDetailView) {
        this.masterDetailView = masterDetailView;
        iHomeModel = new HomeModel();
    }

    @Override
    public void sendMasterDetail(Map<String, String> map) {
        iHomeModel.getMasterDetail().getMasterDatalData(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<MasterDetailEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MasterDetailEntity masterDetailEntity) {
                        masterDetailView.getMasterDetailData(masterDetailEntity);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("getMasterDatalData",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
