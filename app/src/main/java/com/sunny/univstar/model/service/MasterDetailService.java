package com.sunny.univstar.model.service;

import com.sunny.univstar.model.entity.MasterDetailEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by DELL on 2018/5/7.
 */

public interface MasterDetailService {
    @FormUrlEncoded
    @POST("/v1/m/user/teacher/homepage")
    Observable<MasterDetailEntity> getMasterDatalData(@FieldMap Map<String,String> map);
}
