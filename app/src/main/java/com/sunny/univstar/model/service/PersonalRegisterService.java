package com.sunny.univstar.model.service;

import com.sunny.univstar.model.entity.RegisterCodeEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by DELL on 2018/5/5.
 */

public interface PersonalRegisterService {
    @FormUrlEncoded
    @POST
    Observable<RegisterCodeEntity> getRegisterCode(@Url String url,@FieldMap Map<String,String> map);
}
