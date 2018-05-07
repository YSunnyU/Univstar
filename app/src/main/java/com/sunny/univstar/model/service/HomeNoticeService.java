package com.sunny.univstar.model.service;

import com.sunny.univstar.model.entity.HomeNoticeEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by DELL on 2018/5/4.
 */

public interface HomeNoticeService {
    @FormUrlEncoded
    @POST("/v1/m/forthcoming/home")
    Observable<HomeNoticeEntity> getHomeNoticeService(@FieldMap Map<String,String> map);
}
