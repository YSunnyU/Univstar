package com.sunny.univstar.contract;

import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.order.MyOrderBean;

/**
 * Created by 张玗 on 2018/5/10.
 */

public interface MyOrderContract {
    interface MyOrderInView{
        void showMyOrderData(MyOrderBean myOrderBean);
    }
    interface MyOrderInPresenter{
        void sendMyOrderData(int userId,int status);
    }
}
