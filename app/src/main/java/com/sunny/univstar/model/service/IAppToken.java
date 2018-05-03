package com.sunny.univstar.model.service;

import com.sunny.univstar.model.entity.AppTokenModel;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by DELL on 2018/5/3.
 */

public interface IAppToken {
    @POST
    Observable<AppTokenModel> getAppToken(@Url String url);
}
