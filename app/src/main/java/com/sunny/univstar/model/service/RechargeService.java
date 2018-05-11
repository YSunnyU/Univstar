package com.sunny.univstar.model.service;

import com.sunny.univstar.view.personal.activity.login.bean.successview.recharge.RechargeBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 张玗 on 2018/5/9.
 */

public interface RechargeService {
    @FormUrlEncoded
    @POST("v1/m/record/bean/detail")
    Observable<RechargeBean> getRechargeBean(@Field("loginUserId") int userId);
}
