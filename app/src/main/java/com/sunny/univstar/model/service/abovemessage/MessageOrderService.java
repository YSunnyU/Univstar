package com.sunny.univstar.model.service.abovemessage;

import com.sunny.univstar.view.message.bean.MessageOrderBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 张玗 on 2018/5/12.
 */

public interface MessageOrderService {
    @FormUrlEncoded
    @POST("/v1/m/message/order")
    Observable<MessageOrderBean> getAboveMessage(@Field("loginUserId") int UserId);
}
