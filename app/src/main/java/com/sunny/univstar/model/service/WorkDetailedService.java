package com.sunny.univstar.model.service;

import com.sunny.univstar.model.entity.SendWorkDetailedCommentsEntity;
import com.sunny.univstar.model.entity.WorkDetailedCommentsEntity;
import com.sunny.univstar.model.entity.WorkDetailedEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by DELL on 2018/5/9.
 */

public interface WorkDetailedService {
    @FormUrlEncoded
    @POST
    Observable<WorkDetailedEntity> getWorkDetailed(@Url String url ,@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST
    Observable<WorkDetailedCommentsEntity> getWorkDetailedComments(@Url String url ,@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST
    Observable<SendWorkDetailedCommentsEntity> getSendWorkDetailedComments(@Url String url ,@FieldMap Map<String,String> map);
}
