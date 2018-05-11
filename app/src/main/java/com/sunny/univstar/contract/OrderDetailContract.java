package com.sunny.univstar.contract;

import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.order.OrderDetailBean;

/**
 * Created by 张玗 on 2018/5/11.
 */

public interface OrderDetailContract {
    interface OrederDetailInView{
        void showOrderDetailData(OrderDetailBean orderDetailBean);
    }
    interface OrderDetailinPresenter{
        void sendOrderDetailData(String oid,String courseType);
    }
}
