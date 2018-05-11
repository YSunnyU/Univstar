package com.sunny.univstar.model.service;

import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.order.MyOrderBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 张玗 on 2018/5/10.
 */

public interface MyOrderService {
    @FormUrlEncoded
    @POST("/v1/m/user/my/orders")
    Observable<MyOrderBean> getMyOrderBean(@Field("loginUserId") int UserId,@Field("status") int status);
}
