package com.sunny.univstar.presenter;

import android.util.Log;

import com.sunny.univstar.contract.RegisterContract;
import com.sunny.univstar.model.HomeModel;
import com.sunny.univstar.model.IHomeModel;
import com.sunny.univstar.model.entity.RegisterCodeEntity;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DELL on 2018/5/5.
 */

public class RegisterPresenter implements RegisterContract.RegisterPresenter{

    private IHomeModel iHomeModel;
    private RegisterContract.RegisterView registerView;

    public RegisterPresenter(RegisterContract.RegisterView registerView) {
        this.registerView = registerView;
        iHomeModel = new HomeModel();
    }

    @Override
    public void sendRegister(String url , Map<String, String> map) {
        iHomeModel.getPersonalRegister().getRegisterCode(url,map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterCodeEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterCodeEntity registerCodeEntity) {
                        registerView.getIdentifyingCode(registerCodeEntity);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("getPersonalRegister",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void sendCode(String url, Map<String, String> map) {
        iHomeModel.getPersonalRegister().getRegisterCode(url,map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterCodeEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterCodeEntity registerCodeEntity) {
                        registerView.codePhoneAndIdentifying(registerCodeEntity);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("getPersonalRegister",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
