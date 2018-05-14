package com.sunny.univstar.model.service.abovemessage;

import com.sunny.univstar.view.message.bean.MessageHomeworkBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 张玗 on 2018/5/13.
 */

public interface MessageHomeworkService {
    @FormUrlEncoded
    @POST("/v1/m/message/homewok")
    Observable<MessageHomeworkBean> getMessageHomework(@Field("loginUserId") int UserId);
}
