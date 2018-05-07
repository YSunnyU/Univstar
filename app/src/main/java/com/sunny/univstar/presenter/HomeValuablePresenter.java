package com.sunny.univstar.presenter;

import android.util.Log;

import com.sunny.univstar.contract.HomeValuableContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.valuable.fragment.HomeValuableBean;
import com.sunny.univstar.view.valuable.fragment.ValueFlaybannerBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/5/5.
 */

public class HomeValuablePresenter implements HomeValuableContract.HomeValuableInPresenter {
    public HomeValuableContract.HomeValuableInView homeValuableInView;

    public HomeValuablePresenter(HomeValuableContract.HomeValuableInView homeValuableInView) {
        this.homeValuableInView = homeValuableInView;
    }

    @Override
    public void sendHomeValuableData(String page, String rows, String loginUserId, String sortord) {
        Map<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", rows);
        map.put("loginUserId", loginUserId);
        map.put("sortord", sortord);
        RetrofitUtils.getInstance().getHomeValuableService()
                .postPraiseCancle(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeValuableBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("HomeValuablePresenter", "d.isDisposed():" + d.isDisposed());
                    }

                    @Override
                    public void onNext(@NonNull HomeValuableBean homeValuableBean) {
                        homeValuableInView.showHomeValuableData(homeValuableBean);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("HomeValuablePresenter", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void sendFlyBannerData() {
        RetrofitUtils.getInstance()
                .getHomeValuableService()
                .getValueFlyBanner()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ValueFlaybannerBean>() {
                    @Override
                    public void accept(ValueFlaybannerBean valueFlaybannerBean) throws Exception {
                        homeValuableInView.showValueFlyBannerData(valueFlaybannerBean);
                    }
                });
    }
}
