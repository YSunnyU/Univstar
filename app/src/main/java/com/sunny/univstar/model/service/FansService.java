package com.sunny.univstar.model.service;


import com.sunny.univstar.view.myself.jobs.FansBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Lenovo on 2018/5/9.
 */

public interface FansService {

    @FormUrlEncoded
    @POST("/v1/m/user/personal/homepage/fans")
    Observable<FansBean> getFansBean(@Field("studentId") int studentId);
}
