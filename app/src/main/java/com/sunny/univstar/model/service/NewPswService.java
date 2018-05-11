package com.sunny.univstar.model.service;

import com.sunny.univstar.view.personal.activity.login.bean.LoginSucessBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 张玗 on 2018/5/8.
 */

public interface NewPswService {
    //    修改密码
    @FormUrlEncoded
    @POST("/v1/m/user/save/password")
    Observable<LoginSucessBean> getNewPasswordData(@Field("mobile") String mobile, @Field("password") String password);
}
