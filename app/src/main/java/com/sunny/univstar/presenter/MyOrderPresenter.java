package com.sunny.univstar.presenter;

import com.sunny.univstar.contract.MyOrderContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.order.MyOrderBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/5/10.
 */

public class MyOrderPresenter implements MyOrderContract.MyOrderInPresenter {
    public MyOrderContract.MyOrderInView myOrderInView;

    public MyOrderPresenter(MyOrderContract.MyOrderInView myOrderInView) {
        this.myOrderInView = myOrderInView;
    }

    @Override
    public void sendMyOrderData(int userId, int status) {
        RetrofitUtils.getInstance()
                .getMyOrderService()
                .getMyOrderBean(userId, status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyOrderBean>() {
                    @Override
                    public void accept(MyOrderBean myOrderBean) throws Exception {
                        myOrderInView.showMyOrderData(myOrderBean);
                    }
                });
    }
}
