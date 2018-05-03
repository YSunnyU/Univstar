package com.sunny.univstar.model.service;

import com.sunny.univstar.model.entity.HomeWokListModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by DELL on 2018/5/3.
 */

public interface IHomeWork {

    @FormUrlEncoded
    @POST("/v1/m/homewok/home")
    Observable<HomeWokListModel> gethomewokData(@Field("loginUserId") Integer loginUserId, @Field("page") int page, @Field("sortord") int sortord);
}
