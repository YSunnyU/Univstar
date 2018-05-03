package com.sunny.univstar.model.service;

import com.sunny.univstar.view.master.fragment.HomeMasterBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 张玗 on 2018/5/3.
 */

public interface HomeMasterService {




    @FormUrlEncoded
    @POST("/v1/m/home/list")
    Observable<HomeMasterBean> getHomeMasterData(@Field("loginUserId") Integer str);
}
