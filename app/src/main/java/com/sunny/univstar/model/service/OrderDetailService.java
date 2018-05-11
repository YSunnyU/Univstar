package com.sunny.univstar.model.service;

import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.order.OrderDetailBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 张玗 on 2018/5/10.
 */

public interface OrderDetailService {
    @FormUrlEncoded
    @POST("/v1/m/user/my/orders/detail")
    Observable<OrderDetailBean> getOrderDetailBean(@Field("oid") String oid,@Field("courseType")String courseType);
//    POST /v1/m/user/my/orders/detail
}
