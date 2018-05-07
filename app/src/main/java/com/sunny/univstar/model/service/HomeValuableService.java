package com.sunny.univstar.model.service;

import com.sunny.univstar.view.valuable.fragment.HomeValuableBean;
import com.sunny.univstar.view.valuable.fragment.ValueFlaybannerBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 张玗 on 2018/5/5.
 */

public interface HomeValuableService {
    @FormUrlEncoded
    @POST("/v1/m/artcircle/home")
    Observable<HomeValuableBean> postPraiseCancle(@FieldMap Map<String, String> map);


    @POST("/v1/m/artcircle/slideshow")
    Observable<ValueFlaybannerBean> getValueFlyBanner();
}
