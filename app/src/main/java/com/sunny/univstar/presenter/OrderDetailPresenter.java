package com.sunny.univstar.presenter;

import com.sunny.univstar.contract.OrderDetailContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.order.OrderDetailBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/5/11.
 */

public class OrderDetailPresenter implements OrderDetailContract.OrderDetailinPresenter {
    public OrderDetailContract.OrederDetailInView orederDetailInView;
    public OrderDetailPresenter(OrderDetailContract.OrederDetailInView orederDetailInView){
        this.orederDetailInView=orederDetailInView;
    }
    @Override
    public void sendOrderDetailData(String oid, String courseType) {
        RetrofitUtils.getInstance().getOrderDetailService()
                .getOrderDetailBean(oid,courseType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<OrderDetailBean>() {
                    @Override
                    public void accept(OrderDetailBean orderDetailBean) throws Exception {
                        orederDetailInView.showOrderDetailData(orderDetailBean);
                    }
                });
    }
}
