package com.sunny.univstar.presenter;

import com.sunny.univstar.contract.MyRechargeContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.personal.activity.login.bean.successview.recharge.RechargeBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/5/9.
 */

public class MyRechargePresenter implements MyRechargeContract.RechargeDataInPresenter {
    public MyRechargeContract.RechargeDataInView rechargeDataInView;
    public MyRechargePresenter(MyRechargeContract.RechargeDataInView rechargeDataInView){
        this.rechargeDataInView=rechargeDataInView;
    }
    @Override
    public void sendRechargeData(int userId) {
        RetrofitUtils.getInstance()
                .getRechargeService()
                .getRechargeBean(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RechargeBean>() {
                    @Override
                    public void accept(RechargeBean rechargeBean) throws Exception {
                        rechargeDataInView.showRechargeData(rechargeBean);
                    }
                });
    }
}
