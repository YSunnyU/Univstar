package com.sunny.univstar.presenter;

import com.sunny.univstar.contract.AlertPasswordContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.personal.activity.login.bean.AlertPswBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by 张玗 on 2018/5/8.
 */

public class AlertPasswordPresenter implements AlertPasswordContract.AlertPasswordInPresenter {
    public AlertPasswordContract.AlertPasswordInView alertPasswordInView;
    public AlertPasswordPresenter(AlertPasswordContract.AlertPasswordInView alertPasswordInView){
        this.alertPasswordInView=alertPasswordInView;
    }
   /* @Override
    public void sendPasswordData(int loginUserId, String newPassword) {
        RetrofitUtils.getInstance().getAlertPasswordService()
                .getAlertPasswordData(loginUserId,newPassword)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AlertPswBean>() {
                    @Override
                    public void accept(AlertPswBean alertPswBean) throws Exception {
                       alertPasswordInView.getPasswordData(alertPswBean);
                    }
                });

    }*/
//    得到验证码
    @Override
    public void sendValidateCodeData(String mobile) {
        RetrofitUtils.getInstance()
                .getAlertPasswordService()
                .getValidateCodeData(mobile)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AlertPswBean>() {
                    @Override
                    public void accept(AlertPswBean alertPswBean) throws Exception {
                        alertPasswordInView.getValidateCodeDataData(alertPswBean);
                    }
                });
    }

    @Override
    public void sendIsValidateCodeDataTrueData(String mobile, String authCode) {
        RetrofitUtils.getInstance()
                .getAlertPasswordService()
                .getIsAlertPasswordTureData(mobile,authCode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AlertPswBean>() {
                    @Override
                    public void accept(AlertPswBean alertPswBean) throws Exception {
                        alertPasswordInView.getIsValidateCodeDataTrueData(alertPswBean);
                    }
                });
    }
}
