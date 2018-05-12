package com.sunny.univstar.model.service.abovemessage;

import com.sunny.univstar.view.message.AboveMessageBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 张玗 on 2018/5/12.
 */

public interface AboveMessageService {
    @FormUrlEncoded
    @POST("/v1/m/message/list")
    Observable<AboveMessageBean> getAboveMessage(@Field("loginUserId") int UserId);
}
