package com.sunny.univstar.model.service;

import com.sunny.univstar.view.personal.activity.login.bean.LoginBean;
import com.sunny.univstar.view.personal.activity.login.bean.LoginSucessBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 张玗 on 2018/5/7.
 */

public interface PersonalLoginService {
    @FormUrlEncoded
    @POST("/v1/m/user/login/mobile")
    Observable<LoginBean> getPhoneLogin(@Field("mobile") String mobile, @Field("password") String password);


    @FormUrlEncoded
    @POST("/v1/m/user/info")
    Observable<LoginSucessBean> getSuccessLogin(@Field("loginUserId") int loginUserId);
}
