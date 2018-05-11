package com.sunny.univstar.model.service;


import com.sunny.univstar.model.entity.SendValuableDetailedCommentsEntity;
import com.sunny.univstar.model.entity.ValuableDetailedCommentsEntity;
import com.sunny.univstar.model.entity.ValuableDetailedEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by DELL on 2018/5/9.
 */

public interface ValuableDetailedService {
    @FormUrlEncoded
    @POST
    Observable<ValuableDetailedEntity> getValuableDetailed(@Url String url , @FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST
    Observable<ValuableDetailedCommentsEntity> getValuableDetailedComments(@Url String url , @FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST
    Observable<SendValuableDetailedCommentsEntity> getSendValuableDetailedComments(@Url String url , @FieldMap Map<String,String> map);
}
