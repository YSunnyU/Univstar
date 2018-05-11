package com.sunny.univstar.model.service;

import com.sunny.univstar.view.personal.activity.login.bean.AlertPswBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 张玗 on 2018/5/8.
 */

public interface AlertPasswordService {
    //    判断短息验证码是否正确
    @FormUrlEncoded
    @POST("/v1/m/user/verify/authcode")
    Observable<AlertPswBean> getIsAlertPasswordTureData(@Field("mobile") String  mobel, @Field("authCode") String authCode);
    //    获取验证码
    @FormUrlEncoded
    @POST("/v1/m/user/authcode")
    Observable<AlertPswBean> getValidateCodeData(@Field("mobile") String mobile);


}
