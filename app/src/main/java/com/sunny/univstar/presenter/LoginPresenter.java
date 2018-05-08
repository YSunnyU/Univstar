package com.sunny.univstar.presenter;

import com.sunny.univstar.contract.LoginContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.personal.activity.login.bean.LoginBean;
import com.sunny.univstar.view.personal.activity.login.bean.LoginSucessBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by 张玗 on 2018/5/7.
 */

public class LoginPresenter implements LoginContract.LoginContractInPresenter {
    public LoginContract.LoginContractInView loginContractInView;

    public LoginPresenter(LoginContract.LoginContractInView loginContractInView) {
        this.loginContractInView = loginContractInView;
    }

    @Override
    public void sendLoginData(String phoneNum, String password) {
        RetrofitUtils.getInstance()
                .getPersonalLoginService()
                .getPhoneLogin(phoneNum, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        loginContractInView.showLoginData(loginBean);
                    }
                });
    }

    @Override
    public void sendUserLoginData(int loginUserId) {
        RetrofitUtils.getInstance()
                .getPersonalLoginService()
                .getSuccessLogin(loginUserId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginSucessBean>() {
                    @Override
                    public void accept(LoginSucessBean loginSucessBean) throws Exception {
                        loginContractInView.showLoginSuccessData(loginSucessBean);
                    }
                });

    }
}
