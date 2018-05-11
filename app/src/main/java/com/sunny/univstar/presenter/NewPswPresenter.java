package com.sunny.univstar.presenter;

import com.sunny.univstar.contract.NewPswContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.personal.activity.login.bean.LoginSucessBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/5/8.
 */

public class NewPswPresenter implements NewPswContract.NewPasswordInPresenter {
    public NewPswContract.NewPasswordInView newPasswordInView;

    public NewPswPresenter(NewPswContract.NewPasswordInView newPasswordInView) {
        this.newPasswordInView = newPasswordInView;
    }

    @Override
    public void sendPasswordData(String mobile, String newPassword) {
        RetrofitUtils.getInstance()
                .getNewPswService()
                .getNewPasswordData(mobile, newPassword)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginSucessBean>() {
                    @Override
                    public void accept(LoginSucessBean loginSucessBean) throws Exception {
                        newPasswordInView.getPasswordData(loginSucessBean);
                    }
                });
    }
}
